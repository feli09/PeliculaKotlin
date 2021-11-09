package com.example.peliculas.Api

import androidx.paging.PagedList
import com.example.peliculas.Models.PeliculaTipo
import com.example.peliculas.Models.Peliculas

class PeliculasCallback (private val repository: PeliculasRepo, private val movieType: PeliculaTipo) : PagedList.BoundaryCallback<Peliculas>() {

    override fun onZeroItemsLoaded() {
        repository.requestMovies(1, movieType)
    }
    override fun onItemAtEndLoaded(itemAtEnd: Peliculas) {
        repository.requestMovies(itemAtEnd.page + 1, movieType)
    }
}
