package id.samai.mymedcure.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileWriter;

import id.samai.mymedcure.R;
import id.samai.mymedcure.helpers.DBHelper;
import id.samai.mymedcure.models.CSVWriters;

public class share_medical_his extends AppCompatActivity {
    Button btnSaveUser, btnExport,btnExport2,btnExport3;
    TextInputEditText eText;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_medical_his);
        setTitle("share information");

        btnExport = (Button) findViewById(R.id.share_medical_his);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Is used to import data from excel without dropping table
                // ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), DBHelper.DB_NAME);
               medical_history();
                // if you want to add column in excel and import into DB, you must drop the table


            }

        });
        btnExport2 = (Button) findViewById(R.id.share_tabllet);

        btnExport2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Is used to import data from excel without dropping table
                // ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), DBHelper.DB_NAME);
                tablet();
                // if you want to add column in excel and import into DB, you must drop the table


            }

        });
        btnExport3 = (Button) findViewById(R.id.share_total);

        btnExport3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Is used to import data from excel without dropping table
                // ExcelToSQLite excelToSQLite = new ExcelToSQLite(getApplicationContext(), DBHelper.DB_NAME);
                total_history();
                // if you want to add column in excel and import into DB, you must drop the table


            }

        });

    }

    private void tablet() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "/my_medicure/");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        DBHelper db ;
        db = new DBHelper(getApplicationContext());
        mEditText = findViewById(R.id.editname);

        String text =  mEditText.getText().toString();



        File file = new File(exportDir, text + ".csv");
        try {
            file.createNewFile();
            CSVWriters csvWrite = new CSVWriters(new FileWriter(file));
            Cursor curCSV = db.query("Tablet_history", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            int iteration = 0;
            while (curCSV.moveToNext()) {

                //Which column you want to exprort
                String arrStr[] = new String[curCSV.getColumnCount()];
                for (int i = 0; i < curCSV.getColumnCount(); i++)
                    arrStr[i] = curCSV.getString(i);
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("text/*");
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+exportDir.getAbsolutePath() + "/" + text + ".csv"));
            startActivity(share);



        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            Toast.makeText(getApplicationContext(), sqlEx.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    private void medical_history() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "/my_medicure/");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        DBHelper db ;
        db = new DBHelper(getApplicationContext());
        mEditText = findViewById(R.id.editname);

        String text =  mEditText.getText().toString();



        File file = new File(exportDir, text + ".csv");
        try {
            file.createNewFile();
            CSVWriters csvWrite = new CSVWriters(new FileWriter(file));
            Cursor curCSV = db.query("medical_records", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            int iteration = 0;
            while (curCSV.moveToNext()) {

                //Which column you want to exprort
                String arrStr[] = new String[curCSV.getColumnCount()];
                for (int i = 0; i < curCSV.getColumnCount(); i++)
                    arrStr[i] = curCSV.getString(i);
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("text/*");
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+exportDir.getAbsolutePath() + "/" + text + ".csv"));
            startActivity(share);



        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            Toast.makeText(getApplicationContext(), sqlEx.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    private void total_history() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "/my_medicure/");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        DBHelper db ;
        db = new DBHelper(getApplicationContext());
        mEditText = findViewById(R.id.editname);

        String text =  mEditText.getText().toString();



        File file = new File(exportDir, text + ".csv");
        try {
            file.createNewFile();
            CSVWriters csvWrite = new CSVWriters(new FileWriter(file));
            Cursor curCSV = db.query("Day_data", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            int iteration = 0;
            while (curCSV.moveToNext()) {

                //Which column you want to exprort
                String arrStr[] = new String[curCSV.getColumnCount()];
                for (int i = 0; i < curCSV.getColumnCount(); i++)
                    arrStr[i] = curCSV.getString(i);
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("text/*");
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+exportDir.getAbsolutePath() + "/" + text + ".csv"));
            startActivity(share);



        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            Toast.makeText(getApplicationContext(), sqlEx.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }


}