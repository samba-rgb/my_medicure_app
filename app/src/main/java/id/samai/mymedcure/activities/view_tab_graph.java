package id.samai.mymedcure.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

import id.samai.mymedcure.R;
import id.samai.mymedcure.helpers.DBHelper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class view_tab_graph extends AppCompatActivity {
    private int progress = 0;
    Button buttonIncrement;
    Button buttonDecrement;
    ProgressBar progressBar;
    TextView textView;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tab_graph);
        setTitle("Adherence Score");

        buttonIncrement = (Button) findViewById(R.id.button_incr);
        CircularProgressBar circularProgressBar = findViewById(R.id.progress_bar);
        circularProgressBar.setIndeterminateMode(true);
// Set Progress
        textView = (TextView) findViewById(R.id.text_view_progress);
        textView2 = (TextView) findViewById(R.id.textView);

        OkHttpClient okHttpClient = new OkHttpClient();
        getper();
        // when clicked on buttonIncrement progress in increased by 10%
        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Toast toast = Toast.makeText(getApplicationContext(), "This process takes 2 secoonds please wait", Toast.LENGTH_SHORT);
                toast.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 500);
                DBHelper db ;
                db = new DBHelper(getApplicationContext());
                OkHttpClient okHttpClient = new OkHttpClient();

                String dummyText = "1";
                try {
                    JSONObject sam = db.getAllDataAndGenerateJSON();
                    JSONObject sam2 = db.getAllDataAndGenerateJSONmain();

                    RequestBody formbody
                            = new FormBody.Builder()
                            .add("tablet", String.valueOf(sam))
                            .add("pattern",String.valueOf(sam2))
                            .build();
                    Request request = new Request.Builder().url("https://my-medicure-python-mock1.herokuapp.com/debug")
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
                                            Intent intent=new Intent(view_tab_graph.this,test_mock1.class);

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

        // when clicked on buttonIncrement progress in decreased by 10%
    }

    // updateProgressBar() method sets
    // the progress of ProgressBar in text
    private void updateProgressBar() {
        CircularProgressBar circularProgressBar = findViewById(R.id.progress_bar);
        circularProgressBar.setIndeterminateMode(false);

        circularProgressBar.setProgress(progress);

        textView.setText(String.valueOf(progress));

        if( (progress <100 )&&(75<=progress)){

            String text = "Your percentage was " + progress + " please take medicines at correct time";
            textView2.setText(text);

        }
        if( (progress <75 )&&(50<=progress)){

            String text = "Your percentage was " + progress + " please take medicines at correct time";
            textView2.setText(text);

        }
        if( (progress <50 )&&(25<=progress)){

            String text = "Your percentage was " + progress + " please take medicines at correct time" + " your score was very low which leads to damage in your health";
            textView2.setText(text);

        }
        if( (progress <25 )){

            String text = "Your percentage was " + progress + " please take medicines at correct time" + "you are not taking medicines at atime please take at correct time";
            textView2.setText(text);

        }

    }
    public void opendoc(){

        DBHelper db ;
        db = new DBHelper(getApplicationContext());
        OkHttpClient okHttpClient = new OkHttpClient();

        String dummyText = "1";
        try {
            JSONObject sam = db.getAllDataAndGenerateJSON();
            JSONObject sam2 = db.getAllDataAndGenerateJSONmain();

            RequestBody formbody
                    = new FormBody.Builder()
                    .add("tablet", String.valueOf(sam))
                    .add("pattern",String.valueOf(sam2))
                    .build();
            Request request = new Request.Builder().url("http://192.168.1.100:5000/debug")
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
                                    Intent intent=new Intent(view_tab_graph.this,test_mock1.class);

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
    public void getper(){

        DBHelper db ;
        db = new DBHelper(getApplicationContext());
        OkHttpClient okHttpClient = new OkHttpClient();

        String dummyText = "1";
        try {
            JSONObject sam = db.getAllDataAndGenerateJSON();
            JSONObject sam2 = db.getAllDataAndGenerateJSONmain();

            RequestBody formbody
                    = new FormBody.Builder()
                    .add("tablet", String.valueOf(sam))
                    .add("pattern",String.valueOf(sam2))
                    .build();
            Request request = new Request.Builder().url("https://my-medicure-python-mock1.herokuapp.com/outbug")
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
                                    String k = response.body().string();
                                    int i=Integer.parseInt(k);
                                    progress = i;
                                    updateProgressBar();

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

}