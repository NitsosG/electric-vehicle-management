//package aueb.msc
//
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.location.Location as aLocation
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import aueb.msc.model.Location
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.BitmapDescriptorFactory
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//
//class MapFragment : Fragment(), OnMapReadyCallback {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Initialize view
//        val view: View = inflater.inflate(R.layout.fragment_map, container, false)
//
//        // Initialize map fragment
//        val supportMapFragment =
//            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
//
//        // Async map
//        supportMapFragment!!.getMapAsync(this)
//        // Return view
//        return view
//    }
//
//    override fun onMapReady(p0: GoogleMap) {
//        val initialLocation = LatLng(37.983810, 23.727539)
//
//        p0.mapType = GoogleMap.MAP_TYPE_NORMAL;
//        p0.uiSettings.setZoomControlsEnabled(true);
//        p0.uiSettings.setZoomGesturesEnabled(true);
//        p0.uiSettings.setCompassEnabled(true);
//        p0.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 7f))
//        val shell319 = LatLng(38.4585676,22.8974772)
//
//        val irisElectronics = LatLng(36.3999814, 25.4379338)
//        val electropolis = LatLng(38.01774, 23.69246)
//        val ekoGlyfada = LatLng(37.87016, 23.73859)
//
//        p0.addMarker(
//            MarkerOptions()
//                .position(casinoLoutraki)
//                .title("Club Hotel Casino - EV Charger")
//                .snippet("Ποσειδώνος 48, Λουτράκι 203 00")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(shell486)
//                .title("Shell 486 - EV Charger")
//                .snippet("Λεωφ. Κωνσταντινουπόλεως 14, Περιστέρι 121 33")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(bpNeaSmyrni)
//                .title("BP Nea Smyrni - EV Charger")
//                .snippet("Λεωφόρος Νάτο, Ασπρόπυργος - Πηγάδι, 19300, ΑΤΤΙΚΗΣ")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(bpAspropyrgos)
//                .title("BP Aspropyrgos - EV Charger")
//                .snippet("Λεωφόρος Αθηνών Σουνίου 46o χιλ, Σαρωνίδα 190 13")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(tavernaLavraki)
//                .title("Taverna Lavraki - EV Charger")
//                .snippet("Λεωφ. Κηφισίας 264, Κηφισιά 145 62")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(ekoKifissia)
//                .title("EKO Kifissia - EV Charger")
//                .snippet("Χαλκίδος 5, Αθήνα 111 43")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(shell476)
//                .title("Shell 476 - EV Charger")
//                .snippet("Λεωφ. Γ. Γρέγου 10, Πόρτο Ράφτη 190 03")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(beautycomPortoRafti)
//                .title("Beautycom Porto-Rafti - EV Charger")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(shell319)
//                .title("Shell 319 - EV Charger")
//                .snippet("Χλμ, ΕΟ Λιβαδειάς Λαμίας 3Ο, Λιβαδειά 321 00")
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(irisElectronics)
//                .title("Συνεργείο 1")
//                .snippet("Θήρα 847 00")
//                .icon(
//                    BitmapDescriptorFactory
//                    .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(electropolis)
//                .title("Συνεργείο 2")
//                .snippet("Θηβών 453, Αιγάλεω 122 43")
//                .icon(
//                    BitmapDescriptorFactory
//                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//        )
//        p0.addMarker(
//            MarkerOptions()
//                .position(ekoGlyfada)
//                .title("Συνεργείο 3")
//                .snippet("Πανόπης 2, Γλυφάδα 166 74")
//                .icon(
//                    BitmapDescriptorFactory
//                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//        )
//        p0.setInfoWindowAdapter(CustomInfoWindow(this))
//
//    }
//
//    fun calculateDistance(d1: LatLng, d2: LatLng){
//        aLocation.distanceBetween(d1, d2)
//    }
//
//}
