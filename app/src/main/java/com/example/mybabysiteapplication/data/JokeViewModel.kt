package com.example.mybabysiteapplication.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybabysiteapplication.domain.UseCases.GetJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeUseCase
) : ViewModel() {

    private val _joke = MutableStateFlow<String>("")
    val joke: StateFlow<String> = _joke

    fun fetchJoke() {
        viewModelScope.launch {
            val jokeResponse = getJokeUseCase()
            _joke.value = jokeResponse?.value ?: "No se pudo obtener un chiste"
        }
    }
}