package pe.edu.upeu.clinicamobil.domain.usecase

/** Se lanza cuando un paciente no cumple las reglas del Anexo A.1. */
class PacienteInvalidoException(
    val errores: ErroresDePaciente,
) : Exception("El paciente no cumple las reglas del padrón")