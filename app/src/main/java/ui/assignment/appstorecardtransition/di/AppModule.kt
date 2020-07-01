package ui.assignment.appstorecardtransition.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Divya Gupta.
 */
@Module
class AppModule(private val context:Context) {

    @Provides
    @Singleton
    internal fun provideContext():Context{
        return context
    }

}