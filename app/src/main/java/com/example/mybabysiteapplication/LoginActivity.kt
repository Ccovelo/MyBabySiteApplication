package com.example.mybabysiteapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.mybabysiteapplication.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                validateLogin(username, password)
            } else {
                Toast.makeText(this, "Por favor, introduce todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLogin(username: String, password: String) {
        // Acceso a la base de datos Room
        val database = AppDatabase.getDatabase(this)
        CoroutineScope(Dispatchers.IO).launch {
            val user = database.babysitterDao().getBabysitterById(1) // Ejemplo: Puedes buscar por usuario
            if (user?.name == username && user.experience.toString() == password) {
                // Inicio de sesión exitoso
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                }
            } else {
                // Error en el inicio de sesión
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Evita volver a la pantalla de inicio de sesión
    }
}
