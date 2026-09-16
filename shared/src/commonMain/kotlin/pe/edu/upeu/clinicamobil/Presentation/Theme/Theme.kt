package pe.edu.upeu.clinicamobil.Presentation.Theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Tema visual de ClinicaMobil.
 *
 * Usa una paleta verde-azulada (teal) propia del centro de salud: no es el
 * verde de PharmaMobil ni el morado por defecto de Compose. Expone los
 * esquemas claro y oscuro completos, incluidos los roles surfaceContainer*.
 */
@Composable
fun ClinicaMobilTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) ClinicaDarkColors else ClinicaLightColors,
        content = content,
    )
}