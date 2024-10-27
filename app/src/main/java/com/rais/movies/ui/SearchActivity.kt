package com.rais.movies.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rais.movies.R
import com.rais.movies.adapter.NowPlayingAdapter
import com.rais.movies.adapter.SearchAdapter
import com.rais.movies.data.Resource
import com.rais.movies.data.remote.ApiResponse
import com.rais.movies.data.remote.response.SearchMovieResult
import com.rais.movies.databinding.ActivitySearchBinding
import com.rais.movies.utils.SearchCallback
import com.rais.movies.viewmodel.nowplaying.SearchMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), SearchCallback {

    private lateinit var binding: ActivitySearchBinding
    private val seachViewModel: SearchMovieViewModel by viewModels()
    lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        searchAdapter = SearchAdapter(this)

        showRecyclerView()
        searchUser()
    }

    private fun searchUser() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    var searchBar = searchBar.text
                    searchBar = searchView.text
                    searchView.hide()
                    searchView.clearFocus()
//                    seachViewModel.searchMovie(searchView.text.toString())
                    showViewModel(searchView.text.toString())
//                    binding.ivNotFound.visibility = View.GONE
                    true
                }
        }
    }

    private fun showRecyclerView() {
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = searchAdapter
    }

    private fun showViewModel(q: String) {
        seachViewModel.searchMovie(q).observe(this) {
            when(it) {
                is Resource.Success -> {
                    binding.rvMovies.visibility = View.VISIBLE
                    searchAdapter.setData(it.data?.results)
                }

                is Resource.Loading -> {}

                is Resource.Error -> {
                        Toast.makeText(this, "Movie tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    override fun onClickMovie(item: SearchMovieResult) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("sendDetailMovie", item)
        intent.putExtra("sendRequestCode", 1)
        startActivity(intent)
    }
}