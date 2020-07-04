package ui.assignment.appstorecardtransition.network

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import ui.assignment.appstorecardtransition.commons.Constants

/**
 * Created by Divya Gupta.
 */

/**
 * A Retrofit interface class which performs the actual API calls
 */
interface ApiCallInterface {
    @GET(Constants.TEST_ROUTE_ANIMATION_ENDPOINT)
    fun getTestRouteAnimationData(@Header(Constants.HEADER_NAME) apiKey: String): Single<JsonElement>
}