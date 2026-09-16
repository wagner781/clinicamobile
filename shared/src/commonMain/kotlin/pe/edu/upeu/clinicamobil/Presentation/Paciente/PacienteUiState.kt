package pe.edu.upeu.clinicamobil.Presentation.Paciente

/** Las cuatro fases excluyentes del padrón, según RF-03. */
sealed interface FasePaciente {
    data object Cargando : FasePaciente
    data object SinPacientes : FasePaciente
    data class ConPacientes(val pacientes: List<PacienteUi>) : FasePaciente
    data class Error(val mensaje: String) : FasePaciente
}

/**
 * Estado completo de la pantalla de pacientes.
 *
 * - [fase] describe qué mostrar en la lista.
 * - [formulario] guarda los valores escritos y los errores de validación.
 * - [registrando] deshabilita el botón y cambia su texto a "Registrando...".
 * - [mensajeExito] se pinta arriba y se limpia con [PacienteViewModel.limpiarExito].
 */
data class PacienteUiState(
    val fase: FasePaciente = FasePaciente.Cargando,
    val formulario: FormularioPaciente = FormularioPaciente(),
    val registrando: Boolean = false,
    val mensajeExito: String? = null,
)