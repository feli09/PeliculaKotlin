package com.example.peliculas.Models.OutrO

import com.example.peliculas.Models.PeliculaTipo
import com.example.peliculas.Models.Peliculas
import com.google.gson.annotations.SerializedName

data class PeliculasOutRo (

    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: ArrayList<Peliculas>
){
    fun setPagingData(type: PeliculaTipo){
        results.forEach {
            it.page = page
            it.type = type
        }
    }
}