package activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.rupinder.fitking.R

class BodyBuidlingActivity : AppCompatActivity() {
    var glbbMen: GridLayout? = null
    var glbbWomen: GridLayout? = null
    lateinit var toolbar:Toolbar
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_buidling)
        glbbMen = findViewById<View>(R.id.glbbMen) as GridLayout
        glbbWomen = findViewById<View>(R.id.glbbWomen) as GridLayout
        setSingleEvent(glbbMen)
        setSingleEvent2(glbbWomen)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title="Body Building"

        //for showing ads
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
    private fun setSingleEvent(glbbMen: GridLayout?) {
        for (i in 0 until glbbMen!!.childCount) {
            val cardView = glbbMen.getChildAt(i) as CardView
            cardView.setOnClickListener {
                if (i == 0) {
                    val intent = Intent(this@BodyBuidlingActivity, BodyBuildingMensExercisesActivity::class.java)
                    startActivity(intent)
                }
            }
        }}
    private fun setSingleEvent2(glbbWomen: GridLayout?) {
        for (i in 0 until glbbWomen!!.childCount) {
            val cardView = glbbWomen.getChildAt(i) as CardView
            cardView.setOnClickListener {
                when (i) {
                    0 -> {
                        val intent = Intent(this@BodyBuidlingActivity, BodyBuildingWomensExercisesActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId)  {
            R.id.shareApp -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hey my friend(s) check out this fitness application. You can build your body, loss your weight, and you can choose your diet plans. https://play.google.com/store/apps/details?id=com.rupinder.fitking")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }
            R.id.rating -> {
                val i=Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rupinder.fitking"))
                startActivity(i)
                return true
            }
            R.id.contactUs ->{
                val intent = Intent(this@BodyBuidlingActivity, ContactusActivity::class.java)
                startActivity(intent)
            }
            R.id.moreApps ->{
                val i=Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.boss.directchat"))
                startActivity(i)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}