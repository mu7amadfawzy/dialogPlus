package com.dialog.plus.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dialog.plus.BR;
import com.dialog.plus.R;
import com.dialog.plus.databinding.LayoutMonthYearPickerDialogBinding;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class MonthYearPickerDialog extends BaseDialogFragment<LayoutMonthYearPickerDialogBinding> {
    private final int MIN_YEAR = 1970;
    private int MAX_YEAR;
    private int MAX_MONTH = 12;
    private int MIN_MONTH = 1;
    private PickerListener listener;
    private boolean isMonthPicker;
    private DialogUiModel model;
    @ColorRes
    private int dialogPositiveBgColor = R.color.dialogPositiveBgColor, titleTextColor = R.color.titleTextColor;

    public MonthYearPickerDialog getYearPicker(PickerListener listener) {
        return getYearPicker(Calendar.getInstance().get(Calendar.YEAR), listener);
    }

    public MonthYearPickerDialog getYearPicker(int maxYear, PickerListener listener) {
        isMonthPicker = false;
        this.listener = listener;
        this.MAX_YEAR = maxYear;
        return this;
    }

    public MonthYearPickerDialog getMonthPicker(PickerListener listener) {
        isMonthPicker = true;
        this.listener = listener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new DialogUiModel(getTitle(), getBackgroundColor(), getHeaderTextColor());
    }

    private String getTitle() {
        return getResources().getString(isMonthPicker ? R.string.dialog_pick_month_title : R.string.dialog_pick_year_title);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderViews();
        setMonthOrYear();
        setListeners();
    }

    private void setListeners() {
        binding.confirmButton.setOnClickListener(v -> {
            listener.onPicked(binding.picker.getValue());
            dismiss(true);
        });
        binding.headerLayout.closeIV.setOnClickListener(v -> dismiss(true));
    }

    private void renderViews() {
        binding.setIsMonthPicker(isMonthPicker);
    }

    private void setMonthOrYear() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        if (isMonthPicker)
            setPickerViews(binding.picker, MIN_MONTH, MAX_MONTH, cal.get(Calendar.MONTH) + 1);
        else
            setPickerViews(binding.picker, MIN_YEAR, MAX_YEAR, cal.get(Calendar.YEAR));
    }

    private void setPickerViews(NumberPicker picker, int min, int max, int selectedValue) {
        picker.setMinValue(min);
        picker.setMaxValue(max);
        picker.setValue(selectedValue);
        picker.setWrapSelectorWheel(false);
    }

    private int getBackgroundColor() {
        return dialogPositiveBgColor;
    }

    private int getHeaderTextColor() {
        return titleTextColor;
    }

    public MonthYearPickerDialog setDialogPositiveBgColor(@ColorRes int dialogPositiveBgColor) {
        this.dialogPositiveBgColor = dialogPositiveBgColor;
        model.setPositiveBgColor(dialogPositiveBgColor);
        return this;
    }

    public MonthYearPickerDialog setTitleTextColor(@ColorRes int titleTextColor) {
        this.titleTextColor = titleTextColor;
        model.setHeaderTextColor(titleTextColor);
        return this;
    }

    public MonthYearPickerDialog setMAX_MONTH(int MAX_MONTH) {
        this.MAX_MONTH = MAX_MONTH;
        return this;
    }

    public MonthYearPickerDialog setMIN_MONTH(int MIN_MONTH) {
        this.MIN_MONTH = MIN_MONTH;
        return this;
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
        return R.layout.layout_month_year_picker_dialog;
    }

    public interface PickerListener {
        void onPicked(int pickedYear);

    }
}
