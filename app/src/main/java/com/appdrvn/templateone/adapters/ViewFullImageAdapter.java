package com.appdrvn.templateone.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.appdrvn.templateone.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class ViewFullImageAdapter extends PagerAdapter {

    private static final String TAG = "ViewFullImageAdapter";
    private String[] mImages;
    Context mContext;
    RequestOptions requestOptions = new RequestOptions().centerInside()
            .placeholder(R.drawable.img_placeholder);

    public ViewFullImageAdapter(Context context, String[] images) {
        mImages = images;
        this.mContext = context;
    }

    PhotoViewAttacher.OnPhotoTapListener tapListener;

    public void setTapListener(PhotoViewAttacher.OnPhotoTapListener tapListener) {
        this.tapListener = tapListener;
    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PhotoView img = new PhotoView(container.getContext());
        img.setOnPhotoTapListener(tapListener);
        Log.d(TAG,"Image[" + position + "]->" + mImages[position]);

        try {
            if (mImages[position].startsWith("http://") || mImages[position].startsWith("https://")) {
                Glide.with(mContext)
                        .load(mImages[position])
                        .apply(requestOptions)
                        .into(img);
            } else {
                Glide.with(mContext)
                        .load(new File(mImages[position]))
                        .apply(requestOptions)
                        .into(img);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}