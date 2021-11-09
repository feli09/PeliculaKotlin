package com.example.peliculas.Utils

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import com.example.peliculas.Models.Peliculas
import com.example.peliculas.view.DetalleActivity

object Nav {
    fun handle(context: Activity?, movie: Peliculas, activityOptions: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(context)){
        val intent = Intent(context, DetalleActivity::class.java)
        intent.putExtra(PARAM_CONTENT, movie)
        context?.startActivity(intent, activityOptions.toBundle())
    }

    const val PARAM_CONTENT = "param_content"
}