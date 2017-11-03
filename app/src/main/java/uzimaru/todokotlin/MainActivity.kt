package uzimaru.todokotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val todo: ToDoAdapter by lazy { ToDoAdapter(this) }
    private val listView: ListView by lazy { findViewById(R.id.listView) as ListView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById(R.id.mainToolbar) as Toolbar
        setToolBar(toolBar, "ToDoList")

        listInit()

        val fab: FloatingActionButton = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener{
            val intent = Intent(application, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        listInit()
    }

    private fun setToolBar(toolbar: Toolbar, title: String) {
        toolbar.title = title
        toolbar.setTitleTextColor(Color.WHITE)
    }

    private fun listInit() {
        val list = todo.getList()
        val adapter = ToDoListAdapter(this, 0, list)
        listView.adapter = adapter
    }

}
