package fr.tvbarthel.lib.blurdialogfragment.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;


public class SampleActionBarActivity extends AppCompatActivity {


    private SeekBar mBlurRadiusSeekbar;
    private SeekBar mDownScaleFactorSeekbar;

    private TextView mBlurRadiusTextView;
    private TextView mDownScaleFactorTextView;

    private CheckBox mDimmingEnable;
    private CheckBox mBlurredActionBar; // 是否需要模糊标题栏
    private CheckBox mDebugMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mBlurRadiusTextView = ((TextView) findViewById(R.id.blurRadius));
        mBlurRadiusSeekbar = ((SeekBar) findViewById(R.id.blurRadiusSeekbar));

        mDownScaleFactorTextView = ((TextView) findViewById(R.id.downScalefactor));
        mDownScaleFactorSeekbar = ((SeekBar) findViewById(R.id.downScaleFactorSeekbar));

        mDimmingEnable = ((CheckBox) findViewById(R.id.dimmingEnable));
        mBlurredActionBar = ((CheckBox) findViewById(R.id.blur_actionbar_enable));
        mDebugMode = ((CheckBox) findViewById(R.id.debugMode));

        setUpView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actions_fullscreen) {
            startActivity(new Intent(this, SampleFullScreenActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void okClick(View view){
        SampleSupportDialogFragment fragment = SampleSupportDialogFragment.newInstance(
                mBlurRadiusSeekbar.getProgress() + 1,
                (mDownScaleFactorSeekbar.getProgress() / 10f) + 2,
                mDimmingEnable.isChecked(),
                mBlurredActionBar.isChecked(),
                mDebugMode.isChecked()
                );
        fragment.show(getSupportFragmentManager(), "blur_sample");
    }


    private void setUpView() {

        mBlurRadiusSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBlurRadiusTextView.setText(getString(R.string.activity_sample_blur_radius, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mDownScaleFactorSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDownScaleFactorTextView.setText(getString(R.string.activity_sample_down_scale_factor, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // 默认配置
        mBlurRadiusSeekbar.setProgress(7);
        mDownScaleFactorSeekbar.setProgress(20);
    }
}
