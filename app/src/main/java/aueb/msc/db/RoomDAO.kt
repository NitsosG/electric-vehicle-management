package aueb.msc.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import aueb.msc.model.Brand
import aueb.msc.model.Location
import aueb.msc.model.Model
import aueb.msc.model.Profile

@Dao
interface RoomDAO {
    @Insert
    fun addProfile(profile: Profile)

    @Insert
    fun addBrand(brad: Brand)

    @Insert
    fun addModel(model: Model)

//    @Insert
//    fun addLocation(location: Location)

    @Query("SELECT * FROM Profile")
    fun getProfiles() : List<Profile>

//    @Query("SELECT * FROM Location")
//    fun getLocations() : List<Location>

    @Query("SELECT * FROM Profile WHERE name = :profileName")
    fun getProfile(profileName : String) : Profile

    @Query("SELECT * FROM Model  WHERE brandCode = :brandCode")
    fun getBrandModels(brandCode : String) : List<Model>

    @Query("SELECT * FROM Model  WHERE code = :modelCode")
    fun getModel(modelCode : String) : Model

    @Query("SELECT * FROM Brand WHERE code = :brandCode")
    fun getBrand(brandCode : String): Brand

    @Query("SELECT * FROM Brand")
    fun getBrands(): List<Brand>

    @Query("DELETE FROM Brand")
    fun truncateBrand()

    @Query("DELETE FROM Model")
    fun truncateModel()

    @Query("DELETE FROM Profile")
    fun truncateProfile()

//    @Query("DELETE FROM Location")
//    fun truncateLocation()
}