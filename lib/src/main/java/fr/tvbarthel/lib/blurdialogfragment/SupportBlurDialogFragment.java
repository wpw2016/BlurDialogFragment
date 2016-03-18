package fr.tvbarthel.lib.blurdialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

/**
 * 背景模糊窗口
 */
public abstract class SupportBlurDialogFragment extends DialogFragment {

    // 模糊引擎
    private BlurDialogEngine mBlurEngine;

    private Toolbar mToolbar;

    private boolean mDimmingEffect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBlurEngine = new BlurDialogEngine(getActivity());

        if (mToolbar != null) {
            mBlurEngine.setToolbar(mToolbar);
        }

        int radius = getBlurRadius();
        if (radius <= 0) {
            throw new IllegalArgumentException("Blur Radius 必须大于 0, 发现的是: " + radius);
        }
        mBlurEngine.setBlurRadius(radius);

        float factor = getDownScaleFactor();
        if (factor <= 1.0) {
            throw new IllegalArgumentException("Down Scale Factor 必须大于 1, 发现的是: " + factor);
        }
        mBlurEngine.setDownScaleFactor(factor);

        mBlurEngine.debug(isDebugEnable());

        mBlurEngine.setBlurActionBar(isActionBarBlurred());

        mDimmingEffect = isDimmingEnable();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {

            if (!mDimmingEffect) {
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }

            int currentAnimation = dialog.getWindow().getAttributes().windowAnimations;
            if (currentAnimation == 0) {
                dialog.getWindow().getAttributes().windowAnimations = R.style.BlurDialogFragment_Default_Animation;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBlurEngine.onResume(getRetainInstance());
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mBlurEngine.onDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBlurEngine.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }


    public void setToolbar(Toolbar toolBar) {
        mToolbar = toolBar;
        if (mBlurEngine != null) {
            mBlurEngine.setToolbar(toolBar);
        }
    }

    // 保留，供性能测试使用
    protected boolean isDebugEnable() {
        return BlurDialogEngine.DEFAULT_DEBUG_POLICY;
    }

    /**
     * To reduce the size of the source image.
     * Range :  [1.0,infinity)
     */
    protected float getDownScaleFactor() {
        return BlurDialogEngine.DEFAULT_BLUR_DOWN_SCALE_FACTOR;
    }

    /**
     * To reduce the size of the source image.
     * Range :  [1,infinity)
     */
    protected int getBlurRadius() {
        return BlurDialogEngine.DEFAULT_BLUR_RADIUS;
    }

    protected boolean isDimmingEnable() {
        return BlurDialogEngine.DEFAULT_DIMMING_POLICY;
    }

    protected boolean isActionBarBlurred() {
        return BlurDialogEngine.DEFAULT_ACTION_BAR_BLUR;
    }

}
