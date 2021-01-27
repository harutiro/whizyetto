package app.makino.harutiro.whizyetto

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("タイトル")
                    .setMessage("メッセージ")
                    .setPositiveButton("OK") { dialog, which ->
                        // Yesが押された時の挙動
                    }
                    .show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("タイトル")
                    .setMessage("メッセージ")
                    .setPositiveButton("OK") { dialog, which ->
                        // Yesが押された時の挙動
                    }
                    .setNegativeButton("No") { dialog, which ->
                        // Noが押された時の挙動
                    }
                    .show()
        }

        findViewById<View>(R.id.floatingActionButton).setOnClickListener {
            AlertDialog.Builder(this) // FragmentではActivityを取得して生成
                    .setTitle("タイトル")
                    .setMessage("メッセージ")
                    .setPositiveButton("OK") { dialog, which ->
                        // Yesが押された時の挙動
                    }
                    .show()
        }

        findViewById<Button>(R.id.button3).setOnClickListener {

            TimeDialog{ date ->
                findViewById<TextView>(R.id.textView).setText(date)
            }.show(supportFragmentManager,"date_dialog")

        }

        findViewById<Button>(R.id.button4).setOnClickListener {

            DateDialog{ date ->
                findViewById<TextView>(R.id.textView).setText(date)
            }.show(supportFragmentManager,"date_dialog")


        }
    }



    class DateDialog(private val onSelected: (String) -> Unit) :
            DialogFragment(), DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val date = c.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(requireActivity(), this, year, month, date)
        }

        override fun onDateSet(view: DatePicker?, year: Int,
                               month: Int, dayOfMonth: Int) {
            onSelected("$year/${month + 1}/$dayOfMonth")
        }
    }

    class TimeDialog(private val onSelected: (String) -> Unit) :
            DialogFragment(), TimePickerDialog.OnTimeSetListener {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            return TimePickerDialog(context, this, hour, minute, true)
        }

        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
            onSelected("%1$02d:%2$02d".format(hourOfDay, minute))
        }

    }








}