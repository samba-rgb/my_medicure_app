package id.samai.mymedcure.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.samai.mymedcure.models.User;

public class DataBase2 extends SQLiteOpenHelper {

    public static final String database_name = "medicine_manager.db";
    public static final String table_name = "weekdata";
    public static final String table_name2 = "totalmedicine";
    public static final  String table_name3 = "daydata";

    public static final String col_1 = "sname";
    public static final String col_2 = "att_lec";
    public static final String col_3 = "bunk_lec";
    public static final String col_4 = "total_lec";
    public static final String col_5 = "percent";
    public static final String col_6 = "monday";
    public static final String col_7 = "tuesday";
    public static final String  col_8 = "wednessday";
    public static final String  col_9 = "thursday";
    public static final String  col_10 = "friday";
    public static final String  col_11 = "saturday";
    public static final String  col_12 = "sunday";
    public static final String col_13 = "minpercent";


    public static final String coll_1 = "sname";
    public static final String coll_2 = "att_lec";
    public static final String coll_3 = "bunk_lec";
    public static final String coll_4 = "total_lec";
    public static final String coll_5 = "percent";
    public static final String coll_6 = "monday";
    public static final String coll_7 = "tuesday";
    public static final String  coll_8 = "wednessday";
    public static final String  coll_9 = "thursday";
    public static final String  coll_10 = "friday";
    public static final String  coll_11 = "saturday";
    public static final String  coll_12 = "sunday";
    public static final String coll_13 = "monday_morning_start";
    public static final String coll_14 = "monday_morning_end";
    public static final String coll_15 = "monday_afternoon_start";
    public static final String coll_16 = "monday_afternoon_end";
    public static final String coll_17 = "monday_evening_start";
    public static final String coll_18 = "monday_evening_end";
    public static final String coll_19 = "monday_night_start";
    public static final String coll_20 = "monday_night_end";
    public  static final String coll_21 = "monday_morning";
    public  static final String coll_22 = "monday_afternoon";
    public  static final String coll_23 = "monday_evening";
    public  static final String coll_24 = "monday_night";

    public static final String coll_25 = "tuesday_morning_start";
    public static final String coll_26 = "tuesday_morning_end";
    public static final String coll_27 = "tuesday_afternoon_start";
    public static final String coll_28 = "tuesday_afternoon_end";
    public static final String coll_29 = "tuesday_evening_start";
    public static final String coll_30 = "tuesday_evening_end";
    public static final String coll_31 = "tuesday_night_start";
    public static final String coll_32 = "tuesday_night_end";
    public  static final String coll_33 = "tuesday_morning";
    public  static final String coll_34 = "tuesday_afternoon";
    public  static final String coll_35 = "tuesday_evening";
    public  static final String coll_36 = "tuesday_night";


    public static final String coll_37 = "wednessday_morning_start";
    public static final String coll_38 = "wednessday_morning_end";
    public static final String coll_39 = "wednessday_afternoon_start";
    public static final String coll_40 = "wednessday_afternoon_end";
    public static final String coll_41 = "wednessday_evening_start";
    public static final String coll_42 = "wednessday_evening_end";
    public static final String coll_43 = "wednessday_night_start";
    public static final String coll_44 = "wednessday_night_end";
    public  static final String coll_45 = "wednessday_morning";
    public  static final String coll_46 = "wednessday_afternoon";
    public  static final String coll_47 = "wednessday_evening";
    public  static final String coll_48 = "wednessday_night";


    public static final String coll_49 = "thursday_morning_start";
    public static final String coll_50 = "thursday_morning_end";
    public static final String coll_51 = "thursday_afternoon_start";
    public static final String coll_52 = "thursday_afternoon_end";
    public static final String coll_53 = "thursday_evening_start";
    public static final String coll_54 = "thursday_evening_end";
    public static final String coll_55 = "thursday_night_start";
    public static final String coll_56 = "thursday_night_end";
    public  static final String coll_57 = "thursday_morning";
    public  static final String coll_58 = "thursday_afternoon";
    public  static final String coll_59 = "thursday_evening";
    public  static final String coll_60 = "thursday_night";


