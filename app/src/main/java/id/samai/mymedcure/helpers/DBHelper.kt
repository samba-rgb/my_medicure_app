package id.samai.mymedcure.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import id.samai.mymedcure.fragments.Schedule
import id.samai.mymedcure.helpers.DataBase2.table_name2
import id.samai.mymedcure.models.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.FileNotFoundException

class DBHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    /** Schedule Table **/
    private val MEDICINE_TABLE_NAME = "MEDICINE"
    private val MEDICINE_COL_ID = "id_MEDICINE"
    private val MEDICINE_COL_TITLE = "title_MEDICINE"
    private val MEDICINE_COL_LOCATION = "location_MEDICINE"
    private val MEDICINE_COL_INFO = "info_MEDICINE"
    private val MEDICINE_COL_DAY = "day_MEDICINE"
    private  val MEDICINE_COL_DAY_TIME = "day_time"
    private val MEDICINE_COL_TIME_START = "time_start_MEDICINE"
    private val MEDICINE_COL_TIME_END = "time_end_MEDICINE"

    /** tablet expiry and count **/
    private  val EXPIRY_TABLE_NAME  = "Expire_Details"
    private  val EXPIRY_COL_ID = "id_expiry"
    private  val EXPIRY_COL_TITLE = "title_medicine"
    private  val EXPIRY_COL_EXPIRY = "expiry_date"
    private  val EXPIRY_COL_COUNT = "count"
    private  val EXPIRY_COL_LINK = "link"
    private val EXPIRY_COL_WEEK  = "WEEK_COUNT"

    /**  table for tablets history  **/
    private  val TABLET_HISTORY_TABLE_NAME = "Tablet_history"
    private  val TABLET_HISTORY_ID = "id_tablet"
    private  val TABLET_DATE = "tablet_date"
    private  val TABLET_NAME = "tablet_name"
    private  val MORNING = "morning"
    private  val AFTERNOON = "afternoon"
    private  val EVENING = "evening"
    private  val NIGHT = "night"

/**           table for mediacal data         **/
    private val MEDICAL_RECORD_TABLE_NAME = "medical_records"
    private  val MEDICAL_COL_ID = "id_medical"
    private val MEDICAL_COL_TITLE = "medical_record_number"
    private  val MEDICAL_DOCTOR_NAME = "DOC_NAME"
    private val MEDICAL_VISITED_DATE  = "VISITED_DATE"
    private  val MEDICAL_DOCTOR_OBSERVATION = "doctor_observation"
    private  val BP_VALUE  ="bp_value"
    private  val CELL_COUNT = "cell_count"
    private  val HEMOGLOBIN_PERCENTAGE = "HHEMOGLOBIN_PER"
    private  val VIRTUAL_RECORD = "virtual_or_real"
