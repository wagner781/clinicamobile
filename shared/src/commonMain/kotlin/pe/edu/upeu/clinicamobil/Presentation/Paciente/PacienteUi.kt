package pe.edu.upeu.clinicamobil.Presentation.Paciente

import pe.edu.upeu.clinicamobil.domain.model.Paciente
import kotlin.math.roundToLong

/** Versión lista para pintar de un paciente del padrón. */
data class PacienteUi(
    val id: Long,
    val nombre: String,
    val dni: String,
    val lineaSecundaria: String,
    val esPediatrico: Boolean,
)

/**
 * Convierte un [Paciente] del dominio en [PacienteUi].
 *
 * La línea secundaria sigue el Anexo B: `<edad> años · <peso> kg`.
 *  - Singular cuando la edad es 1: `1 año · 9.2 kg`.
 *  - El peso siempre con un decimal: `70.0 kg`.
 */
fun Paciente.ui(): PacienteUi = PacienteUi(
    id = id,
    nombre = nombre,
    dni = dni,
    lineaSecundaria = "${textoEdad(edad)} · ${textoPeso(peso)} kg",
    esPediatrico = esPediatrico,
)

private fun textoEdad(edad: Int): String =
    if (edad == 1) "1 año" else "$edad años"

/** Arma el peso con exactamente un decimal, redondeando al más cercano. */
private fun textoPeso(peso: Double): String {
    val escalado = (peso * 10).roundToLong()
    val entero = escalado / 10
    val decimal = escalado % 10
    return "$entero.$decimal"
}