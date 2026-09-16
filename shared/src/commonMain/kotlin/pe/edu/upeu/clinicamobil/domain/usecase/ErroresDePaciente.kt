package pe.edu.upeu.clinicamobil.domain.usecase


/** Errores de validación del formulario de paciente, uno por campo. */
data class ErroresDePaciente(
    val nombre: String? = null,
    val dni: String? = null,
    val edad: String? = null,
    val peso: String? = null,
) {
    /** ¿Hay al menos un campo con error? */
    fun tieneErrores(): Boolean =
        nombre != null || dni != null || edad != null || peso != null
}