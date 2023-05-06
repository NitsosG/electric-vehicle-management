package aueb.msc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import aueb.msc.db.DatabaseHelper
import aueb.msc.model.Profile

class ProfileSelection : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_selection)
        val profileNames = databaseHelper.getAllProfileNames()
        val newProfileButton = findViewById<Button>(R.id.create_new_profile_button)
        newProfileButton.setOnClickListener(){
            val intent = Intent(this, VehicleProfileSetup::class.java)
            startActivity(intent)
        }
    }
}