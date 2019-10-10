package com.dialog.plus.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.dialog.plus.BR;
import com.dialog.plus.R;
import com.dialog.plus.databinding.LayoutMonthYearPickerDialogBinding;
import com.dialog.plus.utils.CommonUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 * Modified  by Fawzy & ALi
 */
public class MonthYearPickerDialog extends BaseDialogFragment<LayoutMonthYearPickerDialogBinding> {
    private int MAX_MONTH = 12, MIN_MONTH = 1;
    @ColorRes
    private int dialogPositiveBgColor = R.color.dialogPositiveBgColor, titleTextColor = R.color.titleTextColor;

    MonthYearPickerDialog(DialogPlusUiModel model) {
        this.model = model;
    }

    public MonthYearPickerDialog getYearPicker(PickerListener listener) {
        model.setPickerListener(listener);
        return this;
    }

    public MonthYearPickerDialog getMonthPicker(PickerListener listener) {
        model.setPickerListener(listener);
        return this;
    }

    private String getTitle() {
        return getResources().getString(model.isMonthPicker() ? R.string.dialog_pick_month_title
                : model.isYearPicker() ? R.string.dialog_pick_year_title : R.string.dialog_pick_month_year_title);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderViews();
        setListeners();
        setMonthOrYear();
    }

    private void setListeners() {
        setOnConfirmClicked();
        setOnCloseClicked();
        if (model.isDatePicker())
            setMonthPickerListener();
    }

    private void setOnCloseClicked() {
        binding.headerLayout.closeIV.setOnClickListener(v -> dismiss(true));
    }

    private void setOnConfirmClicked() {
        binding.confirmButton.setOnClickListener(v -> {
            if (model.isMonthPicker() | model.isYearPicker() | model.isDayPicker())
                model.getPickerListener().onPicked(getPickerValue());
            else if (model.isYearMonthPicker())
                model.getYearMonthPickerListener().onPicked(getYearValue(), getMonthValue());
            else if (model.isDatePicker())
                model.getDatePickerListener().onPicked(getYearValue(), getMonthValue(), getDayValue());
            dismiss(true);
        });
    }

    private void setMonthPickerListener() {
        NumberPicker.OnValueChangeListener listener = (picker, oldVal, newVal) -> {
            int daysScrollPos = binding.dayPicker.getVerticalScrollbarPosition();
            int maxDay = CommonUtil.getInstance().getDaysInMonth(getYearValue(), getMonthValue() - 1);
            setPickerViews(binding.dayPicker, model.getMinDay(), maxDay, Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_MONTH));
            binding.dayPicker.setVerticalScrollbarPosition(daysScrollPos);
        };
        binding.monthPicker.setOnValueChangedListener(listener);
        binding.yearPicker.setOnValueChangedListener(listener);
    }

    private int getPickerValue() {
        if (model.isMonthPicker())
            return getMonthValue();
        if (model.isDayPicker())
            return getDayValue();
        return getYearValue();
    }

    private int getYearValue() {
        return binding.yearPicker.getValue();
    }

    private int getMonthValue() {
        return binding.monthPicker.getValue();
    }

    private int getDayValue() {
        return binding.dayPicker.getValue();
    }

    private void renderViews() {
        model.set(getTitle(), getBackgroundColor(), getHeaderTextColor());
    }

    private void setMonthOrYear() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        if (model.isMonthPicker() || model.isYearMonthPicker() || model.isDatePicker())
            setPickerViews(binding.monthPicker, MIN_MONTH, MAX_MONTH, cal.get(Calendar.MONTH) + 1);
        if (model.isYearPicker() || model.isYearMonthPicker() || model.isDatePicker())
            setPickerViews(binding.yearPicker, model.getMinYear(), model.getMaxYear(), cal.get(Calendar.YEAR));
        if (model.isDayPicker() || model.isDatePicker()) {
            int maxDay = CommonUtil.getInstance().getDaysInMonth(model.getMaxYear(), model.getMonthOfDays());
            setPickerViews(binding.dayPicker, model.getMinDay(), maxDay, Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_MONTH));
        }
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
    protected View getDialogParentView() {
        return binding.dialogParentView;
    }

    @Override
    protected View getDialogContentView() {
        return binding.dialogContainer;
    }

    @Override
    public int getBindingVariable() {
        return BR.model;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_month_year_picker_dialog;
    }

    /**
     * dialog_plus type will be indicated by one of the bellow integers
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
        int YEAR = 0;
        int MONTH = 1;
        int DAY = 2;
        int YEAR_MONTH = 3;
        int DATE = 4;
    }

    public interface PickerListener {
        void onPicked(int pickedYear);
    }

    public interface YearMonthPickerListener {
        void onPicked(int pickedYear, int pickedMonth);
    }

    public interface DatePickerListener {
        void onPicked(int pickedYear, int pickedMonth, int pickedDay);
    }
}
