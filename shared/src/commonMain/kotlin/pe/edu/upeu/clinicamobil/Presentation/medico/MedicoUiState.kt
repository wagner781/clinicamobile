package pe.edu.upeu.clinicamobil.Presentation.medico

/** Las cuatro fases excluyentes del cuerpo médico, según RF-04. */
sealed interface FaseMedico {
    data object Cargando : FaseMedico
    data object SinMedicos : FaseMedico
    data class ConMedicos(val medicos: List<MedicoUi>) : FaseMedico
    data class Error(val mensaje: String) : FaseMedico
}

/** Estado completo de la pantalla de médicos. */
data class MedicoUiState(
    val fase: FaseMedico = FaseMedico.Cargando,
    val formulario: FormularioMedico = FormularioMedico(),
    val registrando: Boolean = false,
    val mensajeExito: String? = null,
)