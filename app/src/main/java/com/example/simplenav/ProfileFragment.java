package com.example.simplenav;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Bundle b = getArguments();
        Sid sid = new Sid(b.getString("Sid"));
        String s1 = b.getString("someu");
        Integer uid = Integer.valueOf(s1);

        super.onViewCreated(view, savedInstanceState);
        Button button = getView().findViewById(R.id.button3);
        Button goback = getView().findViewById(R.id.goback);
        String BASE_URL1 = "https://develop.ewlab.di.unimi.it/mc/twittok/";
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(BASE_URL1).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface registerAPI1 = retrofit1.create(ApiInterface.class);
        int page = b.getInt("page");

        goback.setOnClickListener(toc->{
            Log.d("entra","entrato");
            if(page!=1){
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.ProfiletoHome);
            }else{
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.ProfiletoFollow);
            }

        });


        Call<Followed> call1 = registerAPI1.isFollowed(sid.getSid(), uid);
        call1.enqueue(new Callback<Followed>() {
            @Override
            public void onResponse(Call<Followed> call, Response<Followed> response) {
                if(response.body().getFollow()){
                    button.setText("Unfollow");
                }else{
                    button.setText("Follow");
                }
            }

            @Override
            public void onFailure(Call<Followed> call, Throwable t) {
                t.printStackTrace();
            }
        });





        TextView t = getView().findViewById(R.id.textViewp);
        String s = b.getString("someS");

        Bitmap bi = b.getParcelable("key_immagine");
        ImageView imageView = getView().findViewById(R.id.imageprofile);
        imageView.setImageBitmap(bi);
        t.setText(s);
        //Sid sid = new Sid(b.getString("Sid"));
        Log.d("uid", uid + "");
        myViewModel mv = new myViewModel(getActivity(), sid, getContext());
        mv.getListaUid(uid).observe(getActivity(), twoks -> {
            Log.d("tata",twoks+"");
            ViewPager2 viewPager = getView().findViewById(R.id.pageprofile);
            TwokAdapterUid twokAdapter = new TwokAdapterUid(getContext(), twoks);
            viewPager.setAdapter(twokAdapter);

        });


        //Button button = getView().findViewById(R.id.button3);
        button.setOnClickListener(click->{
            if(button.getText().equals("Follow")){
                Log.d("button",button.getText()+"f");
                button.setText("Unfollow");
                String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                ApiInterface registerAPI = retrofit.create(ApiInterface.class);


                Call<Twok> call = registerAPI.follow(sid.getSid(), uid);
                call.enqueue(new Callback<Twok>() {
                    @Override
                    public void onResponse(Call<Twok> call, Response<Twok> response) {
                        Log.d("follow", "followato");
                    }

                    @Override
                    public void onFailure(Call<Twok> call, Throwable t) {
                        t.printStackTrace();
                    }
                });



            }else{
                Log.d("button",button.getText()+"u");
                button.setText("Follow");
                String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                ApiInterface registerAPI = retrofit.create(ApiInterface.class);


                Call<Twok> call = registerAPI.unfollow(sid.getSid(), uid);
                call.enqueue(new Callback<Twok>() {
                    @Override
                    public void onResponse(Call<Twok> call, Response<Twok> response) {
                        Log.d("follow", "followato");
                    }

                    @Override
                    public void onFailure(Call<Twok> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }






        });






    }
}