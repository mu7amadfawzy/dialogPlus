package com.dialog.plus.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

import com.dialog.plus.BR;
import com.dialog.plus.R;
import com.dialog.plus.databinding.LayoutMonthYearPickerDialogBinding;
import com.dialog.plus.utils.CommonUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 * Modified  by Fawzy & ALi
 */
public class DatePickerDialog extends BaseDialogFragment<LayoutMonthYearPickerDialogBinding> {


    DatePickerDialog(DialogPlusUiModel model) {
        this.model = model;
    }

    private String getTitle() {
        return model.isMonthDayPicker() ? model.getYearOfMonth() + ""
                : model.isDayPicker() ? CommonUtil.getInstance().getMonthName(model.getMonthOfDay()) + "\t" + model.getYearOfMonth()
                : getResources().getString(model.isMonthPicker() ? R.string.dialog_pick_month_title
                : model.isYearPicker() ? R.string.dialog_pick_year_title
                : model.isDayPicker() ? R.string.dialog_pick_day_title
                : R.string.dialog_pick_month_year_title);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderViews();
        setListeners();
        setPickers();
    }

    private void setListeners() {
        setOnConfirmClicked();
        setOnCloseClicked();
        setPickersListener();
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
            else if (model.isMonthDayPicker())
                model.getMonthDayPickerListener().onPicked(getMonthValue(), getDayValue());
            else if (model.isDatePicker())
                model.getDatePickerListener().onPicked(getYearValue(), getMonthValue(), getDayValue());
            dismiss(true);
        });
    }

    private void setPickersListener() {
        if (model.isDatePicker() || model.isMonthDayPicker())
            setMonthPickListener();
        if (model.isDatePicker() || model.isYearMonthPicker()) {
            setYearPickListener();
        }
    }

    private void setYearPickListener() {
        binding.yearPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            int selectedMonth = binding.monthPicker.getValue() > 0 ? binding.monthPicker.getValue() - 1 : binding.monthPicker.getValue();
            setPickerViews(binding.monthPicker, getMinMonth(), getMaxMonth(), selectedMonth, false);
            notifyValueChanged(binding.monthPicker);
        });
    }

    private void setMonthPickListener() {
        binding.monthPicker.setOnValueChangedListener((picker, oldVal, newMonth) -> {
            updateDaysPicker(getMinDay(), getMaxDay());
        });
    }

    private int getMinMonth() {
        int minMonth = model.getMinMonth();
        if (model.matchesMinCalendarYear(getYearValue()))
            minMonth = model.getMinCalendar().get(Calendar.MONTH) + 1;
        return minMonth;
    }

    private int getMaxMonth() {
        int maxMonth = model.getMaxMonth();
        if (model.matchesMaxCalendarYear(getYearValue()))
            maxMonth = model.getMaxCalendar().get(Calendar.MONTH) + 1;
        return maxMonth;
    }

    private int getMinDay() {
        int minDay = model.getMinDay();
        if (model.matchesMinCalendarYear(getYearValue()) && model.matchesMinCalendarMonth(getMonthValue()))
            minDay = model.getMinCalendar().get(Calendar.DAY_OF_MONTH);
        return minDay;
    }

    private int getMaxDay() {
        if (model.matchesMaxCalendarYear(getYearValue()) && model.matchesMaxCalendarMonth(getMonthValue())) {
            return model.getMaxCalendar().get(Calendar.DAY_OF_MONTH);
        }
        return CommonUtil.getInstance().getMaxDayInMonth(model.getYearOfMonth(), model.getMonthOfDay());
    }


    private void updateDaysPicker(int minDay, int maxDay) {
        int selectedDay = binding.dayPicker.getValue();
        setPickerViews(binding.dayPicker, minDay, maxDay, selectedDay, false);
        binding.dayPicker.setVerticalScrollbarPosition(selectedDay);
    }

    private int getPickerValue() {
        if (model.isMonthPicker())
            return getMonthValue();
        if (model.isDayPicker())
            return getDayValue();
        return getYearValue();
    }

    private int getYearValue() {
        return model.showYearPicker() ? binding.yearPicker.getValue() : model.getYearOfMonth();
    }

    private int getMonthValue() {
        return model.showMonthPicker() ? binding.monthPicker.getValue() : model.getMonthOfDay();
    }

    private int getDayValue() {
        return binding.dayPicker.getValue();
    }

    private void renderViews() {
        model.setTitle(model.getTitle() == null ? getTitle() : model.getTitle());
    }

    private void setPickers() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        setYearPicker(cal);
        setMonthPicker(cal);
        setDayPicker(cal);
    }

    private void setYearPicker(Calendar cal) {
        if (model.isYearPicker() || model.isYearMonthPicker() || model.isDatePicker())
            setPickerViews(binding.yearPicker, model.getMinYear(), model.getMaxYear(), cal.get(Calendar.YEAR), false);
    }

    private void setMonthPicker(Calendar cal) {
        if (model.isMonthPicker() || model.isYearMonthPicker() || model.isMonthDayPicker() || model.isDatePicker()) {
            setMonthNamesPicker();
            setPickerViews(binding.monthPicker, getMinMonth(), getMaxMonth(), cal.get(Calendar.MONTH) + 1, false);
        }
    }

    private void setDayPicker(Calendar cal) {
        if (model.isDayPicker() || model.isMonthDayPicker() || model.isDatePicker()) {
            int maxDay = getMaxDay();
            int minDay = getMinDay();
            setPickerViews(binding.dayPicker, minDay, maxDay, cal.get(Calendar.DAY_OF_MONTH), model.getMinCalendar() != null);
        }
    }

    private void setMonthNamesPicker() {
        if (model.isShowMonthName()) {
            binding.monthPicker.setFormatter(value -> CommonUtil.getInstance().getMonthName(value - 1));
            notifyValueChanged(binding.monthPicker);
        }
    }

    private void setPickerViews(NumberPicker picker, int min, int max, int selectedValue, boolean notifyChange) {
        picker.setMinValue(min);
        picker.setMaxValue(max);
        picker.setValue(selectedValue);
        picker.setWrapSelectorWheel(true);
        if (notifyChange)
            notifyValueChanged(picker);
    }

    private void notifyValueChanged(NumberPicker picker) {
        try {
            Method method = picker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
            method.setAccessible(true);
            method.invoke(binding.monthPicker, true);
        } catch (Exception e) {
            Log.e(getClass().getName(), "couldn't find method changeValueByOne in class NumberPicker to notify selected item");
        }
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
        int MONTH_DAY = 4;
        int DATE = 5;
    }

    public interface PickerListener {
        void onPicked(int pickedYear);
    }

    public interface YearMonthPickerListener {
        void onPicked(int pickedYear, int pickedMonth);
    }

    public interface MonthDayPickerListener {
        void onPicked(int pickedMonth, int pickedDay);
    }

    public interface DatePickerListener {
        void onPicked(int pickedYear, int pickedMonth, int pickedDay);
    }
}
