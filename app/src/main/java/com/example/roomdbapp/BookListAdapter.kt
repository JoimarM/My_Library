package com.example.roomdbapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class BookListAdapter (private val listener: LibroListener) : ListAdapter<Libro, BookListAdapter.LibroViewHolder>(LIBROS_COMPARATOR) {

 //  lateinit var listener: LibroListener
    var libroId:String =  ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        return LibroViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current) { item ->
            libroId = item
            listener(item)
            Log.i("libro",item)
        }
    }


    class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val libroItemViewName: TextView = itemView.findViewById(R.id.textName)
        private val libroItemViewWriter: TextView = itemView.findViewById(R.id.textWriter)
        private val libroItemViewId: TextView = itemView.findViewById(R.id.textId)
        private val button = itemView.findViewById<Button>(R.id.btnDelete)

        fun bind(libro: Libro, listener: LibroListener ) {
            libroItemViewName.text = libro.nombre
            libroItemViewWriter.text = libro.escritor
            libroItemViewId.text = libro.id
            button.setOnClickListener {
                //getLibro(libro)
                listener(libro.id)

            }
        }

        fun getLibro(libro: Libro): Libro{
            val libroSending = libro
            return libroSending
        }


        companion object {
            fun create(parent: ViewGroup): LibroViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return LibroViewHolder(view)
            }
        }


    }


    companion object {
        private val LIBROS_COMPARATOR = object : DiffUtil.ItemCallback<Libro>() {
            override fun areItemsTheSame(oldItem: Libro, newItem: Libro): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Libro, newItem: Libro): Boolean {
                return oldItem.nombre == newItem.nombre
            }
        }
    }
}

typealias LibroListener = (String) -> Unit