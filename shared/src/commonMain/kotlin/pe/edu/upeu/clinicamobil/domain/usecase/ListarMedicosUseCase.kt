package pe.edu.upeu.clinicamobil.domain.usecase

import pe.edu.upeu.clinicamobil.domain.Repository.MedicoRepository
import pe.edu.upeu.clinicamobil.domain.model.Medico

/** Devuelve el cuerpo médico completo. */
class ListarMedicosUseCase(
    private val repositorio: MedicoRepository,
) {
    suspend operator fun invoke(): Result<List<Medico>> = resultadoDe {
        repositorio.listar()
    }
}