package ui.assignment.appstorecardtransition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ui.assignment.appstorecardtransition.network.Repository
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Created by Divya Gupta.
 */
@Suppress("UNCHECKED_CAST")
class HomeScreenViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}