    public static final String coll_61 = "friday_morning_start";
    public static final String coll_62 = "friday_morning_end";
    public static final String coll_63 = "friday_afternoon_start";
    public static final String coll_64 = "friday_afternoon_end";
    public static final String coll_65 = "friday_evening_start";
    public static final String coll_66 = "friday_evening_end";
    public static final String coll_67 = "friday_night_start";
    public static final String coll_68 = "friday_night_end";
    public  static final String coll_69 = "friday_morning";
    public  static final String coll_70 = "friday_afternoon";
    public  static final String coll_71 = "friday_evening";
    public  static final String coll_72 = "friday_night";


    public static final String coll_73 = "saturday_morning_start";
    public static final String coll_74 = "saturday_morning_end";
    public static final String coll_75 = "saturday_afternoon_start";
    public static final String coll_76 = "saturday_afternoon_end";
    public static final String coll_77 = "saturday_evening_start";
    public static final String coll_78 = "saturday_evening_end";
    public static final String coll_79 = "saturday_night_start";
    public static final String coll_80 = "saturday_night_end";
    public  static final String coll_81 = "saturday_morning";
    public  static final String coll_82 = "saturday_afternoon";
    public  static final String coll_83 = "saturday_evening";
    public  static final String coll_84 = "saturday_night";


    public static final String coll_85 = "sunday_morning_start";
    public static final String coll_86 = "sunday_morning_end";
    public static final String coll_87 = "sunday_afternoon_start";
    public static final String coll_88 = "sunday_afternoon_end";
    public static final String coll_89= "sunday_evening_start";
    public static final String coll_90 = "sunday_evening_end";
    public static final String coll_91 = "sunday_night_start";
    public static final String coll_92 = "sunday_night_end";
    public  static final String coll_93 = "sunday_morning";
    public  static final String coll_94 = "sunday_afternoon";
    public  static final String coll_95 = "sunday_evening";
    public  static final String coll_96 = "sunday_night";


    public static final String colll_1 = "sname";
    public static final String colll_2 = "att_lec";
    public static final String colll_3 = "bunk_lec";
    public static final String colll_4 = "total_lec";
    public static final String colll_5 = "percent";
    public static final String colll_6 = "monday";
    public static final String colll_7 = "monday_morning";
    public static final String colll_8 ="monday_afternoon";
    public static final String colll_9 = "monday_evening";
    public static final String colll_10 = "monday_night";
    public static final String colll_11 = "tuesday";
    public static final String colll_12= "tuesday_morning";
    public static final String colll_13="tuesday_afternoon";
    public static final String colll_14= "tuesday_evening";
    public static final String colll_15 = "tuesday_night";
    public static final String colll_16 = "wednessday";
    public static final String colll_17= "wednessday_morning";
    public static final String colll_18="wednessday_afternoon";
    public static final String colll_19= "wednessday_evening";
    public static final String colll_20= "wednessday_night";
    public static final String colll_21 = "thursday";
    public static final String colll_22= "thursday_morning";
    public static final String colll_23="thursday_afternoon";
    public static final String colll_24= "thursday_evening";
    public static final String colll_25 = "thursday_night";
    public static final String colll_26 = "friday";
    public static final String colll_27= "friday_morning";
    public static final String colll_28="friday_afternoon";
    public static final String colll_29= "friday_evening";
    public static final String colll_30 = "friday_night";
    public static final String colll_31 = "saturday";
    public static final String colll_32= "saturday_morning";
    public static final String colll_33="saturday_afternoon";
    public static final String colll_34= "saturday_evening";
    public static final String colll_35 = "saturday_night";
    public static final String colll_36 = "sunday";
    public static final String colll_37= "sunday_morning";
    public static final String colll_38="sunday_afternoon";
    public static final String colll_39= "sunday_evening";
    public static final String colll_40 = "sunday_night";









