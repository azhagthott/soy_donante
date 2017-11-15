package com.android.fbarrios80.soydonante.data;

import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by fbarrios80 on 05-11-17.
 */

public class RemoteDatabase {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("donor").child("user");

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public void saveUserData(@Nullable String gender, @Nullable String bloodType, @Nullable String weight) {

        ref.child(user.getUid()).child("displayName").setValue(user.getDisplayName());
        ref.child(user.getUid()).child("userEmail").setValue(user.getEmail());
        ref.child(user.getUid()).child("uid").setValue(user.getUid());

        if (gender != null) {
            ref.child(user.getUid()).child("gender").setValue(gender);
        }

        if (bloodType != null) {
            ref.child(user.getUid()).child("bloodType").setValue(bloodType);
        }

        if (weight != null) {
            ref.child(user.getUid()).child("weight").setValue(weight);
        }
    }
}
