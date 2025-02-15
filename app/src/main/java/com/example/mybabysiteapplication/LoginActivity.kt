package com.example.mybabysiteapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mybabysiteapplication.data.AppDatabase
import com.example.mybabysiteapplication.ui.theme.LoginScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen { username, password ->
                validateLogin(username, password)
            }
        }
    }

    private fun validateLogin(username: String, password: String) {
        val database = AppDatabase.getDatabase(this)
        CoroutineScope(Dispatchers.IO).launch {
            val user = database.babysitterDao().getBabysitterById(1)
            if (user?.name == username && user.experience.toString() == password) {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
