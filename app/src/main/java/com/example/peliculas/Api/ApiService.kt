package com.example.peliculas.Api

import com.example.peliculas.Models.OutrO.PeliculasOutRo
import com.example.peliculas.Models.Peliculas
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService{
    companion object {
        val api by lazy { create() }

        private fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()
                        val originalHttpUrl = original.url()

                        val url = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", "66cd8899a4842e87ce6dee06951b13b6")
                            .build()

                        val requestBuilder = original.newBuilder()
                            .url(url)
                        val request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                })
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }


    @GET("movie/popular")
    fun getPeliculasPopulares(@Query("page") page: Int): Call<PeliculasOutRo>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: Int): Call<Peliculas>

}