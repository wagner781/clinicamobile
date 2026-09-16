package pe.edu.upeu.clinicamobil.Presentation.Paciente

/** Estado del formulario de registro de paciente, campo a campo. */
data class FormularioPaciente(
    val nombre: String = "",
    val dni: String = "",
    val edad: String = "",
    val peso: String = "",
    val errorNombre: String? = null,
    val errorDni: String? = null,
    val errorEdad: String? = null,
    val errorPeso: String? = null,
)