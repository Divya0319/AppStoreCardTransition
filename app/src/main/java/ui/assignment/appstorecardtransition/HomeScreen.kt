package ui.assignment.appstorecardtransition

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.JsonElement
import ui.assignment.appstorecardtransition.databinding.ActivityHomeScreenBinding
import ui.assignment.appstorecardtransition.network.ApiResponse
import ui.assignment.appstorecardtransition.network.HomeScreenResponse
import ui.assignment.appstorecardtransition.network.Status
import javax.inject.Inject


/**
 * Created by Divya Gupta.
 */
class HomeScreen : AppCompatActivity() {

    @Inject
    lateinit var homeScreenViewModelFactory: HomeScreenViewModelFactory

    lateinit var alertDialogBuilder: AlertDialog.Builder
    lateinit var dialog: Dialog
    lateinit var homeData: HomeScreenResponse

    @Inject
    lateinit var gson: Gson
    lateinit var homeScreenViewModel: HomeScreenViewModel
    lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)

        alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(R.layout.progress_dialog_with_message)
        dialog = alertDialogBuilder.create()
        dialog.setCancelable(false)

        (application as App).appComponent.doInjection(this)

        supportActionBar?.title = "Home Screen"

        homeScreenViewModel =
            ViewModelProvider(this, homeScreenViewModelFactory)[HomeScreenViewModel::class.java]

        homeScreenViewModel.homeScreenResponse.observe(this, Observer { consumeResponse(it) })

        homeScreenViewModel.hitHomeScreenApi()

    }

    private fun consumeResponse(apiResponse: ApiResponse) {
        when (apiResponse.status) {
            Status.LOADING -> showProgressDialog()
            Status.ERROR -> {
                hideProgressDialog()
                renderErrorResponse()
            }

            Status.SUCCESS -> {
                hideProgressDialog()
                renderSuccessResponse(apiResponse.data)
            }
        }
    }

    private fun renderErrorResponse() {
        binding.clCards.visibility = View.INVISIBLE
        binding.clNoInternet.visibility = View.VISIBLE

        binding.tvRetry.setOnClickListener { homeScreenViewModel.hitHomeScreenApi() }
    }

    private fun renderSuccessResponse(data: JsonElement?) {
        if (binding.clNoInternet.visibility == View.VISIBLE) {
            binding.clNoInternet.visibility = View.INVISIBLE
        }
        binding.clCards.visibility = View.VISIBLE
        val jsonObject = data?.asJsonObject
        homeData = gson.fromJson(jsonObject.toString(), HomeScreenResponse::class.java)

        Glide.with(this).load(homeData.card_data!![0].url).into(binding.image1)
        Glide.with(this).load(homeData.card_data!![1].url).into(binding.image2)
        Glide.with(this).load(homeData.card_data!![2].url).into(binding.image3)

        binding.title1.text = homeData.card_data!![0].title
        binding.title2.text = homeData.card_data!![1].title
        binding.title3.text = homeData.card_data!![2].title

        binding.subtitle1.text = homeData.card_data!![0].subtitle
        binding.subtitle2.text = homeData.card_data!![1].subtitle
        binding.subtitle3.text = homeData.card_data!![2].subtitle

    }

    private fun showProgressDialog() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    private fun hideProgressDialog() {
        if (dialog.isShowing)
            dialog.dismiss()
    }

    fun animateView(view: View) {
        val intent = Intent(this, OverViewScreen::class.java)
        var pTitle: Pair<View, String>? = null
        var pSubtitle: Pair<View, String>? = null
        var pImage: Pair<View, String>? = null

        val transitionTitle = getString(R.string.transition_title)
        val transitionSubTitle = getString(R.string.transition_subtitle)
        val transitionImage = getString(R.string.transition_image)

        when (view.id) {
            R.id.cv_1 -> {
                intent.putExtra("ImageUrl", homeData.card_data!![0].url)
                intent.putExtra("Title", homeData.card_data!![0].title)
                intent.putExtra("Subtitle", homeData.card_data!![0].subtitle)
                intent.putExtra("Content", homeData.card_data!![0].content)
                pTitle = Pair.create(binding.title1, transitionTitle)
                pSubtitle = Pair.create(binding.subtitle1, transitionSubTitle)
                pImage = Pair.create(binding.image1, transitionImage)
            }
            R.id.cv_2 -> {
                intent.putExtra("ImageUrl", homeData.card_data!![1].url)
                intent.putExtra("Title", homeData.card_data!![1].title)
                intent.putExtra("Subtitle", homeData.card_data!![1].subtitle)
                intent.putExtra("Content", homeData.card_data!![1].content)
                pTitle = Pair.create(binding.title2, transitionTitle)
                pSubtitle = Pair.create(binding.subtitle2, transitionSubTitle)
                pImage = Pair.create(binding.image2, transitionImage)
            }
            R.id.cv_3 -> {
                intent.putExtra("ImageUrl", homeData.card_data!![2].url)
                intent.putExtra("Title", homeData.card_data!![2].title)
                intent.putExtra("Subtitle", homeData.card_data!![2].subtitle)
                intent.putExtra("Content", homeData.card_data!![2].content)
                pTitle = Pair.create(binding.title3, transitionTitle)
                pSubtitle = Pair.create(binding.subtitle3, transitionSubTitle)
                pImage = Pair.create(binding.image3, transitionImage)
            }


        }


        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            pTitle, pImage, pSubtitle
        )

        ActivityCompat.startActivity(this, intent, options.toBundle())
    }
}