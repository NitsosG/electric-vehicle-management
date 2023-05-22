package aueb.msc.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(@PrimaryKey val code: String, val name: String)
