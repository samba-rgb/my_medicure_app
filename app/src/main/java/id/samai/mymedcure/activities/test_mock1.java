package id.samai.mymedcure.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import id.samai.mymedcure.R;

public class test_mock1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mock1);
        WebView w = (WebView) findViewById(R.id.web);

        // loading http://www.google.com url in the the WebView.
        Bundle b = new Bundle();

        b = getIntent().getExtras();
        String name = b.getString("name");
        String url="";
        String pdf = name;
        try {
            url= URLEncoder.encode(pdf,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        w.getSettings().setJavaScriptEnabled(true);

        w.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);

        // this will enable the javascipt.

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        // w.setWebViewClient(new WebViewClient());
    }
}