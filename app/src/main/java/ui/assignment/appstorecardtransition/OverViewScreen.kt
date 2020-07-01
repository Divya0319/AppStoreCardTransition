package ui.assignment.appstorecardtransition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import ui.assignment.appstorecardtransition.databinding.ActivityOverViewScreenBinding

/**
 * Created by Divya Gupta.
 */
class OverViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityOverViewScreenBinding>(
            this,
            R.layout.activity_over_view_screen
        )

        val receivedIntent = intent.extras
        val imageUrl = receivedIntent?.getString("ImageUrl")
        val title = receivedIntent?.getString("Title")
        val subtitle = receivedIntent?.getString("Subtitle")
        val content = receivedIntent?.getString("Content")

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()


        Glide.with(this).load(imageUrl).placeholder(R.drawable.no_image_available).into(binding.imageOverview)
        binding.titleOverview.text = title
        binding.subtitleOverview.text = subtitle
        binding.webviewOverview.loadData(content, "text/html", "UTF-8")
    }
}