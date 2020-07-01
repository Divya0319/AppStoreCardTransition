package ui.assignment.appstorecardtransition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonElement
import ui.assignment.appstorecardtransition.network.ApiResponse
import ui.assignment.appstorecardtransition.network.Status
import javax.inject.Inject

/**
 * Created by Divya Gupta.
 */
class HomeScreen : AppCompatActivity() {

    @Inject
    lateinit var homeScreenViewModelFactory: HomeScreenViewModelFactory
    lateinit var homeScreenViewModel: HomeScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        (application as App).appComponent.doInjection(this)

        homeScreenViewModel =
            ViewModelProvider(this, homeScreenViewModelFactory)[HomeScreenViewModel::class.java]

        homeScreenViewModel.homeScreenResponse.observe(this, Observer { consumeResponse(it) })

        homeScreenViewModel.hitHomeScreenApi()

    }

    private fun consumeResponse(apiResponse: ApiResponse) {
        when (apiResponse.status) {
            Status.LOADING -> Toast.makeText(this, "Fetching Data", Toast.LENGTH_LONG).show()

            Status.ERROR -> Toast.makeText(this, "Some error occured", Toast.LENGTH_LONG).show()

            Status.SUCCESS -> renderSuccessResponse(apiResponse.data)
        }
    }

    private fun renderSuccessResponse(data: JsonElement?) {
        val jsonObject = data?.asJsonObject
        Log.d("--------", "-----------------")
        Log.d("ResponseCaptured", jsonObject.toString())
        Log.d("--------", "-----------------")

    }
}