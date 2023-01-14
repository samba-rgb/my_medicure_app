package id.samai.mymedcure.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.samai.mymedcure.R
import id.samai.mymedcure.helpers.DBHelper
import kotlinx.android.synthetic.main.activity_view_medical_history.*

class view_medical_history : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_medical_history)

        val bundle: Bundle? = intent.extras
        val name = bundle!!.getString("SCHEDULE_TITLE")
        val dbHelper: DBHelper
        dbHelper = DBHelper(applicationContext)
        val schedules = dbHelper.getmedicalhisname(name.toString())
        if(!schedules.isEmpty()){
            val schedule = schedules.get(0)
            visited_number.setText(schedule.title)
            doctor_name.setText(schedule.doctor_name)
            doctor_observation.setText(schedule.doctor_observation)
            if(schedule.virtual==1){
                virtualswitch.isChecked == true
            }
            else{
                virtualswitch.isChecked == false
            }
            visited_date.setText(schedule.visited_date)
            bp_value.setText(schedule.bp)
            cell_count_value.setText(schedule.cell_count.toString())
            hemoglobin_value.setText(schedule.hemoglobin.toString())

        }

    }
}