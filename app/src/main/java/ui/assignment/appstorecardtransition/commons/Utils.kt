package ui.assignment.appstorecardtransition.commons

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

/**
 * Created by Divya Gupta.
 */
object Utils {
    val gson = Gson()

    fun toPrettyFormat(jsonObject: JsonObject): String {

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(jsonObject)
    }
}