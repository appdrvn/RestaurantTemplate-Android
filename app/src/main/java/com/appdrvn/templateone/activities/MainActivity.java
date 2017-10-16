package com.appdrvn.templateone.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.fragments.RestaurantListFragment;
import com.appdrvn.templateone.fragments.MapFragment;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

public class MainActivity extends AppCompatActivity {

    View mVListView, mVMapView;
    Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, new RestaurantListFragment()).commit();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        mVListView = findViewById(R.id.list_view);
        mVMapView = findViewById(R.id.map_view);

        mVListView.setSelected(true);
        mVMapView.setSelected(false);

        mVListView.setOnClickListener(listViewClickListener);
        mVMapView.setOnClickListener(mapViewClickListener);

    }

    View.OnClickListener listViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mCurrentFragment = new RestaurantListFragment();
            ft.replace(R.id.content_frame, mCurrentFragment).commit();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);


            mVListView.setSelected(true);
            mVMapView.setSelected(false);
        }
    };
    View.OnClickListener mapViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mCurrentFragment = new MapFragment();
            ft.replace(R.id.content_frame, mCurrentFragment).commit();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);


            mVListView.setSelected(false);
            mVMapView.setSelected(true);
        }
    };

    @Override
    public void onBackPressed() {
        if (mCurrentFragment instanceof RestaurantListFragment) {
            super.onBackPressed();
        } else {
            listViewClickListener.onClick(mVListView);
        }
    }
}
