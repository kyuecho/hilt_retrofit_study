package com.kecho.hilttest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kecho.hilttest.data.repository.MovieServiceImpl
import com.kecho.hilttest.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieServiceImpl: MovieServiceImpl) :
    ViewModel() {

    private var _boxOffice = MutableLiveData<List<Movie>>()
    val boxOffice: LiveData<List<Movie>>
        get() = _boxOffice

    private var _range = MutableLiveData<String>()
    val range: LiveData<String>
        get() = _range

    init {
        getBoxOffice()
    }

    fun getBoxOffice(openDate : String? = null) = viewModelScope.launch {
        val data  = movieServiceImpl.getMovie(targetDt = openDate)
        when (data.isSuccessful) {
            true -> {
                val list = data.body()?.boxOfficeResult?.dailyBoxOfficeList?.map {
                    Movie(it.movieNm, it.openDt, it.rank)
                } ?: emptyList()
                _boxOffice.postValue(list)
                _range.postValue(data.body()?.boxOfficeResult?.showRange)
            }
            else -> {
                _boxOffice.postValue(emptyList())
                _range.postValue("")
            }
        }
    }
}