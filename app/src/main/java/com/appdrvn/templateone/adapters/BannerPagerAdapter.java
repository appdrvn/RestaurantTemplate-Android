package com.appdrvn.templateone.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.activities.ViewFullImageActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class BannerPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    String[] mImages = new String[0];
    boolean mImageLoaded = false;
    private String TAG = "BannerAdapter";
    boolean centerCrop = false;
    boolean clickable = true;

    RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.img_placeholder);

    public BannerPagerAdapter(Context context, String[] banners) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImages = banners;
    }

    @Override
    public int getCount() {
        if (mImages != null && mImages.length > 0)
            return mImages.length;
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View itemView = mLayoutInflater.inflate(R.layout.pager_banner, container, false);

        ImageView imageView = itemView.findViewById(R.id.image);
        container.addView(itemView);

        itemView.setTag(position);
        try {
            Glide.with(mContext)
                    .load(mImages[position])
                    .apply(requestOptions)
                    .into(imageView);
        } catch (Exception ex) {

        }
        itemView.setTag(position);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                mContext.startActivity(ViewFullImageActivity.newInstance(mContext, mImages, position));
            }
        });

        return itemView;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}