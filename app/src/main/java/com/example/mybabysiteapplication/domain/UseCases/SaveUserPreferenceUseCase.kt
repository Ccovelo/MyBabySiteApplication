package com.example.mybabysiteapplication.domain.UseCases

import com.example.mybabysiteapplication.data.repository.UserRepository
import javax.inject.Inject

class SaveUserPreferenceUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(username: String) {
        repository.saveUsername(username)
    }
}
