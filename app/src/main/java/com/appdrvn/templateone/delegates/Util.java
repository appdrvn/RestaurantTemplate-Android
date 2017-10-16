package com.appdrvn.templateone.delegates;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.util.Log;
import android.view.Display;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

public class Util {


    public static Point getScreenSize(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return size;
        }else{
            return null;
        }
    }
    public static Intent openBrowserIntent(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        return i;
    }

}
