package com.appdrvn.templateone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appdrvn.templateone.R;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing);

        findViewById(R.id.continue_button).setOnClickListener(continueClickListener);

    }

    View.OnClickListener continueClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LandingActivity.this, MainActivity.class));
            finish();
        }
    };
}
