package pe.edu.upeu.clinicamobil.domain.usecase


import pe.edu.upeu.clinicamobil.domain.Repository.PacienteRepository
import pe.edu.upeu.clinicamobil.domain.model.Paciente

/**
 * Incorpora un paciente al padrón a partir de los datos crudos del formulario.
 *
 * Recibe todo como [String] porque así llega desde la pantalla; valida con las
 * reglas del Anexo A.1; recorta nombre y DNI; y delega el alta al repositorio,
 * que es quien asigna el id definitivo.
 */
class RegistrarPacienteUseCase(
    private val repositorio: PacienteRepository,
) {

    suspend operator fun invoke(
        nombre: String,
        dni: String,
        edad: String,
        peso: String,
    ): Result<Paciente> = resultadoDe {
        val errores = validar(nombre, dni, edad, peso)
        if (errores.tieneErrores()) {
            throw PacienteInvalidoException(errores)
        }

        val paciente = Paciente(
            id = 0L, // el repositorio asigna el id correlativo
            nombre = nombre.trim(),
            dni = dni.trim(),
            edad = edad.trim().toInt(),
            peso = peso.trim().toDouble(),
        )

        repositorio.registrar(paciente)
    }

    private fun validar(
        nombre: String,
        dni: String,
        edad: String,
        peso: String,
    ): ErroresDePaciente {
        val nombreError = if (nombre.isBlank()) "El nombre es obligatorio" else null

        val dniLimpio = dni.trim()
        val dniError = when {
            dniLimpio.isEmpty() -> "El DNI es obligatorio"
            !Regex("^[0-9]{8}$").matches(dniLimpio) -> "El DNI debe tener 8 dígitos"
            else -> null
        }

        val edadLimpia = edad.trim()
        val edadError = when {
            edadLimpia.isEmpty() -> "La edad es obligatoria"
            edadLimpia.toIntOrNull() == null -> "La edad debe ser un número entero"
            edadLimpia.toInt() !in 0..Paciente.EDAD_MAXIMA ->
                "La edad debe estar entre 0 y ${Paciente.EDAD_MAXIMA}"
            else -> null
        }

        val pesoLimpio = peso.trim()
        val pesoError = when {
            pesoLimpio.isEmpty() -> "El peso es obligatorio"
            else -> {
                val pesoDouble = pesoLimpio.toDoubleOrNull()
                when {
                    pesoDouble == null || !pesoDouble.isFinite() ->
                        "El peso debe ser un número válido"
                    pesoDouble <= 0.0 -> "El peso debe ser mayor a 0"
                    else -> null
                }
            }
        }

        return ErroresDePaciente(
            nombre = nombreError,
            dni = dniError,
            edad = edadError,
            peso = pesoError,
        )
    }
}