package id.samai.mymedcure.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.warkiz.widget.IndicatorSeekBar
import id.samai.mymedcure.R
import id.samai.mymedcure.extensions.config
import id.samai.mymedcure.extensions.dbHelper
import id.samai.mymedcure.extensions.getNextNotificationSchedule
import id.samai.mymedcure.fragments.Schedule
import id.samai.mymedcure.helpers.DataBase2
import id.samai.mymedcure.helpers.Formatter
import id.samai.mymedcure.helpers.Formatter.getTimeInMills
import id.samai.mymedcure.helpers.UPDATE_ACTION
import id.samai.mymedcure.models.User
import id.samai.mymedcure.models.medic
import id.samai.mymedcure.models.tab_expire_count
import kotlinx.android.synthetic.main.activity_medicine_1.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Medicine_1: AppCompatActivity() {
    private var id: Int? = null
    private lateinit var historyTitles: Set<String>
    var db: DataBase2? = null
    private fun initDatabaseHelper() {

        if (db == null) {
            db = DataBase2(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_1)


        initDatabaseHelper()

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
        schedule_title2.threshold = 1
        schedule_title2.setAdapter(suggestText)
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.YEAR, Calendar.YEAR + 1)

        val date = SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US).format(calendar.time)

        task_date.text = date
        /** App bar **/
        initAppbar()

        /** click handle **/
        initUi()

    }


    private fun initAppbar() {
        if (intent.action == UPDATE_ACTION)
            supportActionBar?.title = getString(R.string.schedule_update)
        else
            supportActionBar?.title = "New Medicine"
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
            textView.text = SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US).format(calendar.time)
        }

        DatePickerDialog(this, R.style.DialogTheme, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

    }



    private fun saveSchedule() {

        val title = schedule_title2.text.toString()
        val location = schedule_location2.text.toString()
        val info: String? = schedule_info_med.text.toString()



        val monday: Boolean =findViewById<SwitchMaterial>(R.id.mondayswitch).isChecked()
        val monday_morning : Boolean = findViewById<SwitchMaterial>(R.id.monday_morning_switch).isChecked()
        val monday_morning_time = Formatter.getMinuteFromTime(monday_morning_start_time_text.text.toString())
        val monday_afternoon : Boolean = findViewById<SwitchMaterial>(R.id.monday_afternoon_switch).isChecked()
        val monday_afternoon_time = Formatter.getMinuteFromTime(monday_afternoon_start_time_text.text.toString())
        val monday_evening : Boolean = findViewById<SwitchMaterial>(R.id.monday_evening_switch).isChecked()
        val monday_evening_time = Formatter.getMinuteFromTime(monday_evening_start_time_text.text.toString())
        val monday_night : Boolean = findViewById<SwitchMaterial>(R.id.monday_night_switch).isChecked()
        val monday_night_time = Formatter.getMinuteFromTime(monday_night_start_time_text.text.toString())

        val tuesday: Boolean =findViewById<SwitchMaterial>(R.id.tuesdayswitch).isChecked()
        val tuesday_morning : Boolean = findViewById<SwitchMaterial>(R.id.tuesday_morning_switch).isChecked()
        val tuesday_morning_time = Formatter.getMinuteFromTime(tuesday_morning_start_time_text.text.toString())
        val tuesday_afternoon : Boolean = findViewById<SwitchMaterial>(R.id.tuesday_afternoon_switch).isChecked()
        val tuesday_afternoon_time = Formatter.getMinuteFromTime(tuesday_afternoon_start_time_text.text.toString())
        val tuesday_evening : Boolean = findViewById<SwitchMaterial>(R.id.tuesday_evening_switch).isChecked()
        val tuesday_evening_time = Formatter.getMinuteFromTime(tuesday_evening_start_time_text.text.toString())
        val tuesday_night : Boolean = findViewById<SwitchMaterial>(R.id.tuesday_night_switch).isChecked()
        val tuesday_night_time = Formatter.getMinuteFromTime(tuesday_night_start_time_text.text.toString())

        val wednessday: Boolean =findViewById<SwitchMaterial>(R.id.wednessdayswitch).isChecked()
        val wednessday_morning : Boolean = findViewById<SwitchMaterial>(R.id.wednessday_morning_switch).isChecked()
        val wednessday_morning_time = Formatter.getMinuteFromTime(wednessday_morning_start_time_text.text.toString())
        val wednessday_afternoon : Boolean = findViewById<SwitchMaterial>(R.id.wednessday_afternoon_switch).isChecked()
        val wednessday_afternoon_time = Formatter.getMinuteFromTime(wednessday_afternoon_start_time_text.text.toString())
        val wednessday_evening : Boolean = findViewById<SwitchMaterial>(R.id.wednessday_evening_switch).isChecked()
        val wednessday_evening_time = Formatter.getMinuteFromTime(wednessday_evening_start_time_text.text.toString())
        val wednessday_night : Boolean = findViewById<SwitchMaterial>(R.id.wednessday_night_switch).isChecked()
        val wednessday_night_time = Formatter.getMinuteFromTime(wednessday_night_start_time_text.text.toString())

        val thursday: Boolean =findViewById<SwitchMaterial>(R.id.thursdayswitch).isChecked()
        val thursday_morning : Boolean = findViewById<SwitchMaterial>(R.id.thursday_morning_switch).isChecked()
        val thursday_morning_time = Formatter.getMinuteFromTime(thursday_morning_start_time_text.text.toString())
        val thursday_afternoon : Boolean = findViewById<SwitchMaterial>(R.id.thursday_afternoon_switch).isChecked()
        val thursday_afternoon_time = Formatter.getMinuteFromTime(thursday_afternoon_start_time_text.text.toString())
        val thursday_evening : Boolean = findViewById<SwitchMaterial>(R.id.thursday_evening_switch).isChecked()
        val thursday_evening_time = Formatter.getMinuteFromTime(thursday_evening_start_time_text.text.toString())
        val thursday_night : Boolean = findViewById<SwitchMaterial>(R.id.thursday_night_switch).isChecked()
        val thursday_night_time = Formatter.getMinuteFromTime(thursday_night_start_time_text.text.toString())

        val friday: Boolean =findViewById<SwitchMaterial>(R.id.fridayswitch).isChecked()
        val friday_morning : Boolean = findViewById<SwitchMaterial>(R.id.friday_morning_switch).isChecked()
        val friday_morning_time = Formatter.getMinuteFromTime(friday_morning_start_time_text.text.toString())
        val friday_afternoon : Boolean = findViewById<SwitchMaterial>(R.id.friday_afternoon_switch).isChecked()
        val friday_afternoon_time = Formatter.getMinuteFromTime(friday_afternoon_start_time_text.text.toString())
        val friday_evening : Boolean = findViewById<SwitchMaterial>(R.id.friday_evening_switch).isChecked()
        val friday_evening_time = Formatter.getMinuteFromTime(friday_evening_start_time_text.text.toString())
        val friday_night : Boolean = findViewById<SwitchMaterial>(R.id.friday_night_switch).isChecked()
        val friday_night_time = Formatter.getMinuteFromTime(friday_night_start_time_text.text.toString())

        val saturday: Boolean =findViewById<SwitchMaterial>(R.id.saturdayswitch).isChecked()
        val saturday_morning : Boolean = findViewById<SwitchMaterial>(R.id.saturday_morning_switch).isChecked()
        val saturday_morning_time = Formatter.getMinuteFromTime(saturday_morning_start_time_text.text.toString())
        val saturday_afternoon : Boolean = findViewById<SwitchMaterial>(R.id.saturday_afternoon_switch).isChecked()
        val saturday_afternoon_time = Formatter.getMinuteFromTime(saturday_afternoon_start_time_text.text.toString())
        val saturday_evening : Boolean = findViewById<SwitchMaterial>(R.id.saturday_evening_switch).isChecked()
        val saturday_evening_time = Formatter.getMinuteFromTime(saturday_evening_start_time_text.text.toString())
        val saturday_night : Boolean = findViewById<SwitchMaterial>(R.id.saturday_night_switch).isChecked()
        val saturday_night_time = Formatter.getMinuteFromTime(saturday_night_start_time_text.text.toString())

        val sunday: Boolean =findViewById<SwitchMaterial>(R.id.sundayswitch).isChecked()
        val sunday_morning : Boolean = findViewById<SwitchMaterial>(R.id.sunday_morning_switch).isChecked()
        val sunday_morning_time = Formatter.getMinuteFromTime(sunday_morning_start_time_text.text.toString())
        val sunday_afternoon : Boolean = findViewById<SwitchMaterial>(R.id.sunday_afternoon_switch).isChecked()
        val sunday_afternoon_time = Formatter.getMinuteFromTime(sunday_afternoon_start_time_text.text.toString())
        val sunday_evening : Boolean = findViewById<SwitchMaterial>(R.id.sunday_evening_switch).isChecked()
        val sunday_evening_time = Formatter.getMinuteFromTime(sunday_evening_start_time_text.text.toString())
        val sunday_night : Boolean = findViewById<SwitchMaterial>(R.id.sunday_night_switch).isChecked()
        val sunday_night_time = Formatter.getMinuteFromTime(sunday_night_start_time_text.text.toString())
        if(monday){
            if (monday_morning){
                val day = 1
                val daytim = 1
                val schedule = medic(id, title, location, info, day,daytim, monday_morning_time, monday_morning_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(monday_afternoon){
                val day = 1
                val daytim = 2
                val schedule = medic(id, title, location, info, day,daytim, monday_afternoon_time, monday_afternoon_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }


            }
            if(monday_evening){

                val day = 1
                val daytim = 3
                val schedule = medic(id, title, location, info, day,daytim, monday_evening_time, monday_evening_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(monday_night){
                val day = 1
                val daytim = 4
                val schedule = medic(id, title, location, info, day,daytim, monday_night_time, monday_night_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(tuesday){
            if(tuesday_morning){
                val day = 2
                val daytim = 1
                val schedule = medic(id, title, location, info, day,daytim, tuesday_morning_time, tuesday_morning_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(tuesday_afternoon){
                val day = 2
                val daytim = 2
                val schedule = medic(id, title, location, info, day,daytim, tuesday_afternoon_time, tuesday_afternoon_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(tuesday_evening){
                val day = 2
                val daytim = 3
                val schedule = medic(id, title, location, info, day,daytim, tuesday_evening_time, tuesday_evening_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(tuesday_night){
                val day = 2
                val daytim = 4
                val schedule = medic(id, title, location, info, day,daytim, tuesday_night_time, tuesday_night_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(wednessday){
            if(wednessday_morning){
                val day = 3
                val daytim = 1
                val schedule = medic(id, title, location, info, day,daytim, wednessday_morning_time, wednessday_morning_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(wednessday_afternoon){
                val day = 3
                val daytim = 2
                val schedule = medic(id, title, location, info, day,daytim, wednessday_afternoon_time, wednessday_afternoon_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(wednessday_evening){
                val day = 3
                val daytim = 3
                val schedule = medic(id, title, location, info, day,daytim, wednessday_evening_time, wednessday_evening_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(wednessday_night){
                val day = 3
                val daytim = 4
                val schedule = medic(id, title, location, info, day,daytim, wednessday_night_time, wednessday_night_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(thursday){
            if(thursday_morning){
                val day = 4
                val daytim = 1
                val schedule = medic(id, title, location, info, day,daytim, thursday_morning_time, thursday_morning_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(thursday_afternoon){
                val day = 4
                val daytim = 2
                val schedule = medic(id, title, location, info, day,daytim, thursday_afternoon_time, thursday_afternoon_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(thursday_evening){
                val day = 4
                val daytim = 3
                val schedule = medic(id, title, location, info, day,daytim, thursday_evening_time, thursday_evening_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(thursday_night){
                val day = 4
                val daytim = 4
                val schedule = medic(id, title, location, info, day,daytim, thursday_night_time, thursday_night_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(friday){
            if(friday_morning){
                val day = 5
                val daytim = 1
                val schedule = medic(id, title, location, info, day,daytim, friday_morning_time, friday_morning_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(friday_afternoon){
                val day = 5
                val daytim = 2
                val schedule = medic(id, title, location, info, day,daytim, friday_afternoon_time, friday_afternoon_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(friday_evening){
                val day = 5
                val daytim = 3
                val schedule = medic(id, title, location, info, day,daytim, friday_evening_time, friday_evening_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(friday_night){
                val day = 5
                val daytim = 4
                val schedule = medic(id, title, location, info, day,daytim, friday_night_time, friday_night_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(saturday){
            if(saturday_morning){
                val day = 6
                val daytim = 1
                val schedule = medic(id, title, location, info, day,daytim, saturday_morning_time, saturday_morning_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(saturday_afternoon){
                val day = 6
                val daytim = 2
                val schedule = medic(id, title, location, info, day,daytim, saturday_afternoon_time, saturday_afternoon_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(saturday_evening){
                val day = 6
                val daytim = 3
                val schedule = medic(id, title, location, info, day,daytim, saturday_evening_time, saturday_evening_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(saturday_night){
                val day = 6
                val daytim = 4
                val schedule = medic(id, title, location, info, day,daytim, saturday_night_time, saturday_night_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(sunday){
            if(sunday_morning){
                val day = 7
                val daytim = 1
                val schedule = medic(id, title, location, info, day,daytim, sunday_morning_time, sunday_morning_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(sunday_afternoon){
                val day = 7
                val daytim = 2
                val schedule = medic(id, title, location, info, day,daytim, sunday_afternoon_time, sunday_afternoon_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(sunday_evening){
                val day = 7
                val daytim = 3
                val schedule = medic(id, title, location, info, day,daytim, sunday_evening_time, sunday_evening_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
            if(sunday_night){
                val day = 7
                val daytim = 4
                val schedule = medic(id, title, location, info, day,daytim, sunday_night_time, sunday_night_time+10)

                if (title == "") {
                    emptyTitle()
                }
                else {


                    if (!historyTitles.contains(title))
                        config.addDisplayTitle(title)

                    dbHelper.insertMedicine(schedule)
                    // Toast.makeText(baseContext, getString(R.string.schedule_add_action), Toast.LENGTH_SHORT).show()
                }
            }
        }
        fun btoI(b: Boolean): Int {
            return if (b) 1 else 0
        }
        val k = btoI(monday_morning)+btoI(monday_afternoon)+btoI(monday_evening)+btoI(monday_night)+btoI(tuesday_morning)+btoI(tuesday_afternoon)+btoI(tuesday_evening)+btoI(tuesday_night)+btoI(wednessday_morning)+btoI(wednessday_afternoon)+btoI(wednessday_evening)+btoI(wednessday_night)+btoI(thursday_morning)+btoI(thursday_afternoon)+btoI(thursday_evening)+btoI(thursday_night)+btoI(friday_morning)+btoI(friday_afternoon)+btoI(friday_evening)+btoI(friday_night)+btoI(saturday_morning)+btoI(saturday_afternoon)+btoI(saturday_evening)+btoI(saturday_night)+btoI(sunday_morning)+btoI(sunday_afternoon)+btoI(sunday_evening)+btoI(sunday_night)

        val user = User(id, title,0,0,0,0,btoI(monday),btoI(monday_morning),btoI(monday_afternoon),btoI(monday_evening),btoI(monday_night),btoI(tuesday),btoI(tuesday_morning),btoI(tuesday_afternoon),btoI(tuesday_evening),btoI(tuesday_night),btoI(wednessday),btoI(wednessday_morning),btoI(wednessday_afternoon),btoI(wednessday_evening),btoI(wednessday_night),btoI(thursday),btoI(thursday_morning),btoI(thursday_afternoon),btoI(thursday_evening),btoI(thursday_night),btoI(friday),btoI(friday_morning),btoI(friday_afternoon),btoI(friday_evening),btoI(friday_night),btoI(saturday),btoI(saturday_morning),btoI(saturday_afternoon),btoI(saturday_evening),btoI(saturday_night),btoI(sunday),btoI(sunday_morning),btoI(sunday_afternoon),btoI(sunday_evening),btoI(sunday_night))

        dbHelper.insertDaydata(user)
        val time = Formatter.getTimeInMills("${task_date.text} ${task_time.text}")
        val simpleSeekBar =
            findViewById<View>(R.id.slider) as IndicatorSeekBar // initiate the Seek bar
        val  seekBarValue = simpleSeekBar.progress

        val tab_expire = tab_expire_count(id, title, time,seekBarValue.toInt(),location,k)
        dbHelper.insertExpiry(tab_expire)
        finish()
    }


    private fun initUi() {

        monday_morning_start_time_text.setOnClickListener {
            setupTimePicker(monday_morning_start_time_text)
        }
        monday_afternoon_start_time_text.setOnClickListener {
            setupTimePicker(monday_afternoon_start_time_text)
        }
        monday_evening_start_time_text.setOnClickListener {
            setupTimePicker(monday_evening_start_time_text)
        }
        monday_night_start_time_text.setOnClickListener{
            setupTimePicker(monday_night_start_time_text)
        }
        tuesday_morning_start_time_text.setOnClickListener {
            setupTimePicker(tuesday_morning_start_time_text)
        }
        tuesday_afternoon_start_time_text.setOnClickListener {
            setupTimePicker(tuesday_afternoon_start_time_text)
        }
        tuesday_evening_start_time_text.setOnClickListener {
            setupTimePicker(tuesday_evening_start_time_text)
        }
        tuesday_night_start_time_text.setOnClickListener{
            setupTimePicker(tuesday_night_start_time_text)
        }
        wednessday_morning_start_time_text.setOnClickListener {
            setupTimePicker(wednessday_morning_start_time_text)
        }
        wednessday_afternoon_start_time_text.setOnClickListener {
            setupTimePicker(wednessday_afternoon_start_time_text)
        }
        wednessday_evening_start_time_text.setOnClickListener {
            setupTimePicker(wednessday_evening_start_time_text)
        }
        wednessday_night_start_time_text.setOnClickListener{
            setupTimePicker(wednessday_night_start_time_text)
        }
        thursday_morning_start_time_text.setOnClickListener {
            setupTimePicker(thursday_morning_start_time_text)
        }
        thursday_afternoon_start_time_text.setOnClickListener {
            setupTimePicker(thursday_afternoon_start_time_text)
        }
        thursday_evening_start_time_text.setOnClickListener {
            setupTimePicker(thursday_evening_start_time_text)
        }
        thursday_night_start_time_text.setOnClickListener{
            setupTimePicker(thursday_night_start_time_text)
        }
        friday_morning_start_time_text.setOnClickListener {
            setupTimePicker(friday_morning_start_time_text)
        }
        friday_afternoon_start_time_text.setOnClickListener {
            setupTimePicker(friday_afternoon_start_time_text)
        }
        friday_evening_start_time_text.setOnClickListener {
            setupTimePicker(friday_evening_start_time_text)
        }
        friday_night_start_time_text.setOnClickListener{
            setupTimePicker(friday_night_start_time_text)
        }
        saturday_morning_start_time_text.setOnClickListener {
            setupTimePicker(saturday_morning_start_time_text)
        }
        saturday_afternoon_start_time_text.setOnClickListener {
            setupTimePicker(saturday_afternoon_start_time_text)
        }
        saturday_evening_start_time_text.setOnClickListener {
            setupTimePicker(saturday_evening_start_time_text)
        }
        saturday_night_start_time_text.setOnClickListener{
            setupTimePicker(saturday_night_start_time_text)
        }
        sunday_morning_start_time_text.setOnClickListener {
            setupTimePicker(sunday_morning_start_time_text)
        }
        sunday_afternoon_start_time_text.setOnClickListener {
            setupTimePicker(sunday_afternoon_start_time_text)
        }
        sunday_evening_start_time_text.setOnClickListener {
            setupTimePicker(sunday_evening_start_time_text)
        }
        sunday_night_start_time_text.setOnClickListener{
            setupTimePicker(sunday_night_start_time_text)
        }
        task_date.setOnClickListener {
            setupDatePicker(task_date)
        }
        task_time.setOnClickListener {
            setupTimePicker(task_time)
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
    override fun onDestroy() {

        if (config.notificationScheduleStatus)
            getNextNotificationSchedule()
        super.onDestroy()
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
