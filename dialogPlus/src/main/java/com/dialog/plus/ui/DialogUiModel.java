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
import java.util.List;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class DialogUiModel extends BaseObservable {
    private boolean withSend, withResend, withCounter, separateActionButtons;
    private String title, correctCode, codeEntry, content, typed_code, positiveText, negativeText, headerText;
    private int timeLeft, starsNumber = 5, counterSeconds, maxYear;
    private float rateValue;
    private @ColorRes
    int positiveBgColor = -1, negativeBgColor = -1, headerBgColor = -1;
    private @DrawableRes
    int positiveBgDrawable = -1, negativeBgDrawable = -1, headerBgDrawable = -1;
    private @ColorRes
    int positiveTextColor = -1, negativeTextColor = -1, headerTextColor = -1;
    @ColorInt
    private int dialogCodeTextColor = Color.BLACK;
    @DialogPlus.TYPE
    private int dialog_type;
    /**
     * Listeners
     **/
    private DialogPlus.CodeTypeListener codeTypeListener;
    private DialogPlus.DialogActionListener dialogActionListener;
    private DialogPlus.DialogListListener dialogListListener;
    private DialogPlus.DialogRateListener rateListener;
    private MonthYearPickerDialog.PickerListener pickerListener;
    private MultiOptionsDialog.ActionListener multiOptionsDialogListener;

    private List<String> listDialogItems = new ArrayList<>();

    public DialogUiModel() {
    }

    public DialogUiModel(String title, int headerBgColor, int headerTextColor) {
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

    public void setHeaderBgColor(@ColorRes int headerBgColor) {
        this.headerBgColor = headerBgColor;
        notifyPropertyChanged(BR.headerBgColor);
    }

    @Bindable
    public @ColorRes
    int getNegativeBgColor() {
        return negativeBgColor;
    }


    public void setNegativeBgColor(@ColorRes int negativeColorRes) {
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

    public DialogUiModel setTitle(String title) {
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

    public void setPositiveBgDrawable(int positiveBgDrawable) {
        this.positiveBgDrawable = positiveBgDrawable;
        notifyPropertyChanged(BR.positiveBgDrawable);
    }

    @Bindable
    public int getNegativeBgDrawable() {
        return negativeBgDrawable;
    }

    public void setNegativeBgDrawable(int negativeBgDrawable) {
        this.negativeBgDrawable = negativeBgDrawable;
        notifyPropertyChanged(BR.negativeBgDrawable);
    }

    @Bindable
    public int getHeaderBgDrawable() {
        return headerBgDrawable;
    }

    public void setHeaderBgDrawable(int headerBgDrawable) {
        this.headerBgDrawable = headerBgDrawable;
        notifyPropertyChanged(BR.headerBgDrawable);
    }

    @Bindable
    public int getPositiveTextColor() {
        return positiveTextColor;
    }

    public void setPositiveTextColor(int positiveTextColor) {
        this.positiveTextColor = positiveTextColor;
        notifyPropertyChanged(BR.positiveTextColor);
    }

    @Bindable
    public int getNegativeTextColor() {
        return negativeTextColor;
    }

    public void setNegativeTextColor(int negativeTextColor) {
        this.negativeTextColor = negativeTextColor;
        notifyPropertyChanged(BR.negativeTextColor);
    }

    @Bindable
    public int getHeaderTextColor() {
        return headerTextColor;
    }

    public void setHeaderTextColor(int headerTextColor) {
        this.headerTextColor = headerTextColor;
        notifyPropertyChanged(BR.headerTextColor);
    }

    @Bindable
    public String getCodeEntry() {
        return codeEntry;
    }

    public void setCodeEntry(String codeEntry) {
        this.codeEntry = codeEntry;
        notifyPropertyChanged(BR.codeEntry);
    }

    @Bindable
    public String getCorrectCode() {
        return correctCode;
    }

    public void setCorrectCode(String correctCode) {
        this.correctCode = correctCode;
    }

    public void setPositiveBgColor(@ColorRes int positiveBgColor) {
        this.positiveBgColor = positiveBgColor;
        notifyPropertyChanged(BR.positiveTextColor);
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

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
        notifyPropertyChanged(BR.positiveText);
    }

    @Bindable
    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
        notifyPropertyChanged(BR.negativeText);
    }

    @Bindable
    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
        notifyPropertyChanged(BR.headerText);
    }

    @Bindable
    public int getDialogCodeTextColor() {
        return dialogCodeTextColor;
    }

    public void setDialogCodeTextColor(int dialogCodeTextColor) {
        this.dialogCodeTextColor = dialogCodeTextColor;
        notifyPropertyChanged(BR.dialogCodeTextColor);
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

    public void setDialog_type(int dialog_type) {
        this.dialog_type = dialog_type;
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

    public void setDialogActionListener(DialogPlus.DialogActionListener dialogActionListener) {
        this.dialogActionListener = dialogActionListener;
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

    public List<String> getListDialogItems() {
        return listDialogItems;
    }

    public DialogUiModel setListDialogItems(List<String> listDialogItems) {
        this.listDialogItems = listDialogItems;
        return this;
    }

    public MonthYearPickerDialog.PickerListener getPickerListener() {
        return pickerListener;
    }

    public void setPickerListener(MonthYearPickerDialog.PickerListener pickerListener) {
        this.pickerListener = pickerListener;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public DialogUiModel setMaxYear(int maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public MultiOptionsDialog.ActionListener getMultiOptionsDialogListener() {
        return multiOptionsDialogListener;
    }

    public DialogUiModel setMultiOptionsDialogListener(MultiOptionsDialog.ActionListener multiOptionsDialogListener) {
        this.multiOptionsDialogListener = multiOptionsDialogListener;
        return this;
    }
}
