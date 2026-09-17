package pe.edu.upeu.clinicamobil.di

import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class AppModuleTest {
    private lateinit var koin: Koin

    @BeforeTest
    fun setUp() {
        koin = startKoin {
            modules(dataModule, domainModule, presentationModule, module { })
        }.koin
    }

    @AfterTest
    fun tearDown() { stopKoin() }
    // ...
}