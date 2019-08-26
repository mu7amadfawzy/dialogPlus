package com.dialog.plus.ui;

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
    private String title, correctCode, codeEntry, content, typed_code;
    private int timeLeft;
    private @ColorRes
    int positiveBgColor, negativeBgColor, headerBgColor;
    private @DrawableRes
    int positiveBgDrawable, negativeBgDrawable, headerBgDrawable;
    private @ColorRes
    int positiveTextColor, negativeTextColor, headerTextColor;

    public DialogUiModel() {
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
    }

    @Bindable
    public @ColorRes
    int getNegativeBgColor() {
        return negativeBgColor;
    }

    public void setNegativeBgColor(int negativeBgColor) {
        this.negativeBgColor = negativeBgColor;
    }

    public void setNegativeBackground(@ColorRes int negativeColorRes) {
        this.negativeBgColor = negativeColorRes;
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
    }

    @Bindable
    public int getNegativeBgDrawable() {
        return negativeBgDrawable;
    }

    public void setNegativeBgDrawable(int negativeBgDrawable) {
        this.negativeBgDrawable = negativeBgDrawable;
    }

    @Bindable
    public int getHeaderBgDrawable() {
        return headerBgDrawable;
    }

    public void setHeaderBgDrawable(int headerBgDrawable) {
        this.headerBgDrawable = headerBgDrawable;
    }

    @Bindable
    public int getPositiveTextColor() {
        return positiveTextColor;
    }

    public void setPositiveTextColor(int positiveTextColor) {
        this.positiveTextColor = positiveTextColor;
    }

    @Bindable
    public int getNegativeTextColor() {
        return negativeTextColor;
    }

    public void setNegativeTextColor(int negativeTextColor) {
        this.negativeTextColor = negativeTextColor;
    }

    @Bindable
    public int getHeaderTextColor() {
        return headerTextColor;
    }

    public void setHeaderTextColor(int headerTextColor) {
        this.headerTextColor = headerTextColor;
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
    public int getDialogWhite() {
        return R.color.dialogTransparent;
    }

    @Bindable
    public int getNoDrawable() {
        return -1;
    }

}
