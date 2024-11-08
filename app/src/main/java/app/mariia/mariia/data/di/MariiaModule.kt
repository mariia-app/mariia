package app.mariia.mariia.data.di

import app.mariia.mariia.data.di.auth.authModule
import org.koin.dsl.module

val mariiaModule = module {
    includes(authModule)
}