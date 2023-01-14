package id.samai.mymedcure.activities

import android.Manifest
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.fxn.BubbleTabBar
import com.fxn.OnBubbleClickListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import id.samai.mymedcure.R
import id.samai.mymedcure.adapters.ViewPagerAdapter
import id.samai.mymedcure.extensions.config
import id.samai.mymedcure.extensions.getNextNotificationSchedule
import id.samai.mymedcure.recievers.BootCompleteReceiver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import kotlinx.android.synthetic.main.content.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawerLayout: DrawerLayout
    lateinit var receiver: BootCompleteReceiver
    private lateinit var auth: FirebaseAuth

//android:background="#e1f5fe" for app_bar_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)
        //title = "My Medicure"
        val title = findViewById<TextView>(R.id.title2)
        title2.text = "My Medicure"
        if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this@MainActivity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )) {
                ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 101
                )
            } else {
                ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 101
                )
            }
        }


        receiver= BootCompleteReceiver()
        IntentFilter(Intent.ACTION_DATE_CHANGED).also {
            // registering the receiver
            // it parameter which is passed in  registerReceiver() function
            // is the intent filter that we have just created
            registerReceiver(receiver, it)
        }

        this.cacheDir.deleteRecursively()

        setSupportActionBar(toolbar)

        val texttool = findViewById<ImageView>(R.id.qricon)

texttool.setOnClickListener { showDialog() }
        val login = findViewById<ImageView>(R.id.firebaseloginicon)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        if(currentUser != null){
          login.setImageResource(R.drawable.login_user)
        }
        else{
            login.setImageResource(R.drawable.firebaseligin)

        }



        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initFragments()
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.about -> {
                val intent = Intent(this, add_medical_his::class.java)
                startActivity(intent)
            }
            R.id.addSubject -> {
                val intent = Intent(this, Medicine_1::class.java)
                startActivity(intent)
            }
            android.R.id.home -> finish()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun initFragments() {


        /** Binding view_pager and TabLayout **/
        val viewpager2 = findViewById<ViewPager2>(R.id.view_pager)
        val bubbleTabBar = findViewById<BubbleTabBar>(R.id.bubbleTabBar)
        viewpager2.adapter = ViewPagerAdapter(this)

        bubbleTabBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.blogs -> viewpager2.currentItem = 0
                    R.id.morninglist -> viewpager2.currentItem = 1
                    R.id.afternnonlist -> viewpager2.currentItem = 2
                    R.id.eveninglist -> viewpager2.currentItem = 3
                    R.id.nightlist -> viewpager2.currentItem = 4
                    R.id.totallist -> viewpager2.currentItem = 5


                }

            }
        })
        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bubbleTabBar.setSelected(position, false)
            }
        })

var ink = 1
        val c = Calendar.getInstance()
        val timeOfDay = c[Calendar.HOUR_OF_DAY]

        if (timeOfDay >= 0 && timeOfDay < 12) {
            Handler().postDelayed({
                viewpager2.setCurrentItem(1, false)
            }, 100)

        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            Handler().postDelayed({
                viewpager2.setCurrentItem(2, false)
            }, 100)

        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            Handler().postDelayed({
                viewpager2.setCurrentItem(3, false)
            }, 100)

        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            Handler().postDelayed({
                viewpager2.setCurrentItem(4, false)
            }, 100)

        }
        else{
            Handler().postDelayed({
                viewpager2.setCurrentItem(5, false)
            }, 100)

        }




    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.totalsubjectys -> {
                val intent = Intent(this, All_tablets_activity::class.java)
                startActivity(intent)
            }
            R.id.addSubject -> {
                val intent = Intent(this, Medicine_1::class.java)
                startActivity(intent)
            }
            R.id.exportdatabase -> {
                val intent = Intent(this, share_medical_his::class.java)
                startActivity(intent)
            }
            R.id.med_his -> {
                val intent = Intent(this, medical_health::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(this, about::class.java)
                startActivity(intent)
            }

            R.id.import_medicine -> {
                val intent = Intent(this, add_medical_csv::class.java)
                startActivity(intent)
            }

            R.id.importdatabase -> {
                val intent = Intent(this, import_medical_his::class.java)
                startActivity(intent)
            }
            R.id.report -> {
                val intent = Intent(this, view_tab_graph::class.java)
                startActivity(intent)
            }
            R.id.downsam->{
                val intent = Intent(this, downloadview::class.java)
                startActivity(intent)
            }

            R.id.regis->{
                val intent = Intent(this, View_profile::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    private fun sendintentsuggestion() {
        val to = "samba24052001@gmail.com"
        val subject = "User Feedback for Android "
        val body = "Suggestion on app : "

        val uriBuilder = StringBuilder("mailto:" + Uri.encode(to))
        uriBuilder.append("?subject=" + Uri.encode(subject))
        uriBuilder.append("&body=" + Uri.encode(body))
        val uriString = uriBuilder.toString()

        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(uriString))

        try {
            startActivity(intent, null)
        } catch (e: Exception) {
            Log.e("LOG_TAG", e.localizedMessage)

            // If there is no email client application, than show error message for the user.
            if (e is ActivityNotFoundException) {
                Toast.makeText(
                        applicationContext,
                        "No application can handle this request. Please install an email client app.",
                        Toast.LENGTH_LONG
                ).show()
            }
        }


    }
    private fun setupDatePicker() {

        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.apply {
                set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
                set(Calendar.MONTH, calendar.get(Calendar.MONTH))
                set(Calendar.YEAR, calendar.get(Calendar.YEAR))
            }
        }

        DatePickerDialog(this, R.style.DialogTheme, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

    }
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.setCancelable(false)
        dialog.setContentView(R.layout.qrcode)
val qr = dialog.findViewById<ImageView>(R.id.qrcode)

        val uri = "https://firebasestorage.googleapis.com/v0/b/fir-recyclerview-b8013.appspot.com/o/samba.jpeg?alt=media&token=ddbd77bd-1a7d-4494-86c8-94a9d915f594"

        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(uri, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        qr.setImageBitmap(bitmap)

        val yesBtn = dialog.findViewById(R.id.ok_qr) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }
    private fun sendintent() {
        val to = "samba24052001@gmail.com"
        val subject = "User Feedback for Android "
        val body = "Report on bug "

        val uriBuilder = StringBuilder("mailto:" + Uri.encode(to))
        uriBuilder.append("?subject=" + Uri.encode(subject))
        uriBuilder.append("&body=" + Uri.encode(body))
        val uriString = uriBuilder.toString()

        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(uriString))

        try {
            startActivity(intent, null)
        } catch (e: Exception) {
            Log.e("LOG_TAG", e.localizedMessage)

            // If there is no email client application, than show error message for the user.
            if (e is ActivityNotFoundException) {
                Toast.makeText(
                        applicationContext,
                        "No application can handle this request. Please install an email client app.",
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    override fun onDestroy() {

        if (config.notificationScheduleStatus)
            getNextNotificationSchedule()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        val login = findViewById<ImageView>(R.id.firebaseloginicon)

        val currentUser = auth.currentUser
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        if(currentUser != null){
            login.setImageResource(R.drawable.login_user)
        }
        else{
            login.setImageResource(R.drawable.firebaseligin)

        }

    }


}
