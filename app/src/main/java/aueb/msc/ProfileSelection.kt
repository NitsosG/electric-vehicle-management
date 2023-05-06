package aueb.msc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import aueb.msc.db.DatabaseHelper
import aueb.msc.model.Profile

class ProfileSelection : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private val activity = this@ProfileSelection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_selection)
        databaseHelper = DatabaseHelper(activity)
        //Display existing profiles
        val profileNames = databaseHelper.getAllProfileNames()
        val linearLayout = findViewById<LinearLayout>(R.id.profile_selection_linear_layout)
        profileNames.forEach { buttonText ->
            val button = Button(this)
            button.text = buttonText
            linearLayout.addView(button)
        }
        // Create new profile
        val newProfileButton = findViewById<Button>(R.id.create_new_profile_button)
        newProfileButton.setOnClickListener() {
            val intent = Intent(this, VehicleProfileSetup::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }

}