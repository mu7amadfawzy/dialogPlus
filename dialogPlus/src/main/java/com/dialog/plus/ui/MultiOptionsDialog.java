package com.dialog.plus.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.dialog.plus.BR;
import com.dialog.plus.R;
import com.dialog.plus.databinding.MultiOptionsDialogBinding;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 **/
public class MultiOptionsDialog extends BaseDialogFragment<MultiOptionsDialogBinding> {

    MultiOptionsDialog(DialogPlusUiModel uiModel) {
        this.model = uiModel;
    }

    MultiOptionsDialog(String title, List<String> optionsTitle, ActionListener actionListener) {
        model.setMultiOptionsDialogListener(actionListener);
        model.setDialogListItems(optionsTitle);
        model.setTitle(title);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setCallback(getActionListener());
        setListeners();
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void onActionClicked(String clickedOption, int position) {
                model.getMultiOptionsDialogListener().onActionClicked(clickedOption, position);
                dismiss(true);
            }
        };
    }

    private void setListeners() {
        binding.cancelBtn.setOnClickListener(v -> onCloseClicked());
        binding.headerLayout.closeIV.setOnClickListener(v -> dismiss(true));
    }

    private void onCloseClicked() {
        if (model.getMultiOptionsDialogListener() != null)
            model.getMultiOptionsDialogListener().onCancel(this);
        dismiss(true);
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
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.multi_options_dialog;
    }

    @Override
    protected View getDialogParentView() {
        return binding.dialogParentView;
    }

    @Override
    protected View getDialogContentView() {
        return binding.dialogContainer;
    }

    public abstract static class ActionListener {
        public void onCancel(MultiOptionsDialog multiOptionsDialog) {

        }

        public abstract void onActionClicked(String clickedOption, int position);
    }
}
