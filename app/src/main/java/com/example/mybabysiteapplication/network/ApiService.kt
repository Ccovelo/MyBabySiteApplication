package com.example.mybabysiteapplication.network
import com.example.mybabysiteapplication.data.JokeResponse
import retrofit2.http.GET


interface ApiService {
    @GET ("jokes/random")
    suspend fun getRandomJoke(): JokeResponse
}

