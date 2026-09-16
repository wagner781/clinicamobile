package pe.edu.upeu.clinicamobil.android

import android.app.Application
import pe.edu.upeu.clinicamobil.di.initKoinAndroid

/**
 * Punto de entrada Android.
 *
 * Arranca Koin antes de que cualquier Activity o Composable lo consulte.
 * Está declarada en el manifiesto con android:name=".MainApplication".
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
    }
}