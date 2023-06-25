package aueb.msc.service

import com.google.android.gms.maps.model.LatLng
import java.math.BigDecimal

data class ChargingStationData(var title: String, var description: String, var latLng: LatLng,
                               var available: Boolean, var type: String)
