package com.example.mybabysiteapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BabysitterViewModel (application: Application):AndroidViewModel(application) {
private val babysitterDao= AppDatabase.getDatabase(application).babysitterDao()
    //para la UI
    val babysitters: LiveData<List<BabysitterEntity>> = babysitterDao.getAllBabysitters()
    //para operaciones suspendidas
    suspend fun getAllBabysittersList():List<BabysitterEntity>{
        return babysitterDao.getAllBabysittersList()
    }
    fun insertBabysitter (babysitter:BabysitterEntity){
        viewModelScope.launch {
            babysitterDao.insertBabysitter(babysitter)
        }
    }
    fun updateBabysitter (babysitter: BabysitterEntity){
        viewModelScope.launch {
            babysitterDao.updateBabysitter(babysitter)
        }
    }
    fun deleteBabysitter(babysitter: BabysitterEntity){
        viewModelScope.launch {
            babysitterDao.deleteBabysiter(babysitter)
        }
    }
}