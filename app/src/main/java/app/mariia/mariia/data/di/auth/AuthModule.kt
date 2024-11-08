package app.mariia.mariia.data.di.auth

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import app.mariia.mariia.ui.screens.auth.AuthViewModel

val authModule = module {
    viewModel<AuthViewModel> { AuthViewModel(get()) }

    includes(firebaseModule)
}