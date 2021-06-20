package com.example.roomdbapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class BookListAdapter(var listener: BookListener) :
    ListAdapter<Libro, BookListAdapter.LibroViewHolder>(LIBROS_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        return LibroViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, listener)
        val libroViewHolder: LibroViewHolder? = null
    }


    class LibroViewHolder(itemView: View, listener: BookListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var libroName: String = ""
        var libroEdit: Libro = Libro("","","")

        private val libroItemViewName: TextView = itemView.findViewById(R.id.textName)
        private val libroItemViewWriter: TextView = itemView.findViewById(R.id.textWriter)
        private val libroItemViewId: TextView = itemView.findViewById(R.id.textId)
        private val button = itemView.findViewById<Button>(R.id.btnDelete)
        private val buttonEdit = itemView.findViewById<Button>(R.id.btnEdit)
        var listener: BookListener? = null

        fun bind(libro: Libro, listener: BookListener) {
            libroItemViewName.text = libro.nombre
            libroItemViewWriter.text = libro.escritor
            libroItemViewId.text = libro.id
            libroName = libroItemViewName.text.toString()
            libroEdit = libro
            button.setOnClickListener {
                listener.onDelete(itemView,adapterPosition,libro.nombre)
            }
            buttonEdit.setOnClickListener {
                listener.onEdit(itemView,adapterPosition,libro)
            }
        }

        init {
            this.listener = listener
        }


        companion object {
            fun create(parent: ViewGroup, listener: BookListener): BookListAdapter.LibroViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return LibroViewHolder(view, listener)
            }
        }

        override fun onClick(v: View?) {
            this.listener?.onDelete(v!!,adapterPosition,libroName)
            this.listener?.onEdit(v!!,adapterPosition, libroEdit!!)

        }
    }

    interface ItemClicked{
        fun updateClick(libro:Libro)
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

