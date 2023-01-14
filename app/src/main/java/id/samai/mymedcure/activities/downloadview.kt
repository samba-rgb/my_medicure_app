package id.samai.mymedcure.activities

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.Gravity

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.from
import com.airbnb.lottie.LottieAnimationView
import com.cazaea.sweetalert.SweetAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ncorti.slidetoact.SlideToActView
import id.samai.mymedcure.R
import id.samai.mymedcure.extensions.config
import id.samai.mymedcure.extensions.dbHelper
import id.samai.mymedcure.extensions.getNextNotificationSchedule
import id.samai.mymedcure.models.User
import id.samai.mymedcure.models.medic
import id.samai.mymedcure.models.tab_expire_count
import id.samai.mymedcure.models.userdown
import mehdi.sakout.fancybuttons.FancyButton
import java.io.*
import java.util.concurrent.Executor


class  downloadview : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt

    private lateinit var mTextViewCsvResult: TextView
    private var id: Int? = null
    var nam : String = null.toString()
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
 var  scrollup : RelativeLayout?= null
    var frombottom: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloadview)

        supportActionBar?.title = "Importing"
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        val progrss =  findViewById<FrameLayout>(R.id.loadingFrame_download)
        progrss.visibility = View.INVISIBLE

        auth = FirebaseAuth.getInstance()
        var miserror = findViewById<RelativeLayout>(R.id.relative_error)
        miserror.visibility = View.INVISIBLE
        var menus = findViewById<RelativeLayout>(R.id.relative_down)
        var login = findViewById<FancyButton>(R.id.loginbtn2)
        login.visibility = View.INVISIBLE
        menus.visibility = View.INVISIBLE

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Not logged in?")
                    .setContentText("please login in ")
                    .setCustomImage(R.drawable.danger)

                    .setConfirmText("login")
                    .show()
            login.visibility = View.VISIBLE


        } else {
            startBiometricLock()
/*
            val uid = auth.currentUser?.uid
            // Toast.makeText(this@downloadview, uid, Toast.LENGTH_SHORT).show()
            var link = findViewById<TextView>(R.id.link)
            //var user = userdown("none",0,"none")

            databaseReference = FirebaseDatabase.getInstance().getReference("csv_data")
            var user = userdown("none", 0, "sam")
            val slide = findViewById<SlideToActView>(R.id.download_link)

            databaseReference.child(uid.toString())
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user2 = snapshot.getValue(userdown::class.java)!!

                            link.text = "Version : " + user2.version
                            var progress = findViewById<ProgressBar>(R.id.progressBar)
                            progress.visibility = View.INVISIBLE

                            var menus = findViewById<RelativeLayout>(R.id.relative_down)
                            menus.visibility = View.VISIBLE
                            menus!!.startAnimation(frombottom)

                            // Toast.makeText(getApplicationContext(), "morning_1", Toast.LENGTH_SHORT).show();
                            slide.onSlideCompleteListener =
                                    object : SlideToActView.OnSlideCompleteListener {
                                        override fun onSlideComplete(view: SlideToActView) {
                                            samsai3(user2.dblink)
                                        }
                                    }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@downloadview, "problem araised", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    })


 */
        }

        // mTextViewCsvResult = findViewById(R.id.textView_csvResult)


