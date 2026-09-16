package pe.edu.upeu.clinicamobil.Presentation.medico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.edu.upeu.clinicamobil.domain.usecase.ListarMedicosUseCase
import pe.edu.upeu.clinicamobil.domain.usecase.MedicoInvalidoException
import pe.edu.upeu.clinicamobil.domain.usecase.RegistrarMedicoUseCase

/**
 * ViewModel del módulo de médicos.
 *
 * No valida: la validación vive en [RegistrarMedicoUseCase]. Aquí solo se
 * reciben los errores ya construidos y se coordinan las cuatro fases.
 */
class MedicoViewModel(
    private val registrarMedico: RegistrarMedicoUseCase,
    private val listarMedicos: ListarMedicosUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MedicoUiState())
    val state: StateFlow<MedicoUiState> = _state.asStateFlow()

    init {
        cargarMedicos()
    }

    fun cargarMedicos() {
        _state.update { it.copy(fase = FaseMedico.Cargando) }
        viewModelScope.launch {
            val resultado = listarMedicos()
            resultado.fold(
                onSuccess = { lista ->
                    _state.update {
                        it.copy(
                            fase = if (lista.isEmpty()) {
                                FaseMedico.SinMedicos
                            } else {
                                FaseMedico.ConMedicos(lista.map { m -> m.ui() })
                            }
                        )
                    }
                },
                onFailure = {
                    _state.update {
                        it.copy(fase = FaseMedico.Error(ERROR_CARGA_CUERPO_MEDICO))
                    }
                }
            )
        }
    }

    // ---- Formulario: un onChange por campo --------------------------------

    fun onNombreChange(valor: String) {
        _state.update {
            it.copy(formulario = it.formulario.copy(nombre = valor, errorNombre = null))
        }
    }

    fun onColegiaturaChange(valor: String) {
        _state.update {
            it.copy(
                formulario = it.formulario.copy(
                    colegiatura = valor,
                    errorColegiatura = null,
                )
            )
        }
    }

    fun onEspecialidadChange(valor: String) {
        _state.update {
            it.copy(
                formulario = it.formulario.copy(
                    especialidad = valor,
                    errorEspecialidad = null,
                )
            )
        }
    }

    // ---- Registro ---------------------------------------------------------

    fun registrar() {
        if (_state.value.registrando) return

        val f = _state.value.formulario
        _state.update { it.copy(registrando = true, mensajeExito = null) }

        viewModelScope.launch {
            val resultado = registrarMedico(f.nombre, f.colegiatura, f.especialidad)
            resultado.fold(
                onSuccess = { medico ->
                    _state.update {
                        it.copy(
                            registrando = false,
                            formulario = FormularioMedico(),
                            mensajeExito = "Médico ${medico.nombre} registrado correctamente",
                        )
                    }
                    cargarMedicos()
                },
                onFailure = { error ->
                    val errores = (error as? MedicoInvalidoException)?.errores
                    _state.update {
                        it.copy(
                            registrando = false,
                            formulario = it.formulario.copy(
                                errorNombre = errores?.nombre,
                                errorColegiatura = errores?.colegiatura,
                                errorEspecialidad = errores?.especialidad,
                            ),
                        )
                    }
                }
            )
        }
    }
}

private const val ERROR_CARGA_CUERPO_MEDICO = "No se pudo cargar el cuerpo médico"