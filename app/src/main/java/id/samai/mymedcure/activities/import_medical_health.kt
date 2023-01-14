package id.samai.mymedcure.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import id.samai.mymedcure.R
import id.samai.mymedcure.helpers.DBHelper
import id.samai.mymedcure.models.MEDICAL_HIS
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class import_medical_health : AppCompatActivity() {
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_medical_health)
        // mTextViewCsvResult = findViewById(R.id.textView_csvResult)

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


                        val str = line.split(",".toRegex(), 9)
                            .toTypedArray() // defining 3 columns with null or blank field //values acceptance

//Id, Company,Name,Price
                        val title1 = str[0]

                        val title = title1.subSequence(1,title1.length-1)
                        Log.e("line", title as String)
                        // Toast.makeText(applicationContext, title, Toast.LENGTH_SHORT).show()

                        val doctorname = str[1]
                        val visited_date = str[2]
                        val observation = str[3]
                        val bp = str[4]
                        val cell = substr( str[5])
                        val hemoglobin = substr(str[6])
                        val virtual = substr(str[7])


                        var dbHelper: DBHelper
                        dbHelper = DBHelper(applicationContext)
                        val medic_his = MEDICAL_HIS(id,title,doctorname,visited_date,observation,bp,strtoint(cell),strtoint(hemoglobin),strtoint(virtual))
                        dbHelper.insertMedical_history(medic_his)

                    }
                    catch (e: Exception){
                        Toast.makeText(applicationContext, "completed", Toast.LENGTH_SHORT).show()

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
            //  Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()

            // Handle the condition when str is not a number.
            0
        }
    }
    private fun substr(inp : String) : String {
        return inp.subSequence(1,inp.length-1).toString()
    }


}