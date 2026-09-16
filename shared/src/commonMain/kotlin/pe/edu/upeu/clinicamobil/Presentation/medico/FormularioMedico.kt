package pe.edu.upeu.clinicamobil.Presentation.medico

/** Estado del formulario de registro de médico, campo a campo. */
data class FormularioMedico(
    val nombre: String = "",
    val colegiatura: String = "",
    val especialidad: String = "",
    val errorNombre: String? = null,
    val errorColegiatura: String? = null,
    val errorEspecialidad: String? = null,
)