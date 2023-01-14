package id.samai.mymedcure.activities

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.squareup.picasso.Picasso
import id.samai.mymedcure.R
import id.samai.mymedcure.extensions.dbHelper
import kotlinx.android.synthetic.main.activity_tab_details_1.*

class Tab_details_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_details_1)
        val subname = getDataIntent(intent.extras!!)

        val monday_mor: ImageView = findViewById(R.id.monday_morning_icon)
        val monday_after: ImageView = findViewById(R.id.monday_after_icon)
        val monday_even: ImageView = findViewById(R.id.monday_even_icon)
        val monday_nig: ImageView = findViewById(R.id.monday_nig_icon)
        val tuesday_mor: ImageView = findViewById(R.id.tuesday_morning_icon)
        val tuesday_after: ImageView = findViewById(R.id.tuesday_after_icon)
        val tuesday_even: ImageView = findViewById(R.id.tuesday_even_icon)
        val tuesday_nig: ImageView = findViewById(R.id.tuesday_nig_icon)
        val wednessday_mor: ImageView = findViewById(R.id.wednessday_morning_icon)
        val wednessday_after: ImageView = findViewById(R.id.wednessday_after_icon)
        val wednessday_even: ImageView = findViewById(R.id.wednessday_even_icon)
        val wednessday_nig: ImageView = findViewById(R.id.wednessday_nig_icon)
        val thursday_mor: ImageView = findViewById(R.id.thursday_morning_icon)
        val thursday_after: ImageView = findViewById(R.id.thursday_after_icon)
        val thursday_even: ImageView = findViewById(R.id.thursday_even_icon)
        val thursday_nig: ImageView = findViewById(R.id.thursday_nig_icon)
        val friday_mor: ImageView = findViewById(R.id.friday_morning_icon)
        val friday_after: ImageView = findViewById(R.id.friday_after_icon)
        val friday_even: ImageView = findViewById(R.id.friday_even_icon)
        val friday_nig: ImageView = findViewById(R.id.friday_nig_icon)
        val saturday_mor: ImageView = findViewById(R.id.saturday_morning_icon)
        val saturday_after: ImageView = findViewById(R.id.saturday_after_icon)
        val saturday_even: ImageView = findViewById(R.id.saturday_even_icon)
        val saturday_nig: ImageView = findViewById(R.id.saturday_nig_icon)
        val sunday_mor: ImageView = findViewById(R.id.sunday_morning_icon)
        val sunday_after: ImageView = findViewById(R.id.sunday_after_icon)
        val sunday_even: ImageView = findViewById(R.id.sunday_even_icon)
        val sunday_nig: ImageView = findViewById(R.id.sunday_nig_icon)


        val bundle: Bundle? = intent.extras
        val link = bundle!!.getString("link")
        val week_count = bundle!!.getInt("week_count")
        val total = bundle!!.getInt("total")
        val expriry = bundle!!.getString("expiry")
        val quo = total/week_count
        val rem = week_count%7
        val str = quo.toString() + "\n" + "weeks"

        Picasso.get().load(link).into(imageview, object: com.squareup.picasso.Callback {
            override fun onSuccess() {
                //set animations heire
                informationprogress.setVisibility(View.GONE)
            }

            override fun onError(e: java.lang.Exception?) {
                informationprogress.setVisibility(View.VISIBLE)
            }
        })
        schedule_title2.text = subname
