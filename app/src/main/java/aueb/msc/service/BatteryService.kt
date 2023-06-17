package aueb.msc.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import java.math.BigDecimal
import java.util.*

class BatteryService : Service() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private var level : BigDecimal = BigDecimal(100)
    private val binder = MyBinder()


    inner class MyBinder : Binder() {
        fun getService(): BatteryService = this@BatteryService
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        toast("Battery level service started.")
        // Do a periodic task
        mHandler = Handler(Looper.getMainLooper())
        mRunnable = Runnable { monitorVehicleData() }
        mHandler.post(mRunnable)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Battery Service destroyed.")
        mHandler.removeCallbacks(mRunnable)
    }

    // Custom method to do a task
    private fun monitorVehicleData() {
        level = level.minus(BigDecimal(0.1).multiply(BigDecimal(Random().nextInt(3))));
        mHandler.postDelayed(mRunnable, 2000)
    }


    private fun toast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT)
    }

     public fun getLevel() : BigDecimal{
        return level;
    }
}