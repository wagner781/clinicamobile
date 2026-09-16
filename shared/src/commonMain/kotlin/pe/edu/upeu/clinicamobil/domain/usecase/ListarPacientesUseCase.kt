package pe.edu.upeu.clinicamobil.domain.usecase

import pe.edu.upeu.clinicamobil.domain.Repository.PacienteRepository
import pe.edu.upeu.clinicamobil.domain.model.Paciente

/** Devuelve el padrón completo de pacientes. */
class ListarPacientesUseCase(
    private val repositorio: PacienteRepository,
) {
    suspend operator fun invoke(): Result<List<Paciente>> = resultadoDe {
        repositorio.listar()
    }
}