package app.mariia.mariia

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import app.mariia.mariia.data.di.mariiaModule

class MariiaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin(this)
    }

    private fun configureKoin(ctx: Context) {
        startKoin {
            androidLogger()
            androidContext(ctx)
            modules(mariiaModule)
        }
    }
}