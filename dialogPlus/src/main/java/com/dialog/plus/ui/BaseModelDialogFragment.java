package com.dialog.plus.ui;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.databinding.ViewDataBinding;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dialog.plus.utils.KeyboardUtil;

/**
 * Created by fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 * <p>
 * param Binding: defines the layout binding implementation.
 */

public abstract class BaseModelDialogFragment<Binding extends ViewDataBinding> extends BaseDialogFragment<Binding> {
    protected DialogUiModel model = new DialogUiModel();

    /**
     * sets the background color to header background and positive background
     */
    public BaseModelDialogFragment<Binding> setPrimaryBgColor(@ColorRes int primaryColor) {
        model.setPositiveBgColor(primaryColor);
        model.setHeaderBgColor(primaryColor);
        return this;
    }

    /**
     * sets the background drawable to header background and positive background
     */
    public BaseModelDialogFragment<Binding> setPrimaryDrawable(@DrawableRes int primaryDrawable) {
        model.setPositiveBgDrawable(primaryDrawable);
        model.setHeaderBgDrawable(primaryDrawable);
        return this;
    }

    /**
     * sets the text color to header background and positive background
     */
    public BaseModelDialogFragment<Binding> setPrimaryTextColor(@ColorRes int primaryTextColor) {
        model.setPositiveTextColor(primaryTextColor);
        model.setHeaderTextColor(primaryTextColor);
        return this;
    }

    /**
     * sets the background color to the negative
     */

    public BaseModelDialogFragment<Binding> setSecondaryBgColor(@ColorRes int secondaryColor) {
        model.setNegativeBgColor(secondaryColor);
        return this;
    }

    /**
     * sets the background drawable to the negative
     */
    public BaseModelDialogFragment<Binding> setSecondaryBgDrawable(@DrawableRes int secondaryDrawable) {
        model.setNegativeBgDrawable(secondaryDrawable);
        return this;
    }

    /**
     * sets the text color to the negative
     */
    public BaseModelDialogFragment<Binding> setSecondaryTextColor(@ColorRes int secondaryTextColor) {
        model.setNegativeBgColor(secondaryTextColor);
        return this;
    }

    /**
     * sets the background color to the header background and positive andnegative
     */
    public BaseModelDialogFragment<Binding> setBackgroundColors(@ColorRes int positiveBackground, @ColorRes int negativeColorRes) {
        return setBackgroundColors(positiveBackground, negativeColorRes, 0);
    }

    public BaseModelDialogFragment<Binding> setBackgroundColors(@ColorRes int positiveBgColor, @ColorRes int negativeBgColor, @ColorRes int headerBgColor) {
        model.setPositiveBgColor(positiveBgColor);
        model.setHeaderBgColor(headerBgColor);
        model.setNegativeBgColor(negativeBgColor);
        return this;
    }

    /**
     * sets the text color to the header background and positive and negative
     */
    public BaseModelDialogFragment<Binding> setTextColors(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor) {
        return setTextColors(positiveTextColor, negativeTextColor, 0);
    }

    /**
     * sets the text to the positiveBtn and negativeBtn
     */
    public BaseModelDialogFragment<Binding> setTexts(String positiveText) {
        return setTexts(positiveText, null);
    }

    public BaseModelDialogFragment<Binding> setTexts(String positiveText, String negativeText) {
        model.setPositiveText(positiveText);
        model.setNegativeText(negativeText);
        return this;
    }

    public BaseModelDialogFragment<Binding> setTextColors(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor, @ColorRes int headerTextColor) {
        model.setPositiveTextColor(positiveTextColor);
        model.setHeaderTextColor(headerTextColor);
        model.setNegativeTextColor(negativeTextColor);
        return this;
    }

    /**
     * sets the background drawable to the header background and positive andnegative
     */
    public BaseModelDialogFragment<Binding> setBackgrounds(@DrawableRes int positiveBackground, @DrawableRes int negativeBackground) {
        return setBackgrounds(positiveBackground, negativeBackground, 0);
    }

    public BaseModelDialogFragment<Binding> setBackgrounds(@DrawableRes int positiveBgDrawable, @DrawableRes int negativeBgDrawable, @DrawableRes int headerBgDrawable) {
        model.setPositiveBgDrawable(positiveBgDrawable);
        model.setHeaderBgDrawable(headerBgDrawable);
        model.setNegativeBgDrawable(negativeBgDrawable);
        return this;
    }

    public BaseModelDialogFragment<Binding> setHeaderBgColor(@ColorRes int headerBgColor) {
        model.setHeaderBgColor(headerBgColor);
        return this;
    }

    public BaseModelDialogFragment<Binding> setNegativeBgColor(@ColorRes int negativeBgColor) {
        model.setNegativeBgColor(negativeBgColor);
        return this;
    }

    public BaseModelDialogFragment<Binding> setNegativeTextColor(@ColorRes int negativeTextColor) {
        model.setNegativeTextColor(negativeTextColor);
        return this;
    }

    public BaseModelDialogFragment<Binding> setHeaderTextColor(@ColorRes int headerTextColor) {
        model.setHeaderTextColor(headerTextColor);
        return this;
    }

    public BaseModelDialogFragment<Binding> setDialogCodeTextColor(@ColorInt int dialogCodeTextColor) {
        model.setDialogCodeTextColor(dialogCodeTextColor);
        return this;
    }

    public BaseModelDialogFragment<Binding> setHeaderBgDrawable(@DrawableRes int headerBgDrawable) {
        model.setHeaderBgDrawable(headerBgDrawable);
        return this;
    }

    protected BaseModelDialogFragment setDialog_type(@DialogPlus.TYPE int dialog_type) {
        model.setDialog_type(dialog_type);
        return this;
    }

    public BaseModelDialogFragment<Binding> setContent(String content) {
        model.setContent(content);
        return this;
    }

    protected BaseModelDialogFragment<Binding> setNormalTextColor() {
        model.setDialogCodeTextColor(model.getDialogCodeTextColor());
        return this;
    }

    protected BaseModelDialogFragment<Binding> setMessageDialog(String positiveText) {
        model.setPositiveText(positiveText);
        setDialog_type(DialogPlus.TYPE.MESSAGE);
        return this;
    }

    protected void shakeView(EditText editText) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .onEnd(animator -> editText.setText(null))
                .playOn(editText);
    }

    public BaseModelDialogFragment<Binding> setTitle(String title) {
        model.setTitle(title);
        return this;
    }

    protected void hideKeyboard(View view) {
        KeyboardUtil.getInstance().hideKeyboard(view);
    }

    protected void showKeyboard(View view) {
        KeyboardUtil.getInstance().showKeyboard(view);
    }
}
