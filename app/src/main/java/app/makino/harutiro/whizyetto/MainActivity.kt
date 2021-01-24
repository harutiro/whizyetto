package app.makino.harutiro.whizyetto

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("タイトル")
                    .setMessage("メッセージ")
                    .setPositiveButton("OK", { dialog, which ->
                        // TODO:Yesが押された時の挙動
                    })
                    .show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("タイトル")
                    .setMessage("メッセージ")
                    .setPositiveButton("OK", { dialog, which ->
                        // TODO:Yesが押された時の挙動
                    })
                    .setNegativeButton("No", { dialog, which ->
                        // TODO:Noが押された時の挙動
                    })
                    .show()
        }
    }



}