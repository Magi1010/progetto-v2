package com.example.simplenav;

import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FollowerRepository {

    static void initializeFollower(Sid sid,  FollowerIniazializeListener listener) {
        String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface registerAPI = retrofit.create(ApiInterface.class);


        Call<List<Twok>> call = registerAPI.getFollowed(sid.getSid());
        call.enqueue(new Callback<List<Twok>>() {
            @Override
            public void onResponse(Call<List<Twok>> call, Response<List<Twok>> response) {
                Log.d("followato", "followato"+response.body());
                listener.onFollowerInizialize(response.body());
            }

            @Override
            public void onFailure(Call<List<Twok>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
    }


