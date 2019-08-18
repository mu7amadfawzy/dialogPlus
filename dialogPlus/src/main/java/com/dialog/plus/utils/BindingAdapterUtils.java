package com.dialog.plus.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.dialog.plus.ui.DialogUiModel;
import com.dialog.plus.ui.PinEntryEditText;

import carbon.BR;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class BindingAdapterUtils {

    @BindingAdapter("visible")
    public static void visibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("background_color")
    public static void background_color(View view, @ColorRes int colorRes) {
        if (colorRes != 0 && colorRes != 0) {
            view.setBackground(null);
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorRes));
        }
    }

    @BindingAdapter("background_drawable")
    public static void background_drawable(View view, @DrawableRes int drawable) {
        if (drawable != 0 && drawable != 0) {
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setBackground(ContextCompat.getDrawable(view.getContext(), drawable));
        }
    }

    @BindingAdapter("text_color")
    public static void text_color(TextView view, @ColorRes int colorRes) {
        if (colorRes != 0 && colorRes != 0)
            view.setTextColor(ContextCompat.getColor(view.getContext(), colorRes));
    }

    @BindingAdapter("tint_color")
    public static void tint_color(ImageView view, @ColorRes int colorRes) {
        if (colorRes != 0 && colorRes != 0)
            view.setColorFilter(ContextCompat.getColor(view.getContext(), colorRes));
    }

    @BindingAdapter("setText")
    public static void setText(TextView textView, String text) {
        if (text != null && !text.isEmpty())
            textView.setText(text);
    }

    @BindingAdapter("charsNumber")
    public static void charsNumber(PinEntryEditText textView, int charsNumber) {
        textView.setNumOfChars(charsNumber);
    }

    @BindingAdapter("dialog_width_percent")
    public static void setLayoutWidthPercent(View view, int percent) {
        float approxWidth = getScreenWidth(view.getContext()) * (percent / 100f);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = Math.round(approxWidth);
        view.setLayoutParams(params);
    }

    private static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    @BindingAdapter({"bind:includeLayout", "model"})
    public static void includeLayout(ViewGroup view, int viewRes, DialogUiModel model) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), viewRes, view, true);
        binding.setVariable(BR.model, model);
        binding.executePendingBindings();
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
