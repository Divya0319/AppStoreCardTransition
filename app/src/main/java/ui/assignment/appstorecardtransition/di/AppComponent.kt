package ui.assignment.appstorecardtransition.di

import dagger.Component
import ui.assignment.appstorecardtransition.HomeScreen
import ui.assignment.appstorecardtransition.network.Repository
import javax.inject.Singleton

/**
 * Created by Divya Gupta.
 */

@Component(modules = [AppModule::class, UtilsModule::class])
@Singleton
interface AppComponent {
    fun doInjection(homeScreen: HomeScreen)
    fun doInjection(repository: Repository)

}
