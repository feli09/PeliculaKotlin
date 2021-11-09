package com.example.peliculas.ViewModelo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.peliculas.Api.PeliculasRepo
import com.example.peliculas.Models.Peliculas

class DetalleViewModel (application: Application, movie: Peliculas) : AndroidViewModel(application){
    private val repository: PeliculasRepo = PeliculasRepo(application)
    var movieDetail: LiveData<Peliculas>

    init {
        movieDetail = repository.peliculaDetalle(movie.id)

    }
}