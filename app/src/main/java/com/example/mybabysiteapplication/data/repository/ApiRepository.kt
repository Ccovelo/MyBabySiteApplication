package com.example.mybabysiteapplication.data.repository

import com.example.mybabysiteapplication.data.JokeResponse
import com.example.mybabysiteapplication.network.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRandomJoke(): JokeResponse {
        return apiService.getRandomJoke()
    }
}
