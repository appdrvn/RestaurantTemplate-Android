package com.appdrvn.templateone.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.adapters.BannerPagerAdapter;
import com.appdrvn.templateone.delegates.Util;
import com.appdrvn.templateone.fragments.RestaurantMenuFragment;
import com.appdrvn.templateone.models.Restaurant;

import org.parceler.Parcels;

/**
 * Created by kelvynlaw on 20/09/2017.
 */

public class DetailsActivity extends AppCompatActivity {

    static final String INTENT_RESTAURANT = "INTENT_RESTAURANT";

    Restaurant mRestaurant = new Restaurant();

    BannerPagerAdapter mBannerAdapter;
    TextView mTvTitle, mTvAddress;
    RatingBar mRbRating;
    ViewPager mVpBanner;
    FrameLayout mFlContent;

    CoordinatorLayout coordinatorLayout;
    AppBarLayout appBarLayout;

    public static Intent newInstance(Context context, Restaurant restaurant) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(INTENT_RESTAURANT, Parcels.wrap(restaurant));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        appBarLayout = findViewById(R.id.app_bar_layout);
        mTvTitle = findViewById(R.id.title);
        mTvAddress = findViewById(R.id.address);
        mVpBanner = findViewById(R.id.banner);
        mFlContent = findViewById(R.id.content);
        mRbRating = findViewById(R.id.rating);

        findViewById(R.id.back).setOnClickListener(backClickListener);
        findViewById(R.id.info).setOnClickListener(infoClickListener);

        loadData();

        mVpBanner.getLayoutParams().height = (int)(((double)Util.getScreenSize(DetailsActivity.this).x /16d * 9d));

    }

    View.OnClickListener backClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    View.OnClickListener infoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(MoreInfoActivity.newInstance(DetailsActivity.this,mRestaurant));
        }
    };

    public void loadData() {
        mRestaurant = Parcels.unwrap(getIntent().getParcelableExtra(INTENT_RESTAURANT));
        fillInData();
    }

    private void fillInData() {
        mTvAddress.setText(mRestaurant.address.getFullAddress());
        mTvTitle.setText(mRestaurant.name);
        mRbRating.setRating((float) mRestaurant.rating);

        mBannerAdapter = new BannerPagerAdapter(DetailsActivity.this, mRestaurant.images);
        mVpBanner.setAdapter(mBannerAdapter);
        h1.postDelayed(mPromoBannerAnimate, 3500);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, RestaurantMenuFragment.newInstance(mRestaurant)).commit();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    private Runnable mPromoBannerAnimate = new Runnable() {
        public void run() {
            try {
                if (mVpBanner.getCurrentItem() + 1 < mVpBanner.getAdapter().getCount()) {
                    mVpBanner.setCurrentItem(mVpBanner.getCurrentItem() + 1);
                } else {
                    mVpBanner.setCurrentItem(0);
                }
                h1.postDelayed(mPromoBannerAnimate, 3500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler h1 = new Handler();
}


