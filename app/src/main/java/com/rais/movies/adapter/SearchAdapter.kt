package com.rais.movies.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.rais.movies.data.remote.response.SearchMovieResponse
import com.rais.movies.data.remote.response.SearchMovieResult
import com.rais.movies.databinding.MovieItemBinding
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.utils.NowPlayingCallback
import com.rais.movies.utils.SearchCallback

class SearchAdapter(private val searchCallback: SearchCallback): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var moviesList = ArrayList<SearchMovieResult>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(lisMovie: List<SearchMovieResult>?) {
        if (lisMovie == null) return
        moviesList.clear()
        moviesList.addAll(lisMovie)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: SearchMovieResult) {
            binding.apply {
                layout.setOnClickListener { searchCallback.onClickMovie(movies) }

                tvTitle.text = movies.title ?: ""
                tvOverview.text = movies.overview ?: ""

                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500" + movies.poster_path)
                    .into(binding.ivPoster)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }
}