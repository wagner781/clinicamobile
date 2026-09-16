package pe.edu.upeu.clinicamobil.Presentation.Theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// ---- Paleta clara ---------------------------------------------------------

private val LightPrimary = Color(0xFF00696E)
private val LightOnPrimary = Color(0xFFFFFFFF)
private val LightPrimaryContainer = Color(0xFF6FF6FE)
private val LightOnPrimaryContainer = Color(0xFF002022)
private val LightInversePrimary = Color(0xFF4DD9E1)

private val LightSecondary = Color(0xFF4A6365)
private val LightOnSecondary = Color(0xFFFFFFFF)
private val LightSecondaryContainer = Color(0xFFCDE7E9)
private val LightOnSecondaryContainer = Color(0xFF051F21)

private val LightTertiary = Color(0xFF4C6178)
private val LightOnTertiary = Color(0xFFFFFFFF)
private val LightTertiaryContainer = Color(0xFFCFE5FF)
private val LightOnTertiaryContainer = Color(0xFF071D31)

private val LightError = Color(0xFFBA1A1A)
private val LightOnError = Color(0xFFFFFFFF)
private val LightErrorContainer = Color(0xFFFFDAD6)
private val LightOnErrorContainer = Color(0xFF410002)

private val LightBackground = Color(0xFFF4FBFB)
private val LightOnBackground = Color(0xFF161D1D)
private val LightSurface = Color(0xFFF4FBFB)
private val LightOnSurface = Color(0xFF161D1D)
private val LightSurfaceVariant = Color(0xFFDAE4E5)
private val LightOnSurfaceVariant = Color(0xFF3F4849)
private val LightOutline = Color(0xFF6F797A)
private val LightOutlineVariant = Color(0xFFBEC8C9)
private val LightScrim = Color(0xFF000000)
private val LightInverseSurface = Color(0xFF2B3233)
private val LightInverseOnSurface = Color(0xFFECF2F2)

private val LightSurfaceDim = Color(0xFFD4DBDB)
private val LightSurfaceBright = Color(0xFFF4FBFB)
private val LightSurfaceContainerLowest = Color(0xFFFFFFFF)
private val LightSurfaceContainerLow = Color(0xFFEEF5F5)
private val LightSurfaceContainer = Color(0xFFE8EFEF)
private val LightSurfaceContainerHigh = Color(0xFFE2E9E9)
private val LightSurfaceContainerHighest = Color(0xFFDDE4E4)

// ---- Paleta oscura --------------------------------------------------------

private val DarkPrimary = Color(0xFF4DD9E1)
private val DarkOnPrimary = Color(0xFF003739)
private val DarkPrimaryContainer = Color(0xFF004F53)
private val DarkOnPrimaryContainer = Color(0xFF6FF6FE)
private val DarkInversePrimary = Color(0xFF00696E)

private val DarkSecondary = Color(0xFFB1CBCD)
private val DarkOnSecondary = Color(0xFF1B3436)
private val DarkSecondaryContainer = Color(0xFF324B4D)
private val DarkOnSecondaryContainer = Color(0xFFCDE7E9)

private val DarkTertiary = Color(0xFFB3C9E3)
private val DarkOnTertiary = Color(0xFF1E3247)
private val DarkTertiaryContainer = Color(0xFF35485F)
private val DarkOnTertiaryContainer = Color(0xFFCFE5FF)

private val DarkError = Color(0xFFFFB4AB)
private val DarkOnError = Color(0xFF690005)
private val DarkErrorContainer = Color(0xFF93000A)
private val DarkOnErrorContainer = Color(0xFFFFDAD6)

private val DarkBackground = Color(0xFF0E1415)
private val DarkOnBackground = Color(0xFFDDE4E4)
private val DarkSurface = Color(0xFF0E1415)
private val DarkOnSurface = Color(0xFFDDE4E4)
private val DarkSurfaceVariant = Color(0xFF3F4849)
private val DarkOnSurfaceVariant = Color(0xFFBEC8C9)
private val DarkOutline = Color(0xFF899393)
private val DarkOutlineVariant = Color(0xFF3F4849)
private val DarkScrim = Color(0xFF000000)
private val DarkInverseSurface = Color(0xFFDDE4E4)
private val DarkInverseOnSurface = Color(0xFF2B3233)

private val DarkSurfaceDim = Color(0xFF0E1415)
private val DarkSurfaceBright = Color(0xFF343A3B)
private val DarkSurfaceContainerLowest = Color(0xFF090F0F)
private val DarkSurfaceContainerLow = Color(0xFF161D1D)
private val DarkSurfaceContainer = Color(0xFF1A2122)
private val DarkSurfaceContainerHigh = Color(0xFF252B2C)
private val DarkSurfaceContainerHighest = Color(0xFF303637)

// ---- Esquemas -------------------------------------------------------------

val ClinicaLightColors = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    inversePrimary = LightInversePrimary,

    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,

    tertiary = LightTertiary,
    onTertiary = LightOnTertiary,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = LightOnTertiaryContainer,

    error = LightError,
    onError = LightOnError,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,

    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
    surfaceTint = LightPrimary,

    inverseSurface = LightInverseSurface,
    inverseOnSurface = LightInverseOnSurface,

    outline = LightOutline,
    outlineVariant = LightOutlineVariant,
    scrim = LightScrim,

    surfaceDim = LightSurfaceDim,
    surfaceBright = LightSurfaceBright,
    surfaceContainerLowest = LightSurfaceContainerLowest,
    surfaceContainerLow = LightSurfaceContainerLow,
    surfaceContainer = LightSurfaceContainer,
    surfaceContainerHigh = LightSurfaceContainerHigh,
    surfaceContainerHighest = LightSurfaceContainerHighest,
)

val ClinicaDarkColors = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    inversePrimary = DarkInversePrimary,

    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,

    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,

    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,

    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    surfaceTint = DarkPrimary,

    inverseSurface = DarkInverseSurface,
    inverseOnSurface = DarkInverseOnSurface,

    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    scrim = DarkScrim,

    surfaceDim = DarkSurfaceDim,
    surfaceBright = DarkSurfaceBright,
    surfaceContainerLowest = DarkSurfaceContainerLowest,
    surfaceContainerLow = DarkSurfaceContainerLow,
    surfaceContainer = DarkSurfaceContainer,
    surfaceContainerHigh = DarkSurfaceContainerHigh,
    surfaceContainerHighest = DarkSurfaceContainerHighest,
)