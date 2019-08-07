package com.sample.dialogSample;

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

    public void onClickedConfirmCode(View view) {
        new DialogPlus("Code Dialog", "code dialog with send, resend and counter")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .setConfirmCodeDialog("12345", true, true, 10, null)
                .setOnDialogActionClicked(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidation(View view) {
        new DialogPlus("Validation Dialog", "code dialog with send and zero counter.")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .setConfirmCodeDialog("123", true, false, 0, null)
                .setOnDialogActionClicked(new DialogListener())
                .show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidationConfirmation(View view) {
        new DialogPlus("Confirmation Dialog", "confirmation dialog message content ...")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .setPrimaryBgColor(R.color.colorPrimary)
                .setOnDialogActionClicked(new DialogListener())
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

    private class DialogListener extends DialogPlus.OnDialogActionClicked {
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

