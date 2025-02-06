package com.example.mybabysiteapplication.network
import com.example.mybabysiteapplication.data.BabysitterDao
import retrofit2.http.GET


interface ApiService {
    @GET ("babysitters")
    suspend fun getBabysitters(): List<BabysitterDao>
}

