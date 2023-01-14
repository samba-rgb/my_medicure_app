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
import id.samai.mymedcure.fragments.Schedule
import id.samai.mymedcure.helpers.DBHelper
import id.samai.mymedcure.models.MEDICAL_HIS
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class import_medical_his : AppCompatActivity() {

        private lateinit var mTextViewCsvResult: TextView
        private var id: Int? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_import_medical_his)
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


                            val str = line.split(",".toRegex(), 8)
                                .toTypedArray() // defining 3 columns with null or blank field //values acceptance

//Id, Company,Name,Price
                            val title = str[0]


                            Log.e("line", title as String)
                            //Toast.makeText(applicationContext, title, Toast.LENGTH_SHORT).show()
                            val doctor_name = str[1]
                            val observation = str[2]
                            val virtual = str[3]
                            val bp = str[4]
                            val cell =str[5]
                            val k = 0
                            val hemoglobin = str[6]
                            if (virtual =="on"){
                                 val k = 1
                            }
                            else{
                               val k =0
                            }
                            val calendar = Calendar.getInstance()
                            calendar.time = Date()
                            val date = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(calendar.time)
                            if (title != "NAME") {
                                val dat = MEDICAL_HIS(id,title,doctor_name,date,observation,bp,cell.toInt(),hemoglobin.toInt(),k)

                                dbHelper.insertMedical_history(dat)
                            }

                        }
                        catch (e: Exception){
                            Toast.makeText(applicationContext, "COMPLETED", Toast.LENGTH_SHORT).show()

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

        private fun strtoint(xVal: String): Int {
            return try {
                Integer.parseInt(xVal);
            } catch (e: NumberFormatException) {
                // Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()

                // Handle the condition when str is not a number.
                0
            }
        }
        private fun substr(inp : String) : String {
            return inp.subSequence(1,inp.length-1).toString()
        }

        override fun onDestroy() {
            //triggerWidgetSchedule()
            if (config.notificationScheduleStatus)
                getNextNotificationSchedule()
            super.onDestroy()
        }
    }