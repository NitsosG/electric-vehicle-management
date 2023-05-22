package aueb.msc.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import aueb.msc.model.Brand
import aueb.msc.model.Model
import aueb.msc.model.Profile

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Brands
        db.execSQL("CREATE TABLE brand (brand_code TEXT PRIMARY KEY, brand_name TEXT)")
        db.execSQL("INSERT INTO brand (brand_code, brand_name) VALUES ('TESLA','Tesla')")
        db.execSQL("INSERT INTO brand (brand_code, brand_name) VALUES ('ZERO','Zero')")

        // Models
        db.execSQL("CREATE TABLE model (model_code TEXT PRIMARY KEY, model_name TEXT,brand_code TEXT, FOREIGN KEY (brand_code) REFERENCES brand(brand_code))")
        db.execSQL("INSERT INTO model (model_code, model_name, brand_code) VALUES ('MODEL_X','Model X', 'TESLA')")
        db.execSQL("INSERT INTO model (model_code, model_name, brand_code) VALUES ('FXE','FXE', 'ZERO')")
        db.execSQL("INSERT INTO model (model_code, model_name, brand_code) VALUES ('DSR','DSR', 'ZERO')")

        db.execSQL("CREATE TABLE profile (profile_name TEXT PRIMARY KEY, plate_number TEXT UNIQUE, model_code TEXT, FOREIGN KEY (model_code) REFERENCES model(model_code))")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS brand")
        db.execSQL("DROP TABLE IF EXISTS model")
        db.execSQL("DROP TABLE IF EXISTS profile")
        // call onCreate to recreate the table
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "electricVehicleManagement.db"
        private const val DATABASE_VERSION = 2
    }

    @SuppressLint("Range")
    fun getAllBrands(): List<Brand> {
        val columns =
            arrayOf(COLUMN_BRAND_CODE, COLUMN_BRAND_NAME)
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
                    code = cursor.getString(cursor.getColumnIndex(COLUMN_BRAND_CODE)),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_BRAND_NAME))
                )
                brands.add(brand)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return brands
    }

    @SuppressLint("Range")
    fun getBrandModels(brandCode: String): List<Model> {
        val columns =
            arrayOf(COLUMN_MODEL_CODE, COLUMN_MODEL_NAME, COLUMN_BRAND_CODE)
        // selection criteria
        val selection = "$COLUMN_BRAND_CODE = ?"
        // selection argument
        val selectionArgs = arrayOf(brandCode)
        // sorting orders
        val sortOrder = "$COLUMN_MODEL_NAME ASC"
        val models = ArrayList<Model>()
        val db = this.readableDatabase
        // query the user table
        val cursor = db.query(
            "model", //Table to query
            columns,            //columns to return
            selection,     //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val model = Model(
                    code = cursor.getString(cursor.getColumnIndex(COLUMN_MODEL_CODE)),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_MODEL_NAME)),
                    brandCode = cursor.getString(cursor.getColumnIndex(COLUMN_BRAND_CODE))
                )
                models.add(model)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return models
    }

    fun addProfile(profile: Profile) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PROFILE_NAME, profile.name)
        values.put(COLUMN_PLATE_NUMBER, profile.plateNumber)
        values.put(COLUMN_MODEL_CODE, profile.modelCode)
        // Inserting Row
        db.insert("Profile", null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllProfileNames() : List<Profile> {
        val columns = arrayOf(COLUMN_PROFILE_NAME, COLUMN_PLATE_NUMBER, COLUMN_MODEL_CODE)
        val sortOrder = "$COLUMN_PROFILE_NAME ASC"
        val profiles = ArrayList<Profile>()
        val db = this.readableDatabase;
        val cursor = db.query(
            "profile", //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val profile = Profile(
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE_NAME)),
                    plateNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PLATE_NUMBER)),
                    modelCode = cursor.getString(cursor.getColumnIndex(COLUMN_MODEL_CODE))
                )
                profiles.add(profile)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return profiles;
    }

    private val COLUMN_BRAND_CODE = "brand_code"
    private val COLUMN_BRAND_NAME = "brand_name"
    private val COLUMN_MODEL_CODE = "model_code"
    private val COLUMN_MODEL_NAME = "model_name"
    private val COLUMN_PROFILE_NAME = "profile_name"
    private val COLUMN_PLATE_NUMBER = "plate_number"

}
