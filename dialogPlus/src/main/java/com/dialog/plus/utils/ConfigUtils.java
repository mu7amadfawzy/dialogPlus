package com.dialog.plus.utils;

import android.content.Context;
import android.os.Build;

import java.util.Locale;

/**
 * Created by Fawzy on 02,October,2019
 * ma7madfawzy@gmail.com
 **/
public class ConfigUtils {
    public static Locale getCurrentLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }
}
