package com.example.mybabysiteapplication.domain.UseCases

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mybabysiteapplication.data.BabysitterEntity
import com.example.mybabysiteapplication.data.repository.BabysitterRepository
import com.example.mybabysiteapplication.network.ApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddBabysitterUseCase @Inject constructor(
    private val repository: BabysitterRepository
) {
    suspend operator fun invoke(babysitter: BabysitterEntity) {
        repository.addBabysitter(babysitter)
    }
}
