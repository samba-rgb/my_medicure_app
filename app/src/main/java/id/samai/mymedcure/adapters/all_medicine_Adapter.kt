package id.samai.mymedcure.adapters
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import id.samai.mymedcure.R
import id.samai.mymedcure.activities.Tab_details_1
import id.samai.mymedcure.helpers.DBHelper
import id.samai.mymedcure.helpers.DataBase2
import id.samai.mymedcure.helpers.Formatter
import id.samai.mymedcure.helpers.Formatter.getStringTimeTask
import id.samai.mymedcure.models.tab_expire_count
import kotlinx.android.synthetic.main.all_medicine_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class all_medicine_Adapter (val context: Context, private val schedules: ArrayList<tab_expire_count>) : RecyclerView.Adapter<all_medicine_Adapter.ViewHolder>() {
    var db = DataBase2(context)
    private var id: Int? = null
    val dbHelper: DBHelper get() = DBHelper.newInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.all_medicine_item, parent, false)
    )

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schedules[position])
    }

    open inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        fun bind(schedule: tab_expire_count) {

            view.expiry_list_title.text = schedule.title
            view.schedule_list_count_val.text = schedule.count.toString()
            view.schedule_list_expiry_time.text =  schedule.expiry.toString()
            view.schedule_list_expiry_text.text = "Expiry date"
            view.schedule_list_count_text.text = "count"

            view.setOnClickListener {
                Intent(it.context, Tab_details_1::class.java).apply {
                    val bundle = Bundle().apply {
                        putExtra("SCHEDULE_TITLE", schedule.title)
                        putExtra("link",schedule.link)
                        putExtra("week_count",schedule.week_count)
                        putExtra("total",schedule.count)
                        putExtra("expiry",schedule.expiry)
                    }
                    putExtras(bundle)
                    it.context.startActivity(this)

                }
            }



        }}
}