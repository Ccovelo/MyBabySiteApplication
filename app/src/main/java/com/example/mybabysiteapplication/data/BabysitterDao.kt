package com.example.mybabysiteapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlin.coroutines.Continuation


@Dao

interface BabysitterDao {
    // Insertar un nuevo canguro
    @Insert
    suspend fun insertBabysitter(babysitter: BabysitterEntity): Long

    @Delete
    suspend fun deleteBabysiter (babysitter: BabysitterEntity): Int

    @Update
    suspend fun  updateBabysitter (babysitter: BabysitterEntity): Int

    // Consultar todos los canguros
    @Query("SELECT * FROM babysitter_table")
    fun getAllBabysitters(): LiveData<List<BabysitterEntity>>
    //para UI y observacion en tiempo real

    @Query("SELECT * FROM babysitter_table")
    suspend fun getAllBabysittersList(): List<BabysitterEntity>
    //para consultas directas

    // Consultar canguros por id
    @Query ("SELECT * FROM babysitter_table WHERE id = :id")
    suspend fun getBabysitterById(id: Int): BabysitterEntity

}