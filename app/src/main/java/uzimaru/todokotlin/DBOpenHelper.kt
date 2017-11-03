package uzimaru.todokotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
    companion object {
        private const val dbName = "todo.db"
        private const val dbVersion = 1
        const val tableName = "todo"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("""
                    CREATE TABLE $tableName(
                        _id INTEGER PRIMARY KEY NOT NULL,
                        title TEXT NOT NULL,
                        deadLine INTEGER NOT NULL,
                        complete INTEGER NOT NULL
                    );
                    """)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("""DROP TABLE IF EXISTS $tableName;""")
        onCreate(p0)
    }
}