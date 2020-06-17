package com.example.todoapp.adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.DbHandler
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.model.Task
import kotlinx.android.synthetic.main.item_row_layout.view.*

class ItemRecyclerAdapter(val activity: MainActivity, val list : MutableList<Task>): RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view= LayoutInflater.from(activity).inflate(R.layout.item_row_layout,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.taskName.text = list[position].task_description
        holder.delete.setOnClickListener() {
            activity.dBhandler.deleteToDo(list[position].id)
            activity.refreshList()
        }
    }


    fun addTask(task: Task) {
        list.add(task)
    }


    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val taskName = itemView?.findViewById(R.id.tv_task) as TextView
        val delete =itemView?.findViewById(R.id.btn_delete) as Button


    }


}
