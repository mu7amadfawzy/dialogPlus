package com.sample.dialogSample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dialog.plus.data.CountryRepo;
import com.dialog.plus.ui.CountryDataModel;
import com.dialog.plus.ui.DialogPlus;
import com.dialog.plus.ui.DialogPlusBuilder;
import com.dialog.plus.ui.MultiOptionsDialog;
import com.sample.dialogSample.utils.LocalityUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocality();
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
    }

    private void setLocality() {
        LocalityUtil.getInstance().setLocality(this, isArabic() ? "ar" : "en");
    }

    private boolean isArabic() {
        return getSharedPreferences("sample", MODE_PRIVATE).getBoolean("isArabic", false);
    }

    public void onClickedMessageDialog(View view) {
        new DialogPlusBuilder("Message Dialog", "message dialog_plus sample\n Welcome Back")
                //@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBgColor
                .setTexts("alright").blurBackground()
                .setBackgrounds(R.color.colorPrimary, R.color.colorAccent)
                .buildMessageDialog(new DialogListener() {//implement functions
                })
                .show(this.getSupportFragmentManager(), "Message Dialog");
    }

    public void onClickedMessageDialogWithImage(View view) {
        new DialogPlusBuilder("Image Message Dialog", "message dialog_plus sample\n Welcome Back")
                .setTexts("alright").blurBackground()
                .setBackgrounds(R.color.colorPrimary, R.color.colorAccent)
                .buildMessageDialog(R.drawable.send_anim
                        , new DialogListener() {//implement functions
                        }).show(this.getSupportFragmentManager(), "Image Message Dialog");
    }

    public void onClickedConfirmation(View view) {
        new DialogPlusBuilder("Confirmation Dialog", "confirmation dialog_plus message content ...")
                .setTexts("confirm", "cancel").blurBackground()
                .setBackgroundColors(R.color.colorPrimary, R.color.white, R.color.colorPrimary)
                .buildConfirmationDialog(false, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Confirmation Dialog");
    }

    public void onClickedConfirmation2(View view) {
        new DialogPlusBuilder("Confirmation Dialog option two interface", "Confirmation Dialog with separated action buttons ...")
                .setTexts("confirm", "cancel").blurBackground()
                .setBackgroundColors(R.color.colorPrimary, R.color.white, R.color.colorPrimary)
                .setSecondaryTextColor(R.color.colorPrimary)
                .buildConfirmationDialog(true, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "dialog_plus");
    }

    public void onClickedSuccessDialog(View view) {
        new DialogPlusBuilder("Success message content..")
                .setSuccessDialog("Cool").blurBackground()
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .build(new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Success Dialog");
    }

    public void onClickedErrorDialog(View view) {
        new DialogPlusBuilder("Error Dialog content message")
                .setErrorDialog("Peace").blurBackground()
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .build(new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Error Dialog");
    }

    public void onClickedConfirmCode(View view) {
        new DialogPlusBuilder("Code Dialog", "code dialog_plus sample with send enabled, resend enabled and counter 10 seconds")
                .setTexts("Confirm").blurBackground()
                .setDialogCodeTextColor(getResources().getColor(R.color.colorPrimaryDark))
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .buildCodeDialog("12345", 60, true, true, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Code Dialog");
    }

    public void onClickedConfirmCode2(View view) {
        new DialogPlusBuilder("Code Dialog", "code dialog_plus sample without send enabled and zero seconds counter.")
                .setDialogCodeTextColor(getResources().getColor(R.color.colorAccent))
                .setBackgroundColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimary)
                .setHeaderBgDrawable(R.drawable.bg_header).blurBackground()
                .buildCodeDialog("123", 50, false, true, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "dialog_plus");
    }

    public void onMultiOptionsDialogClicked(View view) {
        new DialogPlusBuilder().setTitle("Multi Options Dialog Sample Title")
                .setHeaderBgColor(R.color.dialogTransparent).setHeaderTextColor(R.color.black)
                .hideCloseIcon().blurBackground()
                .buildMultiOptionsDialog(getOptions()
                        , new MultiOptionsDialog.ActionListener() {
                            @Override
                            public void onActionClicked(String clickedOption) {
                                onOptionSelected(clickedOption);
                            }
                        }).show(this.getSupportFragmentManager(), "Multi Options Dialog");
    }

    private void onOptionSelected(String clickedOption) {
        switch (clickedOption) {
            case "Arabic":
                onSelectArabic();
                Toast.makeText(MainActivity.this, clickedOption, Toast.LENGTH_SHORT).show();
                break;
            case "English":
                onSelectEnglish();
                Toast.makeText(MainActivity.this, clickedOption, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this, "Peace dude", Toast.LENGTH_SHORT).show();
        }
    }


    public void onListDialogClicked(View view) {
        new DialogPlusBuilder().setTitle("List Dialog").blurBackground()
                .buildListDialog(getListItems(), true, new DialogPlus.DialogListListener() {
                    @Override
                    public void onItemClicked(String title, int index, DialogPlus dialogPlus) {
                        super.onItemClicked(title, index, dialogPlus);
                        Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                    }
                })
                .show(this.getSupportFragmentManager(), "List Dialog");
    }

    public void onCountriesListDialogClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .buildCountriesListDialog(true, new DialogPlus.CountriesDialogListener() {
                    @Override
                    public void onItemClicked(CountryDataModel countryDataModel, DialogPlus dialogPlus) {
                        super.onItemClicked(countryDataModel, dialogPlus);
                        String arabicStr = new CountryRepo(MainActivity.this).getCounty("ar", countryDataModel.getCode()).getName();
                        Toast.makeText(MainActivity.this, "country:" + countryDataModel.getName()
                                + " ,arabic name: " + arabicStr, Toast.LENGTH_SHORT).show();
                    }
                })
                .show(this.getSupportFragmentManager(), "Countries List Dialog");
    }

    public void onYearDialogClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .setTitle("Please pick a Year").setHeaderBgColor(R.color.white).setHeaderTextColor(R.color.colorPrimaryDark)
                .buildYearPickerDialog(pickedYear ->
                        Toast.makeText(this, "picked year: " + pickedYear, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Year Picker");
    }

    public void onMonthDialogClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .setHeaderBgColor(R.color.white).setHeaderTextColor(R.color.colorPrimaryDark)
                .buildMonthPickerDialog(pickedYear ->
                        Toast.makeText(this, "picked month: " + pickedYear, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Month Picker");
    }

    public void onDaysDialogClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .setHeaderBgColor(R.color.white).setHeaderTextColor(R.color.colorPrimaryDark)
                .buildDayPickerDialog(2, 2019, 1, pickedDay ->
                        Toast.makeText(this, "picked day: " + pickedDay, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Day Picker");
    }

    public void onYearMonthDialogClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .setHeaderBgColor(R.color.white).setHeaderTextColor(R.color.colorPrimaryDark)
                .buildMonthYearPickerDialog(Calendar.getInstance(), true,//remove true and it will be minDate
                        (pickedYear, pickedMonth) -> Toast.makeText(this, "picked year: " + pickedYear + " ,picked month: " + pickedMonth, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Year/Month Picker");
    }

    public void onMonthDayDialogClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .setHeaderBgColor(R.color.white).setHeaderTextColor(R.color.colorPrimaryDark)
                .buildMonthDayPickerDialog(Calendar.getInstance(), true,//remove true and it will be minDate
                        (pickedMonth, pickedDay) -> Toast.makeText(this, "picked day: " + pickedDay + " ,picked month: " + pickedMonth, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Month Day Picker");
    }

    public void onDateDialogClicked(View view) {
        Calendar afterYearCalendar = Calendar.getInstance();
        afterYearCalendar.set(Calendar.getInstance().get(Calendar.YEAR) + 1, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        new DialogPlusBuilder().blurBackground().setTitle("Pick a date")
                .setHeaderBgColor(R.color.white).setHeaderTextColor(R.color.colorPrimaryDark)
                .buildDatePickerDialog(Calendar.getInstance(), afterYearCalendar
                        , (pickedYear, pickedMonth, pickedDay) -> Toast.makeText(this, pickedYear + "-" + pickedMonth + "-" + pickedDay, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Date Picker");
    }

    public void onClickedRating(View view) {
        new DialogPlusBuilder("Rating Dialog", "Rate dialog_plus message content ...")
                .setTexts("rate", "cancel").blurBackground()
                .buildRatingDialog(1.7f, false
                        , new DialogPlus.DialogRateListener() {
                            @Override
                            public void onRateGiven(float rate, DialogPlus dialogPlus) {
                                Toast.makeText(MainActivity.this, "rated with " + rate, Toast.LENGTH_SHORT).show();
                            }
                        })
                .show(this.getSupportFragmentManager(), "Rating Dialog");
    }

    public void onCustomLayoutClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .buildCustomLayoutDialog(R.layout.custom_layout)
                .show(this.getSupportFragmentManager(), "Custom Layout Dialog");
    }

    public void onShowViewClicked(View view) {
        new DialogPlusBuilder().blurBackground()
                .buildCustomLayoutDialog(findViewById(R.id.viewToShow))
                .show(this.getSupportFragmentManager(), "Custom Layout Dialog");
    }

    private List<String> getListItems() {
        List<String> dialogItemDMS = new ArrayList<>();
        dialogItemDMS.add("List Item Title 1");
        dialogItemDMS.add(("List Item Title 2"));
        dialogItemDMS.add(("List Item Title 3"));
        dialogItemDMS.add(("List Item Title 4"));
        dialogItemDMS.add(("List Item Title 5"));
        dialogItemDMS.add(("List Item Title 6"));
        dialogItemDMS.add(("List Item Title 7"));
        dialogItemDMS.add(("List Item Title 8"));
        dialogItemDMS.add(("List Item Title 9"));
        dialogItemDMS.add(("List Item Title 10"));
        dialogItemDMS.add(("List Item Title 11"));
        dialogItemDMS.add(("List Item Title 12"));
        dialogItemDMS.add(("List Item Title 13"));
        dialogItemDMS.add(("List Item Title 14"));
        dialogItemDMS.add(("List Item Title 15"));
        dialogItemDMS.add(("List Item Title 16"));
        return dialogItemDMS;
    }

    private List<String> getOptions() {
        String[] titles = {"Arabic", "English", "Just Kidding"};
        return new ArrayList<>(Arrays.asList(titles));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                onMultiOptionsDialogClicked(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSelectArabic() {
        setAppLanguageArabic();
        restart();
    }

    private void setAppLanguageArabic() {
        getSharedPreferences("sample", MODE_PRIVATE).edit().putBoolean("isArabic", true).apply();
    }

    public void onSelectEnglish() {
        getSharedPreferences("sample", MODE_PRIVATE).edit().putBoolean("isArabic", false).apply();
        restart();
    }

    public void restart() {
        restartMainActivity();
    }

    protected void restartMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    private class DialogListener extends DialogPlus.DialogActionListener {
        @Override
        public void onPositive(DialogPlus dialogPlus) {
            Toast.makeText(MainActivity.this, "onPositive", Toast.LENGTH_SHORT).show();
            dialogPlus.dismiss();
        }

        @Override
        public void onNegative(DialogPlus dialogPlus) {
            super.onNegative(dialogPlus);
            Toast.makeText(MainActivity.this, "onNegative", Toast.LENGTH_SHORT).show();
        }
    }
}

