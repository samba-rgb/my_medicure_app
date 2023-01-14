package id.samai.mymedcure.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import id.samai.mymedcure.R;

public class web_article extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_article);
        WebView w = (WebView) findViewById(R.id.web);


        // loading http://www.google.com url in the the WebView.
        w.loadUrl("https://my.clevelandclinic.org/health/articles/4062-chronic-illness");

        // this will enable the javascipt.
        WebSettings settings = w.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        w.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        w.setScrollbarFadingEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            w.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        // w.setWebViewClient(new WebViewClient());
        w.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                //True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.
                return true;
            }
        });
    }
}