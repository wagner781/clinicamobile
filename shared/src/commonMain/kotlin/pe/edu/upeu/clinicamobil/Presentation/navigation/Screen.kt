package pe.edu.upeu.clinicamobil.Presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Destinos de navegación de ClinicaMobil.
 *
 * El título y el icono viven aquí, no en la pantalla, para que la lista
 * [DESTINOS] sea la única fuente de verdad del menú lateral y de la barra
 * superior.
 */
sealed class Screen(
    val titulo: String,
    val icono: ImageVector,
) {
    data object Inicio : Screen("Inicio", Icons.Default.Home)
    data object Pacientes : Screen("Pacientes", Icons.Default.Person)
    data object Medicos : Screen("Médicos", Icons.Default.MedicalServices)
    data object Historias : Screen("Historias clínicas", Icons.Default.Assignment)
}

/**
 * Única fuente de verdad del menú lateral y del título de la barra superior.
 * Si se agrega un destino, se agrega aquí y aparece en toda la app.
 */
val DESTINOS: List<Screen> = listOf(
    Screen.Inicio,
    Screen.Pacientes,
    Screen.Medicos,
    Screen.Historias,
)

/**
 * Guarda y restaura [Screen] por su índice en [DESTINOS].
 * Sobrevive a la rotación porque no serializa la instancia de Compose.
 */
val ScreenSaver: Saver<Screen, Int> = Saver(
    save = { screen -> DESTINOS.indexOf(screen) },
    restore = { indice -> DESTINOS[indice] },
)