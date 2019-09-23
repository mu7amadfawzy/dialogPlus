package com.dialog.plus.ui;

import android.view.View;
import android.widget.EditText;

import androidx.databinding.ViewDataBinding;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dialog.plus.utils.KeyboardUtil;

/**
 * Created by fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 * <p>
 * param Binding: defines the layout binding implementation.
 */

public abstract class BaseModelDialogFragment<Binding extends ViewDataBinding> extends BaseDialogFragment<Binding> {
    protected DialogUiModel model = new DialogUiModel();
    protected BaseModelDialogFragment<Binding> setNormalTextColor() {
        model.setDialogCodeTextColor(model.getDialogCodeTextColor());
        return this;
    }
    protected void shakeView(EditText editText) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .onEnd(animator -> editText.setText(null))
                .playOn(editText);
    }
    protected void hideKeyboard(View view) {
        KeyboardUtil.getInstance().hideKeyboard(view);
    }

    protected void showKeyboard(View view) {
        KeyboardUtil.getInstance().showKeyboard(view);
    }
}
