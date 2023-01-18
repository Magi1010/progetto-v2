package com.example.simplenav;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  SidRepository {
    private static Sid sid = null;
    static void initializeSid(Context x,SidInizializeListener listener){
        // controllare tra lo share preferences, se c'è notifica la callback, sennò chiamata di rete e nella callback richiama la nostra callback
        sid = new Sid();

        SharedPreferences settings = x.getSharedPreferences("SidDettails", 0);
        String sidd= settings.getString("Sid","");
        String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) .addConverterFactory(GsonConverterFactory.create()) .build();
        ApiInterface registerAPI = retrofit.create(ApiInterface.class);

        if(sidd.equals("")){
            Call<Sid> call = registerAPI.getSid();
            call.enqueue(new Callback<Sid>() {
                @Override
                public void onResponse(Call<Sid> call, Response<Sid> response) {

                    sid.setSid(response.body().getSid());
                    SharedPreferences.Editor editor =  settings.edit();
                    editor.putString("Sid", sid.getSid());
                    editor.commit();
                    Log.d("Mainact","non lo avevo");
                    listener.onSidInizialize(sid);
                }
                @Override
                public void onFailure(Call<Sid> call, Throwable t) {
                    t.printStackTrace();
                } });


        }else{
            sid.setSid(settings.getString("Sid",""));
            Log.d("Mainact","lo avevo");
            listener.onSidInizialize(sid);
        }
        /*
        SharedPreferences.Editor editor =  settings.edit();
        editor.putString("Sid", "");
        editor.commit();

/*






*/




/*
        Log.d("Main","vuoto?"+sidd);


        Log.d("Main","vuoto?"+sidd);

 */

    }

    static Sid getSid(){
       return sid;// metodo chiamato solo quando so che fa una visualizzazione
    }
}
