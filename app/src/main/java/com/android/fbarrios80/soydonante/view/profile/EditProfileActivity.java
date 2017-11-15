package com.android.fbarrios80.soydonante.view.profile;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.fbarrios80.soydonante.R;
import com.android.fbarrios80.soydonante.data.RemoteDatabase;
import com.android.fbarrios80.soydonante.view.BaseActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton backArrowImageView;
    private CircleImageView profileCircleImageView;
    private TextView displayNameTextView;
    private TextView userEmailTextView;
    private Spinner genderSpinner;
    private Spinner bloodTypeSpinner;
    private EditText weightUserEditText;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.light_blue_A200));
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        findViews();

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gender());
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bloodTypes());
        bloodTypeSpinner.setAdapter(bloodAdapter);
        bloodTypeSpinner.setOnItemSelectedListener(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Glide.with(this).load(user.getPhotoUrl()).into(profileCircleImageView);
            displayNameTextView.setText(user.getDisplayName());
            userEmailTextView.setText(user.getEmail());
        }

        backArrowImageView = findViewById(R.id.backArrowImageView);
        backArrowImageView.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new RemoteDatabase().saveUserData(null, null, weightUserEditText.getText().toString());
        Intent intent = new Intent(this, ProfileActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(backArrowImageView, 0, 0, width, height);
        startActivity(intent, options.toBundle());
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.genderSpinner:
                if (position != 0) {
                    new RemoteDatabase().saveUserData(gender().get(position), null, null);
                }
                break;
            case R.id.bloodTypeSpinner:
                if (position != 0) {
                    new RemoteDatabase().saveUserData(null, bloodTypes().get(position), null);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.backArrowImageView) {
            new RemoteDatabase().saveUserData(null, null, weightUserEditText.getText().toString());
            Intent intent = new Intent(this, ProfileActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(backArrowImageView, Math.round(backArrowImageView.getX()), 0, width, height);
            startActivity(intent, options.toBundle());
            finish();
        }
    }

    private void findViews() {
        profileCircleImageView = findViewById(R.id.profileCircleImageView);
        displayNameTextView = findViewById(R.id.displayNameTextView);
        userEmailTextView = findViewById(R.id.userEmailTextView);
        genderSpinner = findViewById(R.id.genderSpinner);
        bloodTypeSpinner = findViewById(R.id.bloodTypeSpinner);
        weightUserEditText = findViewById(R.id.weightUserEditText);
    }

    private List<String> gender() {
        List<String> gender = new ArrayList<>();
        gender.add("-- Seleccione --");
        gender.add("Femenino");
        gender.add("Masculino");
        gender.add("Otro");
        gender.add("Dejar en blanco");
        return gender;
    }

    private List<String> bloodTypes() {
        List<String> blood = new ArrayList<>();
        blood.add("-- Seleccione --");
        blood.add("RH+1");
        blood.add("RH+2");
        blood.add("RH+3");
        blood.add("RH+4");
        blood.add("RH+5");
        return blood;
    }
}