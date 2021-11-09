package com.example.peliculas.ViewModelo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.peliculas.Models.Peliculas

class FactViewModel  (private val application: Application, val movie: Peliculas) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetalleViewModel(application, movie) as T
    }
}