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
        new DialogPlus().showConfirmCodeDialog(5, "title", "content", null, null, new DialogPlus.OnCodeTyped() {
            @Override
            public void onCodeTyped(String typedCode) {
                Toast.makeText(MainActivity.this, "onCodeTyped: " + typedCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTimeUp(DialogPlus dialog) {
                Toast.makeText(MainActivity.this, "onTimeUp", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResend() {
                Toast.makeText(MainActivity.this, "onResend", Toast.LENGTH_SHORT).show();

            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidation(View view) {
        new DialogPlus().showValidateCodeDialog(5, "title", "content", "12345", false, null, null, new DialogPlus.OnValidateCode() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(DialogPlus dialog) {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResend() {
                Toast.makeText(MainActivity.this, "onResend", Toast.LENGTH_SHORT).show();
            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidationConfrmation(View view) {
        new DialogPlus().showConfirmationDialog("title", "content", null, null, null, new DialogPlus.OnDialogActionClicked() {
            @Override
            public void onPositiveClicked() {
                Toast.makeText(MainActivity.this, "onPositiveClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegativeClicked() {
                Toast.makeText(MainActivity.this, "onNegativeClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorClicked() {
                Toast.makeText(MainActivity.this, "onErrorClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccessClicked() {

            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedErrorDialog(View view) {

        new DialogPlus().showErrorDialog("content", new DialogPlus.OnDialogActionClicked() {
            @Override
            public void onPositiveClicked() {
                Toast.makeText(MainActivity.this, "onPositiveClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegativeClicked() {
                Toast.makeText(MainActivity.this, "onNegativeClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorClicked() {
                Toast.makeText(MainActivity.this, "onErrorClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccessClicked() {

            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedSuccessDialog(View view) {
        new DialogPlus().showSuccessDialog("content", new DialogPlus.OnDialogActionClicked() {
            @Override
            public void onPositiveClicked() {
                Toast.makeText(MainActivity.this, "onPositiveClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegativeClicked() {
                Toast.makeText(MainActivity.this, "onNegativeClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorClicked() {
                Toast.makeText(MainActivity.this, "onErrorClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccessClicked() {

            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }
}

