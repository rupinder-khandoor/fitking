package activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.rupinder.fitking.R


class ContactusActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private var mEditTextTo: EditText? = null
    private var mEditTextSubject: EditText? = null
    private var mEditTextMessage: EditText? = null
    private lateinit var contactus:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactus)
        mEditTextTo = findViewById(R.id.edit_text_to)
        mEditTextSubject = findViewById(R.id.edit_text_subject)
        mEditTextMessage = findViewById(R.id.edit_text_message)
        contactus=findViewById(R.id.txtContactUs)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title="Contact Us"
        val buttonSend: Button = findViewById(R.id.button_send)
        buttonSend.setOnClickListener { sendMail() }
    }

    private fun sendMail() {
        val recipientList = mEditTextTo!!.text.toString()
        val recipients =
            recipientList.split(",".toRegex()).toTypedArray()
        val subject = mEditTextSubject!!.text.toString()
        val message = mEditTextMessage!!.text.toString()
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, recipients)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.type = "message/rfc822"
        startActivity(Intent.createChooser(intent, "Choose an email client"))
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
                val intent = Intent(this@ContactusActivity, ContactusActivity::class.java)
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
