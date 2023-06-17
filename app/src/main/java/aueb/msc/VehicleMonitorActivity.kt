package aueb.msc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import aueb.msc.db.AppDatabaseRoom
import aueb.msc.model.Brand
import aueb.msc.model.Model
import aueb.msc.model.Profile
import aueb.msc.service.VehicleMonitorService
import aueb.msc.notification.batteryLevelNotification;


class VehicleMonitorActivity : AppCompatActivity() {
    private lateinit var database: AppDatabaseRoom
    private lateinit var profile: Profile
    private lateinit var brand : Brand
    private lateinit var model : Model
    // Service
    private val vehicleMonitorService = VehicleMonitorService::class.java
    private lateinit var boundService: VehicleMonitorService
    private var isBound = false
    // Handler - Runnable
    private lateinit var handler: Handler
    private lateinit var refreshRunnable: Runnable
    private lateinit var serviceIntent: Intent

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as VehicleMonitorService.MyBinder
            boundService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_monitor)
        database = AppDatabaseRoom.getAppDatabase(this)!!
        initServices()
        initObjects()
        initHandler()

    }

    private fun initHandler() {
        handler = Handler()
        // Initialize the refresh runnable
        refreshRunnable = object : Runnable {
            override fun run() {

                // Check if service is bound
                // Example: Refresh a elements
                if (isBound) {
                    batteryLevelNotification(applicationContext, boundService.vehicleMonitorData().batteryLevel);
                    findViewById<TextView>(R.id.monitor_battery_level).text =  boundService.vehicleMonitorData().batteryLevel.toString()
                    findViewById<TextView>(R.id.monitor_remaining_km).text =  boundService.vehicleMonitorData().remainingKilometers.toString();
                    findViewById<TextView>(R.id.monitor_total_km).text =  boundService.vehicleMonitorData().totalKilometers.toString();
                    findViewById<ProgressBar>(R.id.monitor_battery_bar).progress =  boundService.vehicleMonitorData().batteryLevel.toInt();
                }
                // Schedule the next refresh after a certain delay
                handler.postDelayed(this, 2000) // Refresh every 1 second
            }
        }

        // Start the initial refresh
        handler.post(refreshRunnable)
    }

    private fun initServices() {
        //Why??
        serviceIntent = Intent(this, vehicleMonitorService)
        startService(serviceIntent)
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)
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
    private fun toast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop, unbind the sevice & remove the refresh runnable callbacks when the activity is destroyed
        handler.removeCallbacks(refreshRunnable)
        if (isBound) {
            boundService.stopService(serviceIntent)
            unbindService(connection)
            isBound = false
        }
    }
}