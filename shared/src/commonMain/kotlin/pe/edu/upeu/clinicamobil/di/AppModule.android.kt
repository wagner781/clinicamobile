package pe.edu.upeu.clinicamobil.di

import android.content.Context
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

/** Helper para que MainApplication no tenga que saber de Koin. */
fun initKoinAndroid(applicationContext: Context) {
    initKoin(platformModule = androidPlatformModule())
}