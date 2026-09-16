package pe.edu.upeu.clinicamobil.Presentation.medico

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.edu.upeu.clinicamobil.Presentation.Components.EstadoVacio
import pe.edu.upeu.clinicamobil.Presentation.Components.MensajeExito
import pe.edu.upeu.clinicamobil.Presentation.Components.ValidatedTextField

@Composable
fun MedicoScreen(
    viewModel: MedicoViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { FormularioMedicoCard(state, viewModel) }

        state.mensajeExito?.let { mensaje ->
            item {
                MensajeExito(
                    mensaje = mensaje,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }

        item { EncabezadoCuerpoMedico(state) }

        when (val fase = state.fase) {
            FaseMedico.Cargando -> item { FaseCargando() }
            FaseMedico.SinMedicos -> item {
                EstadoVacio(
                    icon = Icons.Default.MedicalServices,
                    titulo = "Aún no hay médicos en el cuerpo médico",
                    descripcion = "Registra al primer médico con el formulario de arriba.",
                )
            }
            is FaseMedico.ConMedicos -> items(
                items = fase.medicos,
                key = { it.id },
            ) { medico ->
                TarjetaMedico(medico)
            }
            is FaseMedico.Error -> item {
                EstadoVacio(
                    icon = Icons.Default.ErrorOutline,
                    titulo = "No se pudo cargar el cuerpo médico",
                    descripcion = fase.mensaje,
                    colorIcono = MaterialTheme.colorScheme.error,
                    accion = {
                        OutlinedButton(onClick = viewModel::cargarMedicos) {
                            Text("Reintentar")
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun FormularioMedicoCard(
    state: MedicoUiState,
    viewModel: MedicoViewModel,
) {
    val f = state.formulario
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "Registrar médico",
                style = MaterialTheme.typography.titleMedium,
            )

            ValidatedTextField(
                value = f.nombre,
                onValueChange = viewModel::onNombreChange,
                label = "Nombre",
                error = f.errorNombre,
                enabled = !state.registrando,
            )

            ValidatedTextField(
                value = f.colegiatura,
                onValueChange = viewModel::onColegiaturaChange,
                label = "Colegiatura (CMP)",
                error = f.errorColegiatura,
                keyboardType = KeyboardType.Number,
                enabled = !state.registrando,
            )

            ValidatedTextField(
                value = f.especialidad,
                onValueChange = viewModel::onEspecialidadChange,
                label = "Especialidad (opcional)",
                error = f.errorEspecialidad,
                enabled = !state.registrando,
            )

            Button(
                onClick = viewModel::registrar,
                enabled = !state.registrando,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(if (state.registrando) "Registrando..." else "Registrar")
            }
        }
    }
}

@Composable
private fun EncabezadoCuerpoMedico(state: MedicoUiState) {
    val conteo = when (val fase = state.fase) {
        is FaseMedico.ConMedicos -> {
            val n = fase.medicos.size
            if (n == 1) "1 médico" else "$n médicos"
        }
        else -> null
    }
    if (conteo != null) {
        Text(
            text = conteo,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun FaseCargando() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
    ) {
        CircularProgressIndicator()
        Text("Cargando cuerpo médico...")
    }
}

@Composable
private fun TarjetaMedico(medico: MedicoUi) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = medico.nombre,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = medico.lineaSecundaria,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}