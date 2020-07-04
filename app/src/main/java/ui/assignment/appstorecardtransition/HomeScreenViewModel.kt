package ui.assignment.appstorecardtransition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ui.assignment.appstorecardtransition.network.ApiResponse
import ui.assignment.appstorecardtransition.network.Repository

/**
 * Created by Divya Gupta.
 */

/**
 * HomeScreen ViewModel class which call repository methods to perform homescreen api calls,
 * and do changes in relevant livedata
 * based on API response type
 */
class HomeScreenViewModel internal constructor(private val repository: Repository) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val responseHomeScreen = MutableLiveData<ApiResponse>()
    val homeScreenResponse: LiveData<ApiResponse> get() = responseHomeScreen

    fun hitHomeScreenApi() {
        disposable.add(repository.executeApiCall()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseHomeScreen.value = ApiResponse.loading() }
            .subscribe(
                { result -> responseHomeScreen.value = ApiResponse.success(result) },
                { error -> responseHomeScreen.value = ApiResponse.error(error) }
            ))
    }

    override fun onCleared() {
        disposable.clear()
    }
}