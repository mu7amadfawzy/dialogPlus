package com.dialog.plus.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.dialog.plus.R;
import com.dialog.plus.databinding.LayoutDialogOptionBinding;
import com.dialog.plus.ui.DialogUiModel;
import com.dialog.plus.ui.MultiOptionsDialog;
import com.dialog.plus.ui.PinEntryEditText;

import java.util.List;

import carbon.BR;
import carbon.widget.Button;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class BindingAdapterUtils {

    private static final short COLOR_NOT_EXIST = -99;

    @BindingAdapter("visible")
    public static void visibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("strokeWidth")
    public static void strokeWidth(carbon.view.View view, float width) {
        view.setStrokeWidth(width);
    }

    @BindingAdapter("setSelected")
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    @BindingAdapter("strokeColor")
    public static void strokeColor(carbon.widget.LinearLayout view, @ColorRes int colorRes) {
        view.setStroke(colorRes);
    }

    private static int getColor(@ColorRes int colorRes, Context context) {
        try {
            return ContextCompat.getColor(context, colorRes);
        } catch (Resources.NotFoundException e) {
            return COLOR_NOT_EXIST;
        }
    }

    @BindingAdapter("cornerRadius")
    public static void cornerRadius(carbon.view.View view, float corner) {
        view.setCornerRadius(corner);
    }

    @BindingAdapter("cornerRadius")
    public static void cornerRadius(Button view, float corner) {
        view.setCornerRadius(corner);
    }

    @BindingAdapter("cornerRadius")
    public static void cornerRadius(carbon.widget.LinearLayout view, float corner) {
        view.setCornerRadius(corner);
    }

    @BindingAdapter("marginTop")
    public static void marginTop(carbon.view.View view, float margin) {
        if (view.getLayoutParams() instanceof carbon.widget.LinearLayout.LayoutParams) {
            carbon.widget.LinearLayout.LayoutParams params = (carbon.widget.LinearLayout.LayoutParams) view.getLayoutParams();
            params.topMargin = Math.round(margin);
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("marginTop")
    public static void marginTop(View view, float margin) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.topMargin = Math.round(margin);
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("marginBottom")
    public static void marginBottom(carbon.view.View view, float margin) {
        if (view.getLayoutParams() instanceof carbon.widget.LinearLayout.LayoutParams) {
            carbon.widget.LinearLayout.LayoutParams params = (carbon.widget.LinearLayout.LayoutParams) view.getLayoutParams();
            params.bottomMargin = Math.round(margin);
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("marginBottom")
    public static void marginBottom(View view, float margin) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.bottomMargin = Math.round(margin);
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("dialog_elevation")
    public static void dialog_elevation(Button view, float elevation) {
        view.setElevation(elevation);
    }

    private static boolean resourceExist(int res) {
        return res != -1;
    }

    @BindingAdapter("layout_weight")
    public static void layout_weight(View view, float weight) {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) view.getLayoutParams();
            param.weight = weight;
            view.setLayoutParams(param);
        }
    }

    @BindingAdapter("backgroundColor")
    public static void backgroundColor(View view, @ColorRes int colorRes) {
        if (!resourceExist(colorRes))
            return;
        int color = getColor(colorRes, view.getContext());
        if (color != COLOR_NOT_EXIST) {//if color exists
            view.setBackground(null);
            view.setBackgroundColor(color);
        } else Log.e("DialogPlus !!!", "ResourcesNotFoundException to setBackgroundColor");
    }

    @BindingAdapter("tintColor")
    public static void tintColor(ImageView view, @ColorRes int tintColor) {
        if (!resourceExist(tintColor))
            return;
        int color = getColor(tintColor, view.getContext());
        if (color != COLOR_NOT_EXIST) {//if color exists
            view.setColorFilter(color);
        } else Log.e("DialogPlus !!!", "ResourcesNotFoundException to setBackgroundColor");
    }

    @BindingAdapter("strokeColor")
    public static void strokeColor(carbon.view.View view, @ColorInt int colorRes) {
        view.setStroke(colorRes);
    }

    @BindingAdapter("backgroundDrawable")
    public static void backgroundDrawable(View view, @DrawableRes int drawableRes) {
        if (!resourceExist(drawableRes))
            return;
        Drawable drawable = getDrawable(view, drawableRes);

        if (drawable != null) {
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setBackground(drawable);
        } else Log.e("DialogPlus !!!", "ResourcesNotFoundException to backgroundDrawable");
    }

    private static Drawable getDrawable(View view, @DrawableRes int drawableRes) {
        try {
            return ContextCompat.getDrawable(view.getContext(), drawableRes);
        } catch (Exception e) {
            return null;
        }
    }

    @BindingAdapter("text_color")
    public static void text_color(TextView view, @ColorRes int colorRes) {
        if (!resourceExist(colorRes))
            return;
        int color = getColor(colorRes, view.getContext());
        if (resourceExist(color))
            view.setTextColor(color);
        else Log.e("DialogPlus !!!", "ResourcesNotFoundException to text_color");
    }

    @BindingAdapter("tint_color")
    public static void tint_color(ImageView view, @ColorRes int colorRes) {
        if (!resourceExist(colorRes))
            return;
        int color = getColor(colorRes, view.getContext());
        if (resourceExist(color))
            view.setColorFilter(color);
        else Log.e("DialogPlus !!!", "ResourcesNotFoundException to tint_color");
    }

    @BindingAdapter("setText")
    public static void setText(TextView textView, String text) {
        if (text != null && !text.isEmpty())
            textView.setText(text);
    }

    @BindingAdapter("dialog_plus_charsNumber")
    public static void dialog_plus_charsNumber(PinEntryEditText textView, int dialog_plus_charsNumber) {
        textView.setNumOfChars(dialog_plus_charsNumber);
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

    @BindingAdapter({"includeLayout", "model"})
    public static void includeLayout(ViewGroup view, int viewRes, DialogUiModel model) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), viewRes, view, true);
        binding.setVariable(BR.model, model);
        binding.executePendingBindings();
    }

    @BindingAdapter({"addOptions", "callback"})
    public static void addOptions(ViewGroup view, List options, MultiOptionsDialog.ActionListener callback) {
        for (Object text : options) {
            LayoutDialogOptionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), R.layout.layout_dialog_option, view, true);
            binding.setText((String) text);
            binding.actionBtn.setOnClickListener(v -> callback.onActionClicked(binding.actionBtn.getText().toString()));
            binding.executePendingBindings();
        }
    }

    @BindingAdapter("dialog_plus_underline")
    public static void dialog_plus_underline(TextView textView, boolean doUnderline) {
        if (doUnderline) {
            SpannableString content = new SpannableString(textView.getText().toString());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            textView.setText(content);
        }
    }

    @BindingAdapter("pinEntryTextColor")
    public static void setCodeTextColor(PinEntryEditText view, @ColorInt int colorInt) {
        view.setTextColor(colorInt);
        view.getPaint().setColor(colorInt);
    }
}
