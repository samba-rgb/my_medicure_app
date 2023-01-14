package id.samai.mymedcure.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.ncorti.slidetoact.SlideToActView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import id.samai.mymedcure.R;
import id.samai.mymedcure.helpers.DBHelper;
import id.samai.mymedcure.helpers.DataBase2;
import id.samai.mymedcure.models.TABLET;

public class tab_details_2_java extends AppCompatActivity {
    private int id ;
    AlertDialog.Builder builder;
    Dialog dialog;
    private VideoView mVideoView;
    private MediaController mediaControls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_details_2_java);
        Bundle b = new Bundle();
        builder = new AlertDialog.Builder(this);

dialog = new Dialog(this);

        DBHelper dbHelper;
        dbHelper = new DBHelper(getApplicationContext());
        b = getIntent().getExtras();
        String name = b.getString("name");
        String link = b.getString("link");
        String info = b.getString("info");
        String update_info = b.getString("update_info");
        final SlideToActView slide = findViewById(R.id.taken_tik_java);
        String s = "You have taken this medicine dose"+'\n' + "taking this medicine again leads to side affects";

        TextView name_med = (TextView)findViewById(R.id.medcine_name_java);
        name_med.setText(name);
        TextView info_med = (TextView)findViewById(R.id.info_tab_2_java);
        info_med.setText(info);
        ImageView imageView = findViewById(R.id.image_medicine_java);

        /*
        File  exportDir =  new File(Environment.getExternalStorageDirectory(), "/Medicure/Image/");


        File imgFile = new  File(exportDir, name + ".jpg");

        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }

         */
// start of dialog
        findViewById(R.id.info_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = "https://www.youtube.com/watch?v=bVnhOSsCyVk";
                String video_id = "vG2PNdI8axo";

                dialog.setContentView(R.layout.video_view);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color. TRANSPARENT));
                Button btnok = dialog.findViewById(R.id.ok_video);
// declaring variable for youtubeplayer view
                final YouTubePlayerView youTubePlayerView = dialog.findViewById(R.id.videoPlayer);


                getLifecycle().addObserver(youTubePlayerView);


                youTubePlayerView.getPlayerUiController();



                // adding listener for our youtube player view.
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(video_id, 0);
                    }

                    @Override
                    public void onStateChange(@NonNull YouTubePlayer youTubePlayer,
                                              @NonNull PlayerConstants.PlayerState state) {
                        super.onStateChange(youTubePlayer, state);
                    }
                });
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }

        });


//end of dialog

        try {
            if(isConnected()) {
                Picasso.get()
                        .load(link)
                        .error(R.drawable.my_medicure)
                        .into(imageView);
            }
            else{
                File  exportDir =  new File(Environment.getExternalStorageDirectory(), "/Medicure/Image/");


                File imgFile = new  File(exportDir, name + ".jpg");

                Picasso.get()
                        .load(imgFile)


                        .into(imageView);

            }
        } catch (InterruptedException e) {
            File  exportDir =  new File(Environment.getExternalStorageDirectory(), "/Medicure/Image/");


            File imgFile = new  File(exportDir, name + ".jpg");

            Picasso.get()
                    .load(imgFile)


                    .into(imageView);
        } catch (IOException e) {
            File  exportDir =  new File(Environment.getExternalStorageDirectory(), "/Medicure/Image/");


            File imgFile = new  File(exportDir, name + ".jpg");

            Picasso.get()
                    .load(imgFile)


                    .into(imageView);
        }
        //trail start
       // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


        // trail end
        Calendar c = Calendar.getInstance();
        Date date =  new Date();
        c.setTime(date);
        //c.add(Calendar.YEAR, Calendar.YEAR);
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd yyyy (EEE)", Locale.US);
        String  dateTime = df.format(c.getTime()).toString();
        ArrayList<TABLET> schedules = dbHelper.getTabletdatebool(name.toString(), dateTime);
        if(schedules.isEmpty()){
            TABLET sam = new TABLET(id, name.toString(), dateTime, 0, 0, 0, 0);
            dbHelper.insertTABLET_history(sam);
        }
        else {
            TABLET first = schedules.get(0);
            if (update_info.equals("morning")) {
                //Toast.makeText(getApplicationContext(), "morning", Toast.LENGTH_SHORT).show();

                if (first.getMorning() == 1) {
                   // Toast.makeText(getApplicationContext(), "e.getMessage()", Toast.LENGTH_SHORT).show();
                    slide.setVisibility(View.INVISIBLE);
                    /*
                    builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                    //Setting message manually and performing action on button click
                    builder.setMessage("Dont take this medicine now")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    builder.setTitle("Dont take this medicine now");
                    builder.setIcon(R.drawable.danger);
                    AlertDialog alert = builder.create();

                    alert.show();

                     */
                    new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                            .setTitleText("Dont take this medicine now")
                            .setContentText(s)
                            .setCustomImage(R.drawable.danger)

                            .setConfirmText("OK")
                            .show();

        }
            }
            if (update_info.equals("afternoon")) {
                //Toast.makeText(getApplicationContext(), "morning", Toast.LENGTH_SHORT).show();

                if (first.getAfternoon() == 1) {
                    // Toast.makeText(getApplicationContext(), "e.getMessage()", Toast.LENGTH_SHORT).show();
                    slide.setVisibility(View.INVISIBLE);
                    new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                            .setTitleText("Dont take this medicine now")
                            .setContentText(s)
                            .setCustomImage(R.drawable.danger)

                            .setConfirmText("OK")
                            .show();

                }
            }
            if (update_info.equals("evening")) {
                //Toast.makeText(getApplicationContext(), "morning", Toast.LENGTH_SHORT).show();

                if (first.getEvening() == 1) {
                    // Toast.makeText(getApplicationContext(), "e.getMessage()", Toast.LENGTH_SHORT).show();
                    slide.setVisibility(View.INVISIBLE);
                    new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                            .setTitleText("Dont take this medicine now")
                            .setContentText(s)
                            .setCustomImage(R.drawable.danger)

                            .setConfirmText("OK")
                            .show();

                }
            }
            if (update_info.equals("night")) {
                //Toast.makeText(getApplicationContext(), "morning", Toast.LENGTH_SHORT).show();

                if (first.getNight() == 1) {
                    // Toast.makeText(getApplicationContext(), "e.getMessage()", Toast.LENGTH_SHORT).show();
                    slide.setVisibility(View.INVISIBLE);
                    new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                            .setTitleText("Dont take this medicine now")
                            .setContentText(s)
                            .setCustomImage(R.drawable.danger)

                            .setConfirmText("OK")
                            .show();

                }
            }



        }
       // Toast.makeText(getApplicationContext(), "morning_1", Toast.LENGTH_SHORT).show();

        slide.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull SlideToActView view) {
                dbHelper.attend(name,dateTime,update_info);
                dbHelper.degrade(name);

            }
        });







    }

    private void openDialogVideo() {
        dialog.setContentView(R.layout.video_view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color. TRANSPARENT));
        Button btnok = dialog.findViewById(R.id.ok_video);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

    @Override
    protected void onStart() {

        super.onStart();
    }
}