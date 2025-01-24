package com.example.mybabysiteapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "babysitter_table") //Nomnbre de la tabla
data class BabysitterEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Clave primaria
    @ColumnInfo (name = "name") val name: String?, // Nombre del canguro
    @ColumnInfo (name= "age") val age: Int, // Edad
    @ColumnInfo (name= "experience") val experience: Int // Años de experiencia
)