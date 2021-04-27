package com.example.ithinktask;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListViewModel extends ViewModel {
    public String id = "";
    public String name = "";
    public String desc = "";
    public String moviename = "";
    public Owner owner;
    public String avatar_url;
    public MutableLiveData<ArrayList<MyListViewModel>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<MyListViewModel> arrayList;

    private List<Data> myList;

    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    public MyListViewModel() {

    }

    public MyListViewModel(Data myList) {
        this.id = myList.id;
        this.name = myList.name;
        this.desc = myList.description;
        this.avatar_url = myList.owner.avatar_url;


    }

    public MutableLiveData<ArrayList<MyListViewModel>> getMutableLiveData() {

        arrayList = new ArrayList<>();

        MyApi api = MyClient.getInstance().getMyApi();
        Call<List<Data>> call = api.getartistdata();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                myList = new ArrayList<>();
                myList = response.body();
                for (int i = 0; i < myList.size(); i++) {
                    Data myk = myList.get(i);
                    MyListViewModel myListViewModel = new MyListViewModel(myk);
                    arrayList.add(myListViewModel);
                    mutableLiveData.setValue(arrayList);
                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}