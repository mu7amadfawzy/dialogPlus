package com.sample.dialogSample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dialog.plus.ui.DialogPlus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickedMessageCode(View view) {
        new DialogPlus("Message Dialog", "message dialog sample\n Welcome Back")
                //@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBgColor
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .setMessageDialog(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedConfirmCode(View view) {
        new DialogPlus("Code Dialog", "code dialog sample with send enabled, resend enabled and counter 10 seconds")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .setConfirmCodeDialog("12345", true, true, 10, Color.BLACK, null)
                .setDialogActionListener(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidation(View view) {
        new DialogPlus("Code Dialog", "code dialog sample with send enabled and zero seconds counter.")
                .setBackgroundColors(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimary)
                .setHeaderBgDrawable(R.drawable.bg_header)
                .setConfirmCodeDialog("123", false, true, 0, Color.BLUE, null)
                .setDialogActionListener(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidationConfirmation(View view) {
        new DialogPlus("Confirmation Dialog", "confirmation dialog message content ...")
                .setBackgroundColors(R.color.colorPrimary, R.color.white, R.color.colorPrimary)
                .setSecondaryTextColor(R.color.colorPrimary)
                .setDialogActionListener(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedErrorDialog(View view) {
        new DialogPlus("error dialog content message")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .setErrorDialog(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedSuccessDialog(View view) {
        new DialogPlus("Success message content..")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .setSuccessDialog(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
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

        @Override
        public void onWrongCode(DialogPlus dialogPlus) {
            super.onWrongCode(dialogPlus);
            Toast.makeText(MainActivity.this, "onWrongCode", Toast.LENGTH_SHORT).show();
        }

    }
}

