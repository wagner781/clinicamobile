package pe.edu.upeu.clinicamobil.domain.Repository

import pe.edu.upeu.clinicamobil.domain.model.Paciente

/**
 * Acceso al padrón de pacientes del Centro de Salud Universitario.
 *
 * El padrón es la lista oficial de pacientes registrados: cada paciente
 * que llega al mostrador se incorpora aquí antes de poder agendar una
 * atención. El origen real (memoria local o servidor del área de salud)
 * es un detalle de implementación.
 */
interface PacienteRepository {

    /** Incorpora un paciente al padrón y devuelve la versión con id asignado. */
    suspend fun registrar(paciente: Paciente): Paciente

    /** Devuelve el padrón completo, en orden de incorporación. */
    suspend fun listar(): List<Paciente>
}