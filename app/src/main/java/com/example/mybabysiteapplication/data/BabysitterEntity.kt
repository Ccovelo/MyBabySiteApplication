package com.example.mybabysiteapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "babysitters") //Nomnbre de la tabla
data class BabysitterEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Llave primaria
    val name: String?, // Nombre del canguro
    val age: Int, // Edad
    val experience: Int // Años de experiencia
)