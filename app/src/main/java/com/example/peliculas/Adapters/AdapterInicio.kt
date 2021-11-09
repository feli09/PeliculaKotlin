package com.example.peliculas.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculas.Models.Peliculas
import com.example.peliculas.R
import kotlinx.android.synthetic.main.item_pelicula.view.*

class AdapterInicio (val context: Context, val onClickListener: (movie: Peliculas, view: View) -> Unit): PagedListAdapter<Peliculas, AdapterInicio.PeliculasViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        return PeliculasViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false))
    }

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        holder.bindTo(holder, getItem(position))
    }

    inner class PeliculasViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val ivBackground: ImageView = view.imageViewBrackground
        private val tvTitulo: TextView = view.titulo
        private val tvDescripcion: TextView = view.descripcion

        fun bindTo(vh: PeliculasViewHolder, movie: Peliculas?){
            Glide.with(context)
                .load(movie?.getPosterUrl())
                .placeholder(android.R.color.black)
                .into(ivBackground)

            movie?.let { item -> vh.itemView.setOnClickListener {
                it.transitionName = "poster"
                onClickListener(item, it)
            } }

            tvTitulo.setText(movie?.title)
            tvDescripcion.setText(movie?.overview)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Peliculas>() {
            override fun areItemsTheSame(oldItem: Peliculas, newItem: Peliculas) = oldItem.id == newItem.id
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Peliculas, newItem: Peliculas) = oldItem == newItem
        }
    }
}