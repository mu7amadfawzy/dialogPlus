package com.dialog.plus.ui;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.dialog.plus.BR;
import com.dialog.plus.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.dialog.plus.ui.DatePickerDialog.TYPE.DATE;
import static com.dialog.plus.ui.DatePickerDialog.TYPE.DAY;
import static com.dialog.plus.ui.DatePickerDialog.TYPE.MONTH;
import static com.dialog.plus.ui.DatePickerDialog.TYPE.MONTH_DAY;
import static com.dialog.plus.ui.DatePickerDialog.TYPE.YEAR;
import static com.dialog.plus.ui.DatePickerDialog.TYPE.YEAR_MONTH;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 * Modified  by Fawzy & ALi
 */

public class DialogPlusUiModel extends BaseObservable {
    public static int MAX_MONTH = 12, MIN_MONTH = 1, MIN_DAY = 1, MAX_YEAR = 2040, MIN_YEAR = 1970;

    private boolean withSend, withResend, withCounter, separateActionButtons, enableSearch, showCountryCode, hideCloseIcon, blurBackground, showMonthName;
    private String title, correctCode, codeEntry, content, typed_code, positiveText, negativeText, headerText;
    private int timeLeft, starsNumber = 5, counterSeconds, maxYear, minYear = MIN_YEAR, minDay = MIN_DAY, monthOfDay;
    private int minMonth = MIN_MONTH, maxMonth = MAX_MONTH, yearOfMonth;
    private float rateValue;
    @ColorRes
    private int positiveBgColor = -1, negativeBgColor = -1, headerBgColor = -1;
    @DrawableRes
    private int positiveBgDrawable = -1, negativeBgDrawable = -1, headerBgDrawable = -1, dialogImageRes = -1;
    @ColorRes
    private int positiveTextColor = -1, negativeTextColor = -1, headerTextColor = -1;
    @ColorInt
    private int dialogCodeTextColor = Color.BLACK;
    @DialogPlus.TYPE
    private int dialog_type;
    private DialogPlus.CountriesDialogListener countriesDialogListener;
    private ArrayList<CountryDataModel> countryDataModels;
    /**
     * Listeners
     **/
    private DialogPlus.CodeTypeListener codeTypeListener;
    private DialogPlus.DialogActionListener dialogActionListener;
    private DialogPlus.DialogListListener dialogListListener;
    private DialogPlus.DialogRateListener rateListener;
    private DatePickerDialog.PickerListener pickerListener;
    private MultiOptionsDialog.ActionListener multiOptionsDialogListener;

    private ArrayList<String> listDialogItems = new ArrayList<>();
    private DatePickerDialog.YearMonthPickerListener yearMonthPickerListener;
    private DatePickerDialog.DatePickerListener datePickerListener;
    private DatePickerDialog.MonthDayPickerListener monthDayPickerListener;
    private Calendar minCalendar, maxCalendar;

    DialogPlusUiModel() {
    }

    public DialogPlusUiModel(String title, int headerBgColor, int headerTextColor) {
        this.title = title;
        this.headerBgColor = headerBgColor;
        this.headerTextColor = headerTextColor;
    }

    public void set(String title, int headerBgColor, int headerTextColor) {
        this.title = title;
        this.headerBgColor = headerBgColor;
        this.headerTextColor = headerTextColor;
    }

    @Bindable
    public boolean isWithResend() {
        return withResend;
    }

    public void setWithResend(boolean withResend) {
        this.withResend = withResend;
    }

    @Bindable
    public @ColorRes
    int getHeaderBgColor() {
        return headerBgColor;
    }

    public DialogPlusUiModel setHeaderBgColor(@ColorRes int headerBgColor) {
        if (headerBgColor == -1)
            return null;
        this.headerBgColor = headerBgColor;
        notifyPropertyChanged(BR.headerBgColor);
        return this;
    }

    @Bindable
    public @ColorRes
    int getNegativeBgColor() {
        return negativeBgColor;
    }


    public void setNegativeBgColor(@ColorRes int negativeColorRes) {
        if (negativeBgColor == -1)
            return;
        this.negativeBgColor = negativeColorRes;
        notifyPropertyChanged(BR.negativeBgColor);
    }

    @Bindable
    public @ColorRes
    int getPositiveBgColor() {
        return positiveBgColor;
    }

