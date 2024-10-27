package com.rais.movies.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rais.movies.R
import com.rais.movies.adapter.NowPlayingAdapter
import com.rais.movies.databinding.ActivityFavoriteBinding
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.utils.NowPlayingCallback
import com.rais.movies.viewmodel.nowplaying.FavoriteViewModel
import com.rais.movies.viewmodel.toprated.MovieTopRatedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), NowPlayingCallback {

    lateinit var binding: ActivityFavoriteBinding
    val viewModel: FavoriteViewModel by viewModels()
    lateinit var favAdapter: NowPlayingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        favAdapter = NowPlayingAdapter(this)

        prepareRecyclerView()
        getData()
    }

    private fun getData() {
        viewModel.moviesFavorite.observe(this) {
            favAdapter.setData(it)
        }
    }

    private fun prepareRecyclerView() {
        binding.rvFavoriteMovies.setHasFixedSize(true)
        binding.rvFavoriteMovies.layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteMovies.adapter = favAdapter
    }

    override fun onClickMovie(item: MovieNowPlaying) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("sendIdMovie", item.idMovie)
        startActivity(intent)
    }
}