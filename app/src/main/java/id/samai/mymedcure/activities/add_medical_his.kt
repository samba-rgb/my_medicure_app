package id.samai.mymedcure.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.switchmaterial.SwitchMaterial
import id.samai.mymedcure.R
import id.samai.mymedcure.extensions.config
import id.samai.mymedcure.extensions.dbHelper
import id.samai.mymedcure.helpers.DataBase2
import id.samai.mymedcure.helpers.Formatter
import id.samai.mymedcure.helpers.UPDATE_ACTION
import id.samai.mymedcure.models.MEDICAL_HIS
import kotlinx.android.synthetic.main.activity_add_medical_his.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class add_medical_his : AppCompatActivity() {
    private var id: Int? = null
    private lateinit var historyTitles: Set<String>
    var db: DataBase2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medical_his)
        /** parsing data intent */
        // mondayswitch.text = Formatter.getDayFromInt(config.lastDayScheduleSelect)
        //monday_start_time_text.text = Formatter.getTimeFromMinute(config.lastScheduleEndTime)
        // monday_end_time_text.text = Formatter.getTimeFromMinute(config.lastScheduleEndTime + config.intervalStartEnd)


        historyTitles = config.displayTitles

        /** Auto Complete Text View **/
        val suggestText = ArrayAdapter<String>(
            this, R.layout.custom_select_dialog, ArrayList<String>(
                historyTitles
            )
        )
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val date = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(calendar.time)
        visited_date.text = date
        /** App bar **/
        initAppbar()

        /** click handle **/
        initUi()

    }


    private fun initAppbar() {
        if (intent.action == UPDATE_ACTION)
            supportActionBar?.title = getString(R.string.schedule_update)
        else
            supportActionBar?.title = getString(R.string.schedule_new)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_update_delete, menu)
        if (intent.action != UPDATE_ACTION) {
            val item = menu?.findItem(R.id.delete_action)
            item?.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_action -> saveSchedule()

            android.R.id.home -> finish()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
    private fun setupDatePicker(textView: AppCompatTextView) {

        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US).parse(textView.text.toString())
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.apply {
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                set(Calendar.MONTH, month)
                set(Calendar.YEAR, year)
            }
            textView.text = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(calendar.time)
        }

        DatePickerDialog(this, R.style.DialogTheme, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(
            Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

    }



    private fun saveSchedule() {

        val visited_number = visited_number.text.toString()
        val name = doctor_name.text.toString()
        val observation: String = doctor_observation.text.toString()
        val virtual : Boolean =findViewById<SwitchMaterial>(R.id.virtualswitch).isChecked()
        val bp = bp_value.text.toString()
        val cell = cell_count_value.text.toString()
        val hemo = hemoglobin_value.text.toString()
val visited =  visited_date.text
        fun btoI(b: Boolean): Int {
            return if (b) 1 else 0
        }
val dat = MEDICAL_HIS(id,visited_number,name,visited.toString(),observation,bp,cell.toInt(),hemo.toInt(),btoI(virtual))
dbHelper.insertMedical_history(dat)
        finish()
    }


    private fun initUi() {


        visited_date.setOnClickListener {
            setupDatePicker(visited_date)
        }


    }

    private fun setupTimePicker(textView: AppCompatTextView) {

        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("HH:mm", Locale.US).parse(textView.text.toString())
        val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.apply {
                set(Calendar.HOUR_OF_DAY, hourOfDay)
                set(Calendar.MINUTE, minute)
            }
            textView.text = SimpleDateFormat("HH:mm", Locale.US).format(calendar.time)
        }

        TimePickerDialog(
            this,
            R.style.DialogTheme,
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(
                Calendar.MINUTE
            ),
            true
        ).show()
    }




    private fun emptyTitle() {
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this, R.style.DialogTheme).create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setMessage(resources.getString(R.string.schedule_empty_title))
        alertDialog.setButton(
            androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, resources.getText(
                R.string.ok
            )
        ) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

}