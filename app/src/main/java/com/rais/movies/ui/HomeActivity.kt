package com.rais.movies.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.rais.movies.R
import com.rais.movies.adapter.CarouselAdapter
import com.rais.movies.adapter.NowPlayingAdapter
import com.rais.movies.data.Resource
import com.rais.movies.databinding.ActivityHomeBinding
import com.rais.movies.model.MovieNowPlaying
import com.rais.movies.utils.NowPlayingCallback
import com.rais.movies.utils.showToast
import com.rais.movies.viewmodel.nowplaying.DetailMovieNowPlayingViewModel
import com.rais.movies.viewmodel.nowplaying.MovieNowPlayingViewModel
import com.rais.movies.viewmodel.toprated.MovieTopRatedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), NowPlayingCallback {

    lateinit var binding: ActivityHomeBinding
    lateinit var nowPlayingAdapter : NowPlayingAdapter
    val viewModel: MovieTopRatedViewModel by viewModels()
    val nowPlayingViewModel: MovieNowPlayingViewModel by viewModels()
    lateinit var viewPager2: ViewPager2
    lateinit var carouselAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nowPlayingAdapter = NowPlayingAdapter(this)
        viewPager2 = binding.carousel

        prepareRecyclerView()
        intentHandler()

        viewModel.movieTopRated.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    carouselAdapter.setData(it.data)
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(this, "Error")
                }
            }
        }

        nowPlayingViewModel.movieNowPlaying.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    nowPlayingAdapter.setData(it.data)
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(this, "Error")
                }
            }
        }
    }

    private fun intentHandler() {
        binding.btnFavorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.btnSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    private fun prepareRecyclerView() {
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = nowPlayingAdapter

        carouselAdapter = CarouselAdapter()
        viewPager2.adapter = carouselAdapter

        viewPager2.setPageTransformer { page, position ->
            val scaleFactor = 0.85f + (1 - Math.abs(position)) * 0.15f
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor
        }
    }

    override fun onClickMovie(item: MovieNowPlaying) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("sendIdMovie", item.idMovie)
        intent.putExtra("sendRequestCode", 0)
        startActivity(intent)
    }
}