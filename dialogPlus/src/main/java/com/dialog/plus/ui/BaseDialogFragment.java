package com.dialog.plus.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.dialog.plus.R;
import com.dialog.plus.utils.AnimationUtils;

/**
 * Created by fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 * <p>
 * param Binding: defines the layout binding implementation.
 */

public abstract class BaseDialogFragment<Binding extends ViewDataBinding> extends DialogFragment {
    protected Binding binding;
    protected View mDialogView;
    private AnimationSet mModalInAnim, mModalOutAnim;

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
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
    public void onViewCreated(@androidx.annotation.NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    private void initAnimations() {
        mModalInAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.modal_out);
        setAnimationListener();
    }

    private void setAnimationListener() {
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(() -> dismiss());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public void dismiss(boolean animate) {
        if (animate)
            mDialogView.startAnimation(mModalOutAnim);
        else dismiss(true);
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
