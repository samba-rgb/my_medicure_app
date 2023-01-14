package id.samai.mymedcure.activities

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.slidetoact.SlideToActView
import com.ncorti.slidetoact.SlideToActView.*
import com.squareup.picasso.Picasso
import id.samai.mymedcure.R
import id.samai.mymedcure.extensions.dbHelper
import id.samai.mymedcure.helpers.DataBase2
import id.samai.mymedcure.models.TABLET
import kotlinx.android.synthetic.main.activity_medicine_1.*
import kotlinx.android.synthetic.main.activity_tab_details_1.*
import kotlinx.android.synthetic.main.activity_tab_details_2.*
import java.text.SimpleDateFormat
import java.util.*


class tab_details_2 : AppCompatActivity() {
    private var id: Int? = null
    private lateinit var historyTitles: Set<String>
    var db: DataBase2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_details_2)


        val bundle: Bundle? = intent.extras
        val link = bundle!!.getString("link")
        val name = bundle!!.getString("name")
        val info = bundle!!.getString("info")
        medcine_name.text = name.toString()
        info_tab_2.text = info.toString()
        Picasso.get().load(link).into(image_medicine, object : com.squareup.picasso.Callback {
            override fun onSuccess() {
                //set animations heire
                informationprogress_1.setVisibility(View.GONE)
            }

            override fun onError(e: java.lang.Exception?) {
                informationprogress_1.setVisibility(View.VISIBLE)
            }
        })

        val calendar = Calendar.getInstance()
        calendar.time = Date()


        val date = SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US).format(calendar.time)

        val schedules = dbHelper.getTabletdatebool(name.toString(), date)
        if(schedules.isEmpty()){
            val sam =  TABLET(id, name.toString(), date, 0, 0, 0, 0)
            dbHelper.insertTABLET_history(sam)
        }


    }



}