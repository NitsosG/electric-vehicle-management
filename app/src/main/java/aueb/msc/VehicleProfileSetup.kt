package aueb.msc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import aueb.msc.db.AppDatabaseRoom
import aueb.msc.model.Brand
import aueb.msc.model.Profile

class VehicleProfileSetup : AppCompatActivity() {

    private val activity = this@VehicleProfileSetup
    private lateinit var database: AppDatabaseRoom
    private lateinit var brands : MutableList<Brand>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_profile_setup)
        initObjects()

        // Create an adapter to populate the dropdown list with the items
        val brandAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brands)
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Get a reference to the Spinner in the layout
        val brandSpinner = findViewById<Spinner>(R.id.brandSpinner)

        // Set the adapter on the Spinner
        brandSpinner.adapter = brandAdapter

        // Get a reference to the Spinner in the layout
        val modelSpinner = findViewById<Spinner>(R.id.modelSpinner)

        // Set a listener to handle item selection events
        brandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val models = database.roomDao().getBrandModels(brands[position].code)
                modelSpinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, models)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Submit button
        val vehicleProfileSubmitButton = findViewById<Button>(R.id.vehicle_profile_submit_button)
        vehicleProfileSubmitButton.setOnClickListener(){
            val profileName = findViewById<EditText>(R.id.profileNameValue).text.toString()
            val plateNumber = findViewById<EditText>(R.id.plateNumberValue).text.toString()
            val modelSelected = modelSpinner.selectedItem.toString()
            database.roomDao().addProfile(Profile(profileName, plateNumber, modelSelected))
            // Redirect to Profiles activity
            val intent = Intent(activity, ProfileSelection::class.java)
            startActivity(intent)
        }
    }

    private fun initObjects() {
        database = AppDatabaseRoom.getAppDatabase(this)!!
        val result : List<Brand> = database.roomDao().getBrands()
        brands = ArrayList()
        brands.addAll(result)
    }
}