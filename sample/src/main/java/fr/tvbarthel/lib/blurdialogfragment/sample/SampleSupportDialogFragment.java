package fr.tvbarthel.lib.blurdialogfragment.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * 背景模糊窗口
 */
public class SampleSupportDialogFragment extends SupportBlurDialogFragment {

    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";
    private static final String BUNDLE_KEY_BLURRED_ACTION_BAR = "bundle_key_blurred_action_bar";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private boolean mBlurredActionBar;

    public static SampleSupportDialogFragment newInstance(int radius,
                                                          float downScaleFactor,
                                                          boolean dimming,
                                                          boolean mBlurredActionBar,
                                                          boolean debug) {

        SampleSupportDialogFragment fragment = new SampleSupportDialogFragment();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_BLUR_RADIUS, radius);
        args.putFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR, downScaleFactor);
        args.putBoolean(BUNDLE_KEY_DIMMING, dimming);
        args.putBoolean(BUNDLE_KEY_BLURRED_ACTION_BAR, mBlurredActionBar);
        args.putBoolean(BUNDLE_KEY_DEBUG, debug);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mBlurredActionBar = args.getBoolean(BUNDLE_KEY_BLURRED_ACTION_BAR);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment, null);
        TextView label = ((TextView) view.findViewById(R.id.textView));
        label.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(label, Linkify.WEB_URLS);
        builder.setView(view);
        return builder.create();
    }

    @Override
    protected int getBlurRadius() {
        return mRadius;
    }

    @Override
    protected float getDownScaleFactor() {
        return mDownScaleFactor;
    }

    @Override
    protected boolean isDimmingEnable() {
        return mDimming;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return mBlurredActionBar;
    }

    @Override
    protected boolean isDebugEnable() {
        return mDebug;
    }

}
