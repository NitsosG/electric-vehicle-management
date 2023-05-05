package aueb.msc.db

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import aueb.msc.model.Brand

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS brand (brand_id INTEGER PRIMARY KEY AUTOINCREMENT, brand_name TEXT)")
        db.execSQL("INSERT INTO BRAND (brand_name) VALUES ('Tesla')")
        db.execSQL("INSERT INTO BRAND (brand_name) VALUES ('Ford')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_NAME = "electricVehicleManagement.db"
        private const val DATABASE_VERSION = 1
    }

    @SuppressLint("Range")
    fun getAllBrands(): List<Brand>{
        val columns =
            arrayOf(COLUMN_BRAND_ID, COLUMN_BRAND_NAME)
        // sorting orders
        val sortOrder = "$COLUMN_BRAND_NAME ASC"
        val brands = ArrayList<Brand>()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(
            "brand", //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val brand = Brand(
                    id = cursor.getInt(cursor.getColumnIndex(COLUMN_BRAND_ID)),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_BRAND_NAME))
                )
                brands.add(brand)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return brands
    }

    private val COLUMN_BRAND_ID = "brand_id"
    private val COLUMN_BRAND_NAME = "brand_name"

}
