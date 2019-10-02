package com.sample.dialogSample.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sample.dialogSample.MainActivity;
import com.sample.dialogSample.R;

/**
 * Created by Ali on 21,July,2019
 * aligamal.dev@gmail.com
 */
public class SettingsActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public void onSelectArabic(View view) {
        setAppLanguageArabic();
        restart();
    }

    private void setAppLanguageArabic() {
        getSharedPreferences("sample", MODE_PRIVATE).edit().putBoolean("isArabic", true).apply();
    }

    public void onSelectEnglish(View view) {
        getSharedPreferences("sample", MODE_PRIVATE).edit().putBoolean("isArabic", false).apply();
        restart();
    }

    public void restart() {
        restartMainActivity();
    }

    @Override
    public void onBackPressed() {
        restartMainActivity();
    }

    protected void restartMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


}



