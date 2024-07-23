package com.example.retrofitcrashcourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcrashcourse.databinding.ItemTodoBinding

class RecyclerViewAdapter(private val todos: List<Todo>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
//        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
//        val cbDone = itemView.findViewById<CheckBox>(R.id.cbDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        val view = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
//        holder.tvTitle.text = todos[position].title
//        holder.cbDone.isChecked = todos[position].completed
        val currentTodo = todos[position]
        holder.binding.apply {
            tvTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}