package com.dialog.plus.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.dialog.plus.BR;
import com.dialog.plus.R;
import com.dialog.plus.databinding.DialogPlusBinding;
import com.dialog.plus.utils.KeyboardUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class DialogPlus extends BaseDialogFragment<DialogPlusBinding> implements View.OnClickListener {
    private CountDownTimer countDownTimer;

    DialogPlus(DialogPlusUiModel model) {
        this.model = model;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderView();
        initViews();
        setCounter();
        setListeners();
    }

    private void renderView() {
        binding.setDialogLayoutRes(getDialogLayoutRes());
        binding.executePendingBindings();
    }

    private @LayoutRes
    int getDialogLayoutRes() {
        switch (model.getDialog_type()) {
            case TYPE.CODE_DIALOG:
                return R.layout.layout_code_dialog;
            case TYPE.ERROR_DIALOG:
                return R.layout.layout_error_dialog;
            case TYPE.SUCCESS_DIALOG:
                return R.layout.layout_success_dialog;
            case TYPE.LIST_DIALOG:
                return R.layout.layout_dialog_list;
            case TYPE.RATING_DIALOG:
                return R.layout.layout_rating_dialog;
        }
        return R.layout.layout_confirmation_dialog;
    }

    private void initViews() {
        getDialog().setCanceledOnTouchOutside(false);
        setDialogType();
    }

    private void setDialogType() {
        switch (model.getDialog_type()) {
            case TYPE.CODE_DIALOG:
                setOnTextListener();
                break;
            case TYPE.ERROR_DIALOG:
                setErrorAnimation();
                break;
            case TYPE.SUCCESS_DIALOG:
                setSuccessAnimation();
                break;
            case TYPE.LIST_DIALOG:
                renderItemsList();
                break;
        }
    }

    private void renderItemsList() {
        ListDialogAdapter listDialogAdapter = new ListDialogAdapter(this, model.getListDialogItems(), model.getDialogListListener());
        ((RecyclerView) getDialogAddedView(R.id.recycler)).setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        ((RecyclerView) getDialogAddedView(R.id.recycler)).setAdapter(listDialogAdapter);
    }

    private void setErrorAnimation() {
        animate(getDialogAddedView(R.id.error_img), Techniques.BounceIn);
    }

    private void setSuccessAnimation() {
        animate(getDialogAddedView(R.id.successImg), Techniques.FadeIn);
    }
    private void setOnTextListener() {
        ((EditText) getDialogAddedView(R.id.txtPinEntry)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNormalCodeTextColor();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || charSequence.toString().isEmpty() || charSequence.toString().length() < model.getCorrectCode().length())
                    return;
                /////////// required length reached
                hideKeyboard(getDialogAddedView(R.id.txtPinEntry));
                if (!model.isWithSend())
                    sendCode();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == model.getCorrectCode().length())
                    hideKeyboard(getDialogAddedView(R.id.txtPinEntry));
            }
        });
    }

    private boolean validateCode() {
        return model.isCorrectCode();
    }

    private void onCorrect() {
        if (model.getCodeTypeListener() != null)
            model.getCodeTypeListener().onSuccess(this);
        dismiss(true);
    }

    private void onWrong() {
        setErrorTextColor();
        if (model.getCodeTypeListener() != null)
            model.getCodeTypeListener().onWrongCode(this);
        shakeView((EditText) getDialogAddedView(R.id.txtPinEntry));
    }

    private void setErrorTextColor() {
        setPinEntryTextColor(getResources().getColor(R.color.carbon_red_400));
    }

    private void setNormalCodeTextColor() {
        setPinEntryTextColor(model.getDialogCodeTextColor());
    }

    private void setPinEntryTextColor(@ColorInt int color) {
        ((PinEntryEditText) getDialogAddedView(R.id.txtPinEntry)).setTextColor(color);
        ((PinEntryEditText) getDialogAddedView(R.id.txtPinEntry)).getPaint().setColor(color);
    }

    private void setCounter() {
        if (model.getCounterSeconds() > 0) {
            countDownTimer = new CountDownTimer(model.getCounterSeconds() * 1000, 1000) {

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
        if (model.getDialog_type() == TYPE.SUCCESS_DIALOG || model.getDialog_type() == TYPE.ERROR_DIALOG) {
            setSuccessErrorDialogListeners();
        } else if (model.getDialog_type() == TYPE.CODE_DIALOG || model.getDialog_type() == TYPE.CONFIRMATION_DIALOG
                || model.getDialog_type() == TYPE.RATING_DIALOG || model.getDialog_type() == TYPE.MESSAGE_DIALOG) {
            getHeaderChildView(R.id.closeIV).setOnClickListener(this);
            if (model.getDialog_type() == TYPE.CODE_DIALOG) {
                setCodeDialogListeners();
            } else {
                getDialogAddedView(R.id.confirmButton).setOnClickListener(this);
                if (model.getDialog_type() == TYPE.CONFIRMATION_DIALOG || model.getDialog_type() == TYPE.RATING_DIALOG)
                    getDialogAddedView(R.id.cancelBtn).setOnClickListener(this);
            }
        } else {
            getHeaderChildView(R.id.closeIV).setOnClickListener(this);
        }
    }

    private void setSuccessErrorDialogListeners() {
        getDialogAddedView(R.id.closeIV).setOnClickListener(this);
        getDialogAddedView(R.id.confirmButton).setOnClickListener(this);
    }

    void setCodeDialogListeners() {
        getDialogAddedView(R.id.sendCode).setOnClickListener(this);
        getDialogAddedView(R.id.resendCode).setOnClickListener(this);
    }

    private void timeUp() {
        model.setTimeLeft(0);
        getDialogAddedView(R.id.sendCode).setClickable(false);
        getDialogAddedView(R.id.txtPinEntry).setEnabled(false);
        KeyboardUtil.getInstance().hideKeyboard(binding.getRoot());
        if (getContext() != null)
            getDialogAddedView(R.id.sendCode).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.carbon_grey_300));
        if (model.getCodeTypeListener() != null)
            model.getCodeTypeListener().onTimeUp(DialogPlus.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendCode)
            sendCode();
        else if (view.getId() == R.id.resendCode)
            handleResendCode();
        else if (view.getId() == R.id.confirmButton)
            onPositiveClicked();
        else if (view.getId() == R.id.cancelBtn || view.getId() == R.id.closeIV)
            onNegativeClicked();
    }

    private void onPositiveClicked() {
        if (model.getDialog_type() == TYPE.RATING_DIALOG && model.getRateListener() != null)
            model.getRateListener().onRateGiven(model.getRateValue(), this);
        if (model.getDialogActionListener() != null)
            model.getDialogActionListener().onPositive(this);
        else dismiss(true);
    }

    private void sendCode() {
        if (model.getCodeEntry() != null && model.getCodeEntry().length() == model.getCorrectCode().length()) {
            onCompleteCodeTyped();
        } else
            Toast.makeText(getActivity(), getString(R.string.dialog_incomplete_code_msg), Toast.LENGTH_SHORT).show();
    }

    private void onCompleteCodeTyped() {
        if (validateCode()) {
            onCorrect();
            cancelTimer();
            dismiss(true);
        } else {
            onWrong();
            showKeyboard(getDialogAddedView(R.id.txtPinEntry));
        }
    }

    private void onNegativeClicked() {
        if (model.getDialog_type() == TYPE.RATING_DIALOG && model.getRateListener() != null)
            model.getRateListener().onNegative(this);
        if (model.getDialogActionListener() != null)
            model.getDialogActionListener().onNegative(this);
        else dismiss(true);
    }


    private void handleResendCode() {
        if (model.getCodeTypeListener() != null)
            model.getCodeTypeListener().onResend(this);
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

    @Override
    protected Object getVariableValue() {
        return model;
    }

    @Override
    public int getBindingVariable() {
        return BR.model;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_plus;
    }

    /**
     * dialog_plus type will be indicated by one of the bellow integers
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
        int CONFIRMATION_DIALOG = 0;
        int CODE_DIALOG = 1;
        int MESSAGE_DIALOG = 2;
        int ERROR_DIALOG = 3;
        int SUCCESS_DIALOG = 4;
        int LIST_DIALOG = 5;
        int RATING_DIALOG = 6;
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

    public abstract static class DialogListListener {

        public void onNegative(DialogPlus dialogPlus) {
            dialogPlus.dismiss(true);
        }

        public void onItemClicked(String title, int index, DialogPlus dialogPlus) {
            dialogPlus.dismiss(true);
        }
    }

    public abstract static class DialogRateListener {

        public void onNegative(DialogPlus dialogPlus) {
            dialogPlus.dismiss(true);
        }

        public void onRateGiven(float rate, DialogPlus dialogPlus) {
            dialogPlus.dismiss(true);
        }
    }
}