var retry = findViewById<ImageView>(R.id.image_retry)
        retry.setOnClickListener{
            startBiometricLock2()
        }


    }

    private fun openintent() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)    }

    fun samsai3(sam: String) {
        val uri =
            Uri.parse(sam)
        val r = DownloadManager.Request(uri)

// This put the download in the same Download dir the browser uses
        val exportDir = File(Environment.getExternalStorageDirectory(), "/Medicure/")
        if (!exportDir.exists()) {
            exportDir.mkdirs()
        }
// This put the download in the same Download dir the browser uses
       // r.setDestinationInExternalFilesDir(this, "Medicure", "upload.csv")
         //Toast.makeText(baseContext, Environment.DIRECTORY_DOWNLOADS, Toast.LENGTH_SHORT).show()

        r.setDestinationUri(Uri.fromFile(File(exportDir, "upload" + ".csv")))

        r.allowScanningByMediaScanner()

// Start download

// Start download
        val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(r)
        registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));





    }

    fun samsai2() {
        val uri =
            Uri.parse("https://drive.google.com/uc?export=download&format=csv&id=1Tmj0SGFStHNsxmnzZfrxs9WGflp40dE3")
        val r = DownloadManager.Request(uri)

// This put the download in the same Download dir the browser uses

// This put the download in the same Download dir the browser uses
        r.setDestinationInExternalPublicDir("Medicure", "upload.csv")

        r.allowScanningByMediaScanner()

// Start download

// Start download
        val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(r)
        registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));





    }

    fun onr() {

        try {
            val exportDir = File(Environment.getExternalStorageDirectory(), "/Medicure/")
            if (!exportDir.exists()) {
                exportDir.mkdirs()
            }
            val csvFile = FileInputStream(
                File(

                    //exportDir.absolutePath,
                    exportDir,
                    "upload.csv"
                )
            )


            val isr = InputStreamReader(csvFile)
            val buffer = BufferedReader(isr)

            var line = ""
            var iteration = 0
            while (true) {
                try {
                    if (buffer.readLine().also { line = it } == null) break

                    Log.e("line", line)


                    val str = line.split(",".toRegex(), 41)
                        .toTypedArray() // defining 3 columns with null or blank field //values acceptance
                    val name = str[0]
                    val link = str[1]
                    val info = str[2]
                    val count = str[3]
                    val expiry = str[4]
                    val monday = str[5]
                    val monday_morning = str[6]
                    val monday_afternoon = str[7]
                    val monday_evening = str[8]
                    val monday_night = str[9]
                    val tuesday = str[10]
                    val tuesday_morning = str[11]
                    val tuesday_afternoon = str[12]
                    val tuesday_evening = str[13]
                    val tuesday_night = str[14]
                    val wednessday = str[15]
                    val wednessday_morning = str[16]
                    val wednessday_afternoon = str[17]
                    val wednessday_evening = str[18]
                    val wednessday_night = str[19]
                    val thursday = str[20]
                    val thursday_morning = str[21]
                    val thursday_afternoon = str[22]
                    val thursday_evening = str[23]
                    val thursday_night = str[24]
                    val friday = str[25]
                    val friday_morning = str[26]
                    val friday_afternoon = str[27]
                    val friday_evening = str[28]
                    val friday_night = str[29]
                    val saturday = str[30]
                    val saturday_morning = str[31]
                    val saturday_afternoon = str[32]
                    val saturday_evening = str[33]
                    val saturday_night = str[34]
                    val sunday = str[35]
                    val sunday_morning = str[36]
                    val sunday_afternoon = str[37]
                    val sunday_evening = str[38]
                    val sunday_night = str[39]
//Id, Company,Name,Price
//
                    // Toast.makeText(baseContext, link, Toast.LENGTH_SHORT).show()


                    if (name != "NAME") {
                        downloadimg(name, link)

                        if (monday == "1") {
                            if (monday_morning == "1") {
                                val day = 1
                                val daytim = 1
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 480, 480 + 10)


                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (monday_afternoon == "1") {
                                val day = 1
                                val daytim = 2
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 780, 780 + 10)






                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()


                            }
                            if (monday_evening == "1") {

                                val day = 1
                                val daytim = 3
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1080, 1080 + 10)

                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (monday_night == "1") {
                                val day = 1
                                val daytim = 4
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1260, 1260 + 10)




                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                        }
                        if (tuesday == "1") {
                            if (tuesday_morning == "1") {
                                val day = 2
                                val daytim = 1
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 480, 480 + 10)


                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (tuesday_afternoon == "1") {
                                val day = 2
                                val daytim = 2
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 780, 780 + 10)






                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()


                            }
                            if (tuesday_evening == "1") {

                                val day = 2
                                val daytim = 3
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1080, 1080 + 10)

                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (tuesday_night == "1") {
                                val day = 2
                                val daytim = 4
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1260, 1260 + 10)




                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                        }
                        if (wednessday == "1") {
                            if (wednessday_morning == "1") {
                                val day = 3
                                val daytim = 1
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 480, 480 + 10)


                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (wednessday_afternoon == "1") {
                                val day = 3
                                val daytim = 2
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 780, 780 + 10)






                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()


                            }
                            if (wednessday_evening == "1") {

                                val day = 3
                                val daytim = 3
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1080, 1080 + 10)

                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (wednessday_night == "1") {
                                val day = 3
                                val daytim = 4
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1260, 1260 + 10)




                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                        }
                        if (thursday == "1") {
                            if (thursday_morning == "1") {
                                val day = 4
                                val daytim = 1
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 480, 480 + 10)


                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (thursday_afternoon == "1") {
                                val day = 4
                                val daytim = 2
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 780, 780 + 10)






                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()


                            }
                            if (thursday_evening == "1") {

                                val day = 4
                                val daytim = 3
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1080, 1080 + 10)

                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (thursday_night == "1") {
                                val day = 4
                                val daytim = 4
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1260, 1260 + 10)




                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                        }
                        if (friday == "1") {
                            if (friday_morning == "1") {
                                val day = 5
                                val daytim = 1
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 480, 480 + 10)


                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (friday_afternoon == "1") {
                                val day = 5
                                val daytim = 2
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 780, 780 + 10)






                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()


                            }
                            if (friday_evening == "1") {

                                val day = 5
                                val daytim = 3
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1080, 1080 + 10)

                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (friday_night == "1") {
                                val day = 5
                                val daytim = 4
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1260, 1260 + 10)




                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                        }
                        if (saturday == "1") {
                            if (saturday_morning == "1") {
                                val day = 6
                                val daytim = 1
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 480, 480 + 10)


                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (saturday_afternoon == "1") {
                                val day = 6
                                val daytim = 2
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 780, 780 + 10)






                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()


                            }
                            if (saturday_evening == "1") {

                                val day = 6
                                val daytim = 3
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1080, 1080 + 10)

                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (saturday_night == "1") {
                                val day = 6
                                val daytim = 4
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1260, 1260 + 10)




                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                        }
                        if (sunday == "1") {
                            if (sunday_morning == "1") {
                                val day = 7
                                val daytim = 1
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 480, 480 + 10)


                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (sunday_afternoon == "1") {
                                val day = 7
                                val daytim = 2
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 780, 780 + 10)






                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()


                            }
                            if (sunday_evening == "1") {

                                val day = 7
                                val daytim = 3
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1080, 1080 + 10)

                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                            if (sunday_night == "1") {
                                val day = 7
                                val daytim = 4
                                val schedule =
                                    medic(id, name, link, info, day, daytim, 1260, 1260 + 10)




                                dbHelper.insertMedicine(schedule)
                                // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                            }
                        }

                        val k =
                            btoI(monday_morning) + btoI(monday_afternoon) + btoI(monday_evening) + btoI(
                                monday_night
                            ) + btoI(tuesday_morning) + btoI(tuesday_afternoon) + btoI(
                                tuesday_evening
                            ) + btoI(tuesday_night) + btoI(wednessday_morning) + btoI(
                                wednessday_afternoon
                            ) + btoI(wednessday_evening) + btoI(wednessday_night) + btoI(
                                thursday_morning
                            ) + btoI(thursday_afternoon) + btoI(thursday_evening) + btoI(
                                thursday_night
                            ) + btoI(friday_morning) + btoI(friday_afternoon) + btoI(
                                friday_evening
                            ) + btoI(friday_night) + btoI(saturday_morning) + btoI(
                                saturday_afternoon
                            ) + btoI(saturday_evening) + btoI(saturday_night) + btoI(
                                sunday_morning
                            ) + btoI(sunday_afternoon) + btoI(sunday_evening) + btoI(
                                sunday_night
                            )

                        val user = User(
                            id,
                            name,
                            0,
                            0,
                            0,
                            0,
                            btoI(monday),
                            btoI(monday_morning),
                            btoI(monday_afternoon),
                            btoI(monday_evening),
                            btoI(monday_night),
                            btoI(tuesday),
                            btoI(tuesday_morning),
                            btoI(tuesday_afternoon),
                            btoI(tuesday_evening),
                            btoI(tuesday_night),
                            btoI(wednessday),
                            btoI(wednessday_morning),
                            btoI(wednessday_afternoon),
                            btoI(wednessday_evening),
                            btoI(wednessday_night),
                            btoI(thursday),
                            btoI(thursday_morning),
                            btoI(thursday_afternoon),
                            btoI(thursday_evening),
                            btoI(thursday_night),
                            btoI(friday),
                            btoI(friday_morning),
                            btoI(friday_afternoon),
                            btoI(friday_evening),
                            btoI(friday_night),
                            btoI(saturday),
                            btoI(saturday_morning),
                            btoI(saturday_afternoon),
                            btoI(saturday_evening),
                            btoI(saturday_night),
                            btoI(sunday),
                            btoI(sunday_morning),
                            btoI(sunday_afternoon),
                            btoI(sunday_evening),
                            btoI(sunday_night)
                        )

                        dbHelper.insertDaydata(user)
                        val tab_expire =
                            tab_expire_count(id, name, substr(expiry), btoI(count), link, k)
                        dbHelper.insertExpiry(tab_expire)
                    }


                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "done", Toast.LENGTH_SHORT).show()

                    break;
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()

            //Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }


    }
        @Throws(IOException::class)
        fun readCSV(uri: Uri): List<String> {
            val csvFile = contentResolver.openInputStream(uri)
            val isr = InputStreamReader(csvFile)
            return BufferedReader(isr).readLines()
        }

        companion object {
            const val READ_REQUEST_CODE = 123
        }

        private fun btoI(xVal: String): Int {
            return try {
                Integer.parseInt(xVal);
            } catch (e: NumberFormatException) {
                // Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()

                // Handle the condition when str is not a number.
                0
            }
        }

        private fun substr(inp: String): String {
            return inp.subSequence(0, inp.length - 5).toString()
        }

        override fun onDestroy() {
            //triggerWidgetSchedule()
            if (config.notificationScheduleStatus)
                getNextNotificationSchedule()
            super.onDestroy()
        }
    val onComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context?, intent: Intent?) {
            // your code
onr()

            delfile()
            val progrss =  findViewById<FrameLayout>(R.id.loadingFrame_download)
            progrss.visibility = View.INVISIBLE







        }
    }

     fun delfile() {
         val exportDir = File(Environment.getExternalStorageDirectory(), "/Medicure/")

         val file = File(

             exportDir,
             "upload.csv"
         )
         file.delete()
         if (file.exists()) {
             file.canonicalFile.delete()
             if (file.exists()) {
                 applicationContext.deleteFile(file.name)
             }
         }
         val slide = findViewById<SlideToActView>(R.id.download_link)
         slide.completeIcon = R.drawable.tic



     }
    fun downloadimg(sam: String, link: String) {
        val uri =
                Uri.parse(link)
        val r = DownloadManager.Request(uri)

// This put the download in the same Download dir the browser uses
        val exportDir = File(Environment.getExternalStorageDirectory(), "/Medicure/Image/")
        if (!exportDir.exists()) {
            exportDir.mkdirs()
        }
// This put the download in the same Download dir the browser uses
        // r.setDestinationInExternalFilesDir(this, "Medicure", "upload.csv")
        //Toast.makeText(baseContext, Environment.DIRECTORY_DOWNLOADS, Toast.LENGTH_SHORT).show()

        r.setDestinationUri(Uri.fromFile(File(exportDir, sam + ".jpg")))

        r.allowScanningByMediaScanner()

// Start download

// Start download
        val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(r)
       // registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));





    }



    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
    fun startBiometricLock2() {
        /*
        *   getting Biometric Manger to work with system biometric
        * */
        val biometricManager = androidx.biometric.BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS ->
                showLoginFingerPrintPrompt()
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
            -> showerror("Sorry your phone doesn't have any biometric ")
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                showerror("Sorry your phone doesn't support for biometric")
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                showerror("Please, setup your biometric in settings")
        }
    }
    private fun startBiometricLock() {
        /*
        *   getting Biometric Manger to work with system biometric
        * */
        val biometricManager = androidx.biometric.BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS ->
                showLoginFingerPrintPrompt()
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
            -> showerror("Sorry your phone doesn't have any biometric ")
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                showerror("Sorry your phone doesn't support for biometric")
            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                showerror("Please, setup your biometric in settings")
        }
    }

    private fun showLoginFingerPrintPrompt() {
        executor = ContextCompat.getMainExecutor(this)
//        getting authentication of user
        biometricPrompt = androidx.biometric.BiometricPrompt(
            this,
            executor,
            object : androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@downloadview, "Sorry invalid user", Toast.LENGTH_SHORT)
                        .show()
                    var miserror = findViewById<RelativeLayout>(R.id.relative_error)
                    var menus = findViewById<RelativeLayout>(R.id.relative_down)
                    menus.visibility = View.INVISIBLE
                    miserror.visibility = View.VISIBLE



                }

                @SuppressLint("SetTextI18n")
                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    var miserror = findViewById<RelativeLayout>(R.id.relative_error)
                    var menus = findViewById<RelativeLayout>(R.id.relative_down)
                    menus.visibility = View.VISIBLE
                    miserror.visibility = View.INVISIBLE
                    val uid = auth.currentUser?.uid
                    // Toast.makeText(this@downloadview, uid, Toast.LENGTH_SHORT).show()
                    var link = findViewById<TextView>(R.id.link)
                    //var user = userdown("none",0,"none")

                    databaseReference = FirebaseDatabase.getInstance().getReference("csv_data")
                    var user = userdown("none", 0, "sam")
                    val slide = findViewById<SlideToActView>(R.id.download_link)

                    databaseReference.child(uid.toString())
                        .addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val user2 = snapshot.getValue(userdown::class.java)!!

                                link.text = "Version : " + user2.version


                                var menus = findViewById<RelativeLayout>(R.id.relative_down)
                                menus.visibility = View.VISIBLE
                                menus!!.startAnimation(frombottom)
                                val progrss =  findViewById<FrameLayout>(R.id.loadingFrame_download)

                                // Toast.makeText(getApplicationContext(), "morning_1", Toast.LENGTH_SHORT).show();
                                slide.onSlideCompleteListener =
                                    object : SlideToActView.OnSlideCompleteListener {
                                        override fun onSlideComplete(view: SlideToActView) {
                                            progrss.visibility = View.VISIBLE

                                            samsai3(user2.dblink)

                                        }
                                    }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(this@downloadview, "problem araised", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })
        createPrompt()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun createPrompt() {
        /*
        *   create fingerprint prompt
        *   if mobile have fac pattern then  it also work
        */
        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using your biometric")

            .setNegativeButtonText("Cancel")
            .build()
    }

    private  fun showerror(s: String){
        var miserror = findViewById<RelativeLayout>(R.id.relative_error)
        var menus = findViewById<RelativeLayout>(R.id.relative_down)
        menus.visibility = View.INVISIBLE
        miserror.visibility = View.VISIBLE
        Toast.makeText(
            this,
            s,
            Toast.LENGTH_SHORT
        ).show()

    }

}
