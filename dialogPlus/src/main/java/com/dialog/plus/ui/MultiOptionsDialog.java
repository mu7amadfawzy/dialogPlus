package com.dialog.plus.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.dialog.plus.R;
import com.dialog.plus.databinding.MultiOptionsDialogBinding;

import java.util.List;

/**
 * Created by fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 **/
public class MultiOptionsDialog extends BaseDialogFragment<MultiOptionsDialogBinding> {
    private DialogPlusUiModel model;

    MultiOptionsDialog(DialogPlusUiModel uiModel) {
        this.model = uiModel;
    }

    MultiOptionsDialog(String title, List<String> optionsTitle, ActionListener actionListener) {
        model.setMultiOptionsDialogListener(actionListener);
        model.setDialogListItems(optionsTitle);
        model.setTitle(title);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setCallback(getActionListener());
        binding.setModel(model);
        setListeners();
    }

    private ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void onActionClicked(String clickedOption) {
                model.getMultiOptionsDialogListener().onActionClicked(clickedOption);
                dismiss(true);
            }
        };
    }

    @Override
    protected Object getVariableValue() {
        return null;
    }

    private void setListeners() {
        binding.cancelBtn.setOnClickListener(v -> onCloseClicked());
    }

    private void onCloseClicked() {
        if (model.getMultiOptionsDialogListener() != null)
            model.getMultiOptionsDialogListener().onCancel(this);
        dismiss(true);
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.multi_options_dialog;
    }

    public abstract static class ActionListener {
        public void onCancel(MultiOptionsDialog multiOptionsDialog) {

        }

        public abstract void onActionClicked(String clickedOption);
    }
}
