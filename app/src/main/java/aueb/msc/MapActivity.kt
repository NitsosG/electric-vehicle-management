package aueb.msc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import aueb.msc.service.getStations
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap) {
        val initialLocation = LatLng(37.983810, 23.727539)

        map.mapType = GoogleMap.MAP_TYPE_NORMAL;
        map.uiSettings.isZoomControlsEnabled = true;
        map.uiSettings.isZoomGesturesEnabled = true;
        map.uiSettings.isCompassEnabled = true;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 7f))
        val list = getStations()
        for (l in list){
            val marker = MarkerOptions().position(l.latLng)
                .title(l.title)
                .snippet(l.description)
            if (l.available) {
                marker.icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_GREEN))
            } else{
                marker.icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_RED))
            }
            if (l.type == "service"){
                marker.icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_AZURE))
            }
            map.addMarker(marker)

        }
        map.setInfoWindowAdapter(CustomInfoWindow(this))
    }


}
