package pe.edu.upeu.clinicamobil.domain.usecase

/** Se lanza cuando un médico no cumple las reglas del Anexo A.2. */
class MedicoInvalidoException(
    val errores: ErroresDeMedico,
) : Exception("El médico no cumple las reglas del cuerpo médico")