/**  table for day_database **/
private val DAY_TABLE_NAME = "Day_data"
    private  val DAY_ID = "id_day_data"
    private  val colll_1 = "sname"
    private val colll_2 = "att_lec"
    private val colll_3 = "bunk_lec"
    private val colll_4 = "total_lec"
    private val colll_5 = "percent"
    private val colll_6 = "monday"
    private val colll_7 = "monday_morning"
    private val colll_8 = "monday_afternoon"
    private val colll_9 = "monday_evening"
    private val colll_10 = "monday_night"
    private val colll_11 = "tuesday"
    private val colll_12 = "tuesday_morning"
    private val colll_13 = "tuesday_afternoon"
    private val colll_14 = "tuesday_evening"
    private val colll_15 = "tuesday_night"
    private val colll_16 = "wednessday"
    private val colll_17 = "wednessday_morning"
    private val colll_18 = "wednessday_afternoon"
    private val colll_19 = "wednessday_evening"
    private val colll_20 = "wednessday_night"
    private val colll_21 = "thursday"
    private val colll_22 = "thursday_morning"
    private val colll_23 = "thursday_afternoon"
    private val colll_24 = "thursday_evening"
    private val colll_25 = "thursday_night"
    private val colll_26 = "friday"
    private val colll_27 = "friday_morning"
    private val colll_28 = "friday_afternoon"
    private val colll_29 = "friday_evening"
    private val colll_30 = "friday_night"
    private val colll_31 = "saturday"
    private val colll_32 = "saturday_morning"
    private val colll_33 = "saturday_afternoon"
    private val colll_34 = "saturday_evening"
    private val colll_35 = "saturday_night"
    private val colll_36 = "sunday"
    private val colll_37 = "sunday_morning"
    private val colll_38 = "sunday_afternoon"
    private val colll_39 = "sunday_evening"
    private val colll_40 = "sunday_night"

    /** table for medical version storage**/
    private  val VERSION_HISTORY_TABLE_NAME = "version_history"
    private  val VERSION_HISTORY_ID = "id_version"
    private  val VERSION_DATE = "version_date"
    private  val VERSION_END_DATE= "version_end_date"
    private  val VERSION_NUMBER = "version_name"
    private val  VERSION_LINK ="version_link"


    /** need to add insert and delete function update **/

    /** Handling DB **/




    private val mDb = writableDatabase

    companion object {
        private const val DB_VERSION = 2
        const val DB_NAME = "Medi_cure"
        var dbInstance: DBHelper? = null

        fun newInstance(context: Context): DBHelper {
            if (dbInstance == null) {
                dbInstance = DBHelper(context)
            }

            return dbInstance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(
            "CREATE TABLE $MEDICINE_TABLE_NAME (" +
                    "$MEDICINE_COL_ID INTEGER," +
                    "$MEDICINE_COL_TITLE TEXT," +
                    "$MEDICINE_COL_LOCATION TEXT," +
                    "$MEDICINE_COL_INFO TEXT," +
                    "$MEDICINE_COL_DAY INTEGER," +
                    "$MEDICINE_COL_DAY_TIME INTEGER," +
                    "$MEDICINE_COL_TIME_START INTEGER," +
                    "$MEDICINE_COL_TIME_END INTEGER," +
                    "PRIMARY KEY ($MEDICINE_COL_ID))"
        )

        db?.execSQL(
            """CREATE TABLE $EXPIRY_TABLE_NAME (
            |$EXPIRY_COL_ID INTEGER,
            |$EXPIRY_COL_TITLE TEXT,
            |$EXPIRY_COL_EXPIRY TEXT,
            |$EXPIRY_COL_COUNT INTEGER,
            |$EXPIRY_COL_LINK TEXT,
            |$EXPIRY_COL_WEEK INTEGER,
            |PRIMARY KEY ($EXPIRY_COL_ID)
            |)""".trimMargin()
        )
        db?.execSQL(
            """CREATE TABLE $MEDICAL_RECORD_TABLE_NAME (
            |$MEDICAL_COL_ID INTEGER,
            |$MEDICAL_COL_TITLE TEXT,
            |$MEDICAL_DOCTOR_NAME TEXT,
            |$MEDICAL_VISITED_DATE TEXT,
            |$MEDICAL_DOCTOR_OBSERVATION INTEGER,
            |$BP_VALUE TEXT,
            |$CELL_COUNT INTEGER,
            |$HEMOGLOBIN_PERCENTAGE INTEGER,
            |$VIRTUAL_RECORD INTEGER,
            |PRIMARY KEY ($MEDICAL_COL_ID)
            |)""".trimMargin()
        )
        db?.execSQL(
            """CREATE TABLE $TABLET_HISTORY_TABLE_NAME (
            |$TABLET_HISTORY_ID INTEGER,
            |$TABLET_NAME TEXT,
            |$TABLET_DATE TEXT,
            |$MORNING INTEGER,
            |$AFTERNOON INTEGER,
            |$EVENING INTEGER,
            |$NIGHT INTEGER,
            |PRIMARY KEY ($TABLET_HISTORY_ID)
            |)""".trimMargin()
        )

        db?.execSQL(
            """CREATE TABLE $DAY_TABLE_NAME (
            |$DAY_ID INTEGER,
            |$colll_1 TEXT,
            |$colll_2 INTEGER,
            |$colll_3 INTEGER,
            |$colll_4 INTEGER,
            |$colll_5 INTEGER,
            |$colll_6 INTEGER,
            |$colll_7 INTEGER,
            |$colll_8 INTEGER,
            |$colll_9 INTEGER,
            |$colll_10 INTEGER,
            |$colll_11 INTEGER,
            |$colll_12 INTEGER,
            |$colll_13 INTEGER,
            |$colll_14 INTEGER,
            |$colll_15 INTEGER,
            |$colll_16 INTEGER,
            |$colll_17 INTEGER,
            |$colll_18 INTEGER,
            |$colll_19 INTEGER,
            |$colll_20 INTEGER,
            |$colll_21 INTEGER,
            |$colll_22 INTEGER,
            |$colll_23 INTEGER,
            |$colll_24 INTEGER,
            |$colll_25 INTEGER,
            |$colll_26 INTEGER,
            |$colll_27 INTEGER,
            |$colll_28 INTEGER,
            |$colll_29 INTEGER,
            |$colll_30 INTEGER,
            |$colll_31 INTEGER,
            |$colll_32 INTEGER,
            |$colll_33 INTEGER,
            |$colll_34 INTEGER,
            |$colll_35 INTEGER,
            |$colll_36 INTEGER,
            |$colll_37 INTEGER,
            |$colll_38 INTEGER,
            |$colll_39 INTEGER,
            |$colll_40 INTEGER,

            |PRIMARY KEY ($DAY_ID)
            |)""".trimMargin()

        )
        db?.execSQL(
                """CREATE TABLE $VERSION_HISTORY_TABLE_NAME (
            |$VERSION_HISTORY_ID INTEGER,
            |$VERSION_NUMBER INTEGER,
            |$VERSION_DATE TEXT,
            |$VERSION_END_DATE TEXT,
            |$VERSION_LINK TEXT,
            
            |PRIMARY KEY ($VERSION_HISTORY_ID)
            |)""".trimMargin()
        )


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    fun insertVERSION_history(version : version) {
        val scheduleValues = fillScheduleContentValues_version_his(version)
        mDb.insertOrThrow(VERSION_HISTORY_TABLE_NAME, null, scheduleValues).toInt()
    }

    private fun fillScheduleContentValues_version_his(version: version)=  ContentValues().apply {

        put(VERSION_NUMBER, version.number)
        put(VERSION_DATE, version.Start_Date)
        put(VERSION_END_DATE, version.End_date)
        put(VERSION_LINK, version.link)

    }
    fun getVersiontdatebool(name: String, date: String): ArrayList<TABLET> {
        val schedules = ArrayList<TABLET>()
        var cursor: Cursor? = null
        val order = "$TABLET_HISTORY_ID"

        try {
            cursor = mDb.query(TABLET_HISTORY_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {

                    try {

                        val schedule = extractCursorTABLET_his(cursor)
                        if (schedule.title == name && schedule.DATE == date ){

                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }

    fun insertTABLET_history(tablet: TABLET) {
        val scheduleValues = fillScheduleContentValues_tablet_his(tablet)
        mDb.insertOrThrow(TABLET_HISTORY_TABLE_NAME, null, scheduleValues).toInt()
    }
    private fun fillScheduleContentValues_tablet_his(tablet: TABLET) = ContentValues().apply {
        put(TABLET_NAME, tablet.title)
        put(TABLET_DATE, tablet.DATE)
        put(MORNING, tablet.morning)
        put(AFTERNOON, tablet.afternoon)
        put(EVENING, tablet.evening)
        put(NIGHT, tablet.night)

    }
    private fun extractCursorTABLET_his(cursor: Cursor): TABLET {
        val positionId = cursor.getColumnIndex(TABLET_HISTORY_ID)
        val positionTitle = cursor.getColumnIndex(TABLET_NAME)
        val positiondate = cursor.getColumnIndex(TABLET_DATE)
        val positionmorning = cursor.getColumnIndex(MORNING)
        val positionafternoon = cursor.getColumnIndex(AFTERNOON)
        val positionevening = cursor.getColumnIndex(EVENING)
        val positionnight = cursor.getColumnIndex(NIGHT)


        val id = cursor.getInt(positionId)
        val title = cursor.getString(positionTitle)
        val date = cursor.getString(positiondate)
        val morning_1 = cursor.getInt(positionmorning)
        val afternoon_1 = cursor.getInt(positionafternoon)
        val  evening_1 = cursor.getInt(positionevening)
        val night_1 = cursor.getInt(positionnight)

        return TABLET(id, title, date, morning_1, afternoon_1, evening_1, night_1)
    }

    fun getTabletdatebool(name: String, date: String): ArrayList<TABLET> {
        val schedules = ArrayList<TABLET>()
        var cursor: Cursor? = null
        val order = "$TABLET_HISTORY_ID"

        try {
            cursor = mDb.query(TABLET_HISTORY_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {

                    try {

                        val schedule = extractCursorTABLET_his(cursor)
                        if (schedule.title == name && schedule.DATE == date ){

                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }


    fun insertMedical_history(tablet: MEDICAL_HIS, db: SQLiteDatabase = mDb) {
        val scheduleValues = fillScheduleContentValues_Medical_his(tablet)
        db.insertOrThrow(MEDICAL_RECORD_TABLE_NAME, null, scheduleValues).toInt()
    }
    private fun fillScheduleContentValues_Medical_his(tablet: MEDICAL_HIS) = ContentValues().apply {
        put(MEDICAL_COL_TITLE, tablet.title)
        put(MEDICAL_DOCTOR_NAME, tablet.doctor_name)
        put(MEDICAL_VISITED_DATE, tablet.visited_date)
        put(MEDICAL_DOCTOR_OBSERVATION, tablet.doctor_observation)
        put(BP_VALUE, tablet.bp)
        put(CELL_COUNT, tablet.cell_count)
        put(HEMOGLOBIN_PERCENTAGE, tablet.hemoglobin)
        put(VIRTUAL_RECORD, tablet.virtual)
    }

    private fun extractCursorMedical_his(cursor: Cursor): MEDICAL_HIS {
        val positionId = cursor.getColumnIndex(MEDICAL_COL_ID)
        val positionTitle = cursor.getColumnIndex(MEDICAL_COL_TITLE)
        val positiondoctor = cursor.getColumnIndex(MEDICAL_DOCTOR_NAME)
        val positionvisited = cursor.getColumnIndex(MEDICAL_VISITED_DATE)
        val positionobservation = cursor.getColumnIndex(MEDICAL_DOCTOR_OBSERVATION)
        val positionbp = cursor.getColumnIndex(BP_VALUE)
        val positioncell = cursor.getColumnIndex(CELL_COUNT)
        val positionhemoglobin = cursor.getColumnIndex(HEMOGLOBIN_PERCENTAGE)
        val positionvirtal = cursor.getColumnIndex(VIRTUAL_RECORD)


        val id = cursor.getInt(positionId)
        val title = cursor.getString(positionTitle)
        val doctor = cursor.getString(positiondoctor)
        val visi = cursor.getString(positionvisited)
        val observation = cursor.getString(positionobservation)
        val  bp = cursor.getInt(positionbp)
        val count = cursor.getInt(positioncell)
        val hemoglobin = cursor.getInt(positionhemoglobin)
        val virtual = cursor.getInt(positionvirtal)

        return MEDICAL_HIS(
            id,
            title,
            doctor,
            visi,
            observation,
            bp.toString(),
            count,
            hemoglobin,
            virtual
        )
    }
    fun getallmedical_his(day: Int): ArrayList<MEDICAL_HIS> {
        val schedules = ArrayList<MEDICAL_HIS>()
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(MEDICAL_RECORD_TABLE_NAME, null, null, null, null, null, null)
            if (cursor?.moveToFirst() == true) {
                do {

                    try {
                        val schedule = extractCursorMedical_his(cursor)

                        schedules.add(schedule)
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }
    fun insertDaydata(tablet: User, db: SQLiteDatabase = mDb) {
        val scheduleValues = fillScheduleContentValues_day_data(tablet)
        db.insertOrThrow(DAY_TABLE_NAME, null, scheduleValues).toInt()
    }
    /*
    data class User (val title: String,  val attend: Int, val bunk: Int, val percent: Float,val minpercent: Float,val total : Int,val monday : Int,val monday_morning : Int,val monday_afternoon : Int,val monday_evening: Int,val monday_night : Int,val tuesday : Int,val tuesday_morning : Int,val tuesday_afternoon : Int,val tuesday_evening: Int,val tuesday_night : Int,val wednessday : Int,val wednessday_morning : Int,val wednessday_afternoon : Int,val wednessday_evening: Int,val wednessday_night : Int,val thursday : Int,val thursday_morning : Int,val thursday_afternoon : Int,val thursday_evening: Int,val thursday_night : Int,val friday : Int,val friday_morning : Int,val friday_afternoon : Int,val friday_evening: Int,val friday_night : Int,val saturday : Int,val saturday_morning : Int,val saturday_afternoon : Int,val saturday_evening: Int,val saturday_night : Int,val sunday : Int,val sunday_morning : Int,val sunday_afternoon : Int,val sunday_evening: Int,val sunday_night : Int)

     */
    fun getdaydataname(name: String): ArrayList<User> {
        val schedules = ArrayList<User>()
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(DAY_TABLE_NAME, null, null, null, null, null, null)
            if (cursor?.moveToFirst() == true) {
                do {
                    try {
                        val schedule = extractCursorday_data(cursor)
                        if (schedule.title == name){
                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }
    private fun fillScheduleContentValues_day_data(tablet: User) = ContentValues().apply {
        put(colll_1, tablet.title)
        put(colll_2, tablet.attend)
        put(colll_3, tablet.bunk)
        put(colll_4, tablet.percent)
        put(colll_5, tablet.total)
        put(colll_6, tablet.monday)
        put(colll_7, tablet.monday_morning)
        put(colll_8, tablet.monday_afternoon)
        put(colll_9, tablet.monday_evening)
        put(colll_10, tablet.monday_night)
        put(colll_11, tablet.tuesday)
        put(colll_12, tablet.tuesday_morning)
        put(colll_13, tablet.tuesday_afternoon)
        put(colll_14, tablet.tuesday_evening)
        put(colll_15, tablet.tuesday_night)
        put(colll_16, tablet.wednessday)
        put(colll_17, tablet.wednessday_morning)
        put(colll_18, tablet.wednessday_afternoon)
        put(colll_19, tablet.wednessday_evening)
        put(colll_20, tablet.wednessday_night)
        put(colll_21, tablet.thursday)
        put(colll_22, tablet.thursday_morning)
        put(colll_23, tablet.thursday_afternoon)
        put(colll_24, tablet.thursday_evening)
        put(colll_25, tablet.thursday_night)
        put(colll_26, tablet.friday)
        put(colll_27, tablet.friday_morning)
        put(colll_28, tablet.friday_afternoon)
        put(colll_29, tablet.friday_evening)
        put(colll_30, tablet.friday_night)
        put(colll_31, tablet.saturday)
        put(colll_32, tablet.saturday_morning)
        put(colll_33, tablet.saturday_afternoon)
        put(colll_34, tablet.saturday_evening)
        put(colll_35, tablet.saturday_night)
        put(colll_36, tablet.sunday)
        put(colll_37, tablet.sunday_morning)
        put(colll_38, tablet.sunday_afternoon)
        put(colll_39, tablet.sunday_evening)
        put(colll_40, tablet.sunday_night)
    }
    private fun extractCursorday_data(cursor: Cursor): User {
        val positionId = cursor.getColumnIndex(DAY_ID)
        val positionname = cursor.getColumnIndex(colll_1)
        val positionattend = cursor.getColumnIndex(colll_2)
        val positionbunk = cursor.getColumnIndex(colll_3)
        val positionoercent = cursor.getColumnIndex(colll_4)
        val positiontotal = cursor.getColumnIndex(colll_5)
        val monday = cursor.getColumnIndex(colll_6)
        val monday_mor = cursor.getColumnIndex(colll_7)
        val monday_after = cursor.getColumnIndex(colll_8)
        val monday_even = cursor.getColumnIndex(colll_9)
        val monday_nig = cursor.getColumnIndex(colll_10)
        val tuesday = cursor.getColumnIndex(colll_11)
        val tuesday_mor = cursor.getColumnIndex(colll_12)
        val tuesday_after = cursor.getColumnIndex(colll_13)
        val tuesday_even = cursor.getColumnIndex(colll_14)
        val tuesday_nig = cursor.getColumnIndex(colll_15)
        val wednessday = cursor.getColumnIndex(colll_16)
        val wednessday_mor = cursor.getColumnIndex(colll_17)
        val wednessday_after = cursor.getColumnIndex(colll_18)
        val wednessday_even = cursor.getColumnIndex(colll_19)
        val wednessday_nig = cursor.getColumnIndex(colll_20)
        val thursday = cursor.getColumnIndex(colll_21)
        val thursday_mor = cursor.getColumnIndex(colll_22)
        val thursday_after = cursor.getColumnIndex(colll_23)
        val thursday_even = cursor.getColumnIndex(colll_24)
        val thursday_nig = cursor.getColumnIndex(colll_25)
        val friday = cursor.getColumnIndex(colll_26)
        val friday_mor = cursor.getColumnIndex(colll_27)
        val friday_after = cursor.getColumnIndex(colll_28)
        val friday_even = cursor.getColumnIndex(colll_29)
        val friday_nig = cursor.getColumnIndex(colll_30)
        val saturday = cursor.getColumnIndex(colll_31)
        val saturday_mor = cursor.getColumnIndex(colll_32)
        val saturday_after = cursor.getColumnIndex(colll_33)
        val saturday_even = cursor.getColumnIndex(colll_34)
        val saturday_nig = cursor.getColumnIndex(colll_35)
        val sunday = cursor.getColumnIndex(colll_36)
        val sunday_mor = cursor.getColumnIndex(colll_37)
        val sunday_after = cursor.getColumnIndex(colll_38)
        val sunday_even = cursor.getColumnIndex(colll_39)
        val sunday_nig = cursor.getColumnIndex(colll_40)


        val id = cursor.getInt(positionId)
        val title = cursor.getString(positionname)
        val attend = cursor.getInt(positionattend)
        val bunk = cursor.getInt(positionbunk)
        val percent = cursor.getInt(positionoercent)
        val total = cursor.getInt(positiontotal)
        val monday1 = cursor.getInt(monday)
        val monday_mor1 = cursor.getInt(monday_mor)
        val monday_after1 = cursor.getInt(monday_after)
        val monday_even1 = cursor.getInt(monday_even)
        val monday_nig1 = cursor.getInt(monday_nig)
        val tuesday1 = cursor.getInt(tuesday)
        val tuesday_mor1 = cursor.getInt(tuesday_mor)
        val tuesday_after1 = cursor.getInt(tuesday_after)
        val tuesday_even1 = cursor.getInt(tuesday_even)
        val tuesday_nig1 = cursor.getInt(tuesday_nig)
        val wednessday1 = cursor.getInt(wednessday)
        val wednessday_mor1 = cursor.getInt(wednessday_mor)
        val wednessday_after1 = cursor.getInt(wednessday_after)
        val wednessday_even1 = cursor.getInt(wednessday_even)
        val wednessday_nig1 = cursor.getInt(wednessday_nig)
        val thursday1 = cursor.getInt(thursday)
        val thursday_mor1 = cursor.getInt(thursday_mor)
        val thursday_after1 = cursor.getInt(thursday_after)
        val thursday_even1 = cursor.getInt(thursday_even)
        val thursday_nig1 = cursor.getInt(thursday_nig)
        val friday1 = cursor.getInt(friday)
        val friday_mor1 = cursor.getInt(friday_mor)
        val friday_after1 = cursor.getInt(friday_after)
        val friday_even1 = cursor.getInt(friday_even)
        val friday_nig1 = cursor.getInt(friday_nig)
        val saturday1 = cursor.getInt(saturday)
        val saturday_mor1 = cursor.getInt(saturday_mor)
        val saturday_after1 = cursor.getInt(saturday_after)
        val saturday_even1 = cursor.getInt(saturday_even)
        val saturday_nig1 = cursor.getInt(saturday_nig)
        val sunday1 = cursor.getInt(sunday)
        val sunday_mor1 = cursor.getInt(sunday_mor)
        val sunday_after1 = cursor.getInt(sunday_after)
        val sunday_even1 = cursor.getInt(sunday_even)
        val sunday_nig1 = cursor.getInt(sunday_nig)


        return User(
            id,
            title,
            attend,
            bunk,
            percent,
            total,
            monday1,
            monday_mor1,
            monday_after1,
            monday_even1,
            monday_nig1,
            tuesday1,
            tuesday_mor1,
            tuesday_after1,
            tuesday_even1,
            tuesday_nig1,
            wednessday1,
            wednessday_mor1,
            wednessday_after1,
            wednessday_even1,
            wednessday_nig1,
            thursday1,
            thursday_mor1,
            thursday_after1,
            thursday_even1,
            thursday_nig1,
            friday1,
            friday_mor1,
            friday_after1,
            friday_even1,
            friday_nig1,
            saturday1,
            saturday_mor1,
            saturday_after1,
            saturday_even1,
            saturday_nig1,
            sunday1,
            sunday_mor1,
            sunday_after1,
            sunday_even1,
            sunday_nig1
        )
    }
    fun insertExpiry(tablet: tab_expire_count, db: SQLiteDatabase = mDb) {
        val scheduleValues = fillScheduleContentValues_expiry(tablet)
        db.insertOrThrow(EXPIRY_TABLE_NAME, null, scheduleValues).toInt()
    }
    private fun fillScheduleContentValues_expiry(tablet: tab_expire_count) = ContentValues().apply {
        put(EXPIRY_COL_TITLE, tablet.title)
        put(EXPIRY_COL_EXPIRY, tablet.expiry)
        put(EXPIRY_COL_COUNT, tablet.count)
        put(EXPIRY_COL_LINK, tablet.link)
        put(EXPIRY_COL_WEEK, tablet.week_count)
    }

    private fun extractCursorexpiry(cursor: Cursor): tab_expire_count {
        val positionId = cursor.getColumnIndex(EXPIRY_COL_ID)
        val positionTitle = cursor.getColumnIndex(EXPIRY_COL_TITLE)
        val positionexpiry = cursor.getColumnIndex(EXPIRY_COL_EXPIRY)
        val positioncount = cursor.getColumnIndex(EXPIRY_COL_COUNT)
        val positionlink = cursor.getColumnIndex(EXPIRY_COL_LINK)
        val positionweek = cursor.getColumnIndex(EXPIRY_COL_WEEK)


        val id = cursor.getInt(positionId)
        val title = cursor.getString(positionTitle)
        val expiry = cursor.getString(positionexpiry)
        val count = cursor.getInt(positioncount)
        val link = cursor.getString(positionlink)
        val week = cursor.getInt(positionweek)

        return tab_expire_count(id, title, expiry, count, link, week)
    }
    fun getallmedicine(day: Int): ArrayList<tab_expire_count> {
        val schedules = ArrayList<tab_expire_count>()
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(EXPIRY_TABLE_NAME, null, null, null, null, null, null)
            if (cursor?.moveToFirst() == true) {
                do {

                    try {
                        val schedule = extractCursorexpiry(cursor)

                            schedules.add(schedule)
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }

    fun insertMedicine(medicine: medic, db: SQLiteDatabase = mDb) {
        val scheduleValues = fillScheduleContentValues_medicine(medicine)
        db.insertOrThrow(MEDICINE_TABLE_NAME, null, scheduleValues).toInt()
    }
    private fun fillScheduleContentValues_medicine(medicine: medic) = ContentValues().apply {
        put(MEDICINE_COL_TITLE, medicine.title)
        put(MEDICINE_COL_LOCATION, medicine.location)
        put(MEDICINE_COL_INFO, medicine.info)
        put(MEDICINE_COL_DAY, medicine.day)
        put(MEDICINE_COL_DAY_TIME, medicine.daytim)
        put(MEDICINE_COL_TIME_START, medicine.timeStart)
        put(MEDICINE_COL_TIME_END, medicine.timeEnd)
    }
    fun getScheduleMorning(day: Int): ArrayList<medic> {
        val schedules = ArrayList<medic>()
        val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(MEDICINE_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {
                    try {
                        val schedule = extractCursorSchedule(cursor)
                        if (schedule.day == day && schedule.daytim ==1 ){
                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }
    private fun extractCursorSchedule(cursor: Cursor): medic {
        val positionId = cursor.getColumnIndex(MEDICINE_COL_ID)
        val positionTitle = cursor.getColumnIndex(MEDICINE_COL_TITLE)
        val positionLocation = cursor.getColumnIndex(MEDICINE_COL_LOCATION)
        val positionInfo = cursor.getColumnIndex(MEDICINE_COL_INFO)
        val positionDay = cursor.getColumnIndex(MEDICINE_COL_DAY)
        val positionTime = cursor.getColumnIndex(MEDICINE_COL_DAY_TIME)
        val positionTimeStart = cursor.getColumnIndex(MEDICINE_COL_TIME_START)
        val positionTimeEnd = cursor.getColumnIndex(MEDICINE_COL_TIME_END)

        val id = cursor.getInt(positionId)
        val title = cursor.getString(positionTitle)
        val location = cursor.getString(positionLocation)
        val info = cursor.getString(positionInfo)
        val day = cursor.getInt(positionDay)
        val daytim = cursor.getInt(positionTime)
        val timeStart = cursor.getInt(positionTimeStart)
        val timeEnd = cursor.getInt(positionTimeEnd)

        return medic(id, title, location, info, day, daytim, timeStart, timeEnd)
    }

    fun getScheduleAfternoon(day: Int): ArrayList<medic> {
        val schedules = ArrayList<medic>()
        val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(MEDICINE_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {
                    try {
                        val schedule = extractCursorSchedule(cursor)
                        if (schedule.day == day && schedule.daytim ==2 ){
                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }

    fun getScheduleEvening(day: Int): ArrayList<medic> {
        val schedules = ArrayList<medic>()
        val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(MEDICINE_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {
                    try {
                        val schedule = extractCursorSchedule(cursor)
                        if (schedule.day == day && schedule.daytim ==3 ){
                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules

    }

    fun getScheduleNight(day: Int): ArrayList<medic> {
        val schedules = ArrayList<medic>()
        val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(MEDICINE_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {
                    try {
                        val schedule = extractCursorSchedule(cursor)
                        if (schedule.day == day && schedule.daytim ==4 ){
                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules


    }
    fun getScheduleDay(day: Int): ArrayList<medic> {
        val schedules = ArrayList<medic>()
        val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(MEDICINE_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {
                    try {
                        val schedule = extractCursorSchedule(cursor)
                        if (schedule.day == day ){
                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules


    }
    fun query(table_name2: String, o: Any?): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("Select * from $table_name2", null)
    }

    fun attend(subname: String, date: String, time: String) {  //to attend
        val db = this.writableDatabase
        val query =
            "UPDATE " + TABLET_HISTORY_TABLE_NAME + " SET " + time + " = " + time + " + 1 " + " WHERE " + TABLET_NAME + " = " + "'" + subname + "'" + "AND " + TABLET_DATE + " = " + "'" + date + "'"
        db.execSQL(query)
    }

    @Throws(JSONException::class, FileNotFoundException::class)
    fun getAllDataAndGenerateJSON(): JSONObject {
        val db = this.writableDatabase
        val c = db.rawQuery("Select * from $TABLET_HISTORY_TABLE_NAME", null)
        c!!.moveToFirst()
        val Root = JSONObject()
        val ContactArray = JSONArray()
        var i = 0
        while (!c.isAfterLast) {
            val contact = JSONObject()
            try {
                contact.put("ID", c.getString(c.getColumnIndex(TABLET_HISTORY_ID)))
                contact.put("NAME", c.getString(c.getColumnIndex(TABLET_NAME)))
                contact.put("DATE", c.getString(c.getColumnIndex(TABLET_DATE)))
                contact.put("morning", c.getString(c.getColumnIndex(MORNING)))
                contact.put("afternoon", c.getString(c.getColumnIndex(AFTERNOON)))
                contact.put("evening", c.getString(c.getColumnIndex(EVENING)))
                contact.put("night", c.getString(c.getColumnIndex(NIGHT)))

                c.moveToNext()
                ContactArray.put(i, contact)
                i++
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        Root.put("CONTACTDETAILS", ContactArray)
        return Root
    }

    fun getAllDataAndGenerateJSONmain(): JSONObject {
        val db = this.writableDatabase
        val c = db.rawQuery("Select * from $DAY_TABLE_NAME", null)
        c!!.moveToFirst()
        val Root = JSONObject()
        val ContactArray = JSONArray()
        var i = 0
        while (!c.isAfterLast) {
            val contact = JSONObject()
            try {
                contact.put("ID", c.getString(c.getColumnIndex(DAY_ID)))
                contact.put("NAME", c.getString(c.getColumnIndex(colll_1)))
                contact.put("ATTENDED", c.getString(c.getColumnIndex(colll_2)))
                contact.put("BUNKED", c.getString(c.getColumnIndex(colll_3)))
                contact.put("total", c.getString(c.getColumnIndex(colll_4)))
                contact.put("percent", c.getString(c.getColumnIndex(colll_5)))
                contact.put("monday", c.getString(c.getColumnIndex(colll_6)))
                contact.put("monday_morning",c.getString(c.getColumnIndex(colll_7)))
                contact.put("monday_afternoon",c.getString(c.getColumnIndex(colll_8)))
                contact.put("monday_evening",c.getString(c.getColumnIndex(colll_9)))
                contact.put("monday_night",c.getString(c.getColumnIndex(colll_10)))
                contact.put("tuesday", c.getString(c.getColumnIndex(colll_11)))
                contact.put("tuesday_morning",c.getString(c.getColumnIndex(colll_12)))
                contact.put("tuesday_afternoon",c.getString(c.getColumnIndex(colll_13)))
                contact.put("tuesday_evening",c.getString(c.getColumnIndex(colll_14)))
                contact.put("tuesday_night",c.getString(c.getColumnIndex(colll_15)))
                contact.put("wednessday", c.getString(c.getColumnIndex(colll_16)))
                contact.put("wednessday_morning",c.getString(c.getColumnIndex(colll_17)))
                contact.put("wednessday_afternoon",c.getString(c.getColumnIndex(colll_18)))
                contact.put("wednessday_evening",c.getString(c.getColumnIndex(colll_19)))
                contact.put("wednessday_night",c.getString(c.getColumnIndex(colll_20)))
                contact.put("thursday", c.getString(c.getColumnIndex(colll_21)))
                contact.put("thursday_morning",c.getString(c.getColumnIndex(colll_22)))
                contact.put("thursday_afternoon",c.getString(c.getColumnIndex(colll_23)))
                contact.put("thursday_evening",c.getString(c.getColumnIndex(colll_24)))
                contact.put("thursday_night",c.getString(c.getColumnIndex(colll_25)))
                contact.put("friday", c.getString(c.getColumnIndex(colll_26)))
                contact.put("friday_morning",c.getString(c.getColumnIndex(colll_27)))
                contact.put("friday_afternoon",c.getString(c.getColumnIndex(colll_28)))
                contact.put("friday_evening",c.getString(c.getColumnIndex(colll_29)))
                contact.put("friday_night",c.getString(c.getColumnIndex(colll_30)))
                contact.put("saturday", c.getString(c.getColumnIndex(colll_31)))
                contact.put("saturday_morning",c.getString(c.getColumnIndex(colll_32)))
                contact.put("saturday_afternoon",c.getString(c.getColumnIndex(colll_33)))
                contact.put("saturday_evening",c.getString(c.getColumnIndex(colll_34)))
                contact.put("saturday_night",c.getString(c.getColumnIndex(colll_35)))
                contact.put("sunday", c.getString(c.getColumnIndex(colll_36)))
                contact.put("sunday_morning",c.getString(c.getColumnIndex(colll_37)))
                contact.put("sunday_afternoon",c.getString(c.getColumnIndex(colll_38)))
                contact.put("sunday_evening",c.getString(c.getColumnIndex(colll_39)))
                contact.put("sunday_night",c.getString(c.getColumnIndex(colll_40)))
                c.moveToNext()
                ContactArray.put(i, contact)
                i++
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        Root.put("CONTACTDETAILS", ContactArray)
        return Root
    }

    fun degrade(subname: String) {  //to attend
        val db = this.writableDatabase
        val query =
                "UPDATE " + EXPIRY_TABLE_NAME + " SET " + EXPIRY_COL_COUNT + " = " + EXPIRY_COL_COUNT + " - 1 " + " WHERE " + EXPIRY_COL_TITLE + " = " + "'" + subname + "'"
        db.execSQL(query)
    }

    private fun sameDayNextTime(day: Int, time: Int): medic? {
        var schedule: medic? = null
        var cursor: Cursor? = null
        try {
            val selection = "$MEDICINE_COL_DAY == ? AND $MEDICINE_COL_TIME_START > ?"
            val selectionArgs = arrayOf(day.toString(), time.toString())
            val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
            val limit = "1"
            cursor = mDb.query(MEDICINE_TABLE_NAME,
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    order,
                    limit)

            if (cursor?.moveToFirst() == true) {
                schedule = extractCursorSchedule(cursor)
            }
        } finally {
            cursor?.close()
        }

        return schedule
    }

    private fun nextDay(day: Int): medic? {
        var schedule: medic? = null
        var cursor: Cursor? = null
        try {
            val selection =  "$MEDICINE_COL_DAY> ?"
            val selectionArgs = arrayOf(day.toString())
            val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
            val limit = "1"
            cursor = mDb.query(MEDICINE_TABLE_NAME,
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    order,
                    limit)

            if (cursor?.moveToFirst() == true) {
                schedule = extractCursorSchedule(cursor)
            }
        } finally {
            cursor?.close()
        }

        return schedule
    }

    private fun nextWeek(): medic? {
        var schedule: medic? = null
        var cursor: Cursor? = null
        try {
            val order = "$MEDICINE_COL_DAY, $MEDICINE_COL_TIME_START"
            val limit = "1"
            cursor = mDb.query(MEDICINE_TABLE_NAME, null, null, null, null, null, order, limit)
            if (cursor?.moveToFirst() == true) {
                schedule = extractCursorSchedule(cursor)
            }
        } finally {
            cursor?.close()
        }

        return schedule
    }





    private fun oneSchedule(cursor: Cursor): medic? {
        var schedule: medic? = null
        if (cursor.moveToFirst()) {
            schedule = extractCursorSchedule(cursor)
        }

        return schedule
    }

    private fun moreThenOne(day: Int, time: Int): medic? = sameDayNextTime(day, time) ?: nextDay(
            day) ?: nextWeek()

    fun getNextSchedule(day: Int, time: Int): medic? {
        var schedule: medic? = null
        var cursor: Cursor? = null
        try {
            cursor = mDb.query(MEDICINE_TABLE_NAME, null, null, null, null, null, null)
            val count = cursor.count
            schedule = when (count) {
                0 -> null
                1 -> oneSchedule(cursor)
                else -> moreThenOne(day, time)
            }
        } finally {
            cursor?.close()
        }

        return schedule
    }

    fun getTabletdateboolday(name: String, date: String,time : String): ArrayList<TABLET> {
        val schedules = ArrayList<TABLET>()
        var cursor: Cursor? = null
        val order = "$TABLET_HISTORY_ID"

        try {
            cursor = mDb.query(TABLET_HISTORY_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {

                    try {

                        val schedule = extractCursorTABLET_his(cursor)
                        if (schedule.title == name && schedule.DATE == date){

                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }

    fun getmedicalhisname(name: String): ArrayList<MEDICAL_HIS> {
        val schedules = ArrayList<MEDICAL_HIS>()
        var cursor: Cursor? = null
        val order = "$MEDICAL_COL_ID"

        try {
            cursor = mDb.query(MEDICAL_RECORD_TABLE_NAME, null, null, null, null, null, order)
            if (cursor?.moveToFirst() == true) {
                do {

                    try {

                        val schedule = extractCursorMedical_his(cursor)
                        if (schedule.title == name ){

                            schedules.add(schedule)}
                    } catch (e: Exception) {
                        continue
                    }
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        return schedules
    }

}

