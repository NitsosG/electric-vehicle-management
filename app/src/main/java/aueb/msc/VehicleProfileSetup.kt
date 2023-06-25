package aueb.msc

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
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
    private val CAMERA_PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 1001
    private var imageUri: Uri? = null
    private var imageView: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_profile_setup)
        database = AppDatabaseRoom.getAppDatabase(this)!!
        initObjects()
        initCamera()
    }

    private fun initCamera() {
        val vehicleProfileSubmitButton = findViewById<Button>(R.id.take_photo)

        vehicleProfileSubmitButton.setOnClickListener(){
            val permissionGranted = requestCameraPermission()
            if (permissionGranted) {
                // Open the camera interface
                openCameraInterface()
            }
        }
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
            var successfulValidation = true
            var validationMessage = ""
            val profileName = findViewById<EditText>(R.id.profileNameValue).text.toString()
            val plateNumber = findViewById<EditText>(R.id.plateNumberValue).text.toString()
            val modelSelected = findModel(modelSpinner.selectedItem.toString()).code
            if(plateNumber.isNullOrEmpty()){
                validationMessage = "Plate number can not be empty"
                successfulValidation = false
                Toast.makeText(this, "Validation successful", Toast.LENGTH_LONG).show()

            }
            try {
                if(successfulValidation){
                    database.roomDao().addProfile(Profile(profileName, plateNumber, modelSelected, imageUri?.toString()))
                }
            }catch (e : SQLiteConstraintException){
                validationMessage = "A profile with the same name exists. Use a different name"
                Toast.makeText(this, "Validation successful", Toast.LENGTH_LONG).show()
                successfulValidation = false
            }
            if(successfulValidation){
                // Redirect to Profiles activity
                val intent = Intent(activity, ProfileSelection::class.java)
                startActivity(intent)
            }else{
                findViewById<TextView>(R.id.validation_text_view).text = validationMessage;
            }
        }

    }

    private fun findModel(name : String): Model{
        return models.stream().filter{ m -> m.name == name}.findFirst().get();
    }

    private fun findBrand(name : String): Brand{
        return brands.stream().filter{ m -> m.name == name}.findFirst().get();
    }

    private fun requestCameraPermission(): Boolean {
        var permissionGranted = false

        // If system os is Marshmallow or Above, we need to request runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val cameraPermissionNotGranted = ActivityCompat.checkSelfPermission(this , android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
            if (cameraPermissionNotGranted){
                val permission = arrayOf(android.Manifest.permission.CAMERA)

                // Display permission dialog
                requestPermissions(permission, CAMERA_PERMISSION_CODE)
            }
            else{
                // Permission already granted
                permissionGranted = true
            }
        }
        else{
            // Android version earlier than M -> no need to request permission
            permissionGranted = true
        }

        return permissionGranted
    }

    // Handle Allow or Deny response from the permission dialog
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode === CAMERA_PERMISSION_CODE) {
            if (grantResults.size === 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Permission was granted
                openCameraInterface()
            }
            else{
                // Permission was denied
                showAlert("Camera permission was denied. Unable to take a picture.");
            }
        }
    }

    private fun openCameraInterface() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Take picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Description")
        imageUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        // Create camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        // Launch intent
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Callback from camera intent
        if (resultCode == Activity.RESULT_OK){
            // Set image captured to image view
            imageView?.setImageURI(imageUri)
        }
        else {
            // Failed to take picture
            showAlert("Failed to take camera picture")
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)

        val dialog = builder.create()
        dialog.show()
    }

}