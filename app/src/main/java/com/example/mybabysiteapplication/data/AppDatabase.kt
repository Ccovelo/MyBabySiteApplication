package com.example.mybabysiteapplication.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context


@Database(entities = [BabysitterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun babysitterDao(): BabysitterDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase (context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "babysitter_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}