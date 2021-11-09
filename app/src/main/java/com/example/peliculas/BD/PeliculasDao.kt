package com.example.peliculas.BD

import androidx.room.*
import com.example.peliculas.Models.PeliculaTipo
import com.example.peliculas.Models.Peliculas
import androidx.paging.DataSource

@Dao
interface PeliculasDao {

    @Query("SELECT * FROM peliculas WHERE type =:movieType ORDER BY page ASC")
    fun loadMovies(movieType: PeliculaTipo): DataSource.Factory<Int, Peliculas>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Peliculas>)

    @Insert
    fun insertMovie(movie: Peliculas)

    @Delete
    fun deleteMovie(movie: Peliculas)

    @Update
    fun updateMovie(movie: Peliculas)
}