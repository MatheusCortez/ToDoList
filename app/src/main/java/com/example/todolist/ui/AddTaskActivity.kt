package com.example.todolist.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.datasouce.TaskDataSource
import com.example.todolist.extensions.format
import com.example.todolist.extensions.text
import com.example.todolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import java.util.*

class AddTaskActivity :AppCompatActivity() {
    private lateinit var binding:ActivityAddTaskBinding
   override fun onCreate(savedInstanceState:Bundle?){
       super.onCreate(savedInstanceState)
     binding = ActivityAddTaskBinding.inflate(layoutInflater)
       setContentView(binding.root)

       insertListeners()
   }

    private fun insertListeners(){
        binding.inputLayoutData.editText?.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            val timeZone = TimeZone.getDefault()
            val offset = timeZone.getOffset(Date().time)*-1
            datePicker.addOnPositiveButtonClickListener {

                binding.inputLayoutData.text=  Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager,"DATE_PICKER")
        }
        binding.inputLayoutHora.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder().build()

            timePicker.addOnPositiveButtonClickListener {
             val minuto=   if(timePicker.minute in 0..9) {
                    "0${timePicker.minute}"
                }else{
                    timePicker.minute
                }
              val hora=  if(timePicker.hour in 0..9) {
                    "0${timePicker.hour}"
                }else{
                    timePicker.hour
                }
                binding.inputLayoutHora.text= "${hora}:${minuto}"
            }
            timePicker.show(supportFragmentManager,null)
        }
        binding.btnCancelar.setOnClickListener {
            finish()
        }
        binding.btnNovaTask.setOnClickListener {
        val task = Task(
            titulo=binding.inputLayoutTitulo.text,
            data=binding.inputLayoutData.text,
            hora=binding.inputLayoutHora.text
        )
            TaskDataSource.insertTask(task)
            setResult(Activity.RESULT_OK)
           finish()
        }
    }
}