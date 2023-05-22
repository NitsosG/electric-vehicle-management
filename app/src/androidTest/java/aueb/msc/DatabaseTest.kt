package aueb.msc

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import aueb.msc.db.AppDatabaseRoom
import aueb.msc.db.RoomDAO
import aueb.msc.model.Brand
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {

    private lateinit var db: AppDatabaseRoom
    private lateinit var roomDao: RoomDAO

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabaseRoom::class.java)
            .allowMainThreadQueries()
            .build()
        roomDao = db.roomDao()
    }

    @Test
    fun addBrand(){
        val brand = Brand("TESLA","Tesla")
        roomDao.addBrand(brand)
    }

    @After
    fun teardown() {
        db.close()
    }
}