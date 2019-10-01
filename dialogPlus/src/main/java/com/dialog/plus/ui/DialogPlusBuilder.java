package com.dialog.plus.ui;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.dialog.plus.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by fawzy on 23,September,2019
 * ma7madfawzy@gmail.com
 **/
public class DialogPlusBuilder {
    private DialogPlusUiModel model = new DialogPlusUiModel();

    /***
     * Constructors
     *  a null title  will make the header view to be invisible.
     *  a null content  will make the content view to be invisible.
     */
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

    /**
     * build: with only @param actionListener returns a dialogPlus.
     */
    public DialogPlus build(DialogPlus.DialogActionListener actionListener) {
        return build(model.getDialog_type(), actionListener);
    }

    /**
     * @param type sets the dialog interface type
     */
    public DialogPlus build(@DialogPlus.TYPE int type, DialogPlus.DialogActionListener actionListener) {
        return new DialogPlus(model.setDialog_type(type).setDialogActionListener(actionListener));
    }

    /**
     * build:  returns a dialogPlus instance which used as MESSAGE DIALOG
     */
    public DialogPlus buildMessageDialog(DialogPlus.DialogActionListener actionListener) {
        return buildMessageDialog(-1, actionListener);
    }

    /**
     * @param gifImageRes shows an imageView(below the content) with the sent res just
     */
    public DialogPlus buildMessageDialog(@DrawableRes int gifImageRes, DialogPlus.DialogActionListener actionListener) {
        return new DialogPlus(model.setDialog_type(DialogPlus.TYPE.MESSAGE_DIALOG)
                .setDialogActionListener(actionListener).setDialogImageRes(gifImageRes));
    }

    /**
     * build: returns a dialogPlus instance which used as CONFIRMATION DIALOG
     */
    public DialogPlus buildConfirmationDialog(DialogPlus.DialogActionListener actionListener) {
        return buildConfirmationDialog(false, actionListener);
    }

    public DialogPlus buildConfirmationDialog(boolean separateActionButtons, DialogPlus.DialogActionListener actionListener) {
        return buildConfirmationDialog(separateActionButtons, -1, actionListener);
    }

    public DialogPlus buildConfirmationDialog(boolean separateActionButtons, @DrawableRes int gifImageRes, DialogPlus.DialogActionListener actionListener) {
        return setSeparateActionButtons(separateActionButtons).setImageRes(gifImageRes)
                .build(DialogPlus.TYPE.CONFIRMATION_DIALOG, actionListener);
    }

    /**
     * build: returns a dialogPlus instance which used as SUCCESS DIALOG
     */
    public DialogPlus buildSuccessDialog(DialogPlus.DialogActionListener actionListener) {
        return build(DialogPlus.TYPE.SUCCESS_DIALOG, actionListener);
    }

    /**
     * build: returns a dialogPlus instance which used as ERROR DIALOG
     */
    public DialogPlus buildErrorDialog(DialogPlus.DialogActionListener actionListener) {
        return build(DialogPlus.TYPE.ERROR_DIALOG, actionListener);
    }

    /**
     * buildCodeDialog: returns a dialogPlus instance which  used as  CODE DIALOG
     *
     * @param counterSeconds sending it as 0 will disable the counter
     */
    public DialogPlus buildCodeDialog(String correct_code, int counterSeconds, DialogPlus.DialogActionListener actionListener) {
        return buildCodeDialog(correct_code, counterSeconds, false, true, actionListener);
    }

    public DialogPlus buildCodeDialog(String correct_code, int counterSeconds, boolean withSend, boolean withResend, DialogPlus.DialogActionListener actionListener) {
        return setDialog_type(DialogPlus.TYPE.CODE_DIALOG)
                .set(correct_code, withSend, withResend, counterSeconds)
                .build(actionListener);
    }

    /**
     * buildMonthPickerDialog: returns a MonthYearPickerDialog instance used to pick month
     */

    public MonthYearPickerDialog buildMonthPickerDialog(MonthYearPickerDialog.PickerListener listener) {
        return new MonthYearPickerDialog(model.setPickerListener(listener), MonthYearPickerDialog.TYPE.MONTH);
    }

    /**
     * buildListDialog: returns a DialogPlus instance used to pick item among list
     */
    public DialogPlus buildListDialog(List<String> listItems, DialogPlus.DialogListListener dialogListListener) {
        return buildListDialog(listItems, false, dialogListListener);
    }

    public DialogPlus buildListDialog(List<String> listItems, boolean enableSearch, DialogPlus.DialogListListener dialogListListener) {
        model.setDialog_type(DialogPlus.TYPE.LIST_DIALOG).setDialogListItems(listItems).enableSearch(enableSearch).setDialogListListener(dialogListListener);
        return new DialogPlus(model);
    }

    /**
     * buildCountriesListDialog: returns a DialogPlus instance used to pick country
     */
    public DialogPlus buildCountriesListDialog(DialogPlus.CountriesDialogListener countriesDialogListener) {
        return buildCountriesListDialog(true, countriesDialogListener);
    }

    public DialogPlus buildCountriesListDialog(boolean showCountryCode, DialogPlus.CountriesDialogListener countriesDialogListener) {
        model.setDialog_type(DialogPlus.TYPE.COUNTRIES_LIST_DIALOG).enableSearch(true).setShowCountryCode(showCountryCode).setCountriesDialogListener(countriesDialogListener);
        return new DialogPlus(model);
    }

    /**
     * buildMonthPickerDialog: returns a MonthYearPickerDialog instance used to pick year
     */
    public MonthYearPickerDialog buildYearPickerDialog(MonthYearPickerDialog.PickerListener listener) {
        model.setMaxYear(Calendar.getInstance().get(Calendar.YEAR)).setPickerListener(listener);
        return new MonthYearPickerDialog(model, MonthYearPickerDialog.TYPE.YEAR);
    }

    public MonthYearPickerDialog buildYearPickerDialog(int maxYear, MonthYearPickerDialog.PickerListener listener) {
        model.setMaxYear(maxYear).setPickerListener(listener);
        return new MonthYearPickerDialog(model, MonthYearPickerDialog.TYPE.YEAR);
    }

    /**
     * buildMultiOptionsDialog: returns a MultiOptionsDialog instance used to pick option among many
     */
    public MultiOptionsDialog buildMultiOptionsDialog(List<String> optionsTitle, MultiOptionsDialog.ActionListener actionListener) {
        model.setDialogListItems(optionsTitle).setMultiOptionsDialogListener(actionListener);
        return new MultiOptionsDialog(model);
    }

    /**
     * buildRatingDialog: returns a DialogPlus instance used to give a rate
     */
    public DialogPlus buildRatingDialog(DialogPlus.DialogRateListener rateListener) {
        return buildRatingDialog(2, false, rateListener);
    }

    public DialogPlus buildRatingDialog(float initialRate, boolean separateActionButtons, DialogPlus.DialogRateListener rateListener) {
        setInitialRate(initialRate).setSeparateActionButtons(separateActionButtons).setRateListener(rateListener).setDialog_type(DialogPlus.TYPE.RATING_DIALOG);
        return new DialogPlus(model);
    }

    /********************************************************************************************************/

    /**
     * Sets a Success dialog_plus interface
     */

    public DialogPlusBuilder setSuccessDialog(String positiveText) {
        setDialog_type(DialogPlus.TYPE.SUCCESS_DIALOG).setTexts(positiveText);
        return this;
    }

    public DialogPlusBuilder setSuccessDialog() {
        setDialog_type(DialogPlus.TYPE.SUCCESS_DIALOG);
        return this;
    }

    /**
     * Sets a Error dialog_plus interface
     */

    public DialogPlusBuilder setErrorDialog(String positiveText) {
        setDialog_type(DialogPlus.TYPE.ERROR_DIALOG).setTexts(positiveText);
        return this;
    }

    public DialogPlusBuilder setErrorDialog() {
        setDialog_type(DialogPlus.TYPE.ERROR_DIALOG);
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
        return setBackgroundColors(positiveBackground, negativeColorRes, -1);
    }

    public DialogPlusBuilder setBackgroundColors(@ColorRes int positiveBgColor, @ColorRes int negativeBgColor, @ColorRes int headerBgColor) {
        model.setPositiveBgColor(positiveBgColor).setHeaderBgColor(headerBgColor).setNegativeBgColor(negativeBgColor);
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
        model.setPositiveText(positiveText).setNegativeText(negativeText).setHeaderText(headerText);
        return this;
    }

    public DialogPlusBuilder setNegativeText(String negativeText) {
        model.setNegativeText(negativeText);
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
        model.setPositiveTextColor(positiveTextColor).setHeaderTextColor(headerTextColor).setNegativeTextColor(negativeTextColor);
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
        model.setPositiveBgDrawable(positiveBgDrawable).setHeaderBgDrawable(headerBgDrawable).setNegativeBgDrawable(negativeBgDrawable);
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

    public DialogPlusBuilder setDialog_type(@DialogPlus.TYPE int dialog_type) {
        model.setDialog_type(dialog_type);
        return this;
    }

    public DialogPlusBuilder setContent(String content) {
        model.setContent(content);
        return this;
    }

    public DialogPlusBuilder setMessageDialog() {
        return setMessageDialog(null);
    }

    public DialogPlusBuilder setMessageDialog(String positiveText) {
        model.setPositiveText(positiveText);
        setDialog_type(DialogPlus.TYPE.MESSAGE_DIALOG);
        return this;
    }

    public DialogPlusBuilder setTitle(String title) {
        model.setTitle(title);
        return this;
    }


    public DialogPlusBuilder setCodeDialog(String correct_code, boolean withSend, boolean withResend, int counterSeconds) {
        setDialog_type(DialogPlus.TYPE.CODE_DIALOG).set(correct_code, withSend, withResend, counterSeconds);
        return this;
    }

    private DialogPlusBuilder setInitialRate(float initialRate) {
        model.setRateValue(initialRate);
        return this;
    }

    private DialogPlusBuilder setRateListener(DialogPlus.DialogRateListener rateListener) {
        model.setRateListener(rateListener);
        return this;
    }

    private DialogPlusBuilder setDialogActionListener(DialogPlus.DialogActionListener dialogActionListener) {
        model.setDialogActionListener(dialogActionListener);
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

    private DialogPlusBuilder set(String correct_code, boolean withSend, boolean withResend, int counterSeconds) {
        model.setCorrectCode(correct_code);
        model.setCounterSeconds(counterSeconds);
        model.setTimeLeft(counterSeconds);
        model.setWithSend(withSend);
        model.setWithResend(withResend);
        model.setWithCounter(counterSeconds > 0);
        return this;
    }

    public DialogPlusBuilder setSeparateActionButtons(boolean separateActionButtons) {
        model.setSeparateActionButtons(separateActionButtons);
        return this;
    }

    private DialogPlusBuilder setImageRes(@DrawableRes int gifImageRes) {
        model.setDialogImageRes(gifImageRes);
        return this;
    }
}
