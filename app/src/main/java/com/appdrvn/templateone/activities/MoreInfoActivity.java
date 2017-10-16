package com.appdrvn.templateone.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.models.Restaurant;

import org.parceler.Parcels;

import java.util.Locale;

/**
 * Created by kelvynlaw on 23/09/2017.
 */

public class MoreInfoActivity extends AppCompatActivity {

    static final String INTENT_RESTAURANT = "INTENT_RESTAURANT";
    WebView wvContent;
    Restaurant mRestaurant;

    public static Intent newInstance(Context context, Restaurant restaurant) {
        Intent intent = new Intent(context, MoreInfoActivity.class);
        intent.putExtra(INTENT_RESTAURANT, Parcels.wrap(restaurant));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);


        mRestaurant = Parcels.unwrap(getIntent().getParcelableExtra(INTENT_RESTAURANT));

        TextView tvName = findViewById(R.id.name);
        TextView tvAddress = findViewById(R.id.address);
        wvContent = findViewById(R.id.content);
        ImageView backButton = findViewById(R.id.action_bar_left_icon);


        tvName.setText(mRestaurant.name);
        tvAddress.setText(mRestaurant.address.getFullAddress());
        wvContent.loadData(mRestaurant.htmlContent, "text/html; charset=utf-8", "UTF-8");
        wvContent.getSettings().setJavaScriptEnabled(true);
        backButton.setImageResource(R.drawable.ic_back_white);
        backButton.setOnClickListener(backClickListener);
        tvAddress.setOnClickListener(addressClickListener);

    }

    View.OnClickListener addressClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String output = String.format(Locale.ENGLISH, "geo:0,0?q=") + Uri.encode(String.format("%s@%s,%s", "", mRestaurant.latLng.latitude + "", mRestaurant.latLng.longitude + ""), "UTF-8");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(output));
            startActivity(intent);
        }
    };


    View.OnClickListener backClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    private void setupWebView() {
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                wvContent.loadUrl("javascript:MyApp.resize(document.body.getBoundingClientRect().height)");
                super.onPageFinished(view, url);
            }
        });
        wvContent.addJavascriptInterface(this, "MyApp");
    }

    @JavascriptInterface
    public void resize(final float height) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wvContent.getLayoutParams().height = (int) ((height + 50) * getResources().getDisplayMetrics().density);
            }
        });
    }
}
