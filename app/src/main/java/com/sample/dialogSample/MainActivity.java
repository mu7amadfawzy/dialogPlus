package com.sample.dialogSample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dialog.plus.ui.DialogPlus;
import com.dialog.plus.ui.DialogPlusBuilder;
import com.dialog.plus.ui.MultiOptionsDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickedMessageDialog(View view) {
        new DialogPlusBuilder("Message Dialog", "message dialog_plus sample\n Welcome Back")
                //@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBgColor
                .setTexts("alright")
                .setBackgrounds(R.color.colorPrimary, R.color.colorAccent)
                .buildMessageDialog(new DialogListener() {//implement functions
                })
                .show(this.getSupportFragmentManager(), "Message Dialog");
    }

    public void onClickedMessageDialogWithImage(View view) {
        new DialogPlusBuilder("Message Dialog", "message dialog_plus sample\n Welcome Back")
                //@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBgColor
                .setTexts("alright")
                .setBackgrounds(R.color.colorPrimary, R.color.colorAccent)
                .buildMessageDialog(R.drawable.send_anim, new DialogListener() {//implement functions
                })
                .show(this.getSupportFragmentManager(), "Message Dialog");
    }

    public void onClickedConfirmation(View view) {
        new DialogPlusBuilder("Confirmation Dialog", "confirmation dialog_plus message content ...")
                .setTexts("confirm", "cancel")
                .setBackgroundColors(R.color.colorPrimary, R.color.white, R.color.colorPrimary)
                .buildConfirmationDialog(false, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Confirmation Dialog");
    }

    public void onClickedConfirmation2(View view) {
        new DialogPlusBuilder("Confirmation Dialog option two interface", "Confirmation Dialog with separated action buttons ...")
                .setTexts("confirm", "cancel")
                .setBackgroundColors(R.color.colorPrimary, R.color.white, R.color.colorPrimary)
                .setSecondaryTextColor(R.color.colorPrimary)
                .buildConfirmationDialog(true, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "dialog_plus");
    }

    public void onClickedSuccessDialog(View view) {
        new DialogPlusBuilder("Success message content..")
                .setSuccessDialog("Cool")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .build(new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Success Dialog");
    }

    public void onClickedErrorDialog(View view) {
        new DialogPlusBuilder("Error Dialog content message")
                .setErrorDialog("Peace")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .build(new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Error Dialog");
    }

    public void onClickedConfirmCode(View view) {
        new DialogPlusBuilder("Code Dialog", "code dialog_plus sample with send enabled, resend enabled and counter 10 seconds")
                .setTexts("Confirm")
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
                .setHeaderBgDrawable(R.drawable.bg_header)
                .buildCodeDialog("123", 50, false, true, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "dialog_plus");
    }

    public void onMultiOptionsDialogClicked(View view) {
        new DialogPlusBuilder().setTitle("Multi Options Dialog Sample Title")
                .setHeaderBgColor(R.color.dialogTransparent).setHeaderTextColor(R.color.black)
                .buildMultiOptionsDialog(getOptions()
                        , new MultiOptionsDialog.ActionListener() {
                            @Override
                            public void onActionClicked(String clickedOption) {
                                Toast.makeText(MainActivity.this, clickedOption, Toast.LENGTH_SHORT).show();
                            }
                        }).show(this.getSupportFragmentManager(), "Multi Options Dialog");
    }


    public void onListDialogClicked(View view) {
        new DialogPlusBuilder().setTitle("List Dialog")
                .buildListDialog(getListItems(), new DialogPlus.DialogListListener() {
                    @Override
                    public void onItemClicked(String title, int index, DialogPlus dialogPlus) {
                        super.onItemClicked(title, index, dialogPlus);
                        Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                    }
                })
                .show(this.getSupportFragmentManager(), "List Dialog");
    }

    public void onMonthYearDialogClicked(View view) {
        new DialogPlusBuilder().setHeaderBgDrawable(R.drawable.bg_header).buildYearPickerDialog(pickedYear ->
                Toast.makeText(this, "picked year: " + pickedYear, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Year Picker");
    }

    public void onMonthMonthDialogClicked(View view) {
        new DialogPlusBuilder().setTitle("pick month").setHeaderBgDrawable(R.drawable.bg_header).buildMonthPickerDialog(pickedYear ->
                Toast.makeText(this, "picked month: " + pickedYear, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Month Picker");
    }

    public void onClickedRating(View view) {
        new DialogPlusBuilder("Rating Dialog", "Rate dialog_plus message content ...")
                .setTexts("rate", "cancel")
                .buildRatingDialog(1.7f, false, new DialogPlus.DialogRateListener() {
                    @Override
                    public void onRateGiven(float rate, DialogPlus dialogPlus) {
                        Toast.makeText(MainActivity.this, "rated with " + rate, Toast.LENGTH_SHORT).show();
                    }
                })
                .show(this.getSupportFragmentManager(), "Rating Dialog");
    }

    private List<String> getListItems() {
        List<String> dialogItemDMS = new ArrayList<>();
        dialogItemDMS.add("title 4");
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 7"));
        dialogItemDMS.add(("title 9"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 54"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        dialogItemDMS.add(("title 4"));
        return dialogItemDMS;
    }

    private List<String> getOptions() {
        String[] titles = {"Option 1", "Option 2", "Option 3", "Option 4"};
        return new ArrayList<>(Arrays.asList(titles));
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

