package aueb.msc.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import aueb.msc.model.Brand
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

    @Query("SELECT * FROM Profile")
    fun getProfiles() : List<Profile>

    @Query("SELECT * FROM Model  WHERE brandCode = :brandCode")
    fun getBrandModels(brandCode : String) : List<Model>

    @Query("SELECT * FROM Brand")
    fun getBrands(): List<Brand>


    @Query("DELETE FROM Brand")
    fun truncateBrand()

    @Query("DELETE FROM Model")
    fun truncateModel()

    @Query("DELETE FROM Profile")
    fun truncateProfile()
}