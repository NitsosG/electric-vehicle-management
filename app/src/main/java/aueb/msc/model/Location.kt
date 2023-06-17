package aueb.msc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(@PrimaryKey val uidName:String, val lat: Double, val long: Double,
                    val address:String, val type:String)

