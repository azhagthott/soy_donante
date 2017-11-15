package com.android.fbarrios80.soydonante.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.fbarrios80.soydonante.R;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by fbarrios80 on 03-11-17.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public void rotateView(View view, Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate_around_center_point);
        view.startAnimation(animation);
    }

    @Override
    public void onClick(View view) {

    }
}
