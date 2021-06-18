package com.example.roomdbapp

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Libro::class), version = 1, exportSchema = false)
public abstract class LibroRoomDatabase : RoomDatabase() {

    abstract fun libroDao(): LibroDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LibroRoomDatabase? = null

        fun getDatabase(context: Context, scope:CoroutineScope): LibroRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                        LibroRoomDatabase::class.java,
                    "libro_database"
                ).addCallback(LibroDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
    private class LibroDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.libroDao())
                }
            }
        }

        suspend fun populateDatabase(libroDao: LibroDao) {
            // Delete all content here.
            libroDao.deleteAll()

            // Add sample words.
            var libro = Libro("Valvuena","David Villa","123")
            libroDao.insert(libro)
            libro = Libro("World!","Juan Pachecho","456")
            libroDao.insert(libro)

            // TODO: Add your own words!
        }
    }
}