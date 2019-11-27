package com.dialog.plus.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.Gravity.CENTER;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class CommonUtil {
    private static CommonUtil commonUtil;

    public static CommonUtil getInstance() {
        if (commonUtil == null)
            commonUtil = new CommonUtil();
        return commonUtil;
    }

    public ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.getWindow().setGravity(CENTER);
        }
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
//        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public void showKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public void hideKeyboard(View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    public int getMaxDayInMonth(int yearValue, int monthValue) {
        // First get an instance of calendar object.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, yearValue);
        calendar.set(Calendar.MONTH, monthValue - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay;
    }

    private int[] getAllDays(int maxDay) {
        int[] days = new int[maxDay - 1];
        for (int i = 1; i <= maxDay; i++)
            days[i - 1] = i;
        return days;
    }

    public String[] getMonthNames(int minMonth, int maxMonth) {
        String[] names = new String[(maxMonth - minMonth) + 1];
        int index = 0;
        for (int i = minMonth; i < maxMonth; i++) {
            names[index] = getMonthName(i);
            index++;
            minMonth++;
        }
        return names;
    }

    public String getMonthName(int monthOfYear) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, monthOfYear);
        return new SimpleDateFormat("LLL").format(c.getTime());
    }

    public String formatDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE dd LLL yyyy ");
        return fmt.format(calendar.getTime());
    }

    public String formatDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        SimpleDateFormat fmt = new SimpleDateFormat("LLL yyyy ");
        return fmt.format(calendar.getTime());
    }
}
