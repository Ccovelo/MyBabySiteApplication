package com.example.mybabysiteapplication.data.repository

import com.example.mybabysiteapplication.data.BabysitterDao
import com.example.mybabysiteapplication.data.BabysitterEntity
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class BabysitterRepository @Inject constructor(
    private val babysitterDao: BabysitterDao
) {
    suspend fun getBabysitters(): List<BabysitterEntity> {
        return babysitterDao.getAllBabysitters().firstOrNull()?: emptyList()
    }

    suspend fun addBabysitter(babysitter: BabysitterEntity) {
        babysitterDao.insertBabysitter(babysitter)
    }
}
