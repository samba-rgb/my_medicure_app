package id.samai.mymedcure.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import id.samai.mymedcure.R
import id.samai.mymedcure.extensions.config
import id.samai.mymedcure.extensions.dbHelper
import id.samai.mymedcure.extensions.getNextNotificationSchedule
import id.samai.mymedcure.models.MEDICAL_HIS
import id.samai.mymedcure.models.User
import id.samai.mymedcure.models.medic
import id.samai.mymedcure.models.tab_expire_count
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class add_medical_csv : AppCompatActivity() {
    private lateinit var mTextViewCsvResult: TextView
    private var id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medical_csv)
        supportActionBar?.title = "Importing"
        // mTextViewCsvResult = findViewById(R.id.textView_csvResult)
        findViewById<ImageView>(R.id.shareimage)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "text/*"
            startActivityForResult(intent, READ_REQUEST_CODE)
        }
        findViewById<Button>(R.id.button_loadCsv)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "text/*"
            startActivityForResult(intent, READ_REQUEST_CODE)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                val csvFile = resultData?.data?.let { contentResolver.openInputStream(it) }
                val isr = InputStreamReader(csvFile)
                val buffer = BufferedReader(isr)

                var line = ""
                var iteration = 0
                while (true) {
                    try{
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

                        if(name != "NAME"){
                            if(monday=="1"){
                                if (monday_morning=="1"){
                                    val day = 1
                                    val daytim = 1
                                    val schedule = medic(id, name, link, info, day,daytim, 480, 480+10)


                                    dbHelper.insertMedicine(schedule)
                                        // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(monday_afternoon=="1"){
                                    val day = 1
                                    val daytim = 2
                                    val schedule = medic(id, name, link, info, day,daytim, 780, 780+10)






                                        dbHelper.insertMedicine(schedule)
                                        // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()



                                }
                                if(monday_evening=="1"){

                                    val day = 1
                                    val daytim = 3
                                    val schedule = medic(id, name, link, info, day,daytim, 1080, 1080+10)

                                    dbHelper.insertMedicine(schedule)
                                        // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(monday_night=="1"){
                                    val day = 1
                                    val daytim = 4
                                    val schedule = medic(id, name, link, info, day,daytim, 1260, 1260+10)




                                        dbHelper.insertMedicine(schedule)
                                        // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                            }
                            if(tuesday=="1"){
                                if (tuesday_morning=="1"){
                                    val day = 2
                                    val daytim = 1
                                    val schedule = medic(id, name, link, info, day,daytim, 480, 480+10)


                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(tuesday_afternoon=="1"){
                                    val day = 2
                                    val daytim = 2
                                    val schedule = medic(id, name, link, info, day,daytim, 780, 780+10)






                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()



                                }
                                if(tuesday_evening=="1"){

                                    val day = 2
                                    val daytim = 3
                                    val schedule = medic(id, name, link, info, day,daytim, 1080, 1080+10)

                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(tuesday_night=="1"){
                                    val day = 2
                                    val daytim = 4
                                    val schedule = medic(id, name, link, info, day,daytim, 1260, 1260+10)




                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                            }
                            if(wednessday=="1"){
                                if (wednessday_morning=="1"){
                                    val day = 3
                                    val daytim = 1
                                    val schedule = medic(id, name, link, info, day,daytim, 480, 480+10)


                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(wednessday_afternoon=="1"){
                                    val day = 3
                                    val daytim = 2
                                    val schedule = medic(id, name, link, info, day,daytim, 780, 780+10)






                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()



                                }
                                if(wednessday_evening=="1"){

                                    val day = 3
                                    val daytim = 3
                                    val schedule = medic(id, name, link, info, day,daytim, 1080, 1080+10)

                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(wednessday_night=="1"){
                                    val day = 3
                                    val daytim = 4
                                    val schedule = medic(id, name, link, info, day,daytim, 1260, 1260+10)




                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                            }
                            if(thursday=="1"){
                                if (thursday_morning=="1"){
                                    val day = 4
                                    val daytim = 1
                                    val schedule = medic(id, name, link, info, day,daytim, 480, 480+10)


                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(thursday_afternoon=="1"){
                                    val day = 4
                                    val daytim = 2
                                    val schedule = medic(id, name, link, info, day,daytim, 780, 780+10)






                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()



                                }
                                if(thursday_evening=="1"){

                                    val day = 4
                                    val daytim = 3
                                    val schedule = medic(id, name, link, info, day,daytim, 1080, 1080+10)

                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(thursday_night=="1"){
                                    val day = 4
                                    val daytim = 4
                                    val schedule = medic(id, name, link, info, day,daytim, 1260, 1260+10)




                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                            }
                            if(friday=="1"){
                                if (friday_morning=="1"){
                                    val day = 5
                                    val daytim = 1
                                    val schedule = medic(id, name, link, info, day,daytim, 480, 480+10)


                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(friday_afternoon=="1"){
                                    val day = 5
                                    val daytim = 2
                                    val schedule = medic(id, name, link, info, day,daytim, 780, 780+10)






                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()



                                }
                                if(friday_evening=="1"){

                                    val day = 5
                                    val daytim = 3
                                    val schedule = medic(id, name, link, info, day,daytim, 1080, 1080+10)

                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(friday_night=="1"){
                                    val day = 5
                                    val daytim = 4
                                    val schedule = medic(id, name, link, info, day,daytim, 1260, 1260+10)




                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                            }
                            if(saturday=="1"){
                                if (saturday_morning=="1"){
                                    val day = 6
                                    val daytim = 1
                                    val schedule = medic(id, name, link, info, day,daytim, 480, 480+10)


                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(saturday_afternoon=="1"){
                                    val day = 6
                                    val daytim = 2
                                    val schedule = medic(id, name, link, info, day,daytim, 780, 780+10)






                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()



                                }
                                if(saturday_evening=="1"){

                                    val day = 6
                                    val daytim = 3
                                    val schedule = medic(id, name, link, info, day,daytim, 1080, 1080+10)

                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(saturday_night=="1"){
                                    val day = 6
                                    val daytim = 4
                                    val schedule = medic(id, name, link, info, day,daytim, 1260, 1260+10)




                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                            }
                            if(sunday=="1"){
                                if (sunday_morning=="1"){
                                    val day = 7
                                    val daytim = 1
                                    val schedule = medic(id, name, link, info, day,daytim, 480, 480+10)


                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(sunday_afternoon=="1"){
                                    val day = 7
                                    val daytim = 2
                                    val schedule = medic(id, name, link, info, day,daytim, 780, 780+10)






                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()



                                }
                                if(sunday_evening=="1"){

                                    val day = 7
                                    val daytim = 3
                                    val schedule = medic(id, name, link, info, day,daytim, 1080, 1080+10)

                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                                if(sunday_night=="1"){
                                    val day = 7
                                    val daytim = 4
                                    val schedule = medic(id, name, link, info, day,daytim, 1260, 1260+10)




                                    dbHelper.insertMedicine(schedule)
                                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()

                                }
                            }

                            val k = btoI(monday_morning)+btoI(monday_afternoon)+btoI(monday_evening)+btoI(monday_night)+btoI(tuesday_morning)+btoI(tuesday_afternoon)+btoI(tuesday_evening)+btoI(tuesday_night)+btoI(wednessday_morning)+btoI(wednessday_afternoon)+btoI(wednessday_evening)+btoI(wednessday_night)+btoI(thursday_morning)+btoI(thursday_afternoon)+btoI(thursday_evening)+btoI(thursday_night)+btoI(friday_morning)+btoI(friday_afternoon)+btoI(friday_evening)+btoI(friday_night)+btoI(saturday_morning)+btoI(saturday_afternoon)+btoI(saturday_evening)+btoI(saturday_night)+btoI(sunday_morning)+btoI(sunday_afternoon)+btoI(sunday_evening)+btoI(sunday_night)

                            val user = User(id, name,0,0,0,0,btoI(monday),btoI(monday_morning),btoI(monday_afternoon),btoI(monday_evening),btoI(monday_night),btoI(tuesday),btoI(tuesday_morning),btoI(tuesday_afternoon),btoI(tuesday_evening),btoI(tuesday_night),btoI(wednessday),btoI(wednessday_morning),btoI(wednessday_afternoon),btoI(wednessday_evening),btoI(wednessday_night),btoI(thursday),btoI(thursday_morning),btoI(thursday_afternoon),btoI(thursday_evening),btoI(thursday_night),btoI(friday),btoI(friday_morning),btoI(friday_afternoon),btoI(friday_evening),btoI(friday_night),btoI(saturday),btoI(saturday_morning),btoI(saturday_afternoon),btoI(saturday_evening),btoI(saturday_night),btoI(sunday),btoI(sunday_morning),btoI(sunday_afternoon),btoI(sunday_evening),btoI(sunday_night))

                            dbHelper.insertDaydata(user)
                            val tab_expire = tab_expire_count(id, name, substr(expiry),btoI(count),link,k)
                            dbHelper.insertExpiry(tab_expire)
                        }




                    }
                    catch (e: Exception){
                        Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()

                        break;
                    }
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()

                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
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
    private fun substr(inp : String) : String {
        return inp.subSequence(0,inp.length-5).toString()
    }

    override fun onDestroy() {
        //triggerWidgetSchedule()
        if (config.notificationScheduleStatus)
            getNextNotificationSchedule()
        super.onDestroy()
    }
}