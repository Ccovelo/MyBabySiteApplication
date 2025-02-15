package com.example.mybabysiteapplication.domain.UseCases

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mybabysiteapplication.data.BabysitterDao
import com.example.mybabysiteapplication.data.BabysitterEntity
import com.example.mybabysiteapplication.data.repository.BabysitterRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.firstOrNull


class GetBabysittersUseCase : List<BabysitterEntity> {
    class GetBabysittersUseCase @Inject constructor(
        private val repository: BabysitterRepository
    ) {
        suspend operator fun invoke(): List<BabysitterEntity> {
            return repository.getBabysitters()
        }
    }
    private suspend fun pruebaCRUD(babysitterDao: BabysitterDao) {
        // Insertar un nuevo babysitter
        val newBabysitter = BabysitterEntity(
            name = "María",
            age = 25,
            experience = 5
        )
        val id = babysitterDao.insertBabysitter(newBabysitter)
        Log.d(TAG, "Nuevo canguro insertado con ID: $id")

        // Consultar todos los babysitters
        val babysitters = babysitterDao.getAllBabysitters().firstOrNull()?: emptyList()
        if (babysitters.isNotEmpty()) {
            babysitters.forEach { babysitter: BabysitterEntity ->
                Log.d(TAG, "Canguro: ${babysitter.name}, Edad: ${babysitter.age}, Experiencia: ${babysitter.experience}")
            }

            // Actualizar el primer babysitter
            val updatedBabysitter = babysitters.first().copy(name = "María Actualizada")
            babysitterDao.updateBabysitter(updatedBabysitter)
            Log.d(TAG, "Canguro actualizado: ${updatedBabysitter.name}")

            // Eliminar el babysitter actualizado
            babysitterDao.deleteBabysiter(updatedBabysitter)
            Log.d(TAG, "Canguro eliminado: ${updatedBabysitter.name}")
        } else {
            Log.d(TAG, "No hay canguros en la base de datos.")
        }
    }

    override val size: Int
        get() = TODO("Not yet implemented")

    override fun get(index: Int): BabysitterEntity {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<BabysitterEntity> {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<BabysitterEntity> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<BabysitterEntity> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<BabysitterEntity> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: BabysitterEntity): Int {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: BabysitterEntity): Int {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<BabysitterEntity>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: BabysitterEntity): Boolean {
        TODO("Not yet implemented")
    }
}

