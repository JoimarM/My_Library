package com.example.roomdbapp

import android.view.View

interface BookListener {

    fun onDelete(vista: View, position:Int,writer:String)

}