    public DataBase2(Context context) {
        super(context, database_name,null, 2);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ table_name + "(sname varchar(20) primary key ,att_lec int , bunk_lec int , total_lec Int , percent float,monday int,tuesday int,wednessday int,thursday int,friday int,saturday int,sunday int)");
        db.execSQL("create table "+ table_name2+ "(sname varchar(20) primary key ,att_lec int , bunk_lec int , total_lec Int , percent float,monday int,tuesday int,wednessday int,thursday int,friday int,saturday int,sunday int,monday_morning_start int,monday_morning_end int,monday_afternoon_start int,monday_afternoon_end int,monday_evening_start int,monday_evening_end int,monday_night_start int,monday_night_end int,monday_morning int,monday_afternoon int,monday_evening int,monday_night int,tuesday_morning_start int,tuesday_morning_end int,tuesday_afternoon_start int,tuesday_afternoon_end int,tuesday_evening_start int,tuesday_evening_end int,tuesday_night_start int,tuesday_night_end int,tuesday_morning int,tuesday_afternoon int,tuesday_evening int,tuesday_night int,wednessday_morning_start int,wednessday_morning_end int,wednessday_afternoon_start int,wednessday_afternoon_end int,wednessday_evening_start int,wednessday_evening_end int,wednessday_night_start int,wednessday_night_end int,wednessday_morning int,wednessday_afternoon int,wednessday_evening int,wednessday_night int,thursday_morning_start int,thursday_morning_end int,thursday_afternoon_start int,thursday_afternoon_end int,thursday_evening_start int,thursday_evening_end int,thursday_night_start int,thursday_night_end int,thursday_morning int,thursday_afternoon int,thursday_evening int,thursday_night int,friday_morning_start int,friday_morning_end int,friday_afternoon_start int,friday_afternoon_end int,friday_evening_start int,friday_evening_end int,friday_night_start int,friday_night_end int,friday_morning int,friday_afternoon int,friday_evening int,friday_night int,saturday_morning_start int,saturday_morning_end int,saturday_afternoon_start int,saturday_afternoon_end int,saturday_evening_start int,saturday_evening_end int,saturday_night_start int,saturday_night_end int,saturday_morning int,saturday_afternoon int,saturday_evening int,saturday_night int,sunday_morning_start int,sunday_morning_end int,sunday_afternoon_start int,sunday_afternoon_end int,sunday_evening_start int,sunday_evening_end int,sunday_night_start int,sunday_night_end int,sunday_morning int,sunday_afternoon int,sunday_evening int,sunday_night int)");
        db.execSQL("create table "+ table_name3 + "(sname varchar(20) primary key ,att_lec int , bunk_lec int , total_lec Int , percent float,monday int,monday_morning int,monday_afternoon int,monday_evening int,monday_night int,tuesday int,tuesday_morning int,tuesday_afternoon int,tuesday_evening int,tuesday_night int,wednessday int,wednessday_morning int,wednessday_afternoon int,wednessday_evening int,wednessday_night int,thursday int,thursday_morning int,thursday_afternoon int,thursday_evening int,thursday_night int,friday int,friday_morning int,friday_afternoon int,friday_evening int,friday_night int,saturday int,saturday_morning int,saturday_afternoon int,saturday_evening int,saturday_night int,sunday int,sunday_morning int,sunday_afternoon int,sunday_evening int,sunday_night int )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + table_name2);
        db.execSQL("DROP TABLE IF EXISTS " + table_name3);
        onCreate(db);
    }
    public boolean createalldaydata(String subname,int att_lec, int bunk_lec , int total_lec  , float percent,boolean monday,boolean monday_morning,boolean monday_afternoon,boolean monday_evening,boolean monday_night,boolean tuesday,boolean tuesday_morning,boolean tuesday_afternoon,boolean tuesday_evening,boolean tuesday_night,boolean wednessday,boolean wednessday_morning,boolean wednessday_afternoon,boolean wednessday_evening,boolean wednessday_night,boolean thursday,boolean thursday_morning,boolean thursday_afternoon,boolean thursday_evening,boolean thursday_night,boolean friday,boolean friday_morning,boolean friday_afternoon,boolean friday_evening,boolean friday_night,boolean saturday,boolean saturday_morning,boolean saturday_afternoon,boolean saturday_evening,boolean saturday_night,boolean sunday,boolean sunday_morning,boolean sunday_afternoon,boolean sunday_evening,boolean sunday_night){ //TO create row for each subject
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colll_1,subname);
        contentValues.put(colll_2,att_lec);
        contentValues.put(colll_3,bunk_lec);
        contentValues.put(colll_4,total_lec);
        contentValues.put(colll_5,percent);
        contentValues.put(colll_6,monday);
        contentValues.put(colll_7,monday_morning);
        contentValues.put(colll_8,monday_afternoon);
        contentValues.put(colll_9,monday_evening);
        contentValues.put(colll_10,monday_night);
        contentValues.put(colll_11,tuesday);
        contentValues.put(colll_12,tuesday_morning);
        contentValues.put(colll_13,tuesday_afternoon);
        contentValues.put(colll_14,tuesday_evening);
        contentValues.put(colll_15,tuesday_night);
        contentValues.put(colll_16,wednessday);
        contentValues.put(colll_17,wednessday_morning);
        contentValues.put(colll_18,wednessday_afternoon);
        contentValues.put(colll_19,wednessday_evening);
        contentValues.put(colll_20,wednessday_night);
        contentValues.put(colll_21,thursday);
        contentValues.put(colll_22,thursday_morning);
        contentValues.put(colll_23,thursday_afternoon);
        contentValues.put(colll_24,thursday_evening);
        contentValues.put(colll_25,thursday_night);
        contentValues.put(colll_26,friday);
        contentValues.put(colll_27,friday_morning);
        contentValues.put(colll_28,friday_afternoon);
        contentValues.put(colll_29,friday_evening);
        contentValues.put(colll_30,friday_night);
        contentValues.put(colll_31,saturday);
        contentValues.put(colll_32,saturday_morning);
        contentValues.put(colll_33,saturday_afternoon);
        contentValues.put(colll_34,saturday_evening);
        contentValues.put(colll_35,saturday_night);
        contentValues.put(colll_36,sunday);
        contentValues.put(colll_37,sunday_morning);
        contentValues.put(colll_38,sunday_afternoon);
        contentValues.put(colll_39,sunday_evening);
        contentValues.put(colll_40,sunday_night);

        long result = db.insert(table_name3,null,contentValues);
        return result != -1;
    }
    /*
    public User getContact(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int attend = 1;
        int bunk = 0;
        float minpercent = 80;
        float percent = 0;
        int total = 0;
        int monday = 0;
        int monday_morning =0;
        int monday_afternoon = 0;
        int monday_evening = 0;
        int monday_night = 0;
        int tuesday = 0;
        int tuesday_morning =0;
        int tuesday_afternoon = 0;
        int tuesday_evening = 0;
        int tuesday_night = 0;
        int wednessday = 0;
        int wednessday_morning =0;
        int wednessday_afternoon = 0;
        int wednessday_evening = 0;
        int wednessday_night = 0;
        int thursday = 0;
        int thursday_morning =0;
        int thursday_afternoon = 0;
        int thursday_evening = 0;
        int thursday_night = 0;
        int friday = 0;
        int friday_morning =0;
        int friday_afternoon = 0;
        int friday_evening = 0;
        int friday_night = 0;
        int saturday = 0;
        int saturday_morning =0;
        int saturday_afternoon = 0;
        int saturday_evening = 0;
        int saturday_night = 0;
        int sunday = 0;
        int sunday_morning =0;
        int sunday_afternoon = 0;
        int sunday_evening = 0;
        int sunday_night = 0;


        Cursor cursor = db.query(table_name3, new String[] { colll_1,colll_2 ,colll_3,colll_4,colll_5,colll_6,colll_7,colll_8,colll_9,colll_10,colll_11,colll_12,colll_13,colll_14,colll_15,colll_16,colll_17,colll_18,colll_19,colll_20,colll_21,colll_22,colll_23,colll_24,colll_25,colll_26,colll_27,colll_28,colll_29,colll_30,colll_31,colll_32,colll_33,colll_34,colll_35,colll_36,colll_37,colll_38,colll_39,colll_40}, col_1 + "=?",
                new String[] {name }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        attend = cursor.getInt(cursor.getColumnIndex(colll_2 ));
        bunk  = cursor.getInt(cursor.getColumnIndex(colll_3));
        percent = cursor.getFloat(cursor.getColumnIndex(colll_5));
        total = cursor.getInt(cursor.getColumnIndex(colll_4));
        monday = cursor.getInt(cursor.getColumnIndex(colll_6));
        monday_morning = cursor.getInt(cursor.getColumnIndex(colll_7));
        monday_afternoon = cursor.getInt(cursor.getColumnIndex(colll_8));
        monday_evening = cursor.getInt(cursor.getColumnIndex(colll_9));
        monday_night =cursor.getInt(cursor.getColumnIndex(colll_10));
        tuesday =cursor.getInt(cursor.getColumnIndex(colll_11));
        tuesday_morning =cursor.getInt(cursor.getColumnIndex(colll_12));
        tuesday_afternoon =cursor.getInt(cursor.getColumnIndex(colll_13));
        tuesday_evening =cursor.getInt(cursor.getColumnIndex(colll_14));
        tuesday_night =cursor.getInt(cursor.getColumnIndex(colll_15));
        wednessday =cursor.getInt(cursor.getColumnIndex(colll_16));
        wednessday_morning = cursor.getInt(cursor.getColumnIndex(colll_17));
        wednessday_afternoon = cursor.getInt(cursor.getColumnIndex(colll_18));
        wednessday_evening =cursor.getInt(cursor.getColumnIndex(colll_19));
        wednessday_night=cursor.getInt(cursor.getColumnIndex(colll_20));
        thursday = cursor.getInt(cursor.getColumnIndex(colll_21));
        thursday_morning = cursor.getInt(cursor.getColumnIndex(colll_22));
        thursday_afternoon = cursor.getInt(cursor.getColumnIndex(colll_23));
        thursday_evening = cursor.getInt(cursor.getColumnIndex(colll_24));
        thursday_night = cursor.getInt(cursor.getColumnIndex(colll_25));
        friday = cursor.getInt(cursor.getColumnIndex(colll_26));
        friday_morning = cursor.getInt(cursor.getColumnIndex(colll_27));
        friday_afternoon = cursor.getInt(cursor.getColumnIndex(colll_28));
        friday_evening = cursor.getInt(cursor.getColumnIndex(colll_29));
        friday_night = cursor.getInt(cursor.getColumnIndex(colll_30));
        saturday = cursor.getInt(cursor.getColumnIndex(colll_31));
        saturday_morning = cursor.getInt(cursor.getColumnIndex(colll_32));
        saturday_afternoon = cursor.getInt(cursor.getColumnIndex(colll_33));
        saturday_evening = cursor.getInt(cursor.getColumnIndex(colll_34));
        saturday_night = cursor.getInt(cursor.getColumnIndex(colll_35));
        sunday = cursor.getInt(cursor.getColumnIndex(colll_36));
        sunday_morning = cursor.getInt(cursor.getColumnIndex(colll_37));
        sunday_afternoon = cursor.getInt(cursor.getColumnIndex(colll_38));
        sunday_evening = cursor.getInt(cursor.getColumnIndex(colll_39));
        sunday_night = cursor.getInt(cursor.getColumnIndex(colll_40));



        //User user = new User(cursor.getString(0),attend,bunk,percent,minpercent,total,monday,monday_morning,monday_afternoon,monday_evening,monday_night,tuesday,tuesday_morning,tuesday_afternoon,tuesday_evening,tuesday_night,wednessday,wednessday_morning,wednessday_afternoon,wednessday_evening,wednessday_night,thursday,thursday_morning,thursday_afternoon,thursday_evening,thursday_night,friday,friday_morning,friday_afternoon,friday_evening,friday_night,saturday,saturday_morning,saturday_afternoon,saturday_evening,saturday_night,sunday,sunday_morning,sunday_afternoon,sunday_evening,sunday_night);
        // return contact
        //return user;
    }

     */
}


