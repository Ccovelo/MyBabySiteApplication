package com.example.mybabysiteapplication.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybabysiteapplication.domain.UseCases.AddBabysitterUseCase
import com.example.mybabysiteapplication.domain.UseCases.GetBabysittersUseCase
import com.example.mybabysiteapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BabysitterViewModel @Inject constructor(
    private val getBabysittersUseCase: GetBabysittersUseCase,
    private val addBabysitterUseCase: AddBabysitterUseCase
) : ViewModel() {

    private val _babysitters = MutableStateFlow<List<BabysitterEntity>>(emptyList())
    val babysitters: StateFlow<List<BabysitterEntity>> = _babysitters

    init {
        loadBabysitters()
    }

    fun loadBabysitters() {
        viewModelScope.launch {
            _babysitters.value = getBabysittersUseCase
        }
    }

    fun addBabysitter(babysitter: BabysitterEntity) {
        viewModelScope.launch {
            addBabysitterUseCase(babysitter)
            loadBabysitters() // Recargar lista
        }
    }
}
