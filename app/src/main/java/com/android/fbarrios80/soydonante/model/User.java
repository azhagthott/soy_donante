package com.android.fbarrios80.soydonante.model;

import android.support.annotation.NonNull;

/**
 * Created by fbarrios80 on 03-11-17.
 */

public class User {

    private String localUid;
    private String profileImage;
    private String displayName;
    private String userEmail;
    private String bloodType;
    private String gender;
    private String weight;

    public String getLocalUid() {
        return localUid;
    }

    public void setLocalUid(@NonNull String localUid) {
        this.localUid = localUid;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
