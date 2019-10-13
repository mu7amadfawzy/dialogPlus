package com.dialog.plus.ui;

import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;

import com.dialog.plus.R;

import java.util.Calendar;
import java.util.List;

import static com.dialog.plus.ui.DialogPlusUiModel.MAX_MONTH;
import static com.dialog.plus.ui.DialogPlusUiModel.MAX_YEAR;
import static com.dialog.plus.ui.DialogPlusUiModel.MIN_DAY;
import static com.dialog.plus.ui.DialogPlusUiModel.MIN_MONTH;
import static com.dialog.plus.ui.DialogPlusUiModel.MIN_YEAR;

/**
 * Created by F4awzy on 23,September,2019
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
     * buildMonthPickerDialog: returns a DatePickerDialog instance used to pick month
     */

    public DatePickerDialog buildMonthPickerDialog(DatePickerDialog.PickerListener listener) {
        return buildMonthPickerDialog(true, MIN_MONTH, MAX_MONTH, listener);
    }

    public DatePickerDialog buildMonthPickerDialog(boolean showMonthName, int minMonth, int maxMonth, DatePickerDialog.PickerListener listener) {
        return new DatePickerDialog(model.setShowMonthName(showMonthName).setMinMonth(minMonth).setMaxMonth(maxMonth).setPickerListener(listener).setDialog_type(DatePickerDialog.TYPE.MONTH));
    }

    /**
     * buildDayPickerDialog: returns a DatePickerDialog instance used to pick a day for a particular day
     */
    public DatePickerDialog buildDayPickerDialog(int monthOfDays, DatePickerDialog.PickerListener listener) {
        return buildDayPickerDialog(monthOfDays, Calendar.getInstance().get(Calendar.YEAR), MIN_DAY, listener);
    }

    public DatePickerDialog buildDayPickerDialog(int monthOfDays, int year, int minDay, DatePickerDialog.PickerListener listener) {
        return new DatePickerDialog(model.setMinDay(minDay).setYearOfMonth(year).setMonthOfDay(monthOfDays - 1).setPickerListener(listener).setDialog_type(DatePickerDialog.TYPE.DAY));
    }

    /**
     * buildMonthPickerDialog: returns a DatePickerDialog instance used to pick year
     */
    public DatePickerDialog buildYearPickerDialog(DatePickerDialog.PickerListener listener) {
        return buildYearPickerDialog(Calendar.getInstance().get(Calendar.YEAR), MIN_YEAR, listener);
    }

    public DatePickerDialog buildYearPickerDialog(int maxYear, DatePickerDialog.PickerListener listener) {
        return buildYearPickerDialog(maxYear, MIN_YEAR, listener);
    }

    public DatePickerDialog buildYearPickerDialog(int maxYear, int minYear, DatePickerDialog.PickerListener listener) {
        model.setMaxYear(maxYear).setMinYear(minYear).setPickerListener(listener);
        return new DatePickerDialog(model.setDialog_type(DatePickerDialog.TYPE.YEAR));
    }

    /**
     * buildMonthYearPickerDialog: returns a DatePickerDialog instance used to pick year and month
     */
    public DatePickerDialog buildMonthYearPickerDialog(DatePickerDialog.YearMonthPickerListener listener) {
        return buildMonthYearPickerDialog(Calendar.getInstance().get(Calendar.YEAR), true, listener);
    }

    public DatePickerDialog buildMonthYearPickerDialog(int maxYear, boolean showMonthName, DatePickerDialog.YearMonthPickerListener listener) {
        return buildMonthYearPickerDialog(maxYear, MIN_YEAR, showMonthName, listener);
    }

    public DatePickerDialog buildMonthYearPickerDialog(int maxYear, int minYear, boolean showMonthName, DatePickerDialog.YearMonthPickerListener listener) {
        return buildMonthYearPickerDialog(maxYear, minYear, showMonthName, MIN_MONTH, MAX_MONTH, listener);
    }

    public DatePickerDialog buildMonthYearPickerDialog(int maxYear, int minYear, boolean showMonthName, int mixMonth, int maxMonth, DatePickerDialog.YearMonthPickerListener listener) {
        return new DatePickerDialog(model.setMaxYear(maxYear).setMinYear(minYear).setMinMonth(mixMonth).setMaxMonth(maxMonth).setPickerListener(listener).setShowMonthName(showMonthName).setDialog_type(DatePickerDialog.TYPE.YEAR_MONTH));
    }

    public DatePickerDialog buildMonthYearPickerDialog(Calendar minDate, DatePickerDialog.YearMonthPickerListener listener) {
        return new DatePickerDialog(model.setMaxYear(MAX_YEAR).setMinDate(minDate).setPickerListener(listener)
                .setShowMonthName(true).setDialog_type(DatePickerDialog.TYPE.YEAR_MONTH));
    }

    public DatePickerDialog buildMonthYearPickerDialog(Calendar maxDate, boolean showMonthName, DatePickerDialog.YearMonthPickerListener listener) {
        return new DatePickerDialog(model.setMaxYear(maxDate.get(Calendar.YEAR)).setMaxCalendar(maxDate)
                .setShowMonthName(showMonthName).setPickerListener(listener).setShowMonthName(true).setDialog_type(DatePickerDialog.TYPE.YEAR_MONTH));
    }

    /**
     * buildMonthDayPickerDialog: returns a DatePickerDialog instance used to pick a month and day
     */
    public DatePickerDialog buildMonthDayPickerDialog(DatePickerDialog.MonthDayPickerListener listener) {
        return buildMonthDayPickerDialog(true, listener);
    }

    public DatePickerDialog buildMonthDayPickerDialog(boolean showMonthName, DatePickerDialog.MonthDayPickerListener listener) {
        return buildMonthDayPickerDialog(showMonthName, Calendar.getInstance().get(Calendar.YEAR), listener);
    }

    public DatePickerDialog buildMonthDayPickerDialog(boolean showMonthName, int year, DatePickerDialog.MonthDayPickerListener listener) {
        return buildMonthDayPickerDialog(showMonthName, MIN_MONTH, MAX_MONTH, year, listener);
    }

    public DatePickerDialog buildMonthDayPickerDialog(boolean showMonthName, int minMonth, int maxMonth, int year, DatePickerDialog.MonthDayPickerListener listener) {
        return buildMonthDayPickerDialog(showMonthName, minMonth, maxMonth, year, MIN_DAY, listener);
    }

    public DatePickerDialog buildMonthDayPickerDialog(boolean showMonthName, int minMonth, int maxMonth, int year, int minDay, DatePickerDialog.MonthDayPickerListener listener) {
        return new DatePickerDialog(model.setShowMonthName(showMonthName).setMinMonth(minMonth).setMaxMonth(maxMonth)
                .setMinDay(minDay).setYearOfMonth(year).setMonthDayPickerListener(listener)
                .setDialog_type(DatePickerDialog.TYPE.MONTH_DAY));
    }

    public DatePickerDialog buildMonthDayPickerDialog(Calendar minDate, DatePickerDialog.MonthDayPickerListener listener) {
        return buildMonthDayPickerDialog(Calendar.getInstance().get(Calendar.YEAR), minDate, listener);
    }

    public DatePickerDialog buildMonthDayPickerDialog(int year, Calendar minDate, DatePickerDialog.MonthDayPickerListener listener) {
        return new DatePickerDialog(model.setYearOfMonth(year).setMinDate(minDate).setShowMonthName(true).setMonthDayPickerListener(listener).setDialog_type(DatePickerDialog.TYPE.MONTH_DAY));
    }

    public DatePickerDialog buildMonthDayPickerDialog(Calendar maxDate, boolean showMonthName, DatePickerDialog.MonthDayPickerListener listener) {
        return new DatePickerDialog(model.setMaxCalendar(maxDate).setYearOfMonth(maxDate.get(Calendar.YEAR))
                .setShowMonthName(showMonthName).setMonthDayPickerListener(listener).setDialog_type(DatePickerDialog.TYPE.MONTH_DAY));
    }

    /**
     * buildDatePickerDialog: returns a DatePickerDialog instance used to pick year , month and day
     */
    public DatePickerDialog buildDatePickerDialog(DatePickerDialog.DatePickerListener listener) {
        return buildDatePickerDialog(Calendar.getInstance().get(Calendar.YEAR), listener);
    }

    public DatePickerDialog buildDatePickerDialog(int maxYear, DatePickerDialog.DatePickerListener listener) {
        return buildDatePickerDialog(maxYear, MIN_YEAR, MIN_MONTH, MAX_MONTH, MIN_DAY, true, listener);
    }

    public DatePickerDialog buildDatePickerDialog(int maxYear, int minYear, int minMonth, int maxMonth, int minDay, boolean showMonthName, DatePickerDialog.DatePickerListener listener) {
        return new DatePickerDialog(model.setMaxYear(maxYear).setMinYear(minYear).setMinMonth(minMonth).setMaxMonth(maxMonth).setMinDay(minDay)
                .setShowMonthName(showMonthName).setDatePickerListener(listener).setDialog_type(DatePickerDialog.TYPE.DATE));
    }

    public DatePickerDialog buildDatePickerDialog(boolean showMonthName, Calendar minCalendar, DatePickerDialog.DatePickerListener listener) {
        return new DatePickerDialog(model.setMaxYear(MAX_YEAR).setMinYear(minCalendar.get(Calendar.YEAR))
                .setMinDate(minCalendar).setShowMonthName(showMonthName).setDatePickerListener(listener)
                .setDialog_type(DatePickerDialog.TYPE.DATE));
    }

    public DatePickerDialog buildDatePickerDialog(Calendar maxCalendar, DatePickerDialog.DatePickerListener listener) {
        model.setMaxYear(maxCalendar.get(Calendar.YEAR))
                .setMaxCalendar(maxCalendar).setShowMonthName(true).setDatePickerListener(listener);
        return new DatePickerDialog(model.setDialog_type(DatePickerDialog.TYPE.DATE));
    }

    /**
     * buildMultiOptionsDialog: returns a MultiOptionsDialog instance used to pick option among many
     */
    public MultiOptionsDialog buildMultiOptionsDialog(List<String> optionsTitle, MultiOptionsDialog.ActionListener actionListener) {
        return new MultiOptionsDialog(model.setDialogListItems(optionsTitle).setMultiOptionsDialogListener(actionListener));
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

    /**
     * buildCustomLayoutDialog: returns a CustomLayoutDialog instance which can be used to inflate any custom layout and shows it
     */
    public CustomLayoutDialog buildCustomLayoutDialog(View view) {
        return new CustomLayoutDialog(model, view);
    }

    public CustomLayoutDialog buildCustomLayoutDialog(@LayoutRes int customLayoutRes) {
        return buildCustomLayoutDialog(customLayoutRes, 0, null);
    }

    public CustomLayoutDialog buildCustomLayoutDialog(@LayoutRes int customLayoutRes, int variableId, Object variableValue) {
        return new CustomLayoutDialog(model, customLayoutRes, variableId, variableValue);
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

    public DialogPlusBuilder hideCloseIcon() {
        model.setHideCloseIcon(true);
        return this;
    }

    public DialogPlusBuilder blurBackground() {
        model.setBlurBackground(true);
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
