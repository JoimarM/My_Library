package com.example.roomdbapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private val newBookActivityRequestCode = 1
    private val libroViewModel: LibroViewModel by viewModels {
        LibroViewModelFactory((application as LibroApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BookListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        libroViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewBookActivity::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
        }

        libroViewModel.delete(adapter.libroId)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var name: String = ""
        var writer: String = ""
        var id: String = ""
        if (requestCode == newBookActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val libro = data?.getSerializableExtra(NewBookActivity.EXTRA_LIBRO) as Libro
            name = libro.nombre
            writer = libro.escritor
            id = libro.id
        }
        val libro = Libro(name, writer, id)
        libroViewModel.insert(libro)
    }


}

