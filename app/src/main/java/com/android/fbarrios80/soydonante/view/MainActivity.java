package com.android.fbarrios80.soydonante.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fbarrios80.soydonante.R;
import com.android.fbarrios80.soydonante.view.fragment.MainFragment;
import com.android.fbarrios80.soydonante.view.login.LoginActivity;
import com.android.fbarrios80.soydonante.view.map.MapFragment;
import com.android.fbarrios80.soydonante.view.profile.ProfileActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView displayNameTextView;
    private CircleImageView profileCircleImageView;
    private LinearLayout profileBackground;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        displayNameTextView = headerView.findViewById(R.id.displayNameTextView);
        profileCircleImageView = headerView.findViewById(R.id.profileCircleImageView);
        profileBackground = headerView.findViewById(R.id.profileBackground);

        fab = findViewById(R.id.fab);

        profileCircleImageView.setOnClickListener(this);
        profileBackground.setOnClickListener(this);
        fab.setOnClickListener(this);

        callFragment(new MainFragment());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            displayNameTextView.setText(user.getDisplayName());
            Glide.with(this).load(user.getPhotoUrl()).into(profileCircleImageView);

        } else {
            displayNameTextView.setText("");
        }
    }

    private void callFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                //startActivity(new Intent(this,MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {

            case R.id.nav_init:
                callFragment(new MainFragment());
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.nav_map:
                callFragment(new MapFragment());
                break;
            case R.id.nav_share:
                //startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.nav_settings:
                //startActivity(new Intent(this, MainActivity.class));
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profileCircleImageView:
                startActivity(new Intent(this, LoginActivity.class));
                Log.d("TAG", "onClick: ");
                break;
            case R.id.profileBackground:
                startActivity(new Intent(this, LoginActivity.class));
                Log.d("TAG", "onClick: ");
                break;
            case R.id.fab:
                rotateView(fab, this);
                Toast.makeText(this, "Agregar visita", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
