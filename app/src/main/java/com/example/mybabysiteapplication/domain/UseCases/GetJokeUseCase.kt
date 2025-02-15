package com.example.mybabysiteapplication.domain.UseCases

import com.example.mybabysiteapplication.data.JokeResponse
import com.example.mybabysiteapplication.data.repository.ApiRepository
import javax.inject.Inject

class GetJokeUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    suspend operator fun invoke(): JokeResponse? {
        return try {
            repository.getRandomJoke()
        } catch (e: Exception) {
            null
        }
    }
}
