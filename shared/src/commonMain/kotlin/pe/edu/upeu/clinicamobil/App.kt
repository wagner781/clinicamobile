package pe.edu.upeu.clinicamobil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.koin.compose.KoinContext
import pe.edu.upeu.clinicamobil.Presentation.navigation.AppNavigation
import pe.edu.upeu.clinicamobil.Presentation.Theme.ClinicaMobilTheme

/**
 * Punto de entrada Compose Multiplatform.
 *
 * Envuelve la app con [KoinContext] para que las pantallas puedan pedir
 * dependencias con `koinViewModel()`. El modo oscuro vive aquí, no en la
 * pantalla, para que el interruptor del menú y el tema compartan una única
 * fuente de verdad.
 */
@Composable
fun App() {
    KoinContext {
        var darkTheme by rememberSaveable { mutableStateOf(false) }
        ClinicaMobilTheme(darkTheme = darkTheme) {
            AppNavigation(
                darkTheme = darkTheme,
                onDarkThemeChange = { darkTheme = it },
            )
        }
    }
}