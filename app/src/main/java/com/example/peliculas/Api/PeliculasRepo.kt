package com.example.peliculas.Api

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import retrofit2.Callback
import retrofit2.Call
import com.example.peliculas.BD.PeliculasBD
import com.example.peliculas.Models.OutrO.PeliculasOutRo
import com.example.peliculas.Models.PeliculaTipo
import com.example.peliculas.Models.Peliculas
import retrofit2.Response
import java.util.concurrent.Executors

class PeliculasRepo(private val application: Application) {

    private val api by lazy { ApiService.api }
    private val db by lazy { PeliculasBD.getDB(application) }
    private val peliculaDao by lazy { db.moviesDao() }

    val peliculasPopulares: LiveData<PagedList<Peliculas>> by lazy {
        peliculaLiveBuilder(this, PeliculaTipo.POPULAR)
    }

    val searchResults: MutableLiveData<ArrayList<Peliculas>> by lazy {
        MutableLiveData<ArrayList<Peliculas>>()
    }

    fun requestMovies(page: Int, movieType: PeliculaTipo) {
        val request = when(movieType) {PeliculaTipo.POPULAR -> api.getPeliculasPopulares(page)}

        request.enqueue(object : Callback<PeliculasOutRo> {
            override fun onFailure(call: Call<PeliculasOutRo>, t: Throwable) {
                //TODO handle errors
            }

            override fun onResponse(call: Call<PeliculasOutRo>, response: Response<PeliculasOutRo>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        Executors.newSingleThreadExecutor().execute {
                            it.setPagingData(movieType)
                            peliculaDao.insertMovies(it.results)
                        }
                    }
                }
            }
        })
    }

    fun peliculaDetalle(id: Int): LiveData<Peliculas> {
        val mutableData = MutableLiveData<Peliculas>()

        api.getMovieDetail(id).enqueue(object : Callback<Peliculas> {
            override fun onFailure(call: Call<Peliculas>, t: Throwable) {
                //TODO handle errors
            }

            override fun onResponse(call: Call<Peliculas>, response: Response<Peliculas>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        mutableData.value = it
                    }
                }
            }
        })

        return mutableData
    }

    companion object {
        private val pagingConfig = Config(
            pageSize = 20,
            enablePlaceholders = true
        )

        fun peliculaLiveBuilder(repository: PeliculasRepo, movieType: PeliculaTipo): LiveData<PagedList<Peliculas>> {
            return LivePagedListBuilder(
                    repository.peliculaDao.loadMovies(movieType),
                    pagingConfig)
                    .setBoundaryCallback(PeliculasCallback(repository, movieType))
                    .build()
        }
    }
}