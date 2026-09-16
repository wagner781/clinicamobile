package pe.edu.upeu.clinicamobil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import pe.edu.upeu.clinicamobil.Presentation.navigation.AppNavigation
import pe.edu.upeu.clinicamobil.Presentation.Theme.ClinicaMobilTheme

/**
 * Punto de entrada Compose Multiplatform.
 *
 * El modo oscuro vive aquí, no en la pantalla, para que el interruptor del
 * menú y el tema compartan una sola fuente de verdad. En el Ítem 7 se
 * envolverá con KoinContext.
 */
@Composable
fun App() {
    var darkTheme by rememberSaveable { mutableStateOf(false) }
    ClinicaMobilTheme(darkTheme = darkTheme) {
        AppNavigation(
            darkTheme = darkTheme,
            onDarkThemeChange = { darkTheme = it },
        )
    }
}