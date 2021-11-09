package com.example.peliculas.Models

enum class PeliculaTipo(val type: Int){
    POPULAR(1);

    companion object{
        fun get(value: Int) = values().find { it.type == value }
    }
}