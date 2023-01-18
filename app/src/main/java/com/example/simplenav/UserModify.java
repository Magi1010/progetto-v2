package com.example.simplenav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplenav.ApiInterface;
import com.example.simplenav.FragmentModify;
import com.example.simplenav.R;
import com.example.simplenav.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserModify extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public UserModify() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_modify, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = getView().findViewById(R.id.imageView);
        TextView textname = getView().findViewById(R.id.textView);
        Button b = getView().findViewById(R.id.button);
        String sid = "gEv7N1OvV12Ud9xc8j3X";

        String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface registerAPI = retrofit.create(ApiInterface.class);

        Call<User> call = registerAPI.getProfile(sid);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("mama", response.body().getName());
                textname.setText(response.body().getName());
                if (response.body().getPicture() != null) {


                    byte[] imageBytes = Base64.decode(response.body().getPicture(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    image.setImageBitmap(bitmap);
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("mama", "non va nulla");
            }
        });

        b.setOnClickListener(click -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.modify);

        });
    }
}