package com.rais.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rais.movies.databinding.LayoutCarouselBinding
import com.rais.movies.databinding.MovieItemBinding
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.model.MovieTopRated
import com.rais.movies.utils.loadBackdrop
import com.rais.movies.utils.loadImage


class CarouselAdapter: RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    var moviesList = ArrayList<MovieTopRated>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listStagingArea: List<MovieTopRated>?) {
        if (listStagingArea == null) return
        moviesList.clear()
        moviesList.addAll(listStagingArea)
        notifyDataSetChanged()
    }

    inner class CarouselViewHolder(val binding: LayoutCarouselBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MovieTopRated) {
            binding.apply {
                binding.ivPoster.loadImage(movies.backdrop_path)
                binding.tvTitle.text = movies.title ?: ""
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselAdapter.CarouselViewHolder {
        return CarouselViewHolder(LayoutCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CarouselAdapter.CarouselViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int = moviesList.size
}