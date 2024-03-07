package com.kecho.hilttest.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.kecho.hilttest.R
import com.kecho.hilttest.databinding.ActivityMainBinding
import com.kecho.hilttest.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding

    private val movieViewModel : MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initSearchView()
        initView()
    }

    private fun initSearchView() {
        with(_binding) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = true

                override fun onQueryTextChange(date: String?): Boolean {
                    val searchDate = if(date?.length != 8) null else date
                    movieViewModel.getBoxOffice(searchDate)
                    return true
                }
            })
        }
    }

    private fun initView() {
        with(movieViewModel) {
            boxOffice.observe(this@MainActivity) { data ->
                val stringBuilder = StringBuilder()
                for (movie in data) {
                    stringBuilder.append("영화제목 : ${movie.movieNm} / 개봉일자 : ${movie.openDt} / 순위 : ${movie.rank}위\n")
                }
                _binding.txtMovie.text = stringBuilder.toString()
            }
            range.observe(this@MainActivity) {
                _binding.txtRange.text = "조회 일자 : ${it ?: ""}"
            }
        }
    }
}