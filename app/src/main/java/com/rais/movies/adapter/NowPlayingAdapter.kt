package com.rais.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rais.movies.databinding.MovieItemBinding
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.utils.NowPlayingCallback
import com.rais.movies.utils.loadImage

class NowPlayingAdapter(private val nowPlayingCallback: NowPlayingCallback) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    var moviesList = ArrayList<MovieNowPlaying>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listStagingArea: List<MovieNowPlaying>?) {
        if (listStagingArea == null) return
        moviesList.clear()
        moviesList.addAll(listStagingArea)
        notifyDataSetChanged()
    }

    inner class NowPlayingViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MovieNowPlaying) {
            binding.apply {
                layout.setOnClickListener { nowPlayingCallback.onClickMovie(movies) }

                tvTitle.text = movies.title ?: ""
                tvOverview.text = movies.overview ?: ""

                binding.ivPoster.loadImage("https://image.tmdb.org/t/p/w500" + movies.poster_path)

//                Glide.with(itemView)
//                    .load("https://image.tmdb.org/t/p/w500" + movies.poster_path)
//                    .into(binding.ivPoster)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        return NowPlayingViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }
}