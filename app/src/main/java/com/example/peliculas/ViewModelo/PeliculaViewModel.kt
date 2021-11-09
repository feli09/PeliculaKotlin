package com.example.peliculas.ViewModelo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.peliculas.Api.PeliculasRepo
import com.example.peliculas.Models.PeliculaTipo
import com.example.peliculas.Models.Peliculas

class PeliculaViewModel (application: Application): AndroidViewModel(application) {
    private val repository: PeliculasRepo = PeliculasRepo(application)
    private val moviesPopular: LiveData<PagedList<Peliculas>>

    init {
        moviesPopular = repository.peliculasPopulares
    }

    fun getMovieList(movieType: PeliculaTipo): LiveData<PagedList<Peliculas>> {
        return when(movieType) {
            PeliculaTipo.POPULAR -> moviesPopular
        }
    }
}

