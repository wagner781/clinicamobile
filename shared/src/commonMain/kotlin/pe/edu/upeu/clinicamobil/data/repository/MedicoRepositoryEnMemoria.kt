package pe.edu.upeu.clinicamobil.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pe.edu.upeu.clinicamobil.domain.Repository.MedicoRepository
import pe.edu.upeu.clinicamobil.domain.model.Medico
import kotlin.random.Random

/**
 * Implementación en memoria del cuerpo médico.
 *
 * Misma estrategia que el padrón: [Mutex] para la lista, ids correlativos
 * desde 1 y latencia simulada entre 300 y 800 ms para que la UI ejerza los
 * estados de carga antes de que exista el servidor real.
 */
class MedicoRepositoryEnMemoria : MedicoRepository {

    private val mutex = Mutex()
    private val medicos = mutableListOf<Medico>()
    private var siguienteId: Long = 1L

    override suspend fun registrar(medico: Medico): Medico {
        delay(latenciaSimulada())
        return mutex.withLock {
            val nuevo = medico.copy(id = siguienteId)
            siguienteId++
            medicos.add(nuevo)
            nuevo
        }
    }

    override suspend fun listar(): List<Medico> {
        delay(latenciaSimulada())
        return mutex.withLock { medicos.toList() }
    }

    private fun latenciaSimulada(): Long = Random.nextLong(300L, 801L)
}