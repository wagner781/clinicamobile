package pe.edu.upeu.clinicamobil.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Módulo de plataforma para iOS.
 *
 * Queda vacío por ahora; sirve de punto de extensión para dependencias
 * nativas (NSUserDefaults, Keychain) cuando la app crezca.
 */
private fun iosPlatformModule(): Module = module {
    // Sin dependencias específicas por ahora.
}

/**
 * Arranca Koin desde Swift.
 *
 * iOSApp.swift llama a `initKoinIos()` al inicio del ciclo de vida.
 */
fun initKoinIos() {
    initKoin(platformModule = iosPlatformModule())
}