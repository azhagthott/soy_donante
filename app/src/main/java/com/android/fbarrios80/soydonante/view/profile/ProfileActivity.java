package com.android.fbarrios80.soydonante.view.profile;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.fbarrios80.soydonante.R;
import com.android.fbarrios80.soydonante.model.User;
import com.android.fbarrios80.soydonante.view.BaseActivity;
import com.android.fbarrios80.soydonante.view.MainActivity;
import com.android.fbarrios80.soydonante.view.login.LoginActivity;
import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity {

    private RelativeLayout profileRelativeLayout;
    private CircleImageView profileCircleImageView;
    private TextView nameLabel;
    private TextView emailLabel;
    private TextView genderLabel;
    private TextView bloodTypeLabel;
    private TextView weightLabel;
    private TextView displayNameTextView;
    private TextView userEmailTextView;
    private TextView bloodTypeTextView;
    private TextView genderTextView;
    private TextView weightTextView;
    private Button signInButton;
    private FloatingActionButton fab;
    private FrameLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        findViews();

        if (firebaseUser != null) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("donor").child("user");

            User localUser = new User();
            localUser.setLocalUid(firebaseUser.getUid());

            profileCircleImageView.setVisibility(View.VISIBLE);
            Glide.with(this).load(firebaseUser.getPhotoUrl()).into(profileCircleImageView);

            if (firebaseUser.getPhotoUrl() != null) {
                localUser.setProfileImage(firebaseUser.getPhotoUrl().toString());
            } else {
                localUser.setProfileImage("");
            }

            displayNameTextView.setVisibility(View.VISIBLE);
            displayNameTextView.setText(firebaseUser.getDisplayName());
            localUser.setDisplayName(firebaseUser.getDisplayName());

            userEmailTextView.setVisibility(View.VISIBLE);
            userEmailTextView.setText(firebaseUser.getEmail());
            localUser.setUserEmail(firebaseUser.getEmail());

            localUser.setBloodType("");
            localUser.setGender("");

            genderTextView.setVisibility(View.VISIBLE);
            bloodTypeTextView.setVisibility(View.VISIBLE);
            weightTextView.setVisibility(View.VISIBLE);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataSnapshot.child(firebaseUser.getUid()).getValue(User.class);

                    if (dataSnapshot.child(firebaseUser.getUid()).child("gender").exists()) {
                        genderTextView.setText(dataSnapshot.child(firebaseUser.getUid()).child("gender").getValue().toString());
                    } else {
                        genderTextView.setText("");
                    }

                    if (dataSnapshot.child(firebaseUser.getUid()).child("bloodType").exists()) {
                        bloodTypeTextView.setText(dataSnapshot.child(firebaseUser.getUid()).child("bloodType").getValue().toString());
                    } else {
                        bloodTypeTextView.setText("");
                    }

                    if (dataSnapshot.child(firebaseUser.getUid()).child("weight").exists()) {
                        weightTextView.setText(dataSnapshot.child(firebaseUser.getUid()).child("weight").getValue().toString());
                    } else {
                        weightTextView.setText("");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            signInButton.setVisibility(View.GONE);

            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(this);

        } else {
            signInButton.setVisibility(View.VISIBLE);
            signInButton.setOnClickListener(this);

            nameLabel.setVisibility(View.GONE);
            emailLabel.setVisibility(View.GONE);
            genderLabel.setVisibility(View.GONE);
            bloodTypeLabel.setVisibility(View.GONE);
            weightLabel.setVisibility(View.GONE);

            profileCircleImageView.setVisibility(View.GONE);
            displayNameTextView.setVisibility(View.GONE);
            userEmailTextView.setVisibility(View.GONE);
            genderTextView.setVisibility(View.GONE);
            bloodTypeTextView.setVisibility(View.GONE);
            weightTextView.setVisibility(View.GONE);

            fab.setVisibility(View.GONE);
        }
    }

    private void circularRevealActivity() {

        Rect rect = new Rect();
        fab.getGlobalVisibleRect(rect);
        int cx = rect.right - rect.width() / 2;
        int cy = rect.top - rect.width();

        int startRadius = 0;
        int endRadius = (int) Math.hypot(profileRelativeLayout.getWidth(), profileRelativeLayout.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(profileRelativeLayout, cx, cy, startRadius, endRadius);
        rootLayout.setVisibility(View.VISIBLE);
        anim.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.signInButton:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.fab:
                final Intent intent = new Intent(this, EditProfileActivity.class);
                circularRevealActivity();
                startActivity(intent);
                finish();
                break;
        }
    }

    private void findViews() {
        profileRelativeLayout = findViewById(R.id.profileRelativeLayout);
        profileCircleImageView = findViewById(R.id.profileCircleImageView);
        nameLabel = findViewById(R.id.nameLabel);
        emailLabel = findViewById(R.id.emailLabel);
        genderLabel = findViewById(R.id.genderLabel);
        bloodTypeLabel = findViewById(R.id.bloodTypeLabel);
        weightLabel = findViewById(R.id.weightLabel);
        displayNameTextView = findViewById(R.id.displayNameTextView);
        userEmailTextView = findViewById(R.id.userEmailTextView);
        bloodTypeTextView = findViewById(R.id.bloodTypeTextView);
        genderTextView = findViewById(R.id.genderTextView);
        weightTextView = findViewById(R.id.weightTextView);
        signInButton = findViewById(R.id.signInButton);
        fab = findViewById(R.id.fab);
        rootLayout = findViewById(R.id.root_layout);
    }
}
