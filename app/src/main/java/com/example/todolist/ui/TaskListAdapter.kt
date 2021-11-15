package com.example.todolist.ui
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback()) {


    var listenerEdit:(Task)->Unit={}
    var listenerDelete:(Task)->Unit={}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
       val binding= ItemTaskBinding.inflate(inflater,parent,false)
        return TaskViewHolder(binding)
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

  inner  class  TaskViewHolder(private val binding:
                          ItemTaskBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item: Task) {
            binding.toolbarTitle.text = item.titulo

            binding.toolbarDate.text="${item.data}  ${item.hora}"

            binding.itemMenu.setOnClickListener {
                showPopup(item)
            }
        }
        private fun showPopup(item:Task){
            val menu = binding.itemMenu
            val popupMenu = PopupMenu(menu.context, menu)
            popupMenu.menuInflater.inflate(R.menu.popupmenu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_Edit ->listenerEdit(item)
                        R.id.action_Delete->listenerDelete(item)

                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()

        }


    }
}

class DiffCallback:DiffUtil.ItemCallback<Task>(){

    override fun areContentsTheSame(oldItem: Task, newItem: Task)=oldItem.id==newItem.id

    override fun areItemsTheSame(oldItem: Task, newItem: Task)=oldItem==newItem
    }
