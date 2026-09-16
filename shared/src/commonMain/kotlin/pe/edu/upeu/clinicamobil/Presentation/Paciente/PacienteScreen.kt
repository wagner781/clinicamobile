package pe.edu.upeu.clinicamobil.Presentation.Paciente

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
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Group
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
import androidx.compose.material3.Icon

@Composable
fun PacienteScreen(
    viewModel: PacienteViewModel,
    modifier: Modifier = Modifier,
) {
    // El único remember es de infraestructura, no de negocio.
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { FormularioPacienteCard(state, viewModel) }

        state.mensajeExito?.let { mensaje ->
            item {
                MensajeExito(
                    mensaje = mensaje,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
        }

        item { EncabezadoPadron(state) }

        when (val fase = state.fase) {
            FasePaciente.Cargando -> item { FaseCargando() }
            FasePaciente.SinPacientes -> item {
                EstadoVacio(
                    icon = Icons.Default.Group,
                    titulo = "Aún no hay pacientes en el padrón",
                    descripcion = "Registra al primer paciente con el formulario de arriba.",
                )
            }
            is FasePaciente.ConPacientes -> items(
                items = fase.pacientes,
                key = { it.id },
            ) { paciente ->
                TarjetaPaciente(paciente)
            }
            is FasePaciente.Error -> item {
                EstadoVacio(
                    icon = Icons.Default.ErrorOutline,
                    titulo = "No se pudo cargar el padrón",
                    descripcion = fase.mensaje,
                    colorIcono = MaterialTheme.colorScheme.error,
                    accion = {
                        OutlinedButton(onClick = viewModel::cargarPacientes) {
                            Text("Reintentar")
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun FormularioPacienteCard(
    state: PacienteUiState,
    viewModel: PacienteViewModel,
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
                text = "Registrar paciente",
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
                value = f.dni,
                onValueChange = viewModel::onDniChange,
                label = "DNI",
                error = f.errorDni,
                keyboardType = KeyboardType.Number,
                enabled = !state.registrando,
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ValidatedTextField(
                    value = f.edad,
                    onValueChange = viewModel::onEdadChange,
                    label = "Edad",
                    error = f.errorEdad,
                    keyboardType = KeyboardType.Number,
                    enabled = !state.registrando,
                    modifier = Modifier.weight(1f),
                )
                ValidatedTextField(
                    value = f.peso,
                    onValueChange = viewModel::onPesoChange,
                    label = "Peso (kg)",
                    error = f.errorPeso,
                    keyboardType = KeyboardType.Decimal,
                    enabled = !state.registrando,
                    modifier = Modifier.weight(1f),
                )
            }

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
private fun EncabezadoPadron(state: PacienteUiState) {
    val conteo = when (val fase = state.fase) {
        is FasePaciente.ConPacientes -> {
            val n = fase.pacientes.size
            if (n == 1) "1 paciente" else "$n pacientes"
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
        Text("Cargando padrón...")
    }
}

@Composable
private fun TarjetaPaciente(paciente: PacienteUi) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = paciente.nombre,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f),
                )
                if (paciente.esPediatrico) {
                    BadgePediatrico()
                }
            }
            Text(
                text = "DNI ${paciente.dni}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = paciente.lineaSecundaria,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun BadgePediatrico() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector = Icons.Default.ChildCare,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary,
        )
        Text(
            text = "Pediátrico",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}