package pe.edu.upeu.clinicamobil.domain.usecase

import kotlin.coroutines.cancellation.CancellationException

/**
 * Envuelve un bloque de negocio en un [Result].
 *
 * - Si el bloque termina bien, devuelve [Result.success].
 * - Si el bloque lanza [CancellationException], la vuelve a lanzar para no
 *   romper la cancelación cooperativa de corrutinas.
 * - Cualquier otro error se convierte en [Result.failure] para que las capas
 *   superiores decidan qué mostrar.
 */
suspend fun <T> resultadoDe(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Throwable) {
    Result.failure(e)
}