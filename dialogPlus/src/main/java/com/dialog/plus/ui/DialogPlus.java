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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
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
import com.dialog.plus.databinding.DialogPlusBinding;
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
    private DialogPlusBinding binding;
    @TYPE
    private int dialog_type;
    private AnimationSet mModalInAnim, mModalOutAnim;
    private View mDialogView;
    private CodeTypeListener codeTypeListener;
    private DialogActionListener dialogActionListener;
    private String title, content, correct_code, positiveText, negativeText;
    private int counterSeconds;
    @ColorRes
    private int positiveBgColor, negativeBgColor, headerBgColor;
    @ColorRes
    private int positiveTextColor, negativeTextColor, headerTextColor;
    @DrawableRes
    private int positiveBgDrawable, negativeBgDrawable, headerBgDrawable;
    private boolean withResend, withSend, withCounter, typeMessage, separateActionButtons;
    private CountDownTimer countDownTimer;
    @ColorInt
    private int dialogCodeTextColor = Color.BLACK;

    public DialogPlus(String content) {
        this(null, content);
    }

    public DialogPlus(String title, String content) {
        this(TYPE.MESSAGE, title, content);
    }

    public DialogPlus(@TYPE int type, String title, String content) {
        set(type, title, content);
    }

    /**
     * Helper Methods--> helps to set your specific dialog_plus based on parameters
     */

    /**
     * Sets a code confirmation dialog_plus interface
     */
    public DialogPlus setConfirmCodeDialog(String correct_code, boolean withSend, boolean withResend, int counterSeconds, @ColorInt int codeTextColor, CodeTypeListener codeTypeListener) {
        return setDialog_type(TYPE.CODE).setTexts(positiveText).setConfirmDialog(correct_code, withSend, withResend, counterSeconds, codeTextColor, codeTypeListener);
    }

    /**
     * Sets a confirmation dialog_plus interface(with positive and negative actions)
     */
    public DialogPlus setConfirmationDialog(DialogActionListener actionClicked) {
        return setConfirmationDialog(null, null, actionClicked);
    }

    public DialogPlus setConfirmationDialog(String positiveText, String negativeText, DialogActionListener actionClicked) {
        return setConfirmationDialog(positiveText, negativeText, false, actionClicked);
    }

    public DialogPlus setConfirmationDialog(String positiveText, String negativeText, boolean separateActionButtons, DialogActionListener actionClicked) {
        return setDialog_type(TYPE.CONFIRMATION).setTexts(positiveText, negativeText).setSeparateActionButtons(separateActionButtons).setDialogActionListener(actionClicked);
    }

    /**
     * Sets a message dialog_plus interface
     */
    public DialogPlus setMessageDialog(DialogActionListener actionClicked) {
        return setMessageDialog(getString(R.string.dialog_ok_text), actionClicked);
    }

    public DialogPlus setMessageDialog(String positiveText, DialogActionListener actionClicked) {
        return setMessageDialog(positiveText).setDialogActionListener(actionClicked);
    }

    /**
     * Sets an Error dialog_plus interface
     */
    public DialogPlus setErrorDialog(DialogActionListener dialogActionListener) {
        return setErrorDialog(null, dialogActionListener);
    }

    public DialogPlus setErrorDialog(String positiveText, DialogActionListener dialogActionListener) {
        return setDialog_type(TYPE.ERROR_DIALOG).setTexts(positiveText).setDialogActionListener(dialogActionListener);
    }

    /**
     * Sets an Success dialog_plus interface
     */
    public DialogPlus setSuccessDialog(DialogActionListener dialogActionListener) {
        return setSuccessDialog(null, dialogActionListener);
    }

    public DialogPlus setSuccessDialog(String positiveText, DialogActionListener dialogActionListener) {
        return setDialog_type(TYPE.SUCCESS_DIALOG).setTexts(positiveText).setDialogActionListener(dialogActionListener);
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
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_plus, container, false);
        binding.setDialogLayoutRes(getDialogLayoutRes());
        binding.setModel(model);
        binding.executePendingBindings();
    }

    private @LayoutRes
    int getDialogLayoutRes() {
        switch (dialog_type) {
            case TYPE.CODE:
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
            case TYPE.MESSAGE:
            case TYPE.CONFIRMATION:
                checkTexts();
                break;
            case TYPE.CODE:
                setOnTextListener();
                break;
            case TYPE.ERROR_DIALOG:
                setErrorAnimation();
                checkTexts();
                break;
            case TYPE.SUCCESS_DIALOG:
                setSuccessAnimation();
                checkTexts();
                break;
        }
    }

    private void checkTexts() {
        changePositiveText();
        if (dialog_type == TYPE.CONFIRMATION)
            changeNegativeText();
    }

    private void changePositiveText() {
        if (positiveText != null)
            ((TextView) getDialogAddedView(R.id.confirmButton)).setText(positiveText);
    }

    private void changeNegativeText() {
        ((TextView) getDialogAddedView(R.id.cancelButton)).setText(negativeText);
    }

    private void setErrorAnimation() {
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .playOn(getDialogAddedView(R.id.error_img));
    }

    private void setSuccessAnimation() {
        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .playOn(getDialogAddedView(R.id.successImg));
    }

    private void setOnTextListener() {
        ((EditText) getDialogAddedView(R.id.txtPinEntry)).addTextChangedListener(new TextWatcher() {
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
                if (!withSend)
                    sendCode();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private boolean validateCode() {
        if (model.isCorrectCode()) {
            onCorrect();
            return true;
        } else
            onWrong();
        return false;
    }

    private void onCorrect() {
        if (codeTypeListener != null)
            codeTypeListener.onSuccess(this);
        dismiss(true);
    }

    private void onWrong() {
        setErrorTextColor();
        if (codeTypeListener != null)
            codeTypeListener.onWrongCode(this);
        animateField(getActivity(), (EditText) getDialogAddedView(R.id.txtPinEntry));
    }

    private void setErrorTextColor() {
        setCodeTextColor(Color.RED);
    }

    private void setNormalTextColor() {
        setCodeTextColor(dialogCodeTextColor);
    }

    public void setCodeTextColor(@ColorInt int colorInt) {
        if (dialog_type == TYPE.CODE) {
            ((PinEntryEditText) getDialogAddedView(R.id.txtPinEntry)).setTextColor(colorInt);
            ((PinEntryEditText) getDialogAddedView(R.id.txtPinEntry)).getPaint().setColor(colorInt);
        }
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
                mDialogView.post(() -> dismiss());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public void dismiss(boolean animate) {
        if (animate)
            mDialogView.startAnimation(mModalOutAnim);
        else dismiss(true);
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
        if (dialog_type == TYPE.SUCCESS_DIALOG || dialog_type == TYPE.ERROR_DIALOG) {
            getDialogAddedView(R.id.closeIV).setOnClickListener(this);
            getDialogAddedView(R.id.confirmButton).setOnClickListener(this);
        } else {
            getHeaderChildView(R.id.closeIV).setOnClickListener(this);
            if (dialog_type == TYPE.CODE) {
                getDialogAddedView(R.id.sendCode).setOnClickListener(this);
                getDialogAddedView(R.id.resendCode).setOnClickListener(this);
            } else {
                getDialogAddedView(R.id.confirmButton).setOnClickListener(this);
                if (dialog_type == TYPE.CONFIRMATION)
                    getDialogAddedView(R.id.cancelButton).setOnClickListener(this);
            }

        }
    }

    private void timeUp() {
        model.setTimeLeft(0);
        getDialogAddedView(R.id.sendCode).setClickable(false);
        getDialogAddedView(R.id.txtPinEntry).setEnabled(false);
        KeyboardUtil.getInstance().hideKeyboard(binding.getRoot());
        if (getContext() != null)
            getDialogAddedView(R.id.sendCode).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.carbon_grey_300));
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
            onPositiveClicked();
        else if (view.getId() == R.id.cancelButton || view.getId() == R.id.closeIV)
            onNegativeClicked();
    }

    private void onPositiveClicked() {
        if (dialog_type == TYPE.CONFIRMATION)
            onConfirmClicked();
        else {//case SUCCESS_DIALOG OR ERROR_DIALOG
            dismiss(true);
        }
    }

    private void sendCode() {
        if (model.getCodeEntry() != null && model.getCodeEntry().length() == correct_code.length()) {
            if (validateCode()) {
                cancelTimer();
                dismiss(true);
            }
        } else
            Toast.makeText(getActivity(), getString(R.string.dialog_incomplete_code_msg), Toast.LENGTH_SHORT).show();
    }


    private void onNegativeClicked() {
        if (dialogActionListener != null)
            dialogActionListener.onNegative(this);
    }

    private void onConfirmClicked() {
        if (dialogActionListener != null)
            dialogActionListener.onPositive(this);
    }

    private void handleResendCode() {
        if (codeTypeListener != null)
            codeTypeListener.onResend(this);
        cancelTimer();
    }

    private void cancelTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
    }

    private View getDialogAddedView(@IdRes int viewId) {
        return binding.dialogContainer.getChildAt(0).findViewById(viewId);
    }

    private View getHeaderChildView(int idRes) {
        return getDialogAddedView(R.id.headerLayout).findViewById(idRes);
    }

    /**
     * Builders
     */
    private DialogPlus setConfirmDialog(String correct_code, boolean withSend, boolean withResend, int counterSeconds, @ColorInt int codeTextColor, CodeTypeListener codeTypeListener) {
        set(correct_code, withSend, withResend, counterSeconds, codeTextColor, codeTypeListener);
        return this;
    }

    private DialogPlus set(int dialog_type, String title, String content) {
        this.dialog_type = dialog_type;
        this.title = title;
        this.content = content;
        setBackgroundColors(R.color.dialogPositiveBgColor, R.color.dialogNegativeBgColor, R.color.dialogPositiveBgColor);
        setTextColors(R.color.dialogPositiveTextColor, R.color.dialogNegativeTextColor, R.color.dialogPositiveTextColor);
        return this;
    }

    private void set(String correct_code, boolean withSend, boolean withResend, int counterSeconds, @ColorInt int codeTextColor, CodeTypeListener codeTypeListener) {
        this.correct_code = correct_code;
        this.withResend = withResend;
        this.withSend = withSend;
        this.counterSeconds = counterSeconds;
        this.withCounter = counterSeconds > 0;
        this.dialogCodeTextColor = codeTextColor;
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
        model.setSeparateActionButtons(separateActionButtons);
    }

    private void updateModelBackgroundColor() {
        model.setPositiveBgColor(positiveBgColor);
        model.setNegativeBackground(negativeBgColor);
        model.setHeaderBgColor(headerBgColor);
    }

    private void updateModelTexts() {
        model.setTitle(title);
        model.setContent(content);
        model.setTypeMessage(typeMessage);
        model.setCorrectCode(correct_code);
        model.setTimeLeft(counterSeconds);
        model.setWithCounter(withCounter);
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

    public DialogPlus setBackgroundColors(@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBgColor) {
        this.positiveBgColor = positiveBackground;
        this.headerBgColor = headerBgColor;
        this.negativeBgColor = negativeColorRes;
        return this;
    }

    /**
     * sets the text color to the header background and positive and negative
     */
    public DialogPlus setTextColors(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor) {
        return setTextColors(positiveTextColor, negativeTextColor, 0);
    }

    /**
     * sets the text to the positiveBtn and negativeBtn
     */
    public DialogPlus setTexts(String positiveText) {
        return setTexts(positiveText, null);
    }

    public DialogPlus setTexts(String positiveText, String negativeText) {
        this.positiveText = positiveText;
        this.negativeText = negativeText;
        return this;
    }

    public DialogPlus setTextColors(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor, @ColorRes int headerTextColor) {
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

    public DialogPlus setDialogCodeTextColor(@ColorInt int dialogCodeTextColor) {
        this.dialogCodeTextColor = dialogCodeTextColor;
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

    public DialogPlus setDialogActionListener(DialogActionListener dialogActionListener) {
        this.dialogActionListener = dialogActionListener;
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

    private DialogPlus setSeparateActionButtons(boolean separateActionButtons) {
        this.separateActionButtons = separateActionButtons;
        return this;
    }

    private DialogPlus setMessageDialog(String positiveText) {
        this.typeMessage = true;
        this.positiveText = positiveText;
        return setDialog_type(TYPE.CONFIRMATION);
    }

    /**
     * dialog_plus type will be indicated by one of the bellow integers
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
        int CONFIRMATION = 0;
        int CODE = 1;
        int MESSAGE = 2;
        int ERROR_DIALOG = 3;
        int SUCCESS_DIALOG = 4;
    }

    /**
     * Listeners
     */
    public abstract static class CodeTypeListener {
        public void onTimeUp(DialogPlus dialogPlus) {
            dialogPlus.dismiss(true);
        }

        public abstract void onSuccess(DialogPlus dialogPlus);

        public abstract void onResend(DialogPlus dialogPlus);

        public abstract void onWrongCode(DialogPlus dialogPlus);
    }

    public abstract static class DialogActionListener {
        public abstract void onPositive(DialogPlus dialogPlus);

        public void onNegative(DialogPlus dialogPlus) {
            dialogPlus.dismiss(true);
        }
    }
}
