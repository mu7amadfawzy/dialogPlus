package com.dialog.plus.utils;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class BindingAdapterUtils {
    @BindingAdapter("visibility")
    public static void visibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("background_color")
    public static void background_color(View view, @ColorRes int colorRes) {
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorRes));
    }

    @BindingAdapter("underline")
    public static void underline(View view, boolean doUnderline) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (doUnderline) {
                SpannableString content = new SpannableString(textView.getText().toString());
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                textView.setText(content);
            }
        } else if (view instanceof CheckBox) {
            CheckBox checkBox = (CheckBox) view;
            if (doUnderline) {
                SpannableString content = new SpannableString(checkBox.getText().toString());
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                checkBox.setText(content);
            }
        }
    }
}
