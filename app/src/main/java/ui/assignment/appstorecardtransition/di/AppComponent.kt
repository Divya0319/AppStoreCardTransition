package ui.assignment.appstorecardtransition.di

import dagger.Component
import ui.assignment.appstorecardtransition.HomeScreen
import ui.assignment.appstorecardtransition.network.Repository
import javax.inject.Singleton

/**
 * Created by Divya Gupta.
 */

/**
 * Dagger App component class created to define interface methods taking relevant class as parameter where
 * dependencies are to be injected
 */
@Component(modules = [UtilsModule::class])
@Singleton
interface AppComponent {
    fun doInjection(homeScreen: HomeScreen)
    fun doInjection(repository: Repository)

}
