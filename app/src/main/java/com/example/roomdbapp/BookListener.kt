package com.example.roomdbapp

import android.view.View

interface BookListener {

    fun onDelete(vista: View, position:Int,name:String)

    fun onEdit(vista:View, position: Int, libro: Libro)

}