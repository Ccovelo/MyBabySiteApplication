package com.example.mybabysiteapplication.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "app_database.db"
        const val DATABASE_VERSION = 1

        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear tabla de usuarios
        db?.execSQL(
            "CREATE TABLE $TABLE_USERS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_USERNAME TEXT, " +
                    "$COLUMN_PASSWORD TEXT)"
        )

        // Insertar un usuario por defecto (admin)
        db?.execSQL(
            "INSERT INTO $TABLE_USERS ($COLUMN_USERNAME, $COLUMN_PASSWORD) VALUES ('admin', '1234')"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // En caso de actualizar la base de datos, puedes borrar y volver a crear las tablas
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun validateUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }
}
