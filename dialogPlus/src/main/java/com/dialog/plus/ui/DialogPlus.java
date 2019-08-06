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
import android.widget.Toast;

import androidx.annotation.ColorRes;
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

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class DialogPlus extends DialogFragment implements View.OnClickListener {
    private DialogUiModel model;
    private DialogBinding binding;
    private int dialog_type;
    private AnimationSet mModalInAnim, mModalOutAnim;
    private View mDialogView;
    private Float codeLength = 6f;
    private OnCodeTyped onCodeTyped;
    private OnValidateCode onValidateCode;
    private OnDialogActionClicked onDialogActionClicked;
    private String title, content, confirm_code_text, resend_code_text, correct_code;
    private int counterSeconds = 60;
    private @ColorRes
    int positiveBackground = R.color.colorPrimary, negativeColorRes = R.color.colorAccent, headerBackground = R.color.colorPrimary;
    private boolean withResend;

    /**
     * dialog type will be indicated by one of the bellow integers
     */
    public static final int CONFIRMATION = 0, CONFIRM_CODE = 1, VALIDATE_CODE = 2, ERROR_DIALOG = 3, SUCCESS_DIALOG = 4;
    private CountDownTimer countDownTimer;

    public DialogPlus showConfirmCodeDialog(float codeLength, String title, String content
            , String confirm_code_text, String resend_code_text, @ColorRes int positiveBackground
            , @ColorRes int headerBackground, OnCodeTyped onCodeTyped) {
        this.dialog_type = CONFIRM_CODE;
        this.codeLength = codeLength;
        this.title = title;
        this.resend_code_text = resend_code_text;
        this.content = content;
        this.onCodeTyped = onCodeTyped;
        this.confirm_code_text = confirm_code_text;
        this.positiveBackground = positiveBackground;
        this.headerBackground = headerBackground;
        return this;
    }

    public DialogPlus showConfirmCodeDialog(float codeLength, String title, String content, OnCodeTyped onCodeTyped) {
        this.dialog_type = CONFIRM_CODE;
        this.codeLength = codeLength;
        this.title = title;
        this.content = content;
        this.onCodeTyped = onCodeTyped;
        return this;
    }

    public DialogPlus showConfirmCodeDialog(float codeLength, String title, String content,
                                            @ColorRes int positiveBackground, @ColorRes int headerBackground, OnCodeTyped onCodeTyped) {
        this.dialog_type = CONFIRM_CODE;
        this.codeLength = codeLength;
        this.title = title;
        this.content = content;
        this.onCodeTyped = onCodeTyped;
        this.positiveBackground = positiveBackground;
        this.headerBackground = headerBackground;
        return this;
    }

    public DialogPlus showValidateCodeDialog(float codeLength, String title, String content, String correct_code
            , boolean withResend
            , @ColorRes int positiveBackground, @ColorRes int headerBackground, OnValidateCode onValidateCode) {
        this.dialog_type = VALIDATE_CODE;
        this.codeLength = codeLength;
        this.title = title;
        this.correct_code = correct_code;
        this.content = content;
        this.withResend = withResend;
        this.onValidateCode = onValidateCode;
        this.positiveBackground = positiveBackground;
        this.headerBackground = headerBackground;
        return this;
    }

    public DialogPlus showValidateCodeDialog(float codeLength, String title, String content, String correct_code
            , boolean withResend, OnValidateCode onValidateCode) {
        this.dialog_type = VALIDATE_CODE;
        this.codeLength = codeLength;
        this.title = title;
        this.correct_code = correct_code;
        this.content = content;
        this.withResend = withResend;
        this.onValidateCode = onValidateCode;
        return this;
    }

    public DialogPlus showConfirmationDialog(String title, String content, @ColorRes int positiveBackground
            , @ColorRes int negativeColorRes, @ColorRes int headerBackground, OnDialogActionClicked onDialogActionClicked) {
        this.dialog_type = CONFIRMATION;
        this.title = title;
        this.content = content;
        this.onDialogActionClicked = onDialogActionClicked;
        this.positiveBackground = positiveBackground;
        this.headerBackground = headerBackground;
        this.negativeColorRes = negativeColorRes;
        return this;
    }

    public DialogPlus showConfirmationDialog(String title, String content, OnDialogActionClicked onDialogActionClicked) {
        this.dialog_type = CONFIRMATION;
        this.title = title;
        this.content = content;
        this.onDialogActionClicked = onDialogActionClicked;
        this.positiveBackground = positiveBackground;
        return this;
    }

    public DialogPlus showConfirmationDialog(String title, String content, @ColorRes int colorPrimary, @ColorRes int colorAccent, OnDialogActionClicked onDialogActionClicked) {
        this.dialog_type = CONFIRMATION;
        this.title = title;
        this.content = content;
        this.onDialogActionClicked = onDialogActionClicked;
        this.positiveBackground = colorPrimary;
        this.headerBackground = colorPrimary;
        this.negativeColorRes = colorAccent;
        return this;
    }

    public DialogPlus showConfirmationDialog(String title, String content, @ColorRes int colorPrimary, OnDialogActionClicked onDialogActionClicked) {
        this.dialog_type = CONFIRMATION;
        this.title = title;
        this.content = content;
        this.onDialogActionClicked = onDialogActionClicked;
        this.positiveBackground = colorPrimary;
        this.headerBackground = colorPrimary;
        return this;
    }

    public DialogPlus showErrorDialog(String content, OnDialogActionClicked onDialogActionClicked) {
        this.dialog_type = ERROR_DIALOG;
        this.content = content;
        this.onDialogActionClicked = onDialogActionClicked;
        return this;
    }

    public DialogPlus showSuccessDialog(String content, OnDialogActionClicked onDialogActionClicked) {
        this.dialog_type = SUCCESS_DIALOG;
        this.content = content;
        this.onDialogActionClicked = onDialogActionClicked;
        return this;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        renderView(inflater, container);
        initViews();
        initAnimations();
        setCounter();
        setListeners();
        return binding.getRoot();
    }

    private void setListeners() {
        binding.codeDialog.sendCode.setOnClickListener(this);
        binding.codeDialog.resendCode.setOnClickListener(this);
        binding.confirmationDialog.cancelButton.setOnClickListener(this);
        binding.confirmationDialog.confirmButton.setOnClickListener(this);
        binding.errorDialog.errorButton.setOnClickListener(this);
        binding.successDialog.successButton.setOnClickListener(this);
    }

    private void renderView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        model = new DialogUiModel();
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog, container, false);
        setUiModelData();
        binding.setModel(model);
    }

    private void setUiModelData() {
        model.setTitle(title);
        model.setContent(content);
        model.setConfirm_code_text(confirm_code_text);
        model.setTimeLeft(counterSeconds);
        model.setResend_code_text(resend_code_text);
        model.setPositiveBackground(positiveBackground);
        model.setNegativeColorRes(negativeColorRes);
        model.setHeaderBackground(headerBackground);
        model.setWithResend(withResend);
    }


    private void initViews() {
        mDialogView = getDialog().getWindow().getDecorView().findViewById(android.R.id.content);
        getDialog().setCanceledOnTouchOutside(false);
        setDialogType();
        if (codeLength != null)
            binding.codeDialog.txtPinEntry.setNumOfChars(codeLength);

    }

    private void setDialogType() {
        switch (dialog_type) {
            case CONFIRMATION:
                model.setConfirmation_dialog(true);
                break;
            case CONFIRM_CODE:
                model.setCode_dialog(true);
                break;
            case VALIDATE_CODE:
                model.setValidation_dialog(true);
                setOnTextListener();
                break;
            case ERROR_DIALOG:
                model.setError_dialog(true);
                setErrorAnimation();
                break;
            case SUCCESS_DIALOG:
                model.setSuccess_dialog(true);
                setSuccessAnimation();
                break;
        }
    }

    private void setErrorAnimation() {
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .playOn(binding.errorDialog.errorImg);
    }

    private void setSuccessAnimation() {
        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .playOn(binding.successDialog.successImg);
    }

    private void setOnTextListener() {
        binding.codeDialog.txtPinEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNormalTextColor();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() < correct_code.length())
                    return;
                if (editable.toString().equals(correct_code)) {
                    onCorrect();
                } else
                    onWrong();

            }
        });
    }

    private void onCorrect() {
        onValidateCode.onSuccess();
        getDialog().dismiss();
    }

    private void onWrong() {
        setErrorTextColor();
        onValidateCode.onError(DialogPlus.this);
        animateField(getActivity(), binding.codeDialog.txtPinEntry);
    }

    private void setErrorTextColor() {
        setTextColor(Color.RED);
    }

    private void setNormalTextColor() {
        setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
    }

    private void setTextColor(int colorRes) {
        binding.codeDialog.txtPinEntry.getPaint().setColor(colorRes);
    }

    private void animateField(Context context, PinEntryEditText editText) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .onEnd(animator -> {
                    editText.setText(null);
                    editText.getPaint().setColor(ContextCompat.getColor(context, R.color.colorPrimary));

                })
                .playOn(editText);
    }

    @Override
    public void onStart() {
        super.onStart();
        mDialogView.startAnimation(mModalInAnim);
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
                    getDialog().dismiss();
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
                    if (onCodeTyped != null) {
                        timeUp();
                    }
                }
            }.start();
        }
    }

    private void timeUp() {
        model.setTimeLeft(0);
        binding.codeDialog.sendCode.setClickable(false);
        binding.codeDialog.txtPinEntry.setEnabled(false);
        KeyboardUtil.getInstance().hideKeyboard(binding.getRoot());
        binding.codeDialog.sendCode.setBackgroundColor(binding.codeDialog.sendCode.getContext().getResources().getColor(R.color.carbon_grey_300));
        onCodeTyped.onTimeUp(DialogPlus.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send_code)
            sendCode();
        else if (view.getId() == R.id.resend_code)
            handleResendCode();
        else if (view.getId() == R.id.confirm_button) {
            onDialogActionClicked.onPositive(this);
            getDialog().dismiss();
        } else if (view.getId() == R.id.cancel_button) {
            onDialogActionClicked.onNegative(this);
            getDialog().dismiss();
        } else if (view.getId() == R.id.error_button) {
            onDialogActionClicked.onError(this);
            getDialog().dismiss();
        } else if (view.getId() == R.id.success_button) {
            onCodeTyped.onSuccess(this);
            getDialog().dismiss();
        }
    }

    private void handleResendCode() {
        if (dialog_type == CONFIRM_CODE)
            onCodeTyped.onResend(this);
        else onValidateCode.onResend(this);
        if (countDownTimer != null) countDownTimer.cancel();
        getDialog().dismiss();
    }

    private void sendCode() {
        if (binding.codeDialog.txtPinEntry.getText().length() == codeLength) {
            onCodeTyped.onCodeTyped(binding.codeDialog.txtPinEntry.getText().toString());
            if (countDownTimer != null) countDownTimer.cancel();
            getDialog().dismiss();
        } else
            Toast.makeText(getActivity(), "Please enter complete code", Toast.LENGTH_SHORT).show();

    }

    public abstract static class OnCodeTyped {
        public void onCodeTyped(String typedCode) {

        }

        public void onTimeUp(DialogPlus dialogPlus) {
            dialogPlus.dismiss();
        }

        public abstract void onSuccess(DialogPlus dialogPlus);

        public abstract void onResend(DialogPlus dialogPlus);
    }

    public abstract static class OnDialogActionClicked {
        public abstract void onPositive(DialogPlus dialogPlus);

        public void onNegative(DialogPlus dialogPlus) {
            dialogPlus.dismiss();
        }

        public void onError(DialogPlus dialogPlus) {
            dialogPlus.dismiss();
        }

    }

    public abstract static class OnValidateCode {
        public abstract void onSuccess();

        public void onError(DialogPlus dialogPlus) {
            dialogPlus.dismiss();
        }

        public abstract void onResend(DialogPlus dialogPlus);
    }
}