remdays.text = str
        val schedules = subname?.let { dbHelper.getdaydataname(it) }


        if (schedules != null) {
            if (schedules.first().monday == 1) {
                if (schedules.first().monday_morning == 1) {
                    monday_mor.setVisibility(View.VISIBLE)
                }
                if (schedules.first().monday_afternoon == 1) {
                    monday_after.setVisibility(View.VISIBLE)
                }
                if (schedules.first().monday_evening == 1) {
                    monday_even.setVisibility(View.VISIBLE)
                }
                if (schedules.first().monday_night == 1) {
                    monday_nig.setVisibility(View.VISIBLE)
                }
            }
            if (schedules.first().tuesday == 1) {
                if (schedules.first().tuesday_morning == 1) {
                    tuesday_mor.setVisibility(View.VISIBLE)
                }
                if (schedules.first().tuesday_afternoon == 1) {
                    tuesday_after.setVisibility(View.VISIBLE)
                }
                if (schedules.first().tuesday_evening == 1) {
                    tuesday_even.setVisibility(View.VISIBLE)
                }
                if (schedules.first().tuesday_night == 1) {
                    tuesday_nig.setVisibility(View.VISIBLE)
                }
            }
            if (schedules.first().wednessday == 1) {
                if (schedules.first().wednessday_morning == 1) {
                    wednessday_mor.setVisibility(View.VISIBLE)
                }
                if (schedules.first().wednessday_afternoon == 1) {
                    wednessday_after.setVisibility(View.VISIBLE)
                }
                if (schedules.first().wednessday_evening == 1) {
                    wednessday_even.setVisibility(View.VISIBLE)
                }
                if (schedules.first().wednessday_night == 1) {
                    wednessday_nig.setVisibility(View.VISIBLE)
                }
            }
            if (schedules.first().thursday == 1) {
                if (schedules.first().thursday_morning == 1) {
                    thursday_mor.setVisibility(View.VISIBLE)
                }
                if (schedules.first().thursday_afternoon == 1) {
                    thursday_after.setVisibility(View.VISIBLE)
                }
                if (schedules.first().thursday_evening == 1) {
                    thursday_even.setVisibility(View.VISIBLE)
                }
                if (schedules.first().thursday_night == 1) {
                    thursday_nig.setVisibility(View.VISIBLE)
                }
            }
            if (schedules.first().friday == 1) {
                if (schedules.first().friday_morning == 1) {
                    friday_mor.setVisibility(View.VISIBLE)
                }
                if (schedules.first().friday_afternoon == 1) {
                    friday_after.setVisibility(View.VISIBLE)
                }
                if (schedules.first().friday_evening == 1) {
                    friday_even.setVisibility(View.VISIBLE)
                }
                if (schedules.first().friday_night == 1) {
                    friday_nig.setVisibility(View.VISIBLE)
                }
            }
            if (schedules.first().saturday == 1) {
                if (schedules.first().saturday_morning == 1) {
                    saturday_mor.setVisibility(View.VISIBLE)
                }
                if (schedules.first().saturday_afternoon == 1) {
                    saturday_after.setVisibility(View.VISIBLE)
                }
                if (schedules.first().saturday_evening == 1) {
                    saturday_even.setVisibility(View.VISIBLE)
                }
                if (schedules.first().saturday_night == 1) {
                    saturday_nig.setVisibility(View.VISIBLE)
                }
            }
            if (schedules.first().sunday == 1) {
                if (schedules.first().sunday_morning == 1) {
                    sunday_mor.setVisibility(View.VISIBLE)
                }
                if (schedules.first().sunday_afternoon == 1) {
                    sunday_after.setVisibility(View.VISIBLE)
                }
                if (schedules.first().sunday_evening == 1) {
                    sunday_even.setVisibility(View.VISIBLE)
                }
                if (schedules.first().sunday_night == 1) {
                    sunday_nig.setVisibility(View.VISIBLE)
                }
            }
        }
        history.setOnClickListener {
            Intent(it.context, single_hist_tab::class.java).apply {
                val bundle = Bundle().apply {
                    putExtra("name", subname)
                    putExtra("expiry",expriry)

                }
                putExtras(bundle)
                it.context.startActivity(this)

            }
        }
        histside.setOnClickListener {
            Intent(it.context, single_hist_tab::class.java).apply {
                val bundle = Bundle().apply {
                    putExtra("name", subname)
                    putExtra("expiry",expriry)
                }
                putExtras(bundle)
                it.context.startActivity(this)

            }
        }
    }



    private fun getDataIntent(bundle: Bundle): String? {
        val subname = bundle.getString("SCHEDULE_TITLE")

        return subname
    }

}