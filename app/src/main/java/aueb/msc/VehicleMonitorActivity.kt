package aueb.msc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VehicleMonitorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehile_monitor)
        val receivedValue = intent.getStringExtra("profile")
        findViewById<TextView>(R.id.vehicle_monitor_profile_name).text = receivedValue
    }
}