package pe.edu.upeu.clinicamobil.android

import android.app.Application
import pe.edu.upeu.clinicamobil.di.initKoinAndroid

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid()
    }
}