package aueb.msc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import aueb.msc.db.AppDatabaseRoom
import aueb.msc.model.Brand
import aueb.msc.model.Model
import aueb.msc.model.Profile

class VehicleMonitorActivity : AppCompatActivity() {
    private lateinit var database: AppDatabaseRoom
    private lateinit var profile: Profile
    private lateinit var brand : Brand
    private lateinit var model : Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_monitor)
        database = AppDatabaseRoom.getAppDatabase(this)!!
        initObjects()

    }

    private fun initObjects() {
        val profileName = intent.getStringExtra("profile").toString()
        profile = database.roomDao().getProfile(profileName)
        findViewById<TextView>(R.id.monitor_profile_name_view).text = profile.name
        findViewById<TextView>(R.id.monitor_plate_number_view).text = profile.plateNumber

        model = database.roomDao().getModel(profile.modelCode)
        brand = database.roomDao().getBrand(model.brandCode)
        findViewById<TextView>(R.id.monitor_brand_name_view).text = brand.name
        findViewById<TextView>(R.id.monitor_model_name_view).text = model.name
    }
}