package id.samai.mymedcure.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.samai.mymedcure.R
import id.samai.mymedcure.activities.tab_details_2_java
import id.samai.mymedcure.helpers.DBHelper
import id.samai.mymedcure.helpers.DataBase2
import id.samai.mymedcure.helpers.Formatter
import id.samai.mymedcure.models.medic
import kotlinx.android.synthetic.main.item_trail.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class morningAdapter(val context: Context, private val schedules: ArrayList<medic>) : RecyclerView.Adapter<morningAdapter.ViewHolder>() {
    var db = DataBase2(context)
    private var id: Int? = null
    val dbHelper: DBHelper get() = DBHelper.newInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_trail, parent, false)
    )

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schedules[position])
    }

    open inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        fun bind(schedule: medic) {


            view.schedule_list_title.text = schedule.title
            val c = Calendar.getInstance()
            val date = Date()
            c.time = date
            //c.add(Calendar.YEAR, Calendar.YEAR);
            //c.add(Calendar.YEAR, Calendar.YEAR);
            val df = SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US)
            val dateTime = df.format(c.time).toString()
            val todaystat = dbHelper.getTabletdatebool(schedule.title, dateTime)
            view.percent.setImageResource(R.drawable.wrong4)
            if(!todaystat.isEmpty()) {
                if (todaystat.get(0).morning == 1) {
                    view.percent.setImageResource(R.drawable.ok)

                }

            }
            val scheduleTime = "${Formatter.getTimeFromMinute(schedule.timeStart)}"
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
                    view.setOnClickListener {
                        Intent(it.context, tab_details_2_java::class.java).apply {
                            val bundle = Bundle().apply {
                                putExtra("name", schedule.title)
                                putExtra("link", schedule.location)
                                putExtra("info", schedule.info)
                                putExtra("update_info", "morning")

                            }
                            putExtras(bundle)
                            it.context.startActivity(this)

                        }
                    }

                }
                catch (e: Exception){

                }

            }}
    }