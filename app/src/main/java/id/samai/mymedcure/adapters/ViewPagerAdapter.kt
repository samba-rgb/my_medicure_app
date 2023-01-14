package id.samai.mymedcure.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.samai.mymedcure.fragments.*
import id.samai.mymedcure.helpers.TABS_COUNT
import java.lang.RuntimeException

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = HashMap<Int, Fragment>()

    override fun createFragment(position: Int): Fragment {
        val fragment = getFragment(position)
        fragments[position] = fragment
        return fragment
    }

    private fun getFragment(position: Int) = when (position) {
        0 -> blogs_firebase()
        1 -> morning()
        2 -> Afternoon()
        3 -> evening()
        4-> night()
        5->total_day()


        else -> throw RuntimeException("Trying to fetch unknown fragment id $position")
    }

    override fun getItemCount(): Int = TABS_COUNT

}