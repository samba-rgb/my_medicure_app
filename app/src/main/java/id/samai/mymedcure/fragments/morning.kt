package id.samai.mymedcure.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.switchmaterial.SwitchMaterial
import id.samai.mymedcure.R
import id.samai.mymedcure.adapters.morningAdapter
import id.samai.mymedcure.extensions.dbHelper
import id.samai.mymedcure.helpers.Formatter
import kotlinx.android.synthetic.main.fragment_morning.*
import kotlinx.android.synthetic.main.content.drawer_layout
import kotlinx.android.synthetic.main.fragment_schedule.view.schedule_empty_layout
import kotlinx.android.synthetic.main.fragment_schedule.view.schedule_recycler_view_list

/**
 * A simple [Fragment] subclass.
 * Use the [morning.newInstance] factory method to
 * create an instance of this fragment.
 */
class morning : Fragment() {
    lateinit var view: ViewGroup
    lateinit var diceImage : ImageView
     lateinit var drawerLayout: DrawerLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = (inflater.inflate(R.layout.fragment_morning, container, false) as ViewGroup).apply {



            initRecylerView(this)



        }

        return view
    }

    override fun onResume() {
        initRecylerView(view)

        super.onResume()
    }


    private fun initRecylerView(view: View) {
        view.apply {
            val day = Formatter.getdayforfragment()

            val schedules = context.dbHelper.getScheduleMorning(day)

            if (schedules.isEmpty()) {
                schedule_recycler_view_list.visibility = View.GONE
                schedule_empty_layout.visibility = View.VISIBLE
            } else {
                schedule_recycler_view_list.visibility = View.VISIBLE
                schedule_empty_layout.visibility = View.GONE


                val scheduleAdapter = morningAdapter(context, schedules)
                schedule_recycler_view_list.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = scheduleAdapter
                }
            }
        }
    }

}