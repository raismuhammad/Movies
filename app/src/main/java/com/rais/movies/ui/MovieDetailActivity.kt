package com.rais.movies.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rais.movies.R
import com.rais.movies.data.remote.response.SearchMovieResult
import com.rais.movies.databinding.ActivityMovieDetailBinding
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.utils.Constants
import com.rais.movies.utils.convertDate
import com.rais.movies.utils.loadImage
import com.rais.movies.viewmodel.nowplaying.DetailMovieNowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    val viewModel: DetailMovieNowPlayingViewModel by viewModels()
    private var id: Int? = null
    private var requestCode: Int? = null
    private var DetailSearch: SearchMovieResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getBundleData()
        getMovieData(id)
        intentHandler()
    }

    private fun intentHandler() {
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun getBundleData() {
        id = intent.getIntExtra("sendIdMovie", 0)
        requestCode = intent.getIntExtra("sendRequestCode", 0)
        DetailSearch = intent.getParcelableExtra("sendDetailMovie")
    }

    private fun getMovieData(id: Int?) {
        if (requestCode == 0) {
            viewModel.getDetailMovie(id).observe(this) { data ->
                showMovieData(data)
                binding.btnFav.setOnClickListener { setFavorite(data) }
            }
        } else {
            binding.btnFav.visibility = View.INVISIBLE
            binding.ivPoster.loadImage(DetailSearch?.poster_path)
            binding.ivBackdrop.loadImage(DetailSearch?.backdrop_path)
            binding.tvTitle.text = DetailSearch?.title
            binding.tvVoteAverage.text = DetailSearch?.vote_average.toString()
            binding.tvOriginalTitle.text = DetailSearch?.original_title
            binding.tvReleaseDate.text = DetailSearch?.release_date
            binding.tvPopularity.text = DetailSearch?.popularity.toString()
            binding.tvOverview.text = DetailSearch?.overview
        }


    }

    private fun setFavorite(data: MovieNowPlaying) {
        var likeState = data.isLike
        likeState = !likeState
        setStatusFavorite(likeState)
        viewModel.setFavoriteMovie(data, likeState)
    }

    private fun showMovieData(data: MovieNowPlaying) {
        val releaseDate = data.release_date?.convertDate(
            Constants.YYYY_MM_DD_FORMAT,
            Constants.EEE_D_MMM_YYYY_FORMAT
        )
        binding.ivPoster.loadImage(data.poster_path)
        binding.ivBackdrop.loadImage(data.backdrop_path)
        binding.tvTitle.text = data.title
        binding.tvVoteAverage.text = data.vote_average.toString()
        binding.tvOriginalTitle.text = data.original_title
        binding.tvReleaseDate.text = releaseDate
        binding.tvPopularity.text = data.popularity.toString()
        binding.tvOverview.text = data.overview
        var statusFavorite: Boolean = data.isLike
        setStatusFavorite(statusFavorite)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red))
        } else {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        }
    }
}