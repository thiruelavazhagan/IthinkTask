package com.example.ithinktask;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    @GET("repositories")
    Call<List<Data>> getartistdata();


}