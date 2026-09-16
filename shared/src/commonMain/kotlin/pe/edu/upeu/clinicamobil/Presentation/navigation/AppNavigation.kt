package pe.edu.upeu.clinicamobil.Presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import pe.edu.upeu.clinicamobil.Presentation.historias.HistoriasScreen
import pe.edu.upeu.clinicamobil.Presentation.inicio.InicioScreen
import pe.edu.upeu.clinicamobil.Presentation.medico.MedicoScreen
import pe.edu.upeu.clinicamobil.Presentation.medico.MedicoViewModel
import pe.edu.upeu.clinicamobil.Presentation.Paciente.PacienteScreen
import pe.edu.upeu.clinicamobil.Presentation.Paciente.PacienteViewModel

/**
 * Contenedor de navegación de ClinicaMobil.
 *
 * - El menú lateral y el título de la barra se alimentan de [DESTINOS].
 * - La pantalla actual sobrevive a la rotación gracias a [ScreenSaver].
 * - El modo oscuro vive al pie del menú y se delega al [App].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var pantallaActual by rememberSaveable(stateSaver = ScreenSaver) {
        mutableStateOf<Screen>(Screen.Inicio)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                CabeceraMarca()
                HorizontalDivider()
                DESTINOS.forEach { destino ->
                    NavigationDrawerItem(
                        icon = { Icon(destino.icono, contentDescription = null) },
                        label = { Text(destino.titulo) },
                        selected = destino == pantallaActual,
                        onClick = {
                            pantallaActual = destino
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    )
                }
                Spacer(Modifier.weight(1f))
                HorizontalDivider()
                ModoOscuroItem(
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                )
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(pantallaActual.titulo) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú",
                            )
                        }
                    },
                )
            },
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                when (pantallaActual) {
                    Screen.Inicio -> InicioScreen(
                        onNavegar = { destino -> pantallaActual = destino },
                    )
                    Screen.Pacientes -> PacienteScreen(
                        viewModel = koinViewModel<PacienteViewModel>(),
                    )
                    Screen.Medicos -> MedicoScreen(
                        viewModel = koinViewModel<MedicoViewModel>(),
                    )
                    Screen.Historias -> HistoriasScreen()
                }
            }
        }
    }
}

@Composable
private fun CabeceraMarca() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 24.dp),
    ) {
        Text(
            text = "ClinicaMobil",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Centro de Salud Universitario",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ModoOscuroItem(
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 12.dp),
    ) {
        Icon(
            imageVector = if (darkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
            contentDescription = null,
            modifier = Modifier.padding(end = 12.dp),
        )
        Text(
            text = "Modo oscuro",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
        )
        Switch(
            checked = darkTheme,
            onCheckedChange = onDarkThemeChange,
        )
    }
}