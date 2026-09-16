package pe.edu.upeu.clinicamobil.domain.usecase

/** Errores de validación del formulario de médico, uno por campo. */
data class ErroresDeMedico(
    val nombre: String? = null,
    val colegiatura: String? = null,
    val especialidad: String? = null,
) {
    /** ¿Hay al menos un campo con error? */
    fun tieneErrores(): Boolean =
        nombre != null || colegiatura != null || especialidad != null
}