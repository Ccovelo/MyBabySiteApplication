package com.example.mybabysiteapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBabySiteApplicationTheme {
                BabySiteApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun BabySiteApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bienvenidos a BabySite") },
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Contenido principal
                MainContent()

                // Imagen corporativa en la esquina superior derecha
                Image(
                    painter = painterResource(id = R.drawable.babysitterppal),
                    contentDescription = "Logo corporativo",
                    modifier = Modifier
                        .size(80.dp) // Tamaño de la imagen
                        .align(Alignment.TopEnd) // La coloca en la esquina superior derecha
                        .padding(8.dp) // Margen desde los bordes
                )
            }
        }
    )
}


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