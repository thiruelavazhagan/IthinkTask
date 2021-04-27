package com.example.ithinktask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private MyListViewModel myListViewModel;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        myListViewModel= ViewModelProviders.of(this).get(MyListViewModel.class);
        myListViewModel.getMutableLiveData().observe(this, myListViewModels -> {
            adapter=new MyAdapter(myListViewModels,MainActivity.this);
            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerview.setAdapter(adapter);
        });
    }
}