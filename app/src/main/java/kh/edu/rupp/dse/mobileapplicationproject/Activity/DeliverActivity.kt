package kh.edu.rupp.dse.mobileapplicationproject.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.dse.mobileapplicationproject.R

class DeliverActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deliver)

        // Back button and back arrow click listeners
        findViewById<View>(R.id.back_button).setOnClickListener {
            finish()  // Finish the activity and go back to the previous screen
        }

        // Navigate to home page
        findViewById<View>(R.id.backHome).setOnClickListener {
            val intent = Intent(this@DeliverActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()  // Optionally finish this activity
        }
    }
}