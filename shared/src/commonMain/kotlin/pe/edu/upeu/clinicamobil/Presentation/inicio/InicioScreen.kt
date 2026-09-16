package pe.edu.upeu.clinicamobil.Presentation.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pe.edu.upeu.clinicamobil.Presentation.navigation.Screen

/** Datos de un acceso rápido de la portada. */
private data class AccesoRapido(
    val titulo: String,
    val descripcion: String,
    val icono: ImageVector,
    val destino: Screen,
)

/**
 * Lista única de accesos rápidos.
 *
 * La pantalla se genera a partir de esta lista; no se escriben los tres
 * accesos a mano.
 */
private val ACCESOS_RAPIDOS: List<AccesoRapido> = listOf(
    AccesoRapido(
        titulo = "Registrar pacientes",
        descripcion = "Incorpora un paciente al padrón del centro.",
        icono = Icons.Default.Person,
        destino = Screen.Pacientes,
    ),
    AccesoRapido(
        titulo = "Registrar médicos",
        descripcion = "Incorpora un profesional al cuerpo médico.",
        icono = Icons.Default.MedicalServices,
        destino = Screen.Medicos,
    ),
    AccesoRapido(
        titulo = "Revisar historias clínicas",
        descripcion = "Consulta las historias clínicas del centro.",
        icono = Icons.Default.Assignment,
        destino = Screen.Historias,
    ),
)

@Composable
fun InicioScreen(
    onNavegar: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { Portada() }
        item {
            Text(
                text = "Qué puedes hacer",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
        items(ACCESOS_RAPIDOS) { acceso ->
            TarjetaAccesoRapido(
                acceso = acceso,
                onClick = { onNavegar(acceso.destino) },
            )
        }
    }
}

@Composable
private fun Portada() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = Icons.Default.LocalHospital,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
            )
            Text(
                text = "ClinicaMobil",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = "Tu salud universitaria, siempre a mano",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun TarjetaAccesoRapido(
    acceso: AccesoRapido,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp),
        ) {
            Icon(
                imageVector = acceso.icono,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = acceso.titulo,
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = acceso.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}