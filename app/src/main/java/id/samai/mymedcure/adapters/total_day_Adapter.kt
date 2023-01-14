package id.samai.mymedcure.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.samai.mymedcure.R
import id.samai.mymedcure.helpers.DBHelper
import id.samai.mymedcure.helpers.DataBase2
import id.samai.mymedcure.helpers.Formatter
import id.samai.mymedcure.models.medic
import kotlinx.android.synthetic.main.itemholder.view.*
import java.text.SimpleDateFormat
import java.util.*


class total_day_Adapter(val context: Context, private val schedules: ArrayList<medic>) : RecyclerView.Adapter<total_day_Adapter.ViewHolder>() {
    var db = DataBase2(context)
    private var id: Int? = null
    val dbHelper: DBHelper get() = DBHelper.newInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.itemholder, parent, false)
    )

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schedules[position])
    }

    open inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        fun bind(schedule: medic) {
            var ink = "s"
            val c = Calendar.getInstance()
            val timeOfDay = schedule.timeStart/60

            if (timeOfDay >= 0 && timeOfDay < 11) {
                ink = "morning"
            } else if (timeOfDay >= 11 && timeOfDay < 16) {
                ink ="afternoon"
            } else if (timeOfDay >= 16 && timeOfDay < 20) {
                ink = "evening"
            } else if (timeOfDay >= 20 && timeOfDay < 24) {
                ink = "night"
            }



            view.schedule_list_title.text = schedule.title
            val date = Date()
            c.time = date
            //c.add(Calendar.YEAR, Calendar.YEAR);
            //c.add(Calendar.YEAR, Calendar.YEAR);
            val df = SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US)
            val dateTime = df.format(c.time).toString()

            val todaystat = dbHelper.getTabletdatebool(schedule.title, dateTime)
            view.percent.setImageResource(R.drawable.wrong4)
            if(!todaystat.isEmpty()) {
                if (todaystat.get(0).get(ink)== 1) {
                    view.percent.setImageResource(R.drawable.ok)

                }

            }
            val scheduleTime = "${Formatter.getTimeFromMinute(schedule.timeStart)} - ${
                Formatter.getTimeFromMinute(
                        schedule.timeEnd
                )}"
            view.schedule_list_time.text = scheduleTime

            try {
                val user = schedule.title

/*
                if(1>0) {
                    view.setBackgroundResource(R.drawable.greenrectangle)
                    view.percent.setBackgroundResource(R.drawable.greencircle)

                }
                else{
                    view.setBackgroundResource(R.drawable.redrectangle)
                    view.percent.setBackgroundResource(R.drawable.redcircle)

                }


 */

               // view.schedule_list_location.text = schedule.location

            }
            catch (e: Exception){

            }

        }}
}