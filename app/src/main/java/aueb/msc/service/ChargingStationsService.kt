package aueb.msc.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import aueb.msc.model.ChargingStation

class ChargingStationsService : Service() {

    private lateinit var chargingStationsService: List<ChargingStation>
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate(){
        super.onCreate()
        //chargingStationsService.
    }

}