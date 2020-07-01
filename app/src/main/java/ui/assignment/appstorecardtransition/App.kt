package ui.assignment.appstorecardtransition

import android.app.Application
import android.content.Context
import ui.assignment.appstorecardtransition.di.AppComponent
import ui.assignment.appstorecardtransition.di.AppModule
import ui.assignment.appstorecardtransition.di.DaggerAppComponent
import ui.assignment.appstorecardtransition.di.UtilsModule

/**
 * Created by Divya Gupta.
 */
class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        appComponent =
            DaggerAppComponent.builder().appModule(AppModule(this.applicationContext)).utilsModule(
                UtilsModule()
            ).build()

    }

    companion object {
        var context: Context? = null
            private set
    }
}