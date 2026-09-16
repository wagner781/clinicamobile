package pe.edu.upeu.clinicamobil.Presentation.medico

import pe.edu.upeu.clinicamobil.domain.model.Medico

/** Versión lista para pintar de un médico del cuerpo médico. */
data class MedicoUi(
    val id: Long,
    val nombre: String,
    val lineaSecundaria: String,
)

/**
 * Convierte un [Medico] del dominio en [MedicoUi].
 *
 * La línea secundaria sigue el Anexo B: `CMP <colegiatura> · <especialidad>`.
 * La especialidad ausente se muestra como "Medicina general".
 */
fun Medico.ui(): MedicoUi {
    val especialidadVisible = especialidad ?: "Medicina general"
    return MedicoUi(
        id = id,
        nombre = nombre,
        lineaSecundaria = "CMP $colegiatura · $especialidadVisible",
    )
}