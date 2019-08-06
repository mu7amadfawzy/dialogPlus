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
        new DialogPlus().showConfirmCodeDialog(5, "Code Dialog", "code dialog message content"
                , R.color.colorPrimary, R.color.colorPrimary, new DialogPlus.OnCodeTyped() {
                    @Override
                    public void onCodeTyped(String typedCode) {
                        Toast.makeText(MainActivity.this, "onCodeTyped: " + typedCode, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onTimeUp(DialogPlus dialog) {
                        Toast.makeText(MainActivity.this, "onTimeUp", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResend(DialogPlus dialogPlus) {
                        Toast.makeText(MainActivity.this, "onResend", Toast.LENGTH_SHORT).show();
                    }

                }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidation(View view) {
        new DialogPlus().showValidateCodeDialog(5, "Validation Dialog"
                , "validation dialog content...", "12345", false, new DialogPlus.OnValidateCode() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(DialogPlus dialog) {
                        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResend(DialogPlus dialogPlus) {
                        Toast.makeText(MainActivity.this, "onResend", Toast.LENGTH_SHORT).show();
                    }
                }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidationConfrmation(View view) {
        new DialogPlus().showConfirmationDialog("Confirmation Dialog", "confirmation dialog message content ..."
                , R.color.colorPrimary, new DialogPlus.OnDialogActionClicked() {
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
                    public void onError(DialogPlus dialogPlus) {
                        super.onError(dialogPlus);
                        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(DialogPlus dialogPlus) {

                    }
                }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedErrorDialog(View view) {

        new DialogPlus().showErrorDialog("error dialog content message", new DialogPlus.OnDialogActionClicked() {
            @Override
            public void onPositive(DialogPlus dialogPlus) {
                Toast.makeText(MainActivity.this, "onPositive", Toast.LENGTH_SHORT).show();
                dialogPlus.dismiss();
            }

            @Override
            public void onSuccess(DialogPlus dialogPlus) {

            }

            @Override
            public void onNegative(DialogPlus dialogPlus) {
                super.onNegative(dialogPlus);
                Toast.makeText(MainActivity.this, "onNegative", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(DialogPlus dialogPlus) {
                super.onError(dialogPlus);
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();

            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedSuccessDialog(View view) {
        new DialogPlus().showSuccessDialog("Success message content..", new DialogPlus.OnDialogActionClicked() {
            @Override
            public void onPositive(DialogPlus dialogPlus) {
                Toast.makeText(MainActivity.this, "onPositive", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(DialogPlus dialogPlus) {

            }

            @Override
            public void onNegative(DialogPlus dialogPlus) {
                super.onNegative(dialogPlus);
                Toast.makeText(MainActivity.this, "onNegative", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(DialogPlus dialogPlus) {
                super.onError(dialogPlus);
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();

            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }
}

