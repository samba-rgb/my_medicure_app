package id.samai.mymedcure.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import id.samai.mymedcure.R
import id.samai.mymedcure.extensions.dbHelper
import id.samai.mymedcure.extensions.getNextNotificationSchedule
import id.samai.mymedcure.models.tab_expire_count

class
BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            context?.getNextNotificationSchedule()
            //context?.getNextNotificationTask()


        }
        if (intent?.action.equals(Intent.ACTION_DATE_CHANGED)) {

             context?.getNextNotificationSchedule()
             // context?.getNextNotificationTask()


            //Toast.makeText(context, "day_changed", Toast.LENGTH_SHORT).show()
           // val tab_expire = tab_expire_count(45, "trail","s",50,"sa",0)
            //context?.dbHelper?.insertExpiry(tab_expire)

        }


    }

}