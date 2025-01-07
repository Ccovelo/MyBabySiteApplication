package com.example.mybabysiteapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybabysiteapplication.ui.theme.MyBabySiteApplicationTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.mybabysiteapplication.data.AppDatabase
import com.example.mybabysiteapplication.data.BabysitterEntity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate - Aplicación abierta")

        //Inicializar la BBDD
        val database = AppDatabase.getDatabase(this)
        val babysitterDao = database.babysitterDao()

        // Insertar un canguro
        lifecycleScope.launch{
            val newBabysitter= BabysitterEntity(
                name = "Ana",
                age = 25,
                experience = 3
            )
            babysitterDao.insertBabysitter(newBabysitter)

            // Consultar todos los canguros
            val babysitters = babysitterDao.getAllBabysitters()
            babysitters.forEach {
                println("Canguro: ${it.name}, Edad: ${it.age}, Experiencia: ${it.experience}")
            }
        }
        setContent {
            MyBabySiteApplicationTheme {
                BabySiteApp()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart - Aplicación visible")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume - Aplicación en primer plano")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause - Aplicación minimizada o parcialmente oculta")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop - Aplicación completamente oculta")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy - Aplicación cerrada")
    }
    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState - Guardando estado antes de una rotación o cierre")
    }
    override fun onLowMemory(){
        super.onLowMemory()
        Log.d(TAG, "onLowMemory - Dispositivo con poca memoria")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabySiteApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bienvenidos a BabySite") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen(navController) }
                composable("details/{name}") { backStackEntry ->
                    val name = backStackEntry.arguments?.getString("name")
                    DetailScreen(navController, name ?: "Desconocido")
                }
            }
        }
    )
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Texto descriptivo
        Text(
            text = "Administra tus solicitudes de canguro con BabySite.",
            style = MaterialTheme.typography.bodyLarge
        )

        // Imagen corporativa
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.babysitterppal),
                contentDescription = "Logo BabySite",
                modifier = Modifier.fillMaxSize()
            )
        }

        // Lista interactiva de canguros
        val babysitters = listOf("Juan", "Ana", "Luis", "Sofía")
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(babysitters) { name ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("details/$name") }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = name, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Ver detalles",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreen(navController: NavHostController, name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Detalles del canguro: $name",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Aquí puedes agregar más detalles específicos sobre $name.",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Volver")
        }
    }
}
//podria eliminarse al hacer lo mismo en el home, revisar más adelante
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Texto descriptivo
        Text("Bienvenido a BabySite: Administra tus solicitudes de canguro.")

        // Imagen
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplazar con una imagen mas adelante
            contentDescription = "Logo BabySite",
            modifier = Modifier.size(100.dp)
        )

        // Lista simulada
        val babysitters = listOf("Juan", "Ana", "Luis", "Sofía")
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(babysitters) { ayudantes ->
                Text(text = "Canguro/a: $ayudantes", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BabySiteAppPreview() {
    MyBabySiteApplicationTheme {
        BabySiteApp()
    }
}