package aueb.msc.service

import com.google.android.gms.maps.model.LatLng
import java.util.Random

fun getStations(): List<ChargingStationData> {
    val list = mutableListOf<ChargingStationData>()
    list.add(0, ChargingStationData("Club Hotel Casino - EV Charger",
        "Ποσειδώνος 48, Λουτράκι 203 00",
        LatLng(37.9609727,22.9695429),
        true,
        "charger"
    ))
    list.add(1, ChargingStationData("Shell 486 - EV Charger",
        "Λεωφ. Κωνσταντινουπόλεως 14, Περιστέρι 121 33",
        LatLng(38.8995907,22.4912022),
        true,
        "charger"
    ))
    list.add(2, ChargingStationData("BP Nea Smyrni - EV Charger",
        "Λεωφόρος Νάτο, Ασπρόπυργος - Πηγάδι, 19300, ΑΤΤΙΚΗΣ",
        LatLng(37.930479,23.709785),
        true,
        "charger"
    ))
    list.add(3, ChargingStationData("BP Aspropyrgos - EV Charger",
        "Λεωφόρος Αθηνών Σουνίου 46o χιλ, Σαρωνίδα 190 13",
        LatLng(38.035039,23.594554),
        true,
        "charger"
    ))
    list.add(4, ChargingStationData("Taverna Lavraki - EV Charger",
        "Λεωφ. Κηφισίας 264, Κηφισιά 145 62",
        LatLng(37.738524,23.905168),
        true,
        "charger"
    ))
    list.add(5, ChargingStationData("EKO Kifissia - EV Charger",
        "Χαλκίδος 5, Αθήνα 111 43",
        LatLng(38.077454,23.812966),
        true,
        "charger"
    ))
    list.add(6, ChargingStationData("Shell 476 - EV Charger",
        "Λεωφ. Γ. Γρέγου 10, Πόρτο Ράφτη 190 03",
        LatLng(37.9619568,23.5872516),
        true,
        "charger"
    ))
    list.add(7, ChargingStationData("Beautycom Porto-Rafti - EV Charger",
        "-",
        LatLng(37.888898,24.006862),
        true,
        "charger"
    ))
    list.add(8, ChargingStationData("Συνεργείο 1",
        "Θήρα 847 00",
        LatLng(36.3999814, 25.4379338),
        true,
        "service"
    ))
    list.add(9, ChargingStationData("Shell 319 - EV Charger",
        "Χλμ, ΕΟ Λιβαδειάς Λαμίας 3Ο, Λιβαδειά 321 00\"",
        LatLng(38.4585676,22.8974772),
        true,
        "charger"
    ))
    list.add(10, ChargingStationData("Συνεργείο 2",
        "Θηβών 453, Αιγάλεω 122 43",
        LatLng(38.01774, 23.69246),
        true,
        "service"
    ))
    list.add(11, ChargingStationData("Συνεργείο 3",
        "Πανόπης 2, Γλυφάδα 166 74",
        LatLng(37.87016, 23.73859),
        true,
        "service"
    ))

    for (l in list){
        val weight = Random().nextInt(10);
        if (weight < 4 && l.type == "charger"){
            l.available = false
        }
    }
    return list
}
