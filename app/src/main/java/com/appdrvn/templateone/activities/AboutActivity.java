package com.appdrvn.templateone.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.adapters.AboutReferenceAdapter;
import com.appdrvn.templateone.models.AboutReference;

/**
 * Created by kelvynlaw on 27/09/2017.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView backButton = findViewById(R.id.action_bar_left_icon);
        backButton.setImageResource(R.drawable.ic_back_white);
        backButton.setOnClickListener(backClickListener);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        AboutReferenceAdapter adapter = new AboutReferenceAdapter(AboutActivity.this, AboutReference.createData());

        recyclerView.setLayoutManager(new LinearLayoutManager(AboutActivity.this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.find_us_on_facebook).setOnClickListener(findUsOnFBClickListener);
    }

    View.OnClickListener backClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    View.OnClickListener findUsOnFBClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String FACEBOOK_URL = "https://www.facebook.com/Appdrvn/";
            String FACEBOOK_PAGE_ID = "Appdrvn";
            String url = "";

            //method to get the right URL to use in the intent
//            PackageManager packageManager = getPackageManager();
//            try {
//                int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
//                if (versionCode >= 3002850) { //newer versions of fb app
//                    url = "fb://facewebmodal/f?href=" + FACEBOOK_URL;
//                } else { //older versions of fb app
//                    url = "fb://page/" + FACEBOOK_PAGE_ID;
//                }
//            } catch (PackageManager.NameNotFoundException e) {
                url = FACEBOOK_URL; //normal web url
//            }


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);


        }
    };




}
