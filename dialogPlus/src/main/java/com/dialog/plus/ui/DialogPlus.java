package com.dialog.plus.ui;

import android.graphics.Color;
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
import com.daimajia.androidanimations.library.YoYo;
import com.dialog.plus.R;
import com.dialog.plus.databinding.DialogPlusBinding;
import com.dialog.plus.utils.KeyboardUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class DialogPlus extends BaseModelDialogFragment<DialogPlusBinding> implements View.OnClickListener {
    /**
     * Listeners
     */
    private CodeTypeListener codeTypeListener;
    private DialogActionListener dialogActionListener;
    private DialogListListener dialogListListener;
    private DialogRateListener rateListener;

    private List<String> listDialogItems = new ArrayList<>();
    private int counterSeconds;
    private CountDownTimer countDownTimer;


    public DialogPlus() {
        this(null, null);
    }

    public DialogPlus(String content) {
        this(null, content);
    }

    public DialogPlus(String title, String content) {
        this(TYPE.MESSAGE_DIALOG, title, content);
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
        setConfirmDialog(correct_code, withSend, withResend, counterSeconds, codeTextColor, codeTypeListener).setDialog_type(TYPE.CODE_DIALOG);
        return this;
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
        setSeparateActionButtons(separateActionButtons).setDialogActionListener(actionClicked).setDialog_type(TYPE.CONFIRMATION_DIALOG).setTexts(positiveText, negativeText);
        return this;
    }

    /**
     * Sets a message dialog_plus interface
     */
    public DialogPlus setMessageDialog(DialogActionListener actionClicked) {
        return setMessageDialog(null, actionClicked);
    }

    public DialogPlus setMessageDialog(String positiveText, DialogActionListener actionClicked) {
        setDialogActionListener(actionClicked).setMessageDialog(positiveText);
        return this;
    }

    /**
     * Sets a list dialog_plus interface
     */
    public DialogPlus setListDialog(String title, List<String> listItems, DialogListListener actionClicked) {
        setListItems(listItems).setDialogListListener(actionClicked).setTitle(title).setDialog_type(TYPE.LIST_DIALOG);
        return this;
    }

    private DialogPlus setListItems(List<String> listItems) {
        if (listDialogItems.size() > 0) listDialogItems.clear();
        listDialogItems = listItems;
        return this;
    }

    /**
     * Sets an Error dialog_plus interface
     */
    public DialogPlus setErrorDialog(DialogActionListener dialogActionListener) {
        return setErrorDialog(null, dialogActionListener);
    }

    public DialogPlus setErrorDialog(String positiveText, DialogActionListener dialogActionListener) {
        setDialogActionListener(dialogActionListener).setDialog_type(TYPE.ERROR_DIALOG).setTexts(positiveText);
        return this;
    }


    /**
     * Sets a Rating dialog_plus interface(with positive and negative actions)
     */
    public DialogPlus setRatingDialog(DialogRateListener rateListener) {
        return setRatingDialog(2, null, null, rateListener);
    }

    public DialogPlus setRatingDialog(float initialRate, String positiveText, String negativeText, DialogRateListener rateListener) {
        return setRatingDialog(initialRate, positiveText, negativeText, false, rateListener);
    }

    public DialogPlus setRatingDialog(float initialRate, String positiveText, String negativeText, boolean separateActionButtons, DialogRateListener rateListener) {
        setInitialRate(initialRate).setSeparateActionButtons(separateActionButtons).setRateListener(rateListener).setDialog_type(TYPE.RATING_DIALOG).setTexts(positiveText, negativeText);
        return this;
    }

    /**
     * Sets a Success dialog_plus interface
     */
    public DialogPlus setSuccessDialog(DialogActionListener dialogActionListener) {
        return setSuccessDialog(null, dialogActionListener);
    }

    public DialogPlus setSuccessDialog(String positiveText, DialogActionListener dialogActionListener) {
        setDialogActionListener(dialogActionListener).setDialog_type(TYPE.SUCCESS_DIALOG).setTexts(positiveText);
        return this;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUiModelData();
        renderView();
        initViews();
        setCounter();
        setListeners();
    }

    private void setUiModelData() {
        updateModelTexts();
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
        ListDialogAdapter listDialogAdapter = new ListDialogAdapter(this, listDialogItems, dialogListListener);
        ((RecyclerView) getDialogAddedView(R.id.recycler)).setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        ((RecyclerView) getDialogAddedView(R.id.recycler)).setAdapter(listDialogAdapter);
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
        if (codeTypeListener != null)
            codeTypeListener.onSuccess(this);
        dismiss(true);
    }

    private void onWrong() {
        setErrorTextColor();
        if (codeTypeListener != null)
            codeTypeListener.onWrongCode(this);
        shakeView((EditText) getDialogAddedView(R.id.txtPinEntry));
    }

    private void setErrorTextColor() {
        ((PinEntryEditText) getDialogAddedView(R.id.txtPinEntry)).setTextColor(Color.RED);
        ((PinEntryEditText) getDialogAddedView(R.id.txtPinEntry)).getPaint().setColor(Color.RED);
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
                    getDialogAddedView(R.id.cancelButton).setOnClickListener(this);
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
        if (model.getDialog_type() == TYPE.RATING_DIALOG && rateListener != null)
            rateListener.onRateGiven(model.getRateValue(), this);
        if (dialogActionListener != null)
            dialogActionListener.onPositive(this);
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
        if (model.getDialog_type() == TYPE.RATING_DIALOG && rateListener != null)
            rateListener.onNegative(this);
        if (dialogActionListener != null)
            dialogActionListener.onNegative(this);
        else dismiss(true);
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
        setDialog_type(dialog_type);
        model.setTitle(title);
        model.setContent(content);
        setBackgroundColors(R.color.dialogPositiveBgColor, R.color.dialogNegativeBgColor, R.color.dialogPositiveBgColor);
        setTextColors(R.color.dialogPositiveTextColor, R.color.dialogNegativeTextColor, R.color.dialogPositiveTextColor);
        return this;
    }

    private void set(String correct_code, boolean withSend, boolean withResend, int counterSeconds, @ColorInt int codeTextColor, CodeTypeListener codeTypeListener) {
        model.setCorrectCode(correct_code);
        this.counterSeconds = counterSeconds;
        model.setWithSend(withSend);
        model.setWithResend(withResend);
        model.setWithCounter(counterSeconds > 0);
        model.setDialogCodeTextColor(codeTextColor);
    }

    private void updateModelTexts() {
        model.setTypeMessage(model.getDialog_type() == TYPE.MESSAGE_DIALOG);
        model.setTimeLeft(counterSeconds);
    }


    public DialogPlus setCodeTypeListener(CodeTypeListener codeTypeListener) {
        this.codeTypeListener = codeTypeListener;
        return this;
    }

    public DialogPlus setDialogActionListener(DialogActionListener dialogActionListener) {
        this.dialogActionListener = dialogActionListener;
        return this;
    }

    public DialogPlus setInitialRate(float initialRate) {
        model.setRateValue(initialRate);
        return this;
    }

    public DialogPlus setRateListener(DialogRateListener rateListener) {
        this.rateListener = rateListener;
        return this;
    }

    public DialogPlus setDialogListListener(DialogListListener dialogListListener) {
        this.dialogListListener = dialogListListener;
        return this;
    }

    private DialogPlus setSeparateActionButtons(boolean separateActionButtons) {
        model.setSeparateActionButtons(separateActionButtons);
        return this;
    }

    @Override
    protected Object getVariableValue() {
        return model;
    }

    @Override
    public int getBindingVariable() {
        return com.dialog.plus.BR.model;
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
