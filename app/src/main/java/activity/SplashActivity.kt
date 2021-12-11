package activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitking.MainActivity
import com.rupinder.fitking.R

class SplashActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    var textView1: TextView? = null
    var top: Animation? = null
    var bottom: Animation? = null
    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 2700// 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        imageView = findViewById(R.id.imageView)
        textView1 = findViewById(R.id.textView)
        top = AnimationUtils.loadAnimation(this, R.anim.top)
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom)

        imageView?.animation = top
        textView1?.animation = bottom

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN = 2700
    }
}
