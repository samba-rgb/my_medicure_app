package id.samai.mymedcure.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.samai.mymedcure.R
import id.samai.mymedcure.activities.Tab_details_1
import id.samai.mymedcure.activities.view_medical_history
import id.samai.mymedcure.helpers.DBHelper
import id.samai.mymedcure.helpers.DataBase2
import id.samai.mymedcure.helpers.Formatter
import id.samai.mymedcure.models.MEDICAL_HIS
import id.samai.mymedcure.models.tab_expire_count
import kotlinx.android.synthetic.main.all_medicine_item.view.*
import kotlinx.android.synthetic.main.all_medicine_item.view.expiry_list_title
import kotlinx.android.synthetic.main.all_medicine_item.view.schedule_list_expiry_text
import kotlinx.android.synthetic.main.all_medicine_item.view.schedule_list_expiry_time
import kotlinx.android.synthetic.main.med_health_item.view.*
import java.util.ArrayList

class MEDICINE_HISTORY_Adapter (val context: Context, private val schedules: ArrayList<MEDICAL_HIS>) : RecyclerView.Adapter<MEDICINE_HISTORY_Adapter.ViewHolder>() {
    var db = DataBase2(context)
    private var id: Int? = null
    val dbHelper: DBHelper get() = DBHelper.newInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.med_health_item, parent, false)
    )

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schedules[position])
    }

    open inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        fun bind(schedule: MEDICAL_HIS) {

            view.medical_visit_number.text = schedule.title
            view.doctor_name_1.text = schedule.doctor_name
            view.visited_date_1.text =  schedule.visited_date
            view.visited_date_text.text =  "Visited date"

            view.setOnClickListener {
                Intent(it.context, view_medical_history::class.java).apply {
                    val bundle = Bundle().apply {
                        putExtra("SCHEDULE_TITLE", schedule.title)

                    }
                    putExtras(bundle)
                    it.context.startActivity(this)

                }
            }





        }}
}