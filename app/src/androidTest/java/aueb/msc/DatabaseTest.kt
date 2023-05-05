package aueb.msc

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import aueb.msc.db.DatabaseHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var db: SQLiteDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        // Initialize test data here
    }

    @After
    fun tearDown() {
        dbHelper.close()
    }

    @Test
    fun testMyDatabase() {
        // Run database tests here
    }

}