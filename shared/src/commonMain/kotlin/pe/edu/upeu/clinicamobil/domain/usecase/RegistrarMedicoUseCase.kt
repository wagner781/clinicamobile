package pe.edu.upeu.clinicamobil.domain.usecase

import pe.edu.upeu.clinicamobil.domain.Repository.MedicoRepository
import pe.edu.upeu.clinicamobil.domain.model.Medico

/**
 * Incorpora un médico al cuerpo médico a partir de los datos crudos del formulario.
 *
 * Reglas del Anexo A.2:
 *  - nombre obligatorio;
 *  - colegiatura obligatoria, 5 o 6 dígitos;
 *  - especialidad opcional; si llega, al menos 3 caracteres; si llega en blanco
 *    se guarda como null.
 */
class RegistrarMedicoUseCase(
    private val repositorio: MedicoRepository,
) {

    suspend operator fun invoke(
        nombre: String,
        colegiatura: String,
        especialidad: String,
    ): Result<Medico> = resultadoDe {
        val errores = validar(nombre, colegiatura, especialidad)
        if (errores.tieneErrores()) {
            throw MedicoInvalidoException(errores)
        }

        val especialidadLimpia = especialidad.trim().takeIf { it.isNotEmpty() }

        val medico = Medico(
            id = 0L, // el repositorio asigna el id correlativo
            nombre = nombre.trim(),
            colegiatura = colegiatura.trim(),
            especialidad = especialidadLimpia,
        )

        repositorio.registrar(medico)
    }

    private fun validar(
        nombre: String,
        colegiatura: String,
        especialidad: String,
    ): ErroresDeMedico {
        val nombreError = if (nombre.isBlank()) "El nombre es obligatorio" else null

        val colegiaturaLimpia = colegiatura.trim()
        val colegiaturaError = when {
            colegiaturaLimpia.isEmpty() -> "La colegiatura es obligatoria"
            !Regex("^[0-9]{5,6}$").matches(colegiaturaLimpia) ->
                "La colegiatura debe tener entre 5 y 6 dígitos"
            else -> null
        }

        val especialidadLimpia = especialidad.trim()
        val especialidadError =
            if (especialidadLimpia.isNotEmpty() && especialidadLimpia.length < 3) {
                "La especialidad debe tener al menos 3 caracteres"
            } else null

        return ErroresDeMedico(
            nombre = nombreError,
            colegiatura = colegiaturaError,
            especialidad = especialidadError,
        )
    }
}