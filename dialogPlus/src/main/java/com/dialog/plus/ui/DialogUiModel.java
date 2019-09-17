package com.dialog.plus.ui;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.dialog.plus.BR;
import com.dialog.plus.R;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class DialogUiModel extends BaseObservable {
    private boolean withSend, withResend, withCounter, typeMessage, separateActionButtons;
    private String title, correctCode, codeEntry, content, typed_code, positiveText, negativeText, headerText;
    private int timeLeft;
    private @ColorRes
    int positiveBgColor, negativeBgColor, headerBgColor;
    private @DrawableRes
    int positiveBgDrawable, negativeBgDrawable, headerBgDrawable;
    private @ColorRes
    int positiveTextColor, negativeTextColor, headerTextColor;
    @ColorInt
    private int dialogCodeTextColor = Color.BLACK;
    @DialogPlus.TYPE
    private int dialog_type;

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

    public void setTitle(String title) {
        this.title = title;
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
        return typeMessage;
    }

    public void setTypeMessage(boolean typeMessage) {
        this.typeMessage = typeMessage;
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

    @Bindable
    public int getDialogWhite() {
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
}
