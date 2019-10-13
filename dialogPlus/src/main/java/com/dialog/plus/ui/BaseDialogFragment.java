package com.dialog.plus.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dialog.plus.R;
import com.dialog.plus.utils.AnimationUtils;
import com.dialog.plus.utils.BlurBuilder;
import com.dialog.plus.utils.CommonUtil;
import com.dialog.plus.utils.SampleAnimationListener;

/**
 * Created by Fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 * <p>
 * param Binding: defines the layout binding implementation.
 */

public abstract class BaseDialogFragment<Binding extends ViewDataBinding> extends DialogFragment {
    protected DialogPlusUiModel model = new DialogPlusUiModel();
    protected Binding binding;
    protected View mDialogView;
    private AnimationSet scaleInAnimationSet, scaleOutAnimationSet;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_plus_style);
    }

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialogContentView().startAnimation(scaleInAnimationSet);
    }

    public void dismiss(boolean animate) {
        if (animate) {
            if (model.isBlurBackground())
                clearDialogDim();//makes screen don't flash suddenly
            getDialogContentView().startAnimation(scaleOutAnimationSet);
        } else super.dismiss();
    }

    protected void clearDialogDim() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setDialog(this);
            }

            @Override
            public void onBackPressed() {
                BaseDialogFragment.this.dismiss(true);
            }
        };
    }

    private void setDialog(Dialog dialog) {
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mDialogView = dialog.getWindow().getDecorView().findViewById(android.R.id.content);
            if (model.isBlurBackground())
                BlurBuilder.blurView(getActivity(), getDialogParentView());
        }
    }

    private void initAnimations() {
        scaleInAnimationSet = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.scale_in);
        scaleOutAnimationSet = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.scale_out);
        setAnimationListener();
    }

    private void setAnimationListener() {
        scaleOutAnimationSet.setAnimationListener(new SampleAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                onAnimationEnded();
                dismissAllowingStateLoss();
            }
        });
    }

    protected void onAnimationEnded() {
        //TODO override case needed
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

    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
        model.setHideCloseIcon(!cancelable);
    }

    protected abstract Object getVariableValue();

    protected abstract View getDialogParentView();

    protected abstract View getDialogContentView();

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
