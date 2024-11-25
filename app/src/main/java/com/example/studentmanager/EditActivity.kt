package com.example.studentmanager

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<EditText>(R.id.name)
        name.setText(intent.getStringExtra("name"))
        val mssv = findViewById<EditText>(R.id.mssv)
        mssv.setText(intent.getStringExtra("mssv"))
        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            val nameIntent = name.text.toString().trim()
            val mssvIntent = mssv.text.toString().trim()
            val resultIntent = intent
            resultIntent.putExtra("name", nameIntent)
            resultIntent.putExtra("mssv", mssvIntent)
            resultIntent.putExtra("index", intent.getStringExtra("index"))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}