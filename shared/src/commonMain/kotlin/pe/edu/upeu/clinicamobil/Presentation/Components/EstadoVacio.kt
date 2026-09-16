package pe.edu.upeu.clinicamobil.Presentation.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Estado vacío genérico: un icono, un título y una descripción.
 *
 * Se reutiliza en Pacientes (sin datos, error), Médicos e Historias clínicas.
 * El color del icono es configurable para poder pintar también los errores.
 */
@Composable
fun EstadoVacio(
    icon: ImageVector,
    titulo: String,
    descripcion: String,
    modifier: Modifier = Modifier,
    colorIcono: Color = MaterialTheme.colorScheme.primary,
    accion: @Composable (() -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorIcono,
            modifier = Modifier.size(56.dp),
        )
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = descripcion,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
        accion?.invoke()
    }
}