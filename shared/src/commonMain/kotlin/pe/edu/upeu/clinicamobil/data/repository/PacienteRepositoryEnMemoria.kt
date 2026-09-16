package pe.edu.upeu.clinicamobil.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pe.edu.upeu.clinicamobil.domain.Repository.PacienteRepository
import pe.edu.upeu.clinicamobil.domain.model.Paciente
import kotlin.random.Random

/**
 * Implementación en memoria del padrón de pacientes.
 *
 * Sirve como fuente provisional mientras la dirección del centro de salud
 * habilita el servidor centralizado. Mantiene la lista protegida con un
 * [Mutex] porque registrar y listar pueden invocarse concurrentemente desde
 * la UI, y asigna los ids de forma correlativa desde 1.
 */
class PacienteRepositoryEnMemoria : PacienteRepository {

    private val mutex = Mutex()
    private val pacientes = mutableListOf<Paciente>()
    private var siguienteId: Long = 1L

    override suspend fun registrar(paciente: Paciente): Paciente {
        delay(latenciaSimulada())
        return mutex.withLock {
            val nuevo = paciente.copy(id = siguienteId)
            siguienteId++
            pacientes.add(nuevo)
            nuevo
        }
    }

    override suspend fun listar(): List<Paciente> {
        delay(latenciaSimulada())
        return mutex.withLock { pacientes.toList() }
    }

    /** Latencia aleatoria entre 300 y 800 ms que emula la red. */
    private fun latenciaSimulada(): Long = Random.nextLong(300L, 801L)
}