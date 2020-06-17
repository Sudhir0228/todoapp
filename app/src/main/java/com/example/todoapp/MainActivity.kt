package com.example.todoapp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.adapter.ItemRecyclerAdapter
import com.example.todoapp.model.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row_layout.*

class MainActivity : AppCompatActivity() {
    lateinit var dBhandler: DbHandler
    lateinit var list : MutableList<Task>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tb_toolbar)
        setTitle("Tasks")
        dBhandler = DbHandler(this)
        //list = MutableList<Task>
        recyclerView_tasks.layoutManager=LinearLayoutManager(this)


        img_plus.setOnClickListener() {
            val dialog = AlertDialog.Builder(this)
            val view=layoutInflater.inflate(R.layout.dialog_add_task,null)
            val taskName=view.findViewById<EditText>(R.id.et_task)
            dialog.setView(view)
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                if (taskName.text.isNotEmpty()) {
                val task = Task()
                task.task_description = taskName.text.toString()
                dBhandler.addToDo(task)
                    refreshList()
            }
            }
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

            }
            dialog.show()
        }


    }

    override fun onResume() {
        refreshList()
        super.onResume()
    }

     fun refreshList() {
        recyclerView_tasks.adapter = ItemRecyclerAdapter(this,dBhandler.getToDo())
    }






}