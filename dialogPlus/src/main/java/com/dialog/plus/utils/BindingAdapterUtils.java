package com.dialog.plus.utils;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        if (colorRes != -1 && colorRes != 0)
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorRes));
    }

    @BindingAdapter("background_drawable")
    public static void background_drawable(View view, @DrawableRes int drawable) {
        if (drawable != -1 && drawable != 0)
            view.setBackground(ContextCompat.getDrawable(view.getContext(), drawable));
    }

    @BindingAdapter("text_color")
    public static void text_color(TextView view, @ColorRes int colorRes) {
        if (colorRes != -1 && colorRes != 0)
            view.setTextColor(ContextCompat.getColor(view.getContext(), colorRes));
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
