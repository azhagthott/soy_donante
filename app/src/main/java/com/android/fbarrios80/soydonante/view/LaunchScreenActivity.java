package com.android.fbarrios80.soydonante.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.android.fbarrios80.soydonante.R;

public class LaunchScreenActivity extends BaseActivity {

    private TextView appNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );

        appNameTextView = findViewById(R.id.appNameTextView);
        animateText();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    private void animateText() {
        appNameTextView.setAlpha(0f);
        appNameTextView.animate()
                .alpha(1f)
                .setDuration(2000);
    }
}
