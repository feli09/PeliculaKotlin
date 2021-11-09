package com.example.peliculas.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.peliculas.Models.Peliculas

@Database(
    entities = [Peliculas::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class PeliculasBD : RoomDatabase() {
    abstract fun moviesDao(): PeliculasDao
    companion object {
        @Volatile
        private var INSTANCE: PeliculasBD? = null

        fun getDB(context: Context): PeliculasBD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    PeliculasBD::class.java,
                    "bdp"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}