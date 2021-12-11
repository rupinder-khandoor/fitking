package activity

import Fragment.*
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.rupinder.fitking.R

class BodyBuildingWomensExercisesActivity : AppCompatActivity() {
    var gridLayout: GridLayout? = null
    lateinit var toolbar: Toolbar
    lateinit var mAdView : AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_building_womens_exercises)
        gridLayout = findViewById<View>(R.id.mainGrid) as GridLayout
        setSingleEvent(gridLayout)

        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title="Exercises For Women"

        //for showing ads
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
    private fun setSingleEvent(gridLayout: GridLayout?) {
        for (i in 0 until gridLayout!!.childCount) {
            val cardView = gridLayout.getChildAt(i) as CardView
            cardView.setOnClickListener {
                when (i) {
                    0 -> {
                        val fragment = ChestWomenFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    1 -> {
                        val fragment = TricepsFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    2 -> {
                        val fragment = LatsFragment()
                        val transaction: FragmentTransaction =
                            supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    3 -> {
                        val fragment = BicepsFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    4 -> {
                        val fragment = SholderFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    5 -> {
                        val fragment = LegFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    6 -> {
                        val fragment = AbsFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    7 -> {
                        val fragment = FourArmFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                    8 -> {
                        val fragment = GlutesFragment()
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.bbewomensframe, fragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                }
            }
        }
    }
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
                val intent = Intent(this@BodyBuildingWomensExercisesActivity, ContactusActivity::class.java)
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