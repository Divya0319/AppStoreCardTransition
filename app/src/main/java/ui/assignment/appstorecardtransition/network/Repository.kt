package ui.assignment.appstorecardtransition.network

import android.content.Context
import com.google.gson.JsonElement
import io.reactivex.Single
import ui.assignment.appstorecardtransition.App
import ui.assignment.appstorecardtransition.R
import ui.assignment.appstorecardtransition.commons.Constants
import javax.inject.Inject

/**
 * Created by Divya Gupta.
 */
class Repository(private val apiCallInterface: ApiCallInterface) {

    @Inject
    lateinit var context: Context
    init {
        (App.context as App).appComponent.doInjection(this)

    }

    fun executeApiCall():Single<JsonElement>{
        return apiCallInterface.getTestRouteAnimationData(Constants.API_KEY)
    }

}