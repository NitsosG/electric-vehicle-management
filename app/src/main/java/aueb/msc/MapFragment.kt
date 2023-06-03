package aueb.msc

import aueb.msc.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize view
        val view: View = inflater.inflate(R.layout.fragment_map, container, false)

        // Initialize map fragment
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?

        // Async map
        supportMapFragment!!.getMapAsync(this)
        // Return view
        return view
    }

    override fun onMapReady(p0: GoogleMap) {
        val casinoLoutraki = LatLng(37.9609727,22.9695429)
        val shell486 = LatLng(38.8995907,22.4912022)
        val bpNeaSmyrni = LatLng(37.930479,23.709785)
        val bpAspropyrgos = LatLng(38.035039,23.594554)
        val tavernaLavraki = LatLng(37.738524,23.905168)
        val ekoKifissia = LatLng(38.077454,23.812966)
        val shell476 = LatLng(37.9619568,23.5872516)
        val beautycomPortoRafti = LatLng(37.888898,24.006862)
        val shell319 = LatLng(38.4585676,22.8974772)

        val irisElectronics = LatLng(36.3999814, 25.4379338)
        val electropolis = LatLng(38.01774, 23.69246)
        val ekoGlyfada = LatLng(37.87016, 23.73859)

        p0.addMarker(
            MarkerOptions()
                .position(casinoLoutraki)
                .title("Club Hotel Casino - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(shell486)
                .title("Shell 486 - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(bpNeaSmyrni)
                .title("BP Nea Smyrni - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(bpAspropyrgos)
                .title("BP Aspropyrgos - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(tavernaLavraki)
                .title("Taverna Lavraki - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(ekoKifissia)
                .title("EKO Kifissia - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(shell476)
                .title("Shell 476 - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(beautycomPortoRafti)
                .title("Beautycom Porto-Rafti - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(shell319)
                .title("Shell 319 - EV Charger")
        )
        p0.addMarker(
            MarkerOptions()
                .position(irisElectronics)
                .title("Συνεργείο 1")
                .icon(
                    BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )
        p0.addMarker(
            MarkerOptions()
                .position(electropolis)
                .title("Συνεργείο 2")
                .icon(
                    BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )
        p0.addMarker(
            MarkerOptions()
                .position(ekoGlyfada)
                .title("Συνεργείο 3")
                .icon(
                    BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )

    }
}