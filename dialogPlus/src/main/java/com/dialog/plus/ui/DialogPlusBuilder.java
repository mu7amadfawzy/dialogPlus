package com.dialog.plus.ui;

import android.widget.EditText;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dialog.plus.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by fawzy on 23,September,2019
 * ma7madfawzy@gmail.com
 **/
public class DialogPlusBuilder {
    private DialogUiModel model = new DialogUiModel();

    public DialogPlusBuilder() {
        this(null, null);
    }

    public DialogPlusBuilder(String content) {
        this(null, content);
    }

    public DialogPlusBuilder(String title, String content) {
        this(DialogPlus.TYPE.MESSAGE_DIALOG, title, content);
    }

    public DialogPlusBuilder(@DialogPlus.TYPE int type, String title, String content) {
        set(type, title, content);
    }

    public DialogPlus build() {
        return new DialogPlus(model);
    }

    public MonthYearPickerDialog buildMonthDialog(MonthYearPickerDialog.PickerListener listener) {
        model.setPickerListener(listener);
        return new MonthYearPickerDialog(model, MonthYearPickerDialog.TYPE.MONTH);
    }

    public MonthYearPickerDialog buildYearDialog(MonthYearPickerDialog.PickerListener listener) {
        model.setMaxYear(Calendar.getInstance().get(Calendar.YEAR)).setPickerListener(listener);
        return new MonthYearPickerDialog(model, MonthYearPickerDialog.TYPE.YEAR);
    }

    public MonthYearPickerDialog buildYearDialog(int maxYear, MonthYearPickerDialog.PickerListener listener) {
        model.setMaxYear(maxYear).setPickerListener(listener);
        return new MonthYearPickerDialog(model, MonthYearPickerDialog.TYPE.YEAR);
    }

    public DialogPlus buildMultiOptionsDialog(String title, List<String> optionsTitle, MultiOptionsDialog.ActionListener actionListener) {
        model.setTitle(title).setListDialogItems(optionsTitle).setMultiOptionsDialogListener(actionListener);
        return new DialogPlus(model);
    }

    /**
     * Helper Methods--> helps to set your specific dialog_plus based on parameters
     */

    /**
     * Sets a code confirmation dialog_plus interface
     */
    public DialogPlusBuilder setConfirmCodeDialog(String correct_code, boolean withSend, boolean withResend, int counterSeconds, @ColorInt int codeTextColor, DialogPlus.CodeTypeListener codeTypeListener) {
        setConfirmDialog(correct_code, withSend, withResend, counterSeconds, codeTextColor, codeTypeListener).setDialog_type(DialogPlus.TYPE.CODE_DIALOG);
        return this;
    }

    /**
     * Sets a confirmation dialog_plus interface(with positive and negative actions)
     */
    public DialogPlusBuilder setConfirmationDialog(DialogPlus.DialogActionListener actionClicked) {
        return setConfirmationDialog(null, null, actionClicked);
    }

    public DialogPlusBuilder setConfirmationDialog(String positiveText, String negativeText, DialogPlus.DialogActionListener actionClicked) {
        return setConfirmationDialog(positiveText, negativeText, false, actionClicked);
    }

    public DialogPlusBuilder setConfirmationDialog(String positiveText, String negativeText, boolean separateActionButtons, DialogPlus.DialogActionListener actionClicked) {
        setSeparateActionButtons(separateActionButtons).setDialogActionListener(actionClicked).setDialog_type(DialogPlus.TYPE.CONFIRMATION_DIALOG).setTexts(positiveText, negativeText);
        return this;
    }

    /**
     * Sets a message dialog_plus interface
     */
    public DialogPlusBuilder setMessageDialog(DialogPlus.DialogActionListener actionClicked) {
        return setMessageDialog(null, actionClicked);
    }

    public DialogPlusBuilder setMessageDialog(String positiveText, DialogPlus.DialogActionListener actionClicked) {
        setDialogActionListener(actionClicked).setMessageDialog(positiveText);
        return this;
    }

    /**
     * Sets a list dialog_plus interface
     */
    public DialogPlusBuilder setListDialog(String title, List<String> listItems, DialogPlus.DialogListListener actionClicked) {
        setListItems(listItems).setDialogListListener(actionClicked).setTitle(title).setDialog_type(DialogPlus.TYPE.LIST_DIALOG);
        return this;
    }

    private DialogPlusBuilder setListItems(List<String> listItems) {
        if (model.getListDialogItems().size() > 0) model.getListDialogItems().clear();
        model.setListDialogItems(listItems);
        return this;
    }

    /**
     * Sets an Error dialog_plus interface
     */
    public DialogPlusBuilder setErrorDialog(DialogPlus.DialogActionListener dialogActionListener) {
        return setErrorDialog(null, dialogActionListener);
    }

    public DialogPlusBuilder setErrorDialog(String positiveText, DialogPlus.DialogActionListener dialogActionListener) {
        setDialogActionListener(dialogActionListener).setDialog_type(DialogPlus.TYPE.ERROR_DIALOG).setTexts(positiveText);
        return this;
    }


    /**
     * Sets a Rating dialog_plus interface(with positive and negative actions)
     */
    public DialogPlusBuilder setRatingDialog(DialogPlus.DialogRateListener rateListener) {
        return setRatingDialog(2, null, null, rateListener);
    }

    public DialogPlusBuilder setRatingDialog(float initialRate, String positiveText, String negativeText, DialogPlus.DialogRateListener rateListener) {
        return setRatingDialog(initialRate, positiveText, negativeText, false, rateListener);
    }

    public DialogPlusBuilder setRatingDialog(float initialRate, String positiveText, String negativeText, boolean separateActionButtons, DialogPlus.DialogRateListener rateListener) {
        setInitialRate(initialRate).setSeparateActionButtons(separateActionButtons).setRateListener(rateListener).setDialog_type(DialogPlus.TYPE.RATING_DIALOG).setTexts(positiveText, negativeText);
        return this;
    }

    /**
     * Sets a Success dialog_plus interface
     */
    public DialogPlusBuilder setSuccessDialog(DialogPlus.DialogActionListener dialogActionListener) {
        return setSuccessDialog(null, dialogActionListener);
    }

    public DialogPlusBuilder setSuccessDialog(String positiveText, DialogPlus.DialogActionListener dialogActionListener) {
        setDialogActionListener(dialogActionListener).setDialog_type(DialogPlus.TYPE.SUCCESS_DIALOG).setTexts(positiveText);
        return this;
    }

    /**
     * sets the background color to header background and positive background
     */
    public DialogPlusBuilder setPrimaryBgColor(@ColorRes int primaryColor) {
        model.setPositiveBgColor(primaryColor);
        model.setHeaderBgColor(primaryColor);
        return this;
    }

    /**
     * sets the background drawable to header background and positive background
     */
    public DialogPlusBuilder setPrimaryDrawable(@DrawableRes int primaryDrawable) {
        model.setPositiveBgDrawable(primaryDrawable);
        model.setHeaderBgDrawable(primaryDrawable);
        return this;
    }

    /**
     * sets the text color to header background and positive background
     */
    public DialogPlusBuilder setPrimaryTextColor(@ColorRes int primaryTextColor) {
        model.setPositiveTextColor(primaryTextColor);
        model.setHeaderTextColor(primaryTextColor);
        return this;
    }

    /**
     * sets the background color to the negative
     */

    public DialogPlusBuilder setSecondaryBgColor(@ColorRes int secondaryColor) {
        model.setNegativeBgColor(secondaryColor);
        return this;
    }

    /**
     * sets the background drawable to the negative
     */
    public DialogPlusBuilder setSecondaryBgDrawable(@DrawableRes int secondaryDrawable) {
        model.setNegativeBgDrawable(secondaryDrawable);
        return this;
    }

    /**
     * sets the text color to the negative
     */
    public DialogPlusBuilder setSecondaryTextColor(@ColorRes int secondaryTextColor) {
        model.setNegativeBgColor(secondaryTextColor);
        return this;
    }

    /**
     * sets the background color to the header background and positive andnegative
     */
    public DialogPlusBuilder setBackgroundColors(@ColorRes int positiveBackground, @ColorRes int negativeColorRes) {
        return setBackgroundColors(positiveBackground, negativeColorRes, 0);
    }

    public DialogPlusBuilder setBackgroundColors(@ColorRes int positiveBgColor, @ColorRes int negativeBgColor, @ColorRes int headerBgColor) {
        model.setPositiveBgColor(positiveBgColor);
        model.setHeaderBgColor(headerBgColor);
        model.setNegativeBgColor(negativeBgColor);
        return this;
    }

    /**
     * sets the text to the positiveBtn and negativeBtn
     */
    public DialogPlusBuilder setTexts(String positiveText) {
        return setTexts(positiveText, null);
    }

    public DialogPlusBuilder setTexts(String positiveText, String negativeText) {
        return setTexts(positiveText, negativeText, null);
    }

    public DialogPlusBuilder setTexts(String positiveText, String negativeText, String headerText) {
        if (positiveText != null)
            model.setPositiveText(positiveText);
        if (negativeText != null)
            model.setNegativeText(negativeText);
        if (headerText != null)
            model.setHeaderText(headerText);
        return this;
    }

    /**
     * sets the text color to the header background and positive and negative
     */
    public DialogPlusBuilder setTextColors(@ColorRes int positiveTextColor) {
        return setTextColors(positiveTextColor, -1, -1);
    }

    public DialogPlusBuilder setTextColors(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor) {
        return setTextColors(positiveTextColor, negativeTextColor, -1);
    }

    public DialogPlusBuilder setTextColors(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor, @ColorRes int headerTextColor) {
        if (positiveTextColor != -1)
            model.setPositiveTextColor(positiveTextColor);
        if (headerTextColor != -1)
            model.setHeaderTextColor(headerTextColor);
        if (negativeTextColor != -1)
            model.setNegativeTextColor(negativeTextColor);
        return this;
    }

    /**
     * sets the background drawable to the header background and positive andnegative
     */
    public DialogPlusBuilder setBackgrounds(@DrawableRes int positiveBackground) {
        return setBackgrounds(positiveBackground, -1, -1);
    }

    public DialogPlusBuilder setBackgrounds(@DrawableRes int positiveBackground, @DrawableRes int negativeBackground) {
        return setBackgrounds(positiveBackground, negativeBackground, -1);
    }

    public DialogPlusBuilder setBackgrounds(@DrawableRes int positiveBgDrawable, @DrawableRes int negativeBgDrawable, @DrawableRes int headerBgDrawable) {
        if (positiveBgDrawable != -1)
            model.setPositiveBgDrawable(positiveBgDrawable);
        if (positiveBgDrawable != -1)
            model.setHeaderBgDrawable(positiveBgDrawable);
        if (negativeBgDrawable != -1)
            model.setNegativeBgDrawable(negativeBgDrawable);
        return this;
    }

    public DialogPlusBuilder setHeaderBgColor(@ColorRes int headerBgColor) {
        model.setHeaderBgColor(headerBgColor);
        return this;
    }

    public DialogPlusBuilder setNegativeBgColor(@ColorRes int negativeBgColor) {
        model.setNegativeBgColor(negativeBgColor);
        return this;
    }

    public DialogPlusBuilder setNegativeTextColor(@ColorRes int negativeTextColor) {
        model.setNegativeTextColor(negativeTextColor);
        return this;
    }

    public DialogPlusBuilder setHeaderTextColor(@ColorRes int headerTextColor) {
        model.setHeaderTextColor(headerTextColor);
        return this;
    }

    public DialogPlusBuilder setDialogCodeTextColor(@ColorInt int dialogCodeTextColor) {
        model.setDialogCodeTextColor(dialogCodeTextColor);
        return this;
    }

    public DialogPlusBuilder setHeaderBgDrawable(@DrawableRes int headerBgDrawable) {
        model.setHeaderBgDrawable(headerBgDrawable);
        return this;
    }

    protected DialogPlusBuilder setDialog_type(@DialogPlus.TYPE int dialog_type) {
        model.setDialog_type(dialog_type);
        return this;
    }

    public DialogPlusBuilder setContent(String content) {
        model.setContent(content);
        return this;
    }

    protected DialogPlusBuilder setNormalTextColor() {
        model.setDialogCodeTextColor(model.getDialogCodeTextColor());
        return this;
    }

    protected DialogPlusBuilder setMessageDialog(String positiveText) {
        model.setPositiveText(positiveText);
        setDialog_type(DialogPlus.TYPE.MESSAGE_DIALOG);
        return this;
    }

    protected void shakeView(EditText editText) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .onEnd(animator -> editText.setText(null))
                .playOn(editText);
    }

    public DialogPlusBuilder setTitle(String title) {
        model.setTitle(title);
        return this;
    }

    /**
     * Builders
     */
    private DialogPlusBuilder setConfirmDialog(String correct_code, boolean withSend, boolean withResend, int counterSeconds, @ColorInt int codeTextColor, DialogPlus.CodeTypeListener codeTypeListener) {
        set(correct_code, withSend, withResend, counterSeconds, codeTextColor, codeTypeListener);
        return this;
    }

    private DialogPlusBuilder set(int dialog_type, String title, String content) {
        model.setDialog_type(dialog_type);
        model.setTitle(title);
        model.setContent(content);
        setBackgroundColors(R.color.dialogPositiveBgColor, R.color.dialogNegativeBgColor, R.color.dialogPositiveBgColor);
        setTextColors(R.color.dialogPositiveTextColor, R.color.dialogNegativeTextColor, R.color.dialogPositiveTextColor);
        return this;
    }

    private void set(String correct_code, boolean withSend, boolean withResend, int counterSeconds, @ColorInt int codeTextColor, DialogPlus.CodeTypeListener codeTypeListener) {
        model.setCorrectCode(correct_code);
        model.setCounterSeconds(counterSeconds);
        model.setTimeLeft(counterSeconds);
        model.setWithSend(withSend);
        model.setWithResend(withResend);
        model.setWithCounter(counterSeconds > 0);
        model.setDialogCodeTextColor(codeTextColor);
    }

    public DialogPlusBuilder setCodeTypeListener(DialogPlus.CodeTypeListener codeTypeListener) {
        model.setCodeTypeListener(codeTypeListener);
        return this;
    }

    public DialogPlusBuilder setDialogActionListener(DialogPlus.DialogActionListener dialogActionListener) {
        model.setDialogActionListener(dialogActionListener);
        return this;
    }

    public DialogPlusBuilder setInitialRate(float initialRate) {
        model.setRateValue(initialRate);
        return this;
    }

    public DialogPlusBuilder setRateListener(DialogPlus.DialogRateListener rateListener) {
        model.setRateListener(rateListener);
        return this;
    }

    public DialogPlusBuilder setDialogListListener(DialogPlus.DialogListListener dialogListListener) {
        model.setDialogListListener(dialogListListener);
        return this;
    }

    private DialogPlusBuilder setSeparateActionButtons(boolean separateActionButtons) {
        model.setSeparateActionButtons(separateActionButtons);
        return this;
    }
}
