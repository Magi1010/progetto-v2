package com.example.simplenav;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myViewModel extends ViewModel {
    private final MutableLiveData<List<Twok>> twoks = new MutableLiveData<>();

    private Sid sid;
    private Activity activity;
    private Context context;
    private static final int MAX_VALUE = 5;


    public myViewModel(Activity activity, Sid sid, Context context) {
        this.activity = activity;
        this.sid = sid;
        this.context = context;


    }

    public myViewModel() {

    }


    public MutableLiveData<List<Twok>> getLista(List<Twok> twokL) {
        int i = 0;

        //List<Twok> twokL = new ArrayList<Twok>();

        //for (int i = 0; i < 10; i++) {
//                Log.d("Mainact", "sid?" + sid.getSid());
        String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
        for (i = 0; i < MAX_VALUE; i++) {

           getMoreTwok(BASE_URL,twokL);
        }


        Log.d("a caso", twokL + "");
        //twok.add(new Twok("ciao","come"));
        //Log.d("Main",""+twok);


        return twoks;
    }
    public MutableLiveData<List<Twok>> getListaUid(int uid) {
        int i = 0;

        List<Twok> twokL = new ArrayList<Twok>();

        //for (int i = 0; i < 10; i++) {
//                Log.d("Mainact", "sid?" + sid.getSid());
        String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
        for (i = 0; i < 2; i++) {

            getOneTwokUid(BASE_URL,uid).observe((LifecycleOwner) this.activity, oneTwok -> {
                Log.d("mains", "strada Giusta" + oneTwok);
                twokL.add(oneTwok);
                Log.d("Mains", "lista" + twokL);
                twoks.setValue(twokL);

            });
        }


        Log.d("a caso", twokL + "");
        //twok.add(new Twok("ciao","come"));
        //Log.d("Main",""+twok);


        return twoks;
    }


    public MutableLiveData<Twok> getOneTwok(String BASE_URL) {
        MutableLiveData<Twok> lista = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface registerAPI = retrofit.create(ApiInterface.class);
        Call<Twok> call = registerAPI.getTwok(sid.getSid(), null);
        call.enqueue(new Callback<Twok>() {
            @Override
            public void onResponse(Call<Twok> call, Response<Twok> response) {
                Log.d("main", response.body().getUid().toString());
                PictureRepository.initializePicture(response.body().getUid(), sid, context, picture -> {
                    Log.d("immagine", picture.getPicture() + "");


                    Twok twok = new Twok(response.body().getUid(),response.body().getName(), response.body().getText(), picture.getPicture(),response.body().getBgcol(),response.body().getFontcol(),response.body().getFontSize(),response.body().getFontType(),response.body().getHalign(),response.body().getValign(),response.body().getLat(), response.body().getLon());
                    Log.d("bggg", response.body().getBgcol() + "");


                    lista.setValue(twok);


                });


// Impostare l'immagine su un ImageView
                //ImageView imageView = findViewById(R.id.image_view);


            }


            @Override
            public void onFailure(Call<Twok> call, Throwable t) {
                Log.d("main", "non funziona");
            }
        });

        return lista;
    }

    public MutableLiveData<Twok> getOneTwokUid(String BASE_URL, int uid) {
        MutableLiveData<Twok> lista = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface registerAPI = retrofit.create(ApiInterface.class);
        Call<Twok> call = registerAPI.getTwok(sid.getSid(), uid);
        call.enqueue(new Callback<Twok>() {
            @Override
            public void onResponse(Call<Twok> call, Response<Twok> response) {
                Log.d("main", response.body().getUid().toString());
                PictureRepository.initializePicture(response.body().getUid(), sid, context, picture -> {
                    Log.d("risposta", response+"");


                    Twok twok = new Twok(response.body().getUid(),response.body().getName(), response.body().getText(), picture.getPicture(),response.body().getBgcol(),response.body().getFontcol(),response.body().getFontSize(),response.body().getFontType(),response.body().getHalign(),response.body().getValign(),response.body().getLat(), response.body().getLon());

                    Log.d("immagineeee", twok + "");


                    lista.setValue(twok);


                });


// Impostare l'immagine su un ImageView
                //ImageView imageView = findViewById(R.id.image_view);


            }

            @Override
            public void onFailure(Call<Twok> call, Throwable t) {

            }


        });
        return lista;
    }

    public void getMoreTwok(String BASE_URL,List<Twok> twokL){


    getOneTwok(BASE_URL).observe((LifecycleOwner) this.activity, oneTwok -> {
        Log.d("mains", "strada Giusta" + oneTwok);
        twokL.add(oneTwok);
        Log.d("Mains", "lista" + twokL);
        twoks.setValue(twokL);
    });

}

public MutableLiveData<Twok> plusTwok(String BASE_URL){
    MutableLiveData<Twok> nonva = new MutableLiveData<>();
    getOneTwok(BASE_URL).observe((LifecycleOwner) this.activity, oneTwok -> {
            nonva.setValue(oneTwok);

    });

    return nonva;
    }
}