    @Bindable
    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
        notifyPropertyChanged(BR.timeLeft);
    }

    @Bindable
    public String getTyped_code() {
        return typed_code;
    }

    public void setTyped_code(String typed_code) {
        this.typed_code = typed_code;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public DialogPlusUiModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Bindable
    public int getPositiveBgDrawable() {
        return positiveBgDrawable;
    }

    public DialogPlusUiModel setPositiveBgDrawable(int positiveBgDrawable) {
        if (positiveBgDrawable == -1)
            return this;
        this.positiveBgDrawable = positiveBgDrawable;
        notifyPropertyChanged(BR.positiveBgDrawable);
        return this;
    }

    @Bindable
    public int getNegativeBgDrawable() {
        return negativeBgDrawable;
    }

    public DialogPlusUiModel setNegativeBgDrawable(int negativeBgDrawable) {
        if (negativeBgDrawable == -1)
            return this;
        this.negativeBgDrawable = negativeBgDrawable;
        notifyPropertyChanged(BR.negativeBgDrawable);
        return this;
    }

    @Bindable
    public int getHeaderBgDrawable() {
        return headerBgDrawable;
    }

    public DialogPlusUiModel setHeaderBgDrawable(int headerBgDrawable) {
        if (headerBgDrawable == -1)
            return this;
        this.headerBgDrawable = headerBgDrawable;
        notifyPropertyChanged(BR.headerBgDrawable);
        return this;
    }

    @Bindable
    public int getPositiveTextColor() {
        return positiveTextColor;
    }

    public DialogPlusUiModel setPositiveTextColor(int positiveTextColor) {
        if (positiveTextColor == -1)
            return this;
        this.positiveTextColor = positiveTextColor;
        notifyPropertyChanged(BR.positiveTextColor);
        return this;
    }

    @Bindable
    public int getNegativeTextColor() {
        return negativeTextColor;
    }

    public DialogPlusUiModel setNegativeTextColor(int negativeTextColor) {
        if (negativeTextColor == -1)
            return this;
        this.negativeTextColor = negativeTextColor;
        notifyPropertyChanged(BR.negativeTextColor);
        return this;
    }

    @Bindable
    public int getHeaderTextColor() {
        return headerTextColor;
    }

    public DialogPlusUiModel setHeaderTextColor(int headerTextColor) {
        if (headerTextColor == -1)
            return this;
        this.headerTextColor = headerTextColor;
        notifyPropertyChanged(BR.headerTextColor);
        return this;
    }

    @Bindable
    public String getCodeEntry() {
        return codeEntry;
    }

    public DialogPlusUiModel setCodeEntry(String codeEntry) {
        this.codeEntry = codeEntry;
        notifyPropertyChanged(BR.codeEntry);
        return this;
    }

    @Bindable
    public String getCorrectCode() {
        return correctCode;
    }

    public void setCorrectCode(String correctCode) {
        this.correctCode = correctCode;
    }

    public DialogPlusUiModel setPositiveBgColor(@ColorRes int positiveBgColor) {
        if (positiveBgColor == -1)
            return null;
        this.positiveBgColor = positiveBgColor;
        notifyPropertyChanged(BR.positiveTextColor);
        return this;
    }

    @Bindable
    public boolean isWithSend() {
        return withSend;
    }

    public void setWithSend(boolean withSend) {
        this.withSend = withSend;
    }

    @Bindable
    public boolean isWithCounter() {
        return withCounter;
    }

    public void setWithCounter(boolean withCounter) {
        this.withCounter = withCounter;
    }

    @Bindable
    public boolean isTypeMessage() {
        return getDialog_type() == DialogPlus.TYPE.MESSAGE_DIALOG;
    }

    public boolean isCorrectCode() {
        return getCodeEntry().equals(getCorrectCode());
    }

    public boolean isSeparateActionButtons() {
        return separateActionButtons;
    }

    public void setSeparateActionButtons(boolean separateActionButtons) {
        this.separateActionButtons = separateActionButtons;
    }

    @Bindable
    public String getPositiveText() {
        return positiveText;
    }

    public DialogPlusUiModel setPositiveText(String positiveText) {
        if (positiveText == null)
            return this;
        this.positiveText = positiveText;
        notifyPropertyChanged(BR.positiveText);
        return this;
    }

    @Bindable
    public String getNegativeText() {
        return negativeText;
    }

    public DialogPlusUiModel setNegativeText(String negativeText) {
        if (negativeText == null)
            return this;
        this.negativeText = negativeText;
        notifyPropertyChanged(BR.negativeText);
        return this;
    }

    @Bindable
    public String getHeaderText() {
        return headerText;
    }

    public DialogPlusUiModel setHeaderText(String headerText) {
        if (headerText == null)
            return this;
        this.headerText = headerText;
        notifyPropertyChanged(BR.headerText);
        return this;
    }

    @Bindable
    public int getDialogCodeTextColor() {
        return dialogCodeTextColor;
    }

    public DialogPlusUiModel setDialogCodeTextColor(@ColorInt int dialogCodeTextColor) {
        if (dialogCodeTextColor == -1)
            return this;
        this.dialogCodeTextColor = dialogCodeTextColor;
        notifyPropertyChanged(BR.dialogCodeTextColor);
        return this;
    }

    public DialogPlusUiModel notifyCodeTextColor() {
        notifyPropertyChanged(BR.dialogCodeTextColor);
        return this;
    }

    public int getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(int starsNumber) {
        this.starsNumber = starsNumber;
    }

    @Bindable
    public float getRateValue() {
        return rateValue;
    }

    public void setRateValue(float rateValue) {
        this.rateValue = rateValue;
        notifyPropertyChanged(BR.rateValue);
    }

    @Bindable
    public int getDialogWhite() {
        return R.color.dialog_white;
    }

    @Bindable
    public int getDialogTransparent() {
        return R.color.dialogTransparent;
    }

    @Bindable
    public int getNoDrawable() {
        return -1;
    }

    @Bindable
    public int getDialog_type() {
        return dialog_type;
    }

    public DialogPlusUiModel setDialog_type(int dialog_type) {
        this.dialog_type = dialog_type;
        return this;
    }

    public DialogPlus.CodeTypeListener getCodeTypeListener() {
        return codeTypeListener;
    }

    public void setCodeTypeListener(DialogPlus.CodeTypeListener codeTypeListener) {
        this.codeTypeListener = codeTypeListener;
    }

    public DialogPlus.DialogActionListener getDialogActionListener() {
        return dialogActionListener;
    }

    public DialogPlusUiModel setDialogActionListener(DialogPlus.DialogActionListener dialogActionListener) {
        this.dialogActionListener = dialogActionListener;
        return this;
    }

    public DialogPlus.DialogListListener getDialogListListener() {
        return dialogListListener;
    }

    public void setDialogListListener(DialogPlus.DialogListListener dialogListListener) {
        this.dialogListListener = dialogListListener;
    }

    public DialogPlus.DialogRateListener getRateListener() {
        return rateListener;
    }

    public void setRateListener(DialogPlus.DialogRateListener rateListener) {
        this.rateListener = rateListener;
    }

    public int getCounterSeconds() {
        return counterSeconds;
    }

    public void setCounterSeconds(int counterSeconds) {
        this.counterSeconds = counterSeconds;
    }

    public ArrayList<String> getListDialogItems() {
        return listDialogItems;
    }

    public DialogPlusUiModel setDialogListItems(List<String> listDialogItems) {
        if (this.listDialogItems.size() > 0) this.listDialogItems.clear();
        this.listDialogItems = new ArrayList<>(listDialogItems);
        return this;
    }

    public DatePickerDialog.PickerListener getPickerListener() {
        return pickerListener;
    }

    public DialogPlusUiModel setPickerListener(DatePickerDialog.PickerListener pickerListener) {
        this.pickerListener = pickerListener;
        return this;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public DialogPlusUiModel setMaxYear(int maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public int getMinYear() {
        return minYear;
    }

    public DialogPlusUiModel setMinYear(int minYear) {
        this.minYear = minYear;
        return this;
    }

    public int getMinDay() {
        return minDay;
    }

    public DialogPlusUiModel setMinDay(int minDay) {
        this.minDay = minDay;
        return this;
    }

    public int getMonthOfDay() {
        return monthOfDay;
    }

    public DialogPlusUiModel setMonthOfDay(int monthOfDay) {
        this.monthOfDay = monthOfDay;
        return this;
    }

    public MultiOptionsDialog.ActionListener getMultiOptionsDialogListener() {
        return multiOptionsDialogListener;
    }

    public DialogPlusUiModel setMultiOptionsDialogListener(MultiOptionsDialog.ActionListener multiOptionsDialogListener) {
        this.multiOptionsDialogListener = multiOptionsDialogListener;
        return this;
    }

    public int getDialogImageRes() {
        return dialogImageRes;
    }

    public DialogPlusUiModel setDialogImageRes(int dialogImageRes) {
        this.dialogImageRes = dialogImageRes;
        return this;
    }

    public boolean isEnableSearch() {
        return enableSearch;
    }

    public DialogPlusUiModel enableSearch(boolean enableSearch) {
        this.enableSearch = enableSearch;
        return this;
    }

    public DialogPlus.CountriesDialogListener getCountriesDialogListener() {
        return countriesDialogListener;
    }

    public DialogPlusUiModel setCountriesDialogListener(DialogPlus.CountriesDialogListener countriesDialogListener) {
        this.countriesDialogListener = countriesDialogListener;
        return this;
    }

    public ArrayList<CountryDataModel> getCountryDataModels() {
        return countryDataModels;
    }

    public void setCountryDataModels(ArrayList<CountryDataModel> countryDataModels) {
        this.countryDataModels = countryDataModels;
    }

    public boolean isShowCountryCode() {
        return showCountryCode;
    }

    public DialogPlusUiModel setShowCountryCode(boolean showCountryCode) {
        this.showCountryCode = showCountryCode;
        return this;
    }

    public DialogPlusUiModel showCountryCode() {
        this.showCountryCode = true;
        return this;
    }

    public boolean isHideCloseIcon() {
        return hideCloseIcon;
    }

    public DialogPlusUiModel setHideCloseIcon(boolean hideIcon) {
        this.hideCloseIcon = hideIcon;
        return this;
    }

    public boolean isBlurBackground() {
        return blurBackground;
    }

    public DialogPlusUiModel setBlurBackground(boolean blurBackground) {
        this.blurBackground = blurBackground;
        return this;
    }

    public DialogPlusUiModel setPickerListener(DatePickerDialog.YearMonthPickerListener yearMonthPickerListener) {
        this.yearMonthPickerListener = yearMonthPickerListener;
        return this;
    }

    public DatePickerDialog.YearMonthPickerListener getYearMonthPickerListener() {
        return yearMonthPickerListener;
    }

    public DatePickerDialog.DatePickerListener getDatePickerListener() {
        return datePickerListener;
    }

    public DialogPlusUiModel setDatePickerListener(DatePickerDialog.DatePickerListener datePickerListener) {
        this.datePickerListener = datePickerListener;
        return this;
    }

    public boolean isMonthPicker() {
        return getDialog_type() == MONTH;
    }

    public boolean isYearPicker() {
        return getDialog_type() == YEAR;
    }

    public boolean isYearMonthPicker() {
        return getDialog_type() == YEAR_MONTH;
    }

    public boolean isMonthDayPicker() {
        return getDialog_type() == MONTH_DAY;
    }

    public boolean isDatePicker() {
        return getDialog_type() == DATE;
    }

    public boolean isDayPicker() {
        return getDialog_type() == DAY;
    }

    public DatePickerDialog.MonthDayPickerListener getMonthDayPickerListener() {
        return monthDayPickerListener;
    }

    public DialogPlusUiModel setMonthDayPickerListener(DatePickerDialog.MonthDayPickerListener listener) {
        this.monthDayPickerListener = listener;
        return this;
    }

    public int getMinMonth() {
        return minMonth;
    }

    public DialogPlusUiModel setMinMonth(int minMonth) {
        this.minMonth = minMonth;
        return this;
    }

    public int getMaxMonth() {
        return maxMonth;
    }

    public DialogPlusUiModel setMaxMonth(int maxMonth) {
        this.maxMonth = maxMonth;
        return this;
    }

    public int getYearOfMonth() {
        return yearOfMonth;
    }

    public DialogPlusUiModel setYearOfMonth(int yearOfMonth) {
        this.yearOfMonth = yearOfMonth;
        return this;
    }

    public boolean showYearPicker() {
        return isYearPicker() || isYearMonthPicker() || isDatePicker();
    }

    public boolean showMonthPicker() {
        return isMonthPicker() || isYearMonthPicker() || isMonthDayPicker() || isDatePicker();
    }

    public boolean showDayPicker() {
        return isDayPicker() || isDatePicker() || isMonthDayPicker();
    }

    public boolean isShowMonthName() {
        return showMonthName;
    }

    public DialogPlusUiModel setShowMonthName(boolean showMonthName) {
        this.showMonthName = showMonthName;
        return this;
    }

    public DialogPlusUiModel setMinDate(Calendar minCalendar) {
        this.minCalendar = minCalendar;
        return this;
    }

    public Calendar getMinCalendar() {
        return minCalendar;
    }

    public Calendar getMaxCalendar() {
        return maxCalendar;
    }

    public DialogPlusUiModel setMaxCalendar(Calendar maxCalendar) {
        this.maxCalendar = maxCalendar;
        return this;
    }
}
