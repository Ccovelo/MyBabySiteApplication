package com.example.mybabysiteapplication.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybabysiteapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BabysitterViewModel @Inject constructor(
    private val _babysitterDao:BabysitterDao,
    private val apiService: ApiService
) : ViewModel() {
    private val _babysittersState= MutableStateFlow<List<BabysitterEntity>>(emptyList())
    val babysittersState: StateFlow<List<BabysitterEntity>> = _babysittersState

    init {
        fetchBabysitters()
    }
    private fun fetchBabysitters() {
        viewModelScope.launch {
            _babysitterDao.getAllBabysitters().collect { babysitters ->
                _babysittersState.value = babysitters
            }
        }
    }

    fun insertBabysitter (babysitter:BabysitterEntity){
        viewModelScope.launch {
            _babysitterDao.insertBabysitter(babysitter)
            fetchBabysitters()
        }
    }
    fun updateBabysitter (babysitter: BabysitterEntity){
        viewModelScope.launch {
            _babysitterDao.updateBabysitter(babysitter)
            fetchBabysitters()
        }
    }
    fun deleteBabysitter(babysitter: BabysitterEntity){
        viewModelScope.launch {
            _babysitterDao.deleteBabysiter(babysitter)
            fetchBabysitters()
        }
    }
    fun fetchBabysittersFromApi() {
        viewModelScope.launch {
            try {
                val apiResponse = apiService.getBabysitters()
                // Convertir datos si es necesario antes de insertarlos en Room
                _babysittersState.value = apiResponse.map { BabysitterEntity(it.name, it.age, it.experience) }
            } catch (e: Exception) {
                Log.e("API Error", "Error al obtener babysitters: ${e.message}")
            }
        }
    }
}