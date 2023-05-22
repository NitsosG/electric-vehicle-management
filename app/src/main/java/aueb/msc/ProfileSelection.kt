package aueb.msc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import aueb.msc.component.ProfileRecycleViewAdapter
import aueb.msc.db.DatabaseHelper
import aueb.msc.model.Profile
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileSelection : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private val activity = this@ProfileSelection
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileRecycleViewAdapter
    private lateinit var profileNames : List<Profile>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_selection)
        databaseHelper = DatabaseHelper(activity)
        profileNames = databaseHelper.getAllProfileNames();
        //Display existing profiles
        recyclerView = findViewById(R.id.recycle_view)
        recyclerView.setHasFixedSize(true)
        // Set the layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Create and set the adapter
        adapter = ProfileRecycleViewAdapter(this, profileNames)
        recyclerView.adapter = adapter
    }
}