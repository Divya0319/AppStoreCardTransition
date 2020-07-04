package ui.assignment.appstorecardtransition

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
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

        /**
         * Dagger app component builder for building necessary dependencies at compile time itself
         */
        appComponent =
            DaggerAppComponent.builder().appModule(AppModule(this.applicationContext)).utilsModule(
                UtilsModule()
            ).build()

        /**
         * This code is used to make the whole app orientation portrait only during entire app lifecycle
         */
        registerActivityLifecycleCallbacks(object :
            ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        })

    }

    companion object {
        var context: Context? = null
            private set
    }
}