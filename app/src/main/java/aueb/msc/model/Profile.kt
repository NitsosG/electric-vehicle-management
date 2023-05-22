package aueb.msc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(@PrimaryKey val name:String, val plateNumber:String, val modelCode:String)
