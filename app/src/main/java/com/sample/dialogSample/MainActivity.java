package com.sample.dialogSample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dialog.plus.ui.MultiDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickedConfirmCode(View view) {
        new MultiDialog().showConfirmCodeDialog(5, "title", "content", null, null, new MultiDialog.OnCodeTyped() {
            @Override
            public void onCodeTyped(String typedCode) {
                Toast.makeText(MainActivity.this, "onCodeTyped: " + typedCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTimeUp(MultiDialog dialog) {
                Toast.makeText(MainActivity.this, "onTimeUp", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResend() {
                Toast.makeText(MainActivity.this, "onResend", Toast.LENGTH_SHORT).show();

            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidation(View view) {
        new MultiDialog().showValidateCodeDialog(5, "title", "content", "12345", false, null, null, new MultiDialog.OnValidateCode() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MultiDialog dialog) {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResend() {
                Toast.makeText(MainActivity.this, "onResend", Toast.LENGTH_SHORT).show();
            }
        }).show(this.getSupportFragmentManager(), "dialog");
    }

    public void onClickedValidationConfrmation(View view) {
        new MultiDialog().showConfirmationDialog("title", "content", null, null, null, new MultiDialog.OnDialogActionClicked() {
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

        new MultiDialog().showErrorDialog("content", new MultiDialog.OnDialogActionClicked() {
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
        new MultiDialog().showSuccessDialog("content", new MultiDialog.OnDialogActionClicked() {
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

