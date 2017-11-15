package com.android.fbarrios80.soydonante.model;

import android.graphics.drawable.Drawable;

/**
 * Created by fbarrios80 on 06-11-17.
 */

public class Post {

    private int postId;
    private int postType;
    private String postTitle;
    private String postContent;
    private boolean hasImage;
    private Drawable postImage;

    public Post(String postTitle, String postContent, boolean hasImage, Drawable postImage) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.hasImage = hasImage;
        this.postImage = postImage;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Drawable getPostImage() {
        return postImage;
    }

    public void setPostImage(Drawable postImage) {
        this.postImage = postImage;
    }
}
