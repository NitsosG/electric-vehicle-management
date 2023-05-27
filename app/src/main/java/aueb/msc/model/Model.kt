package aueb.msc.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Brand::class,
        parentColumns = ["code"],
        childColumns = ["brandCode"],
    )]
)
data class Model(@PrimaryKey val code: String , val name: String, val brandCode: String)
