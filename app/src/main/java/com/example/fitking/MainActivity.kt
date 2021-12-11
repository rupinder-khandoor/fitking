package com.example.fitking

import activity.*
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.rupinder.fitking.R
import hotchemi.android.rate.AppRate
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var gridLayout: GridLayout? = null
    lateinit var toolbar: Toolbar
    lateinit var mAdView : AdView
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridLayout = findViewById<View>(R.id.mainGrid) as GridLayout
        setSingleEvent(gridLayout)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title ="Fit King"


        //for rating
        AppRate.with(this)
            .setInstallDays(0)
            .setLaunchTimes(3)
            .setRemindInterval(2)
            .monitor()
        AppRate.showRateDialogIfMeetsConditions(this)

        //for showing ads
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        prepareAd()
        val scheduler: ScheduledExecutorService =
            Executors.newSingleThreadScheduledExecutor()
        scheduler.scheduleAtFixedRate({
            Log.i("hello", "world")
            runOnUiThread {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    Log.d("TAG", " Interstitial not loaded")
                }
                prepareAd()
            }
        }, 15, 30, TimeUnit.SECONDS)
    }
    private fun prepareAd() {
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-8217519970289973/3103779137"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }
    // we are setting onClickListener for each element
    private fun setSingleEvent(gridLayout: GridLayout?) {
        for (i in 0 until gridLayout!!.childCount) {
            val cardView = gridLayout.getChildAt(i) as CardView
            cardView.setOnClickListener {
                when (i) {
                    0 -> {
                        val intent = Intent(this@MainActivity, BodyBuidlingActivity::class.java)
                        startActivity(intent)
                    }
                    1 -> {

                        val intent = Intent(this@MainActivity, BlankActivity::class.java)
                        startActivity(intent)}
                    2 -> {
                        val intent = Intent(this@MainActivity, DietPlanActivity::class.java)
                        startActivity(intent)
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
                val i=Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rupinder.fitking"))
                startActivity(i)
                return true
            }
            R.id.contactUs ->{
                val intent = Intent(this@MainActivity, ContactusActivity::class.java)
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
    //Exit Dialog box
    override fun onBackPressed() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@MainActivity)
        builder.setTitle(android.R.string.dialog_alert_title)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setMessage("This app needs your help to improve the app.Could you rate and write a review on Playstore? :)")
            .setCancelable(false)
            .setPositiveButton("Exit",
                DialogInterface.OnClickListener { _, _ -> finish() })
            .setNegativeButton("Rate",
                DialogInterface.OnClickListener { dialog, _ -> dialog.cancel()
                    val i= Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rupinder.fitking"))
                    startActivity(i)
                })

        val alert: android.app.AlertDialog? = builder.create()
        alert?.show()
    }
}