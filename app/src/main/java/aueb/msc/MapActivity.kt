package aueb.msc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Initialize fragment
        val fragment: Fragment = MapFragment()

        // Open fragment
        supportFragmentManager
            .beginTransaction().replace(R.id.frame_layout, fragment)
            .commit()

    }


}
