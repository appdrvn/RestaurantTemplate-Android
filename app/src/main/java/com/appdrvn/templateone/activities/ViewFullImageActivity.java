package com.appdrvn.templateone.activities;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.adapters.ViewFullImageAdapter;
import com.appdrvn.templateone.delegates.DialogsDelegates;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import uk.co.senab.photoview.PhotoViewAttacher;


public class ViewFullImageActivity extends AppCompatActivity {
    final private String TAG = "ViewFullImageActivity";


    final private int REQUEST_CODE = 207;

    final static public String INTENT_IMAGES = "INTENT_ADVERTISEMENT";
    final static public String INTENT_IMAGE_POSITION = "INTENT_IMAGE_POSITION";
    final static public String INTENT_IMAGE_ORIENTATION = "INTENT_IMAGE_ORIENTATION";

    ViewPager pager;
    View mVToolbar;
    String[] images = new String[0];
    ProgressDialog mProgressDialog;


    public static Intent newInstance(Context context, String[] images) {
        Intent intent = new Intent(context, ViewFullImageActivity.class);
        intent.putExtra(INTENT_IMAGES, images);
        return intent;
    }

    public static Intent newInstance(Context context, String[] images, int position) {
        Intent intent = newInstance(context, images);
        intent.putExtra(INTENT_IMAGE_POSITION, position);
        return intent;
    }


    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full);
        mVToolbar = findViewById(R.id.toolbar_layout);

        FrameLayout leftIconLayout = findViewById(R.id.action_bar_left_icon_layout);
        ImageView leftIcon = findViewById(R.id.action_bar_left_icon);
        leftIcon.setImageDrawable(AppCompatResources.getDrawable(ViewFullImageActivity.this, R.drawable.ic_back_white));
        leftIconLayout.setOnClickListener(backOnClickListener);
        leftIconLayout.setVisibility(View.VISIBLE);

        FrameLayout rightIconLayout = findViewById(R.id.action_bar_right_icon_layout);
        ImageView rightIcon = findViewById(R.id.action_bar_right_icon);
        rightIcon.setImageDrawable(AppCompatResources.getDrawable(ViewFullImageActivity.this, R.drawable.ic_more_white));
        rightIconLayout.setOnClickListener(moreClickListener);
        rightIconLayout.setVisibility(View.VISIBLE);

        pager = findViewById(R.id.basic_pager);

        images = getIntent().getStringArrayExtra(INTENT_IMAGES);
        ViewFullImageAdapter adapter = new ViewFullImageAdapter(this, images);
        adapter.setTapListener(tapListener);
        pager.setAdapter(adapter);
        pager.setCurrentItem(getIntent().getIntExtra(INTENT_IMAGE_POSITION, 0));
        registerForContextMenu(rightIconLayout);

    }

    View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    };

    View.OnClickListener moreClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String[] moreOptions = getResources().getStringArray(R.array.more_options);

            DialogsDelegates.showOptions(moreOptions, getResources().getString(R.string.options), ViewFullImageActivity.this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (moreOptions[which].equalsIgnoreCase(getResources().getString(R.string.download))) {
                        queueDownload();
                    } else if (moreOptions[which].equalsIgnoreCase(getResources().getString(R.string.share))) {
                        downloadPhotoForShare();
                    }
                }
            });
        }
    };

    private void queueDownload() {
        if (pager.getCurrentItem() > images.length) {
            return;
        }
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri Download_Uri = Uri.parse(images[pager.getCurrentItem()]);
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);

        //Restrict the types of networks over which this download may proceed.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //Set whether this download may proceed over a roaming connection.
        request.setAllowedOverRoaming(false);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //Set the title of this download, to be displayed in notifications (if enabled).
        request.setTitle(getResources().getString(R.string.app_name));
        //Set a description of this download, to be displayed in notifications (if enabled)
        request.setDescription(getResources().getString(R.string.downloading_image));
        //Set the local destination for the downloaded file to a path within the application's external files directory
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "IMG_" + getResources().getString(R.string.app_name) + "_" + System.currentTimeMillis() + ".jpg");

        //Enqueue a new download and same the referenceId
        long downloadReference = downloadManager.enqueue(request);


        Log.d(TAG, "Image saved");
    }


    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Snackbar.make(pager, R.string.image_downloaded, Snackbar.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(downloadReceiver);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    PhotoViewAttacher.OnPhotoTapListener tapListener = new PhotoViewAttacher.OnPhotoTapListener() {
        @Override
        public void onPhotoTap(View view, float x, float y) {
            if (mVToolbar.getVisibility() == View.VISIBLE) {
                mVToolbar.setVisibility(View.GONE);
            } else {
                mVToolbar.setVisibility(View.VISIBLE);
            }
        }
    };

    private void downloadPhotoForShare() {
        mProgressDialog = DialogsDelegates.showProgressDialog(ViewFullImageActivity.this, false);
        if (pager.getCurrentItem() > images.length) {
            return;
        }
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(images[pager.getCurrentItem()])
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        prepareToShare(resource);

                    }
                });
    }

    private void prepareToShare(Bitmap bitmap) {
        try {
            File cachePath = new File(getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();
            sharePhoto();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sharePhoto() {
        File imagePath = new File(getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(ViewFullImageActivity.this, "com.appdrvn.templateone.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.share_photo)));
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }

        }
    }
}
