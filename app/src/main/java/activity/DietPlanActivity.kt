package activity

import Fragment.DietPlanWeightGainWomen
import Fragment.DietPlanWeightLossWomen
import Fragment.DietPlansWeightGainFragment
import Fragment.DietPlansWeightLossFragment
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
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.rupinder.fitking.R

class DietPlanActivity : AppCompatActivity() {
    var wlGrid: GridLayout? = null
    var wgGrid: GridLayout? = null
    lateinit var toolbar: Toolbar
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_plan)
        wlGrid = findViewById<View>(R.id.wlGrid) as GridLayout
        wgGrid = findViewById<View>(R.id.wgGrid) as GridLayout
        setSingleEvent(wlGrid)
        setSingleEvent2(wgGrid)

        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title="Diet Plans"

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
    private fun setSingleEvent(wlGrid: GridLayout?) {
        for (i in 0 until wlGrid!!.childCount) {
            val cardView = wlGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                if (i == 0) {
                    val fragment = DietPlansWeightLossFragment()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.dietPlanFrame, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                } else if (i == 1) {
                    val fragment = DietPlanWeightLossWomen()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.dietPlanFrame, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
        }}
    private fun setSingleEvent2(wgGrid: GridLayout?) {
        for (i in 0 until wgGrid!!.childCount) {
            val cardView = wgGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                if (i == 0) {
                    val fragment = DietPlansWeightGainFragment()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.dietPlanFrame, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                } else if (i == 1) {
                    val fragment = DietPlanWeightGainWomen()
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.dietPlanFrame, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
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
                val i= Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rupinder.fitking"))
                startActivity(i)
                return true
            }
            R.id.contactUs ->{
                val intent = Intent(this@DietPlanActivity, ContactusActivity::class.java)
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