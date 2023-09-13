package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val btnDel = findViewById<Button>(R.id.btn_delete_task)
        val edTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val edDesc = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val edDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)


        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)
        detailTaskViewModel.setTaskId(intent.getIntExtra(TASK_ID,0))
        detailTaskViewModel.task.observe(this, { task ->
            if (task != null){
                edTitle.setText(task.title)
                edDesc.setText(task.description)
                edDueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
            }
        })

        btnDel.setOnClickListener { deleteTask() }
    }

    private fun deleteTask() {
        detailTaskViewModel.deleteTask()
        finish()
    }


}