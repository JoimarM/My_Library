package com.example.roomdbapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "libro_table")
class Libro(@PrimaryKey @ColumnInfo(name = "nombre") val nombre: String,
           @ColumnInfo val escritor: String,
           @ColumnInfo val id: String
):Serializable {
}