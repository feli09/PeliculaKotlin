package com.example.peliculas.BD

import androidx.room.TypeConverter
import com.example.peliculas.Models.PeliculaTipo

class Converter {

    @TypeConverter
    fun toMovieType(value: Int) = PeliculaTipo.get(value)

    @TypeConverter
    fun toInt(movieType: PeliculaTipo) = movieType.type
}
