package com.example.todoapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todoapp.model.Task

class DbHandler(val context:Context) :SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createToDoTable="CREATE $TABLE_TODO (" +
                "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
                "$COL_CREATED_AT datetime DEFAULT CURRENT_TIMESTAMP," +
                "$COL_NAME varchar);"


        db.execSQL(createToDoTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun addToDo(task:Task):Boolean {
        val db:SQLiteDatabase=writableDatabase
        val cv=ContentValues()
        cv.put(COL_NAME,task.task_description)
        val result:Long=db.insert(TABLE_TODO,null,cv)
        return result!= (-1).toLong()

    }

    fun deleteToDo(id:Long) {
        val db = writableDatabase
        db.delete(TABLE_TODO,"$COL_ID=?", arrayOf(id.toString()))
    }

    fun getToDo():MutableList<Task> {
        val result:MutableList<Task> =ArrayList()
        val db:SQLiteDatabase=readableDatabase
        val queryResult=db.rawQuery("SELECT * from $TABLE_TODO",null)
        if (queryResult.moveToFirst()) {
            do {
                val task=Task()
                task.id=queryResult.getLong(queryResult.getColumnIndex(COL_ID))
                task.task_description=queryResult.getString(queryResult.getColumnIndex(COL_NAME))
                result.add(task)

            } while (queryResult.moveToNext())
        }

        queryResult.close()
        return result
    }

}
