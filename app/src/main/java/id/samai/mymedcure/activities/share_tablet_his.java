package id.samai.mymedcure.activities;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import id.samai.mymedcure.R;
import id.samai.mymedcure.helpers.DBHelper;
import id.samai.mymedcure.models.CSVWriters;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class share_tablet_his extends AppCompatActivity {
    Button btnSaveUser, btnExport;
    TextInputEditText eText;
    EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_tablet_his);
        setTitle(getResources().getText(R.string.sharetimetable));

        DBHelper db ;
        db = new DBHelper(getApplicationContext());
        btnExport = (Button) findViewById(R.id.share);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        OkHttpClient okHttpClient = new OkHttpClient();



        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String dummyText = "1";
                try {
                    JSONObject sam = db.getAllDataAndGenerateJSON();
                    JSONObject sam2 = db.getAllDataAndGenerateJSONmain();

                    RequestBody formbody
                            = new FormBody.Builder()
                            .add("tablet", String.valueOf(sam))
                            .add("pattern",String.valueOf(sam2))
                            .build();
                    Request request = new Request.Builder().url("http://192.168.202.90:5000/debug")
                            .post(formbody)
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(
                                @NotNull Call call,
                                @NotNull IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (true) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String name="aaaa";
                                        try {
                                            Intent intent=new Intent(share_tablet_his.this,test_mock1.class);

                                            intent.putExtra("name", response.body().string());
                                            startActivity(intent);

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(getApplicationContext(), "data received", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });





    }

    private void samba() {
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
    private void sai() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
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
            String url = "http://192.168.50.90:8000/samba?model=yolov3-tiny";

            try {
                //HttpClient httpclient = new DefaultHttpClient();
                File file2 = new File(exportDir, text + ".csv");
                Uri uri = Uri.fromFile(file);
                File auxFile = new File(uri.toString());
                HttpParams httpParams=new BasicHttpParams();
                DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
                HttpPost httppost = new HttpPost(url);

                InputStreamEntity reqEntity = new InputStreamEntity(
                        new FileInputStream(file2), -1);
                Toast.makeText(getApplicationContext(), file2.getPath(), Toast.LENGTH_SHORT).show();

                //reqEntity.setContentType("multipart/form-data");
                 // Send in multiple parts if needed
               // httppost.setEntity(reqEntity);
                HttpResponse response = httpclient.execute(httppost);
                //Do something with response...

                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                // show error
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

            }


        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            Toast.makeText(getApplicationContext(), sqlEx.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
    private void ramu() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
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
            String url = "http://192.168.50.90:8000/samba?model=yolov3-tiny";

            doUpload(file);


        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
            Toast.makeText(getApplicationContext(), sqlEx.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }


    public void doUpload(File filename)
    {
        HttpURLConnection conn = null;
        DataInputStream inputStream = null;

        String urlServer = "http://192.168.50.90:8000/samba?model=yolov3-tiny";

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";
        int bytesRead, bytesAvailable, bufferSize, bytesUploaded = 0;
        byte[] buffer;
        int maxBufferSize = 2*1024*1024;

        String uploadname = "filename";

        try
        {
            FileInputStream fis = new FileInputStream(filename );

            URL url = new URL(urlServer);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");



            conn.setDoOutput(true);
            String jsonInputString = "{'name': 'Upendra', 'job': 'Programmer'}";




            conn.connect();

            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

            }

        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            //return false;
        }
    }





}
