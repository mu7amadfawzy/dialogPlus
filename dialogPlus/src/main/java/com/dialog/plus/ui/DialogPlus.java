package com.dialog.plus.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dialog.plus.R;
import com.dialog.plus.databinding.DialogBinding;
import com.dialog.plus.utils.AnimationUtils;
import com.dialog.plus.utils.KeyboardUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class DialogPlus extends DialogFragment implements View.OnClickListener {
    private DialogUiModel model;
    private DialogBinding binding;
    private @TYPE
    int dialog_type;
    private AnimationSet mModalInAnim, mModalOutAnim;
    private View mDialogView;
    private CodeTypeListener codeTypeListener;
    private OnDialogActionClicked onDialogActionClicked;
    private String title, content, confirm_code_text, resend_code_text, correct_code;
    private int counterSeconds;
    private @ColorRes
    int positiveBgColor, negativeBgColor, headerBgColor;
    private @ColorRes
    int positiveTextColor, negativeTextColor, headerTextColor;
    private @DrawableRes
    int positiveBgDrawable = -1, negativeBgDrawable = -1, headerBgDrawable = -1;
    private boolean withResend, withSend;
    private CountDownTimer countDownTimer;

    public DialogPlus(String content) {
        this(null, content);
    }

    public DialogPlus(String title, String content) {
        this(TYPE.CONFIRMATION, title, content);
    }

    public DialogPlus(@TYPE int type, String title, String content) {
        set(type, title, content);
    }

    /**
     * Helper Methods--> helps to set your specific dialog based on parameters
     */

    /**
     * Sets a code confirmation dialog interface
     */
    public DialogPlus setConfirmCodeDialog(String correct_code, boolean withSend, boolean withResend, int counterSeconds, CodeTypeListener codeTypeListener) {
        return setDialog_type(TYPE.CONFIRM_CODE).setConfirmDialog(correct_code, withSend, withResend, counterSeconds, codeTypeListener);
    }

    /**
     * Sets an Error dialog interface
     */
    public DialogPlus setErrorDialog(OnDialogActionClicked onDialogActionClicked) {
        return setDialog_type(TYPE.ERROR_DIALOG).setOnDialogActionClicked(onDialogActionClicked);
    }

    /**
     * Sets an Success dialog interface
     */
    public DialogPlus setSuccessDialog(OnDialogActionClicked onDialogActionClicked) {
        return setDialog_type(TYPE.SUCCESS_DIALOG).setOnDialogActionClicked(onDialogActionClicked);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setUiModelData();
        renderView(inflater, container);
        initViews();
        initAnimations();
        setCounter();
        setListeners();
        return binding.getRoot();
    }


    private void renderView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog, container, false);
        binding.setDialogLayoutRes(getDialogLayoutRes());
        binding.setModel(model);
        binding.executePendingBindings();
    }

    private @LayoutRes
    int getDialogLayoutRes() {
        switch (dialog_type) {
            case TYPE.CONFIRM_CODE:
                return R.layout.layout_code_dialog;
            case TYPE.ERROR_DIALOG:
                return R.layout.layout_error_dialog;
            case TYPE.SUCCESS_DIALOG:
                return R.layout.layout_success_dialog;
        }
        return R.layout.layout_confirmation_dialog;
    }

    private void setUiModelData() {
        model = new DialogUiModel();
        updateModelTexts();
        updateModelBackground();
        updateModelBackgroundColor();
        updateModelTextColors();
    }

    private void initViews() {
        mDialogView = getDialog().getWindow().getDecorView().findViewById(android.R.id.content);
        getDialog().setCanceledOnTouchOutside(false);
        setDialogType();
    }

    private void setDialogType() {
        switch (dialog_type) {
            case TYPE.CONFIRM_CODE:
                setOnTextListener();
                break;
            case TYPE.ERROR_DIALOG:
                setErrorAnimation();
                break;
            case TYPE.SUCCESS_DIALOG:
                setSuccessAnimation();
                break;
        }
    }

    private void setErrorAnimation() {
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .playOn(getView(R.id.error_img));
    }

    private void setSuccessAnimation() {
        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .playOn(getView(R.id.successImg));
    }

    private void setOnTextListener() {
        ((EditText) getView(R.id.txtPinEntry)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNormalTextColor();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || charSequence.toString().isEmpty() || charSequence.toString().length() < correct_code.length())
                    return;
                /////////// required length reached
                KeyboardUtil.getInstance().hideKeyboard(binding.getRoot());
                if (!withSend && charSequence.toString().equals(correct_code))
                    onCorrect();
                else
                    onWrong();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void onCorrect() {
        if (codeTypeListener != null)
            codeTypeListener.onSuccess(this);
        dismiss();
    }

    private void onWrong() {
        setErrorTextColor();
        if (codeTypeListener != null)
            codeTypeListener.onWrongCode(this);
        animateField(getActivity(), (EditText) getView(R.id.txtPinEntry));
    }

    private void setErrorTextColor() {
        setTextColor(Color.RED);
    }

    private void setNormalTextColor() {
        setTextColor(ContextCompat.getColor(getContext(), positiveTextColor));
    }

    private void setTextColor(int colorRes) {
        ((PinEntryEditText) getView(R.id.txtPinEntry)).setTextColor(colorRes);
        ((PinEntryEditText) getView(R.id.txtPinEntry)).getPaint().setColor(colorRes);
    }

    private void animateField(Context context, EditText editText) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .onEnd(animator -> {
                    editText.setText(null);
                })
                .playOn(editText);
    }

    @Override
    public void onStart() {
        super.onStart();
        mDialogView.startAnimation(mModalInAnim);
        setDialog();
    }

    private void setDialog() {
        android.app.Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }

    private void initAnimations() {
        mModalInAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.modal_out);
        setAnimationListener();
    }

    private void setAnimationListener() {
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(() -> {
                    dismiss();
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setCounter() {
        if (counterSeconds > 0) {
            countDownTimer = new CountDownTimer(counterSeconds * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    model.setTimeLeft(Math.round(millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    timeUp();
                }
            }.start();
        }
    }

    private void setListeners() {
        if (dialog_type == TYPE.CONFIRM_CODE) {
            getView(R.id.sendCode).setOnClickListener(this);
            getView(R.id.resendCode).setOnClickListener(this);
        } else if (dialog_type == TYPE.ERROR_DIALOG)
            getView(R.id.errorButton).setOnClickListener(this);
        else if (dialog_type == TYPE.SUCCESS_DIALOG)
            getView(R.id.successButton).setOnClickListener(this);
        else {//CONFIRMATION
            getView(R.id.cancelButton).setOnClickListener(this);
            getView(R.id.confirmButton).setOnClickListener(this);
        }
    }

    private void timeUp() {
        model.setTimeLeft(0);
        getView(R.id.sendCode).setClickable(false);
        getView(R.id.txtPinEntry).setEnabled(false);
        KeyboardUtil.getInstance().hideKeyboard(binding.getRoot());
        getView(R.id.sendCode).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.carbon_grey_300));
        if (codeTypeListener != null)
            codeTypeListener.onTimeUp(DialogPlus.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendCode)
            sendCode();
        else if (view.getId() == R.id.resendCode)
            handleResendCode();
        else if (view.getId() == R.id.confirmButton)
            onConfirmClicked();
        else if (view.getId() == R.id.cancelButton)
            onNegativeClicked();
        else if (view.getId() == R.id.errorButton)
            onErrorClicked();
        else if (view.getId() == R.id.successButton)
            onSuccessClicked();
        dismiss();
    }

    private void onSuccessClicked() {
        if (codeTypeListener != null)
            codeTypeListener.onSuccess(this);
    }

    private void onErrorClicked() {
        if (onDialogActionClicked != null)
            onDialogActionClicked.onWrongCode(this);
    }

    private void onNegativeClicked() {
        if (onDialogActionClicked != null)
            onDialogActionClicked.onNegative(this);
    }

    private void onConfirmClicked() {
        if (onDialogActionClicked != null)
            onDialogActionClicked.onPositive(this);
    }

    private void handleResendCode() {
        if (codeTypeListener != null)
            codeTypeListener.onResend(this);
        cancelTimer();
    }

    private void sendCode() {
        if (model.getCodeEntry() != null && model.getCodeEntry().length() == correct_code.length()) {
            cancelTimer();
            dismiss();
            if (codeTypeListener != null)
                codeTypeListener.onCodeTyped(model.getCodeEntry());
        } else
            Toast.makeText(getActivity(), "Please enter complete code", Toast.LENGTH_SHORT).show();
    }

    private void cancelTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
    }

    private View getView(@IdRes int viewId) {
        return binding.container.getChildAt(0).findViewById(viewId);
    }

    /**
     * Builders
     */
    private DialogPlus setConfirmDialog(String correct_code, boolean withSend, boolean withResend, int counterSeconds, CodeTypeListener codeTypeListener) {
        set(correct_code, withSend, withResend);
        this.codeTypeListener = codeTypeListener;
        this.counterSeconds = counterSeconds;
        return this;
    }

    private DialogPlus set(int dialog_type, String title, String content) {
        this.dialog_type = dialog_type;
        this.title = title;
        this.content = content;
        setBackgroundColors(R.color.colorPrimary, R.color.colorPrimary, R.color.colorAccent);
        setTextColors(R.color.carbon_white, R.color.carbon_white, R.color.carbon_white);
        return this;
    }

    private void set(String correct_code, boolean withSend, boolean withResend) {
        this.correct_code = correct_code;
        this.withResend = withResend;
        this.withSend = withSend;
    }

    private void updateModelTextColors() {
        model.setPositiveTextColor(positiveTextColor);
        model.setNegativeTextColor(negativeTextColor);
        model.setHeaderTextColor(headerTextColor);
    }

    private void updateModelBackground() {
        model.setPositiveBgDrawable(positiveBgDrawable);
        model.setNegativeBgDrawable(negativeBgDrawable);
        model.setHeaderBgDrawable(headerBgDrawable);
    }

    private void updateModelBackgroundColor() {
        model.setPositiveBackground(positiveBgColor);
        model.setNegativeBackground(negativeBgColor);
        model.setHeaderBackground(headerBgColor);
    }

    private void updateModelTexts() {
        model.setTitle(title);
        model.setContent(content);
        model.setCorrectCode(correct_code);
        model.setConfirm_code_text(confirm_code_text);
        model.setTimeLeft(counterSeconds);
        model.setResend_code_text(resend_code_text);
        model.setWithResend(withResend);
        model.setWithSend(withSend);
    }

    /**
     * sets the background color to header background and positive background
     */
    public DialogPlus setPrimaryBgColor(@ColorRes int primaryColor) {
        this.positiveBgColor = primaryColor;
        this.headerBgColor = primaryColor;
        return this;
    }

    /**
     * sets the background drawable to header background and positive background
     */
    public DialogPlus setPrimaryDrawable(@DrawableRes int primaryDrawable) {
        this.positiveBgDrawable = primaryDrawable;
        this.headerBgDrawable = primaryDrawable;
        return this;
    }

    /**
     * sets the text color to header background and positive background
     */
    public DialogPlus setPrimaryTextColor(@ColorRes int primaryTextColor) {
        this.positiveTextColor = primaryTextColor;
        this.headerTextColor = primaryTextColor;
        return this;
    }

    /**
     * sets the background color to the negative
     */

    public DialogPlus setSecondaryBgColor(@ColorRes int secondaryColor) {
        this.negativeBgColor = secondaryColor;
        return this;
    }

    /**
     * sets the background drawable to the negative
     */
    public DialogPlus setSecondaryBgDrawable(@DrawableRes int secondaryDrawable) {
        this.negativeBgDrawable = secondaryDrawable;
        return this;
    }

    /**
     * sets the text color to the negative
     */
    public DialogPlus setSecondaryTextColor(@ColorRes int secondaryTextColor) {
        this.negativeTextColor = secondaryTextColor;
        return this;
    }

    /**
     * sets the background color to the header background and positive andnegative
     */
    public DialogPlus setBackgroundColors(@ColorRes int positiveBackground, @ColorRes int negativeColorRes) {
        return setBackgroundColors(positiveBackground, negativeColorRes, 0);
    }

    public DialogPlus setBackgroundColors(@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBackground) {
        this.positiveBgColor = positiveBackground;
        this.headerBgColor = headerBackground;
        this.negativeBgColor = negativeColorRes;
        return this;
    }

    /**
     * sets the text color to the header background and positive andnegative
     */
    public DialogPlus setTextColors(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor) {
        return setTextColors(positiveTextColor, 0, negativeTextColor);
    }

    public DialogPlus setTextColors(@ColorRes int positiveTextColor, @ColorRes int headerTextColor, @ColorRes int negativeTextColor) {
        this.positiveTextColor = positiveTextColor;
        this.headerTextColor = headerTextColor;
        this.negativeTextColor = negativeTextColor;
        return this;
    }

    /**
     * sets the background drawable to the header background and positive andnegative
     */
    public DialogPlus setBackgrounds(@DrawableRes int positiveBackground, @DrawableRes int negativeBackground) {
        return setBackgrounds(positiveBackground, negativeBackground, 0);
    }

    public DialogPlus setBackgrounds(@DrawableRes int positiveBackground, @DrawableRes int negativeBackground, @DrawableRes int headerBackground) {
        this.positiveBgDrawable = positiveBackground;
        this.headerBgDrawable = headerBackground;
        this.negativeBgDrawable = negativeBackground;
        return this;
    }

    public DialogPlus setConfirm_code_text(String confirm_code_text) {
        this.confirm_code_text = confirm_code_text;
        return this;
    }

    public DialogPlus setResend_code_text(String resend_code_text) {
        this.resend_code_text = resend_code_text;
        return this;
    }

    private DialogPlus setDialog_type(@TYPE int dialog_type) {
        this.dialog_type = dialog_type;
        return this;
    }

    public DialogPlus setHeaderBgColor(@DrawableRes int headerBgColor) {
        this.headerBgColor = headerBgColor;
        return this;
    }

    public DialogPlus setNegativeBgColor(@ColorRes int negativeBgColor) {
        this.negativeBgColor = negativeBgColor;
        return this;
    }

    public DialogPlus setNegativeTextColor(@ColorRes int negativeTextColor) {
        this.negativeTextColor = negativeTextColor;
        return this;
    }


    public DialogPlus setHeaderBgDrawable(@DrawableRes int headerBgDrawable) {
        this.headerBgDrawable = headerBgDrawable;
        return this;
    }

    public DialogPlus setCodeTypeListener(CodeTypeListener codeTypeListener) {
        this.codeTypeListener = codeTypeListener;
        return this;
    }

    public DialogPlus setOnDialogActionClicked(OnDialogActionClicked onDialogActionClicked) {
        this.onDialogActionClicked = onDialogActionClicked;
        return this;
    }

    public DialogPlus setContent(String content) {
        this.content = content;
        return this;
    }

    public DialogPlus setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * dialog type will be indicated by one of the bellow integers
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
        int CONFIRMATION = 0;
        int CONFIRM_CODE = 1;
        int ERROR_DIALOG = 3;
        int SUCCESS_DIALOG = 4;
    }

    /**
     * Listeners
     */
    public abstract static class CodeTypeListener {
        public void onCodeTyped(String typedCode) {
        }

        public void onTimeUp(DialogPlus dialogPlus) {
            dialogPlus.dismiss();
        }

        public abstract void onSuccess(DialogPlus dialogPlus);

        public abstract void onResend(DialogPlus dialogPlus);

        public abstract void onWrongCode(DialogPlus dialogPlus);
    }

    public abstract static class OnDialogActionClicked {
        public abstract void onPositive(DialogPlus dialogPlus);

        public void onNegative(DialogPlus dialogPlus) {
            dialogPlus.dismiss();
        }

        public void onWrongCode(DialogPlus dialogPlus) {
            dialogPlus.dismiss();
        }

    }
}
