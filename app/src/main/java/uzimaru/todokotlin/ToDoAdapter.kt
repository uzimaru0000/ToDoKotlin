package uzimaru.todokotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.Date

class ToDoAdapter(context: Context) {

    private val db: SQLiteDatabase

    init {
        val helper = DBOpenHelper(context)
        this.db = helper.writableDatabase
    }

    fun getList(): List<TodoData> {
        val list = mutableListOf<TodoData>()
        val c = db.query(DBOpenHelper.tableName, null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                val id = c.getInt(c.getColumnIndex("_id"))
                val title = c.getString(c.getColumnIndex("title"))
                val deadLine = Date(c.getLong(c.getColumnIndex("deadLine")))
                val complete = c.getInt(c.getColumnIndex("complete")) == 1
                val item = TodoData(id, title, deadLine, complete)
                list.add(item)
            } while (c.moveToNext())
        }

        return list
    }

    fun insert(title: String, deadLine: Date) {
        val values = ContentValues()
        values.put("title", title)
        values.put("deadLine", deadLine.time)
        values.put("complete", 0)
        db.insertOrThrow(DBOpenHelper.tableName, null, values)
    }

    fun remove(id: Int) {
        db.delete(DBOpenHelper.tableName, "_id=?", arrayOf(id.toString()))
    }
}