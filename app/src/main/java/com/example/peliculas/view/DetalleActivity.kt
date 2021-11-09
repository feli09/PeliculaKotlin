package com.example.peliculas.view

import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.peliculas.Models.Peliculas
import com.example.peliculas.R
import com.example.peliculas.Utils.Nav.PARAM_CONTENT
import com.example.peliculas.ViewModelo.DetalleViewModel
import com.example.peliculas.ViewModelo.FactViewModel
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.toolbar.*

class DetalleActivity : AppCompatActivity() {
    private lateinit var viewModel: DetalleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        loadData()
    }

    private fun loadData(){
        val movieTemp = intent.getSerializableExtra(PARAM_CONTENT) as Peliculas
        setupUI(movieTemp) //Temporal data
        viewModel = ViewModelProviders.of(this, FactViewModel(application, movieTemp)).get(DetalleViewModel::class.java)
        viewModel.movieDetail.observe(this, Observer { setupUI(it) })
        viewModel.movieDetail.observe(this, Observer { setupToolbar(it) })
    }

    private fun setupToolbar(movie: Peliculas){
        setSupportActionBar(toolbar as Toolbar)
        Glide.with(this)
            .load(movie.getPosterUrl())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(android.R.color.black)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        tvToolbarTitle.text = movie.title

    }

    private fun setupUI(movie: Peliculas){
        Glide.with(this)
            .load(movie.getBackdropUrl())
            .placeholder(R.color.colorPrimary)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean { return false }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    gradientBanner.visibility = View.VISIBLE
                    return false
                }
            })
            .into(imageViewBanner)

        Glide.with(this)
            .load(movie.getPosterUrl())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(android.R.color.black)
            .into(imageViewPoster)

        textViewVotes.text = movie.getLikes()
        textViewStars.text = movie.getStars()
        textViewDate.text = movie.getYear()
        textViewDescription.text = movie.overview

        if(movie.tagline?.isNotEmpty() == true){
            textViewTagline.text = movie.tagline
            textViewTagline.visibility = View.VISIBLE
        } else {
            textViewTagline.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}