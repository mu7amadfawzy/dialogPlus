package com.dialog.plus.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;
/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class KeyboardUtil {
    private static KeyboardUtil keyboardUtil;

    public static KeyboardUtil getInstance() {
        if (keyboardUtil == null)
            keyboardUtil = new KeyboardUtil();
        return keyboardUtil;
    }

    public void showKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public void hideKeyboard(View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }
}
