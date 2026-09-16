package pe.edu.upeu.clinicamobil.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import pe.edu.upeu.clinicamobil.data.repository.MedicoRepositoryEnMemoria
import pe.edu.upeu.clinicamobil.data.repository.PacienteRepositoryEnMemoria
import pe.edu.upeu.clinicamobil.domain.Repository.MedicoRepository
import pe.edu.upeu.clinicamobil.domain.Repository.PacienteRepository
import pe.edu.upeu.clinicamobil.domain.usecase.ListarMedicosUseCase
import pe.edu.upeu.clinicamobil.domain.usecase.ListarPacientesUseCase
import pe.edu.upeu.clinicamobil.domain.usecase.RegistrarMedicoUseCase
import pe.edu.upeu.clinicamobil.domain.usecase.RegistrarPacienteUseCase
import pe.edu.upeu.clinicamobil.Presentation.medico.MedicoViewModel
import pe.edu.upeu.clinicamobil.Presentation.Paciente.PacienteViewModel

// ---- data ---------------------------------------------------------------

/**
 * Implementaciones concretas de los repositorios.
 *
 * Se registran por su interfaz y como [single] para que toda la app
 * comparta la misma lista en memoria. Cuando el servidor del área de salud
 * reemplace esta fuente, solo cambia este módulo.
 */
val dataModule: Module = module {
    single<PacienteRepository> { PacienteRepositoryEnMemoria() }
    single<MedicoRepository> { MedicoRepositoryEnMemoria() }
}

// ---- domain -------------------------------------------------------------

/**
 * Casos de uso.
 *
 * Se registran como [factory] porque son sin estado: cada pantalla recibe
 * su propia instancia sin compartir nada.
 */
val domainModule: Module = module {
    factory { RegistrarPacienteUseCase(get()) }
    factory { ListarPacientesUseCase(get()) }
    factory { RegistrarMedicoUseCase(get()) }
    factory { ListarMedicosUseCase(get()) }
}

// ---- presentation -------------------------------------------------------

/** ViewModels: uno por pantalla, atados al ciclo de vida del composable. */
val presentationModule: Module = module {
    factory { PacienteViewModel(get(), get()) }
    factory { MedicoViewModel(get(), get()) }
}

// ---- arranque -----------------------------------------------------------

/**
 * Arranca Koin con los módulos comunes más el módulo específico de la
 * plataforma. Android e iOS construyen su propio módulo y lo pasan aquí,
 * de modo que no hace falta usar expect/actual.
 */
fun initKoin(
    platformModule: Module,
    appDeclaration: KoinAppDeclaration = {},
) {
    startKoin {
        appDeclaration()
        modules(dataModule, domainModule, presentationModule, platformModule)
    }
}