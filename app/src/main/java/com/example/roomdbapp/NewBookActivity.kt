package com.example.roomdbapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewBookActivity : AppCompatActivity() {

    private lateinit var editNameView: EditText
    private lateinit var editWriterView: EditText
    private lateinit var editIdView: EditText


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_libro)
        editNameView = findViewById(R.id.edit_name)
        editWriterView = findViewById(R.id.edit_writer)
        editIdView = findViewById(R.id.edit_id)

        val button = findViewById<Button>(R.id.button_edit)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text)
                || TextUtils.isEmpty(editWriterView.text)
                || TextUtils.isEmpty(editIdView.text)
            ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editNameView.text.toString()
                val writer = editWriterView.text.toString()
                val id = editIdView.text.toString()
                replyIntent.putExtra(EXTRA_LIBRO, Libro(name, writer, id))
                /* replyIntent.putExtra(EXTRA_NAME, name)
                 replyIntent.putExtra(EXTRA_ID, id)
                 replyIntent.putExtra(EXTRA_WRITER, writer)*/

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_LIBRO = "com.example.android.wordlistsql.REPLY"
    }
}