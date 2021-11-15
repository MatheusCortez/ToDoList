    package com.example.todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.datasouce.TaskDataSource
import com.example.todolist.ui.AddTaskActivity
import com.example.todolist.ui.TaskListAdapter

    lateinit var binding: ActivityMainBinding

    private val adapter by lazy { TaskListAdapter() }

    class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tasksList.adapter= adapter

        insertListeners()
    }
        private fun insertListeners(){
            binding.btnFlutuante.setOnClickListener {
                startActivityForResult(Intent(this,AddTaskActivity::class.java),CREATE_NEW_TASK)
            }
            adapter.listenerEdit={

            }
            adapter.listenerDelete={
                Log.e("TAG", "listenerDelete: ", )
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode== CREATE_NEW_TASK && resultCode==Activity.RESULT_OK){
                binding.tasksList.adapter= adapter
                adapter.submitList(TaskDataSource.getList())
            }
        }

        private fun updateList(){
            adapter.submitList(TaskDataSource.getList())
        }
        companion object {
            private const val CREATE_NEW_TASK=1000
        }

}