package com.appdrvn.templateone.delegates;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.activities.ViewFullImageActivity;


public class DialogsDelegates {


    public static void showOptions(String[] options, String title, Context c,
                                   DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(c)
                .setTitle(title)
                .setNegativeButton(c.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setItems(options, listener).show();
    }

    public static void showMessage(Context c, String message, String title,
                                   DialogInterface.OnClickListener callback) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(c);
        builder1.setMessage(message);
        builder1.setCancelable(false);
        builder1.setTitle(title);
        builder1.setPositiveButton("OK", callback);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static void showConfirmation(Context c, String message, String title,
                                        String yesButtonLabel, String noButtonLabel, DialogInterface.OnClickListener callback) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(c);
        builder1.setMessage(message);
        builder1.setCancelable(false);
        builder1.setTitle(title);
        try {
            builder1.setNegativeButton(noButtonLabel, callback);
            builder1.setPositiveButton(yesButtonLabel, callback);
        } catch (Exception ex) {
            ex.printStackTrace();
            builder1.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder1.setPositiveButton("OK", callback);
        }
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static ProgressDialog showProgressDialog(Context mContext, boolean mProgressCancelable) {
        ProgressDialog mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage(mContext.getResources().getString(R.string.loading));
        mProgressDialog.setCancelable(mProgressCancelable);
        mProgressDialog.show();
        return mProgressDialog;
    }
}
