package com.example.roomdbapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class LibroDao{
    @Query("SELECT * FROM libro_table ORDER BY nombre ASC")
    abstract fun getAlphabetizedWords(): Flow<List<Libro>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(libro: Libro)

    @Query("DELETE FROM libro_table")
    abstract suspend fun deleteAll()

    @Query("DELETE FROM libro_table WHERE nombre = :writer")
    abstract suspend fun deleteOne(writer: String)


}