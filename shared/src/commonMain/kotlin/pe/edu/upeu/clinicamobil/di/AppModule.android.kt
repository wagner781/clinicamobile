package pe.edu.upeu.clinicamobil.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Módulo de plataforma para Android.
 *
 * Queda vacío por ahora; sirve de punto de extensión para dependencias
 * específicas de Android (SharedPreferences, WorkManager) cuando la app
 * crezca.
 */
private fun androidPlatformModule(): Module = module {
    // Sin dependencias específicas por ahora.
}

/**
 * Arranca Koin desde Android.
 *
 * No recibe Context porque la app todavía no usa dependencias que lo
 * requieran. Cuando se agregue SharedPreferences, se le volverá a pasar.
 */
fun initKoinAndroid() {
    initKoin(platformModule = androidPlatformModule())
}