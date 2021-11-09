package com.example.peliculas.view

import android.app.ActivityOptions
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peliculas.Adapters.AdapterInicio
import com.example.peliculas.Models.PeliculaTipo
import com.example.peliculas.R
import com.example.peliculas.Utils.Nav
import com.example.peliculas.ViewModelo.PeliculaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import java.net.URI.create

class MainActivity : AppCompatActivity() {
    lateinit var adapter: AdapterInicio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        adapter = AdapterInicio(this) { peli, view ->
            val options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(view, "poster"),
                    Pair.create(toolbar as? View, "toolbar")
            )
            Nav.handle(this, peli, options)
        }

        val viewModel = ViewModelProviders.of(this).get(PeliculaViewModel::class.java)
        viewModel.getMovieList(PeliculaTipo.POPULAR).observe(this, Observer { adapter.submitList(it) })
        recyclerView.adapter = adapter
    }

}