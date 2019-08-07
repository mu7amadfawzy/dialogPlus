package com.dialog.plus.ui;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.dialog.plus.BR;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class DialogUiModel extends BaseObservable {
    private boolean withSend, withResend;
    private String title, correctCode, codeEntry, content, confirm_code_text, typed_code, resend_code_text;
    private int timeLeft;
    private @ColorRes
    int positiveBackground, negativeColorRes, headerBackground;
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
    int getHeaderBackground() {
        return headerBackground;
    }

    public void setHeaderBackground(@ColorRes int headerBackground) {
        this.headerBackground = headerBackground;
    }

    @Bindable
    public @ColorRes
    int getNegativeColorRes() {
        return negativeColorRes;
    }

    public void setNegativeBackground(@ColorRes int negativeColorRes) {
        this.negativeColorRes = negativeColorRes;
    }

    @Bindable
    public @ColorRes
    int getPositiveBackground() {
        return positiveBackground;
    }

    public void setPositiveBackground(@ColorRes int positiveBackground) {
        this.positiveBackground = positiveBackground;
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
    public String getResend_code_text() {
        return resend_code_text;
    }

    public void setResend_code_text(String resend_code_text) {
        this.resend_code_text = resend_code_text;
    }

    @Bindable
    public String getTyped_code() {
        return typed_code;
    }

    public void setTyped_code(String typed_code) {
        this.typed_code = typed_code;
    }

    @Bindable
    public String getConfirm_code_text() {
        return confirm_code_text;
    }

    public void setConfirm_code_text(String confirm_code_text) {
        this.confirm_code_text = confirm_code_text;
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

    public void setNegativeColorRes(int negativeColorRes) {
        this.negativeColorRes = negativeColorRes;
    }

    @Bindable
    public boolean isWithSend() {
        return withSend;
    }

    public void setWithSend(boolean withSend) {
        this.withSend = withSend;
    }
}
