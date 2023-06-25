package aueb.msc.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Model::class,
        parentColumns = ["code"],
        childColumns = ["modelCode"],
    )]
)
data class Profile(@PrimaryKey val name:String, val plateNumber:String, val modelCode:String, val imageUri:String?)
