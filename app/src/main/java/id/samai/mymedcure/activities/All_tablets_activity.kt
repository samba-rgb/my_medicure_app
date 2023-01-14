package id.samai.mymedcure.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.youtube.player.internal.g
import id.samai.mymedcure.R
import id.samai.mymedcure.databinding.ActivityAllTabletsActivityBinding

class All_tablets_activity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityAllTabletsActivityBinding>(this, R.layout.activity_all_tablets_activity)
        supportActionBar?.title = "All Medicines"
        supportActionBar?.setBackgroundDrawable(getResources().getDrawable(R.drawable.tool1))
        drawerLayout = binding.drawerLayout
        //ActivityCompat.requestPermisetSupportActionBar()?.setBackgroundResourcesions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);

        val navController = this.findNavController(R.id.myNavHostFragment)



        NavigationUI.setupWithNavController(binding.navView, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }



}