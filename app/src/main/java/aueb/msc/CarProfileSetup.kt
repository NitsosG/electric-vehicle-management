package aueb.msc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import aueb.msc.db.DatabaseHelper

class CarProfileSetup : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private val activity = this@CarProfileSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_profile_setup)
        databaseHelper = DatabaseHelper(activity)

        // Define the list of items to display in the dropdown list
        val brands = databaseHelper.getAllBrands()

        // Create an adapter to populate the dropdown list with the items
        val brandAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, brands)
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
                val selectedOption = brands[position]
                when(selectedOption.name){
                    "Tesla" -> {
                        val models = listOf("Model X", "Model 3")
                        modelSpinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, models)
                    }
                    "Ford" -> {
                        val models = listOf("E-Focus", "E-Puma")
                        modelSpinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, models)
                    }
                    "Renault" -> {
                        val models = listOf("E-Clio", "E-Twingo")
                        modelSpinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, models)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }
}