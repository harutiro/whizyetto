package app.makino.harutiro.whizyetto

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewAppWidgetConfigureActivity]
 */
//com.example.android.stackwidgetの部分は自分のアプリのIDに修正する
private const val COUNT_UP = "com.example.android.app.makino.harutiro.whizyetto.COUNT_UP"

class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (context == null || intent == null) return

        when (intent.action) {
            COUNT_UP -> {
                //　カウント値を読み込み
                val dataStore = context.getSharedPreferences("widget", Context.MODE_PRIVATE)
                var clickCount = dataStore.getInt("clickCount", -1)

                clickCount++

                //　カウントアップ後のカウント値を書き込み
                dataStore.edit().putInt("clickCount", clickCount).commit()

                //TextViewにカウント値を適用
                val views = RemoteViews(context.packageName, R.layout.new_app_widget)
                views.setTextViewText(R.id.number, clickCount.toString())

                // ウィジェットを更新
                val myWidget = ComponentName(context, NewAppWidget::class.java)
                val manager = AppWidgetManager.getInstance(context)
                manager.updateAppWidget(myWidget, views)
            }
        }
    }
}

//onUpdateのappWidgetId毎の処理はこちらで実装する
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    // SharedPreferencesからデータを読み出し（登録されていない場合は0）
    val dataStore = context.getSharedPreferences("widget", Context.MODE_PRIVATE)
    var clickCount = dataStore.getInt("clickCount", -1)
    if (clickCount == -1) {
        dataStore.edit().putInt("clickCount", 0).apply()
        clickCount = 0
    }

    // RemoteViews オブジェクトを作成
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)

    //TextViewにカウント値を適用
    views.setTextViewText(R.id.number, clickCount.toString())

    //Button押下通知用のPendingIntentを作成しに登録
    val countIntent = Intent(context, NewAppWidget::class.java).apply { action = COUNT_UP }
    val countPendingIntent =
        PendingIntent.getBroadcast(context, 0, countIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    views.setOnClickPendingIntent(R.id.add_button, countPendingIntent)

    // ウィジェットを更新
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
