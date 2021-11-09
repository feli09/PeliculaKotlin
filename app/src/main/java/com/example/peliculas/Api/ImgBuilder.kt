package com.example.peliculas.Api

import android.util.Size
import com.example.peliculas.BuildConfig
import java.security.cert.CertPath

object ImgBuilder {
    fun getUrl(path: String?, size: String  = "w500"): String{
        if(path== null || path.isEmpty()) return  ""
        return "${"https://image.tmdb.org/t/p/"}$size$path"
    }
}