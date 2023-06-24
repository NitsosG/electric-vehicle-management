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
        roomDAO.addBrand(Brand("TOYOTA", "Toyota"))

        // Models
        // Tesla
        roomDAO.addModel(Model("MODEL_1", "Model 1", "TESLA"))
        roomDAO.addModel(Model("MODEL_3", "Model 3", "TESLA"))
        roomDAO.addModel(Model("MODEL_X", "Model X", "TESLA"))
        // Ford
        roomDAO.addModel(Model("FOCUS_E", "Focus Electric", "FORD"))
        roomDAO.addModel(Model("KA_E", "Ka Electric", "FORD"))
        roomDAO.addModel(Model("TIGRA_E", "Tigra Electric", "FORD"))
        // Toyota
        roomDAO.addModel(Model("RAV_4", "RAV 4", "TOYOTA"))


        // Profiles
        roomDAO.addProfile(Profile("James Bond", "007", "MODEL_X"))
        roomDAO.addProfile(Profile("Batman", "Bat 001", "KA_E"))

    }
}
