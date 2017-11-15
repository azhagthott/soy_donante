package com.android.fbarrios80.soydonante.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.fbarrios80.soydonante.R;
import com.android.fbarrios80.soydonante.model.Post;
import com.android.fbarrios80.soydonante.view.PostDetailedActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fbarrios80 on 06-11-17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    Context context;
    private List<Post> posts = new ArrayList<>();

    public MainAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {

        Post post = posts.get(position);
        holder.setTitle(post.getPostTitle());
        holder.setContent(post.getPostContent());

        Glide.with(context).load(post.getPostImage()).into(holder.cardImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PostDetailedActivity.class);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, "postDetail");
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardImageView;
        private TextView cardTitleTextView;
        private TextView cardContentTextView;

        public MainViewHolder(View view) {
            super(view);
            cardImageView = view.findViewById(R.id.cardImageView);
            cardTitleTextView = view.findViewById(R.id.cardTitleTextView);
            cardContentTextView = view.findViewById(R.id.cardContentTextView);
        }

        private void setTitle(String title) {
            cardTitleTextView.setText(title);
        }

        private void setContent(String content) {
            cardContentTextView.setText(content);
        }
    }
}
