package com.android.fbarrios80.soydonante.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fbarrios80.soydonante.R;
import com.android.fbarrios80.soydonante.adapter.MainAdapter;
import com.android.fbarrios80.soydonante.model.Post;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView mainRecyclerView;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainRecyclerView.setLayoutManager(linearLayoutManager);
        MainAdapter adapter = new MainAdapter(getContext(), listPost());
        mainRecyclerView.setAdapter(adapter);
    }

    private List<Post> listPost() {
        List<Post> posts = new ArrayList<>();

        posts.add(new Post(getResources().getString(R.string.post_title_1), getResources().getString(R.string.post_content_1), true, getResources().getDrawable(R.drawable.post_1)));
        posts.add(new Post("Titulo", "Contenido", false, null));
        posts.add(new Post("Titulo", "Contenido", false, null));
        posts.add(new Post("Titulo", "Contenido", false, null));
        posts.add(new Post("Titulo", "Contenido", false, null));
        posts.add(new Post("Titulo", "Contenido", false, null));
        posts.add(new Post("Titulo", "Contenido", false, null));

        return posts;
    }
}
