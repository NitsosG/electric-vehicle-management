package aueb.msc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import aueb.msc.db.AppDatabaseRoom
import aueb.msc.model.Brand
import aueb.msc.model.Model
import aueb.msc.model.Profile
import java.util.stream.Collectors

class VehicleProfileSetup : AppCompatActivity() {

    private val activity = this@VehicleProfileSetup
    private lateinit var database: AppDatabaseRoom
    private lateinit var brands : MutableList<Brand>
    private lateinit var models : List<Model>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_profile_setup)
        database = AppDatabaseRoom.getAppDatabase(this)!!
        initObjects()
    }

    private fun initObjects() {
        val result : List<Brand> = database.roomDao().getBrands()
        brands = ArrayList()
        brands.addAll(result)

        val brandNames = brands.stream().map{ b -> b.name}.collect(Collectors.toList())

        // Create an adapter to populate the dropdown list with the items
        val brandAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brandNames)
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
                val brandCode = findBrand(brandNames[position]).code
                models = database.roomDao().getBrandModels(brandCode)
                val modelNames = models.stream().map{ m -> m.name }.collect(Collectors.toList())
                modelSpinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, modelNames)
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
            val modelSelected = findModel(modelSpinner.selectedItem.toString()).code
            database.roomDao().addProfile(Profile(profileName, plateNumber, modelSelected))
            // Redirect to Profiles activity
            val intent = Intent(activity, ProfileSelection::class.java)
            startActivity(intent)
        }

    }

    private fun findModel(name : String): Model{
        return models.stream().filter{ m -> m.name == name}.findFirst().get();
    }

    private fun findBrand(name : String): Brand{
        return brands.stream().filter{ m -> m.name == name}.findFirst().get();
    }
}