package id.samai.mymedcure.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.samai.mymedcure.R;
import id.samai.mymedcure.fragments.Schedule;
import id.samai.mymedcure.helpers.DBHelper;
import id.samai.mymedcure.models.TABLET;
import id.samai.mymedcure.models.User;

public class single_hist_tab extends AppCompatActivity {
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_hist_tab);

        DBHelper dbHelper;
        dbHelper = new DBHelper(getApplicationContext());
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String name = b.getString("name");
        String expiry = b.getString("expiry");
        mCalendarView = (CalendarView) findViewById(R.id.calendarView_1);
        RelativeLayout morning = findViewById(R.id.morning_view);
        RelativeLayout afternoon = findViewById(R.id.afternoon_view);
        RelativeLayout evening = findViewById(R.id.evening_view);
        RelativeLayout night = findViewById(R.id.night_view);
        ImageView morning_image = findViewById(R.id.image_morning);
        ImageView afternoon_image = findViewById(R.id.image_afternoon);
        ImageView evening_image = findViewById(R.id.image_evening);
        ImageView night_image = findViewById(R.id.image_night);
        TextView date = findViewById(R.id.date_now);
        TextView expiry_date  = findViewById(R.id.date_expiry);
        String k  = "Expiry :" + expiry;
        expiry_date.setText(k);
        Calendar c = Calendar.getInstance();
        Date date3 =  new Date();
        c.setTime(date3);
        //c.add(Calendar.YEAR, Calendar.YEAR);
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US);
        String  dateTime = df.format(c.getTime()).toString();
        String today  = "Today :" + dateTime;
        date.setText(today);


        TextView name2 = findViewById(R.id.medicine_name2);
        name2.setText(name);


        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US);
                String  dateTime = df.format(clickedDayCalendar.getTime()).toString();
                editnames(dateTime,name);
            }
        });
    }

    private void editnames(String eventDay,String name) {

        RelativeLayout morning = findViewById(R.id.morning_view);
        RelativeLayout afternoon = findViewById(R.id.afternoon_view);
        RelativeLayout evening = findViewById(R.id.evening_view);
        RelativeLayout night = findViewById(R.id.night_view);
        ImageView morning_image = findViewById(R.id.image_morning);
        ImageView afternoon_image = findViewById(R.id.image_afternoon);
        ImageView evening_image = findViewById(R.id.image_evening);
        ImageView night_image = findViewById(R.id.image_night);
        TextView date = findViewById(R.id.date_now);
        String today  = "Today :" + eventDay;
        date.setText(today);
        TextView name2 = findViewById(R.id.medicine_name2);
        name2.setText(name.toString());
        DBHelper dbHelper;
        dbHelper = new DBHelper(getApplicationContext());
        ArrayList<TABLET> schedules = dbHelper.getTabletdatebool(name.toString(), eventDay);
        ArrayList<User> user = dbHelper.getdaydataname(name);
        User first = user.get(0);
        if(eventDay.contains("Mon")){
            if(first.getMonday()==1){
                if(first.getMonday_morning()==0){
                    morning.setVisibility(View.INVISIBLE);

                }
                else{
                    morning.setVisibility(View.VISIBLE);
                    morning_image.setImageResource(R.drawable.wrong);

                }
                if(first.getMonday_afternoon()==0){
                    afternoon.setVisibility(View.INVISIBLE);
                }
                else {
                    afternoon.setVisibility(View.VISIBLE);
                   afternoon_image.setImageResource(R.drawable.wrong);

                }
                if(first.getMonday_evening()==0){
                    evening.setVisibility(View.INVISIBLE);
                }
                else{
                    evening.setVisibility(View.VISIBLE);
                    evening_image.setImageResource(R.drawable.wrong);
                }
                if(first.getMonday_night()==0){
                    night.setVisibility(View.INVISIBLE);
                }
                else {
                    night.setVisibility(View.VISIBLE);
                    night_image.setImageResource(R.drawable.wrong);
                }

            }
            else{

                morning.setVisibility(View.INVISIBLE);
                afternoon.setVisibility(View.INVISIBLE);
                evening.setVisibility(View.INVISIBLE);
                night.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "no medicine this day", Toast.LENGTH_SHORT).show();
            }
        }
        if(eventDay.contains("Tue")){
            if(first.getTuesday()==1){
                if(first.getTuesday_morning()==0){
                    morning.setVisibility(View.INVISIBLE);
                }
                else{
                    morning.setVisibility(View.VISIBLE);
                    morning_image.setImageResource(R.drawable.wrong);

                }
                if(first.getTuesday_afternoon()==0){
                    afternoon.setVisibility(View.INVISIBLE);
                }
                else {
                    afternoon.setVisibility(View.VISIBLE);
                    afternoon_image.setImageResource(R.drawable.wrong);

                }
                if(first.getTuesday_evening()==0){
                    evening.setVisibility(View.INVISIBLE);
                }
                else{
                    evening.setVisibility(View.VISIBLE);
                    evening_image.setImageResource(R.drawable.wrong);
                }
                if(first.getTuesday_night()==0){
                    night.setVisibility(View.INVISIBLE);
                }
                else {
                    night.setVisibility(View.VISIBLE);
                    night_image.setImageResource(R.drawable.wrong);
                }

            }
            else{

                morning.setVisibility(View.INVISIBLE);
                afternoon.setVisibility(View.INVISIBLE);
                evening.setVisibility(View.INVISIBLE);
                night.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "no medicine this day", Toast.LENGTH_SHORT).show();
            }
        }
        if(eventDay.contains("Wed")){
            if(first.getWednessday()==1){
                if(first.getWednessday_morning()==0){
                    morning.setVisibility(View.INVISIBLE);
                }
                else{
                    morning.setVisibility(View.VISIBLE);
                    morning_image.setImageResource(R.drawable.wrong);

                }
                if(first.getWednessday_afternoon()==0){
                    afternoon.setVisibility(View.INVISIBLE);
                }
                else {
                    afternoon.setVisibility(View.VISIBLE);
                    afternoon_image.setImageResource(R.drawable.wrong);

                }
                if(first.getWednessday_evening()==0){
                    evening.setVisibility(View.INVISIBLE);
                }
                else{
                    evening.setVisibility(View.VISIBLE);
                    evening_image.setImageResource(R.drawable.wrong);
                }
                if(first.getWednessday_night()==0){
                    night.setVisibility(View.INVISIBLE);
                }
                else {
                    night.setVisibility(View.VISIBLE);
                    night_image.setImageResource(R.drawable.wrong);
                }

            }
            else{

                morning.setVisibility(View.INVISIBLE);
                afternoon.setVisibility(View.INVISIBLE);
                evening.setVisibility(View.INVISIBLE);
                night.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "no medicine this day", Toast.LENGTH_SHORT).show();
            }
        }
        if(eventDay.contains("Thu")){
            if(first.getThursday()==1){
                if(first.getThursday_morning()==0){
                    morning.setVisibility(View.INVISIBLE);
                }
                else{
                    morning.setVisibility(View.VISIBLE);
                    morning_image.setImageResource(R.drawable.wrong);

                }
                if(first.getThursday_afternoon()==0){
                    afternoon.setVisibility(View.INVISIBLE);
                }
                else {
                    afternoon.setVisibility(View.VISIBLE);
                    afternoon_image.setImageResource(R.drawable.wrong);

                }
                if(first.getThursday_evening()==0){
                    evening.setVisibility(View.INVISIBLE);
                }
                else{
                    evening.setVisibility(View.VISIBLE);
                    evening_image.setImageResource(R.drawable.wrong);
                }
                if(first.getThursday_night()==0){
                    night.setVisibility(View.INVISIBLE);
                }
                else {
                    night.setVisibility(View.VISIBLE);
                    night_image.setImageResource(R.drawable.wrong);
                }

            }
            else{

                morning.setVisibility(View.INVISIBLE);
                afternoon.setVisibility(View.INVISIBLE);
                evening.setVisibility(View.INVISIBLE);
                night.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "no medicine this day", Toast.LENGTH_SHORT).show();
            }
        }
        if(eventDay.contains("Fri")){
            if(first.getFriday()==1){
                if(first.getFriday_morning()==0){
                    morning.setVisibility(View.INVISIBLE);
                }
                else{
                    morning.setVisibility(View.VISIBLE);
                    morning_image.setImageResource(R.drawable.wrong);

                }
                if(first.getFriday_afternoon()==0){
                    afternoon.setVisibility(View.INVISIBLE);
                }
                else {
                    afternoon.setVisibility(View.VISIBLE);
                    afternoon_image.setImageResource(R.drawable.wrong);

                }
                if(first.getFriday_evening()==0){
                    evening.setVisibility(View.INVISIBLE);
                }
                else{
                    evening.setVisibility(View.VISIBLE);
                    evening_image.setImageResource(R.drawable.wrong);
                }
                if(first.getFriday_night()==0){
                    night.setVisibility(View.INVISIBLE);
                }
                else {
                    night.setVisibility(View.VISIBLE);
                    night_image.setImageResource(R.drawable.wrong);
                }

            }
            else{

                morning.setVisibility(View.INVISIBLE);
                afternoon.setVisibility(View.INVISIBLE);
                evening.setVisibility(View.INVISIBLE);
                night.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "no medicine this day", Toast.LENGTH_SHORT).show();
            }
        }
        if(eventDay.contains("Sat")){
            if(first.getSaturday()==1){
                if(first.getSaturday_morning()==0){
                    morning.setVisibility(View.INVISIBLE);
                }
                else{
                    morning.setVisibility(View.VISIBLE);
                    morning_image.setImageResource(R.drawable.wrong);

                }
                if(first.getSaturday_afternoon()==0){
                    afternoon.setVisibility(View.INVISIBLE);
                }
                else {
                    afternoon.setVisibility(View.VISIBLE);
                    afternoon_image.setImageResource(R.drawable.wrong);

                }
                if(first.getSaturday_evening()==0){
                    evening.setVisibility(View.INVISIBLE);
                }
                else{
                    evening.setVisibility(View.VISIBLE);
                    evening_image.setImageResource(R.drawable.wrong);
                }
                if(first.getSaturday_night()==0){
                    night.setVisibility(View.INVISIBLE);
                }
                else {
                    night.setVisibility(View.VISIBLE);
                    night_image.setImageResource(R.drawable.wrong);
                }

            }
            else{

                morning.setVisibility(View.INVISIBLE);
                afternoon.setVisibility(View.INVISIBLE);
                evening.setVisibility(View.INVISIBLE);
                night.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "no medicine this day", Toast.LENGTH_SHORT).show();
            }
        }
        if(eventDay.contains("Sun")){
            if(first.getSunday()==1){
                if(first.getSunday_morning()==0){
                    morning.setVisibility(View.INVISIBLE);
                }
                else{
                    morning.setVisibility(View.VISIBLE);
                    morning_image.setImageResource(R.drawable.wrong);
                }
                if(first.getSunday_afternoon()==0){
                    afternoon.setVisibility(View.INVISIBLE);
                }
                else {
                    afternoon.setVisibility(View.VISIBLE);
                    afternoon_image.setImageResource(R.drawable.wrong);

                }
                if(first.getSunday_evening()==0){
                    evening.setVisibility(View.INVISIBLE);
                }
                else{
                    evening.setVisibility(View.VISIBLE);
                    evening_image.setImageResource(R.drawable.wrong);
                }
                if(first.getSunday_night()==0){
                    night.setVisibility(View.INVISIBLE);
                }
                else {
                    night.setVisibility(View.VISIBLE);
                    night_image.setImageResource(R.drawable.wrong);
                }

            }
            else{
                morning.setVisibility(View.INVISIBLE);
                afternoon.setVisibility(View.INVISIBLE);
                evening.setVisibility(View.INVISIBLE);
                night.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "no medicine this day", Toast.LENGTH_SHORT).show();
            }
        }

        if (!schedules.isEmpty()) {

            TABLET schedule = schedules.get(0);
            if(schedule.getMorning()==1){
                morning_image.setImageResource(R.drawable.ok);
            }
            else{
                morning_image.setImageResource(R.drawable.wrong);

            }
            if(schedule.getAfternoon()==1){
                afternoon_image.setImageResource(R.drawable.ok);
            }
            else{
                afternoon_image.setImageResource(R.drawable.wrong);
            }
            if(schedule.getEvening()==1){
                evening_image.setImageResource(R.drawable.ok);
            }
            else{
                evening_image.setImageResource(R.drawable.wrong);
            }
            if(schedule.getNight()==1){
                night_image.setImageResource(R.drawable.ok);
            }
            else {
                night_image.setImageResource(R.drawable.wrong);
            }


        }


       // Toast.makeText(getApplicationContext(), eventDay.toString(), Toast.LENGTH_SHORT).show();

    }
}