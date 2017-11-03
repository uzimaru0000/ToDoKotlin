package uzimaru.todokotlin

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.DialogFragment
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.support.v7.widget.Toolbar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class AddActivity : FragmentActivity(), DatePickerDialog.OnDateSetListener {

    private val todo: ToDoAdapter by lazy { ToDoAdapter(this) }
    private val deadLineText: EditText by lazy { findViewById<EditText>(R.id.deadLineInput) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val today = Date()
        deadLineText.setText(SimpleDateFormat("yyyy-MM-dd").format(today))
        deadLineText.setOnClickListener { showDialog() }

        val addBtn = findViewById<Button>(R.id.addButton)
        addBtn.setOnClickListener {
            val title = findViewById<EditText>(R.id.titleInput)
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            var deadLine = Date()
            try {
                deadLine = formatter.parse(deadLineText.text.toString())
            } catch (e: ParseException) {
                Log.e("AddActivity", "Parse Error")
            }
            todo.insert(title.text.toString(), deadLine)
            finish()
        }

        val toolbar = findViewById<Toolbar>(R.id.addToolbar)
        toolbar.title = "Add ToDo"
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { finish() }

    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        deadLineText.setText("$year-${month+1}-$day")
    }

    private fun showDialog() {
        val newFragment: DialogFragment = uzimaru.todokotlin.DatePicker()
        newFragment.show(supportFragmentManager, "datePicker")
    }
}
