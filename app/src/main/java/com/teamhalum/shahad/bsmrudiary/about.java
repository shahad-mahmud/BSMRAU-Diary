package com.teamhalum.shahad.bsmrudiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class about extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar6);
        //setSupportActionBar(myToolbar);
        //this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        mWebView = (WebView) findViewById(R.id.webview6);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Use remote resource
        mWebView.loadUrl("https://en.wikipedia.org/wiki/Bangabandhu_Sheikh_Mujibur_Rahman_Agricultural_University");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
