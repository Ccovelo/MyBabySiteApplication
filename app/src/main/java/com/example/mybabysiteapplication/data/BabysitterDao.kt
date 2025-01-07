package com.example.mybabysiteapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


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
    @Query("SELECT * FROM babysitters")
    suspend fun getAllBabysitters(): List<BabysitterEntity>

    // Consultar canguros por id
    @Query ("SELECT * FROM babysitters WHERE id = :id")
    suspend fun getBabysitterById(id: Int): BabysitterEntity
}