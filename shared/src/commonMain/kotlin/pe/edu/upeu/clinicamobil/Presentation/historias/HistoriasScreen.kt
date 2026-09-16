package pe.edu.upeu.clinicamobil.Presentation.historias

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pe.edu.upeu.clinicamobil.Presentation.Components.EstadoVacio

/** RF-05: módulo de historias clínicas aún no implementado. */
@Composable
fun HistoriasScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        EstadoVacio(
            icon = Icons.Default.Assignment,
            titulo = "Historias clínicas en construcción",
            descripcion = "El módulo de historias clínicas estará disponible " +
                    "en la próxima versión del centro de salud.",
        )
    }
}