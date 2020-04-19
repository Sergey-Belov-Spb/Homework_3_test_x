package com.example.aboutfilms

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CustomDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_exit)
        val button = findViewById<Button>(R.id.btnDialogExit)
        button.setOnClickListener {
            //Do something
            dismiss()

        }
    }

}
