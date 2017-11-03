package uzimaru.todokotlin

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import java.text.SimpleDateFormat

class ToDoListAdapter(context: Context, textViewResourceId: Int, list: List<TodoData>)
    : ArrayAdapter<TodoData>(context, textViewResourceId, list) {

    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val todo: ToDoAdapter = ToDoAdapter(context)

    override fun getView(position: Int, convertView: View?, parentGroup: ViewGroup?): View {
        val todoData = getItem(position)
        val view = convertView ?: layoutInflater.inflate(R.layout.row, null)

        if (view != null) {
            val checkbox = view.findViewById<CheckBox>(R.id.title)
            checkbox.text = todoData.title
            val dateText = view.findViewById<TextView>(R.id.deadLineView)
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            dateText.text = formatter.format(todoData.deadLine)

            if (checkbox != null) {
                (checkbox.parent as ViewGroup).setOnClickListener { v ->
                    if (!checkbox.isChecked) {
                        v.setBackgroundColor(Color.LTGRAY)
                    } else {
                        v.setBackgroundColor(Color.WHITE)
                    }
                    checkbox.isChecked = !checkbox.isChecked
                }
            }
        }

        return view!!
    }

}