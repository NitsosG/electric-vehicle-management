package aueb.msc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import aueb.msc.component.ProfileRecycleViewAdapter
import aueb.msc.db.AppDatabaseRoom
import aueb.msc.model.Profile
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileSelection : AppCompatActivity() {

    private lateinit var database: AppDatabaseRoom
    private val activity = this@ProfileSelection
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileRecycleViewAdapter
    private lateinit var profiles : MutableList<Profile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_selection)
        initObjects()
    }

    private fun initObjects() {
        // Create new profile
        val newProfileButton = findViewById<FloatingActionButton>(R.id.create_new_profile_button)
        newProfileButton.setOnClickListener() {
            val intent = Intent(activity, VehicleProfileSetup::class.java)
            startActivity(intent)
        }

        database = AppDatabaseRoom.getAppDatabase(this)!!
        val result : List<Profile> = database.roomDao().getProfiles()
        profiles = ArrayList()
        profiles.addAll(result)

        //Display existing profiles
        recyclerView = findViewById(R.id.recycle_profiles_view)
        recyclerView.setHasFixedSize(true)
        // Set the layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Create and set the adapter
        adapter = ProfileRecycleViewAdapter(this, profiles)
        recyclerView.adapter = adapter
    }
}