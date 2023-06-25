package aueb.msc

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import aueb.msc.service.getStations
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var userLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(map: GoogleMap) {

        map.mapType = GoogleMap.MAP_TYPE_NORMAL;
        map.uiSettings.isZoomControlsEnabled = true;
        map.uiSettings.isZoomGesturesEnabled = true;
        map.uiSettings.isCompassEnabled = true;
        getUserLocation(map)
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

    private fun getUserLocation(map: GoogleMap?) {
        map?.let {
            googleMap = it

            // Check location permission
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request location permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            } else {
                // Enable user location and move camera to current location
                googleMap.isMyLocationEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true

                // Get user's last known location and move camera
                val fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            userLocation = LatLng(location.latitude, location.longitude)
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,
                                12f))
                        }
                    }

            }
        }
    }




}
