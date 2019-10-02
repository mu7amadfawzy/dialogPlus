package com.dialog.plus.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dialog.plus.R;
import com.dialog.plus.utils.AnimationUtils;
import com.dialog.plus.utils.CommonUtil;
import com.dialog.plus.utils.SampleAnimationListener;

/**
 * Created by fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 * <p>
 * param Binding: defines the layout binding implementation.
 */

public abstract class BaseDialogFragment<Binding extends ViewDataBinding> extends DialogFragment {
    protected DialogPlusUiModel model = new DialogPlusUiModel();
    protected Binding binding;
    protected View mDialogView;
    private AnimationSet mModalInAnim, mModalOutAnim;
    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        bindView();
        initAnimations();
        return binding.getRoot();
    }

    private void bindView() {
        binding.setVariable(getBindingVariable(), getVariableValue());
        binding.setLifecycleOwner(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialog();
        mDialogView.startAnimation(mModalInAnim);
    }

    private void setDialog() {
        android.app.Dialog dialog = getDialog();
        if (dialog != null) {
            mDialogView = dialog.getWindow().getDecorView().findViewById(android.R.id.content);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }

    public void dismiss(boolean animate) {
        if (animate)
            mDialogView.startAnimation(mModalOutAnim);
        else dismiss(true);
    }

    private void initAnimations() {
        mModalInAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.modal_out);
        setAnimationListener();
    }

    private void setAnimationListener() {
        mModalOutAnim.setAnimationListener(new SampleAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(() -> dismiss());
            }
        });
    }

    protected void animate(View view, Techniques techniques) {
        YoYo.with(techniques)
                .duration(700)
                .playOn(view);
    }

    protected void shakeView(EditText editText) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .onEnd(animator -> {
                    editText.setText(null);
                    showKeyboard(editText);
                })
                .playOn(editText);
    }

    protected void hideKeyboard(View view) {
        CommonUtil.getInstance().hideKeyboard(view);
    }

    protected void showKeyboard(View view) {
        CommonUtil.getInstance().showKeyboard(view);
    }

    public void showLoading() {
        hideLoading();
        if (mProgressDialog == null)
            mProgressDialog = CommonUtil.getInstance().getProgressDialog(getContext());
        mProgressDialog.show();
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    protected abstract Object getVariableValue();


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    @LayoutRes
    public abstract int getLayoutId();
}
