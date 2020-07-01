package ui.assignment.appstorecardtransition.network

import com.google.gson.JsonElement

/**
 * Created by Divya Gupta.
 */
class ApiResponse(val status: Status, val data: JsonElement?, val error: Throwable?) {
    companion object {

        fun loading(): ApiResponse {
            return ApiResponse(Status.LOADING, null, null)
        }

        fun success(data: JsonElement): ApiResponse {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): ApiResponse {
            return ApiResponse(Status.ERROR, null, error)
        }

        fun complete(): ApiResponse {
            return ApiResponse(Status.COMPLETE, null, null)
        }
    }
}