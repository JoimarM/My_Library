package com.example.roomdbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels

class EditBookActivity : AppCompatActivity() {
    private lateinit var editNameView: EditText
    private lateinit var editWriterView: EditText
    private lateinit var editIdView: EditText
    private lateinit var btn_update: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_book)

        editWriterView = findViewById(R.id.edit_writer)
        editNameView = findViewById(R.id.edit_name)
        editIdView = findViewById(R.id.edit_id)
        btn_update = findViewById(R.id.button_edit)

        var libro = intent.getSerializableExtra("EXTRA_LIBRO") as Libro
        editIdView.setText(libro.id)
        editNameView.setText(libro.nombre)
        editWriterView.setText(libro.escritor)

        btn_update.setOnClickListener {
           /* val answer = Intent()
            val librito =  Libro(editIdView.toString(), editWriterView.toString(), editIdView.toString())
            answer.putExtra(
               " LIBRO_UPDATE",
                Libro(editIdView.toString(), editWriterView.toString(), editIdView.toString())
            )
*/
        }
    }



}