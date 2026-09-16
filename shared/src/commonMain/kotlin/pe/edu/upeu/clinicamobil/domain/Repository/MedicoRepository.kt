package pe.edu.upeu.clinicamobil.domain.Repository

import pe.edu.upeu.clinicamobil.domain.model.Medico

/**
 * Acceso al cuerpo médico del Centro de Salud Universitario.
 *
 * El cuerpo médico reúne a todos los profesionales habilitados para
 * atender consultas. La colegiatura (CMP) es el identificador oficial
 * con el que se reportan las atenciones a la dirección.
 */
interface MedicoRepository {

    /** Incorpora un médico al cuerpo médico y devuelve la versión con id asignado. */
    suspend fun registrar(medico: Medico): Medico

    /** Devuelve el cuerpo médico completo, en orden de incorporación. */
    suspend fun listar(): List<Medico>
}