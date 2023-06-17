package aueb.msc

import android.app.Application
import aueb.msc.db.AppDatabaseRoom
import aueb.msc.db.RoomDAO
import aueb.msc.model.Brand
import aueb.msc.model.Location
import aueb.msc.model.Model
import aueb.msc.model.Profile
import com.google.android.gms.maps.model.LatLng

class MyApp : Application() {
    lateinit var database: AppDatabaseRoom
    lateinit var roomDAO: RoomDAO

    override fun onCreate() {
        super.onCreate()
        database = AppDatabaseRoom.getAppDatabase(this)!!
        // Clear database
        roomDAO = database.roomDao()
        roomDAO.truncateProfile()
        roomDAO.truncateModel()
        roomDAO.truncateBrand()
//        roomDAO.truncateLocation()

        // Brands
        roomDAO.addBrand(Brand("TESLA", "Tesla"))
        roomDAO.addBrand(Brand("FORD", "Ford"))
        roomDAO.addBrand(Brand("RENAULT", "Renault"))

        // Models
        // Tesla
        roomDAO.addModel(Model("MODEL_1", "Model 1", "TESLA"))
        roomDAO.addModel(Model("MODEL_3", "Model 3", "TESLA"))
        roomDAO.addModel(Model("MODEL_X", "Model X", "TESLA"))
        // Ford
        roomDAO.addModel(Model("FOCUS_E", "Focus Electric", "FORD"))
        roomDAO.addModel(Model("KA_E", "Ka Electric", "FORD"))
        roomDAO.addModel(Model("TIGRA_E", "Tigra Electric", "FORD"))

        // Profiles
        roomDAO.addProfile(Profile("James Bond", "007", "MODEL_X"))
        roomDAO.addProfile(Profile("Batman", "Bat 001", "KA_E"))

        // Locations
//        roomDAO.addLocation(Location("Club Hotel Casino Loutraki", 38.8995907,
//            22.4912022, "Ποσειδώνος 48, Λουτράκι 203 00", "charger"))
//        roomDAO.addLocation(Location("Shell 486", 37.9609727,
//            22.9695429, "Λεωφ. Κωνσταντινουπόλεως 14, Περιστέρι 121 33", "charger"))
//        roomDAO.addLocation(Location("BP - Νέα Σμύρνη", 37.930479,
//            23.709785, " Egeou, Αγίου Πολυκάρπου 85, Νέα Σμύρνη 171 24", "charger"))
//        roomDAO.addLocation(Location("BP - Ασπροπυργος", 38.035039,
//            23.594554, "Λεωφόρος Νάτο, Ασπρόπυργος - Πηγάδι, 19300, ΑΤΤΙΚΗΣ",
//            "charger"))
//        roomDAO.addLocation(Location("Ταβέρνα \"Λαβράκι\"", 37.738524,
//            23.905168, "Λεωφόρος Αθηνών Σουνίου 46o χιλ, Σαρωνίδα 190 13", "charger"))
//        roomDAO.addLocation(Location("ΕΚΟ Κηφισιά", 38.077454,
//            23.812966, " Λεωφ. Κηφισίας 264, Κηφισιά 145 62", "charger"))
//        roomDAO.addLocation(Location("Shell 476", 37.9619568,
//            23.5872516, "Χαλκίδος 5, Αθήνα 111 43", "charger"))
//        roomDAO.addLocation(Location("Beautycom - Πόρτο Ράφτη", 37.888898,
//            24.006862, " Λεωφ. Γ. Γρέγου 10, Πόρτο Ράφτη 190 03", "charger"))
//        roomDAO.addLocation(Location("Shell 319", 38.4585676,
//            22.8974772, "Χλμ, ΕΟ Λιβαδειάς Λαμίας 3Ο, Λιβαδειά 321 00", "charger"))
//
//        roomDAO.addLocation(Location("Συνεργείο 1", 36.3999814,
//            25.4379338, "Θήρα 847 00", "garage"))
//        roomDAO.addLocation(Location("Συνεργείο 2", 38.01774,
//            23.69246, "Θηβών 453, Αιγάλεω 122 43", "garage"))
//        roomDAO.addLocation(Location("Συνεργείο 3", 37.87016,
//            23.73859, "Πανόπης 2, Γλυφάδα 166 74", "garage"))
    }
}
