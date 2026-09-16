package pe.edu.upeu.clinicamobil.Presentation.Paciente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.edu.upeu.clinicamobil.domain.usecase.ListarPacientesUseCase
import pe.edu.upeu.clinicamobil.domain.usecase.PacienteInvalidoException
import pe.edu.upeu.clinicamobil.domain.usecase.RegistrarPacienteUseCase

/**
 * ViewModel del módulo de pacientes.
 *
 * No valida: la validación vive en [RegistrarPacienteUseCase]. Aquí solo se
 * reciben los errores ya construidos, se pintan en el formulario y se
 * coordinan las cuatro fases del padrón.
 */
class PacienteViewModel(
    private val registrarPaciente: RegistrarPacienteUseCase,
    private val listarPacientes: ListarPacientesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PacienteUiState())
    val state: StateFlow<PacienteUiState> = _state.asStateFlow()

    init {
        cargarPacientes()
    }

    // ---- Listado ----------------------------------------------------------

    fun cargarPacientes() {
        _state.update { it.copy(fase = FasePaciente.Cargando) }
        viewModelScope.launch {
            val resultado = listarPacientes()
            resultado.fold(
                onSuccess = { lista ->
                    _state.update {
                        it.copy(
                            fase = if (lista.isEmpty()) {
                                FasePaciente.SinPacientes
                            } else {
                                FasePaciente.ConPacientes(lista.map { p -> p.ui() })
                            }
                        )
                    }
                },
                onFailure = {
                    _state.update {
                        it.copy(fase = FasePaciente.Error(ERROR_CARGA_PADRON))
                    }
                }
            )
        }
    }

    // ---- Formulario: un onChange por campo ---------------------------------

    fun onNombreChange(valor: String) {
        _state.update {
            it.copy(formulario = it.formulario.copy(nombre = valor, errorNombre = null))
        }
    }

    fun onDniChange(valor: String) {
        _state.update {
            it.copy(formulario = it.formulario.copy(dni = valor, errorDni = null))
        }
    }

    fun onEdadChange(valor: String) {
        _state.update {
            it.copy(formulario = it.formulario.copy(edad = valor, errorEdad = null))
        }
    }

    fun onPesoChange(valor: String) {
        _state.update {
            it.copy(formulario = it.formulario.copy(peso = valor, errorPeso = null))
        }
    }

    // ---- Registro ---------------------------------------------------------

    fun registrar() {
        // Un doble toque no registra dos veces.
        if (_state.value.registrando) return

        val f = _state.value.formulario
        _state.update { it.copy(registrando = true, mensajeExito = null) }

        viewModelScope.launch {
            val resultado = registrarPaciente(f.nombre, f.dni, f.edad, f.peso)
            resultado.fold(
                onSuccess = { paciente ->
                    _state.update {
                        it.copy(
                            registrando = false,
                            formulario = FormularioPaciente(),
                            mensajeExito = "Paciente ${paciente.nombre} registrado correctamente",
                        )
                    }
                    cargarPacientes()
                },
                onFailure = { error ->
                    val errores = (error as? PacienteInvalidoException)?.errores
                    _state.update {
                        it.copy(
                            registrando = false,
                            formulario = it.formulario.copy(
                                errorNombre = errores?.nombre,
                                errorDni = errores?.dni,
                                errorEdad = errores?.edad,
                                errorPeso = errores?.peso,
                            ),
                        )
                    }
                }
            )
        }
    }

    fun limpiarExito() {
        _state.update { it.copy(mensajeExito = null) }
    }
}

private const val ERROR_CARGA_PADRON = "No se pudo cargar el padrón de pacientes"