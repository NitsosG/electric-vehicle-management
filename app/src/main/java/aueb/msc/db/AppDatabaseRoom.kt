package aueb.msc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import aueb.msc.model.Brand
import aueb.msc.model.Location
import aueb.msc.model.Model
import aueb.msc.model.Profile

@Database(entities = [Brand::class, Model::class, Profile::class], version = 2)
abstract class AppDatabaseRoom: RoomDatabase() {

    abstract fun roomDao(): RoomDAO

    companion object {
        private var instance: AppDatabaseRoom? = null

        fun getAppDatabase(context: Context): AppDatabaseRoom? {
            if (instance == null){
                synchronized(AppDatabaseRoom::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                                                    AppDatabaseRoom::class.java,
                                                    "RoomDBManager").allowMainThreadQueries().
                                                    fallbackToDestructiveMigration().
                                                    build()
                }
            }
            return instance
        }

    }

}