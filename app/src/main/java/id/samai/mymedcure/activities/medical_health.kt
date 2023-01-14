package id.samai.mymedcure.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import id.samai.mymedcure.R
import id.samai.mymedcure.databinding.ActivityAllTabletsActivityBinding
import id.samai.mymedcure.databinding.ActivityMedicalHealthBinding
import kotlinx.android.synthetic.main.activity_medical_health.view.*

class medical_health : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMedicalHealthBinding>(this, R.layout.activity_medical_health)
        supportActionBar?.title = "medical observations"

        drawerLayout = binding.drawerLayout2
        //ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);

        val navController = this.findNavController(R.id.myNavHostFragment2)



        NavigationUI.setupWithNavController(binding.navView2, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment2)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


}