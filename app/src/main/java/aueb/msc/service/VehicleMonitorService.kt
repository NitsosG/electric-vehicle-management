package aueb.msc.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class VehicleMonitorService : Service() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private val binder = MyBinder()
    private var data = VehicleMonitorData(BigDecimal(100),1000,150)


    inner class MyBinder : Binder() {
        fun getService(): VehicleMonitorService = this@VehicleMonitorService
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        toast("Vehicle monitor service started.")
        // Do a periodic task
        mHandler = Handler(Looper.getMainLooper())
        mRunnable = Runnable { monitorVehicleData() }
        mHandler.post(mRunnable)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Vehicle Service destroyed.")
        mHandler.removeCallbacks(mRunnable)
    }

    // Custom method to do a task
    private fun monitorVehicleData() {
        if(data.batteryLevel > BigDecimal.ZERO) {
            var weight = Random().nextInt(10);
            var level =
                data.batteryLevel.minus(BigDecimal(0.05).multiply(BigDecimal(weight)))
                    .setScale(1, RoundingMode.DOWN)

            if(level < BigDecimal.ZERO){
                data.batteryLevel = BigDecimal.ZERO
            }else{
                data.batteryLevel = level
            }
            if (weight > 7) {
                data.totalKilometers = data.totalKilometers + 1
                data.remainingKilometers = data.remainingKilometers - 1
            }
            mHandler.postDelayed(mRunnable, 2000)
        }else{
            data.batteryLevel = BigDecimal.ZERO;
            data.remainingKilometers = 0
        }
    }


    private fun toast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT)
    }

    fun vehicleMonitorData() : VehicleMonitorData{
        return data;
    }
}