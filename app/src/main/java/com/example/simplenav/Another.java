package com.example.simplenav;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Another extends Fragment {

    public Another() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_another, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        Twok t1 = new Twok("Andrea");
        Twok t2 = new Twok("Simone");
        Twok t3 = new Twok("Giulio");
        ArrayList<Twok> l = new ArrayList<>();
        l.add(t1);
        l.add(t2);
        l.add(t3);

        SidRepository.initializeSid(getContext(), sid -> {
            List<Twok> followerList = new ArrayList<>();
            ViewModelFollower vmf = new ViewModelFollower();
            vmf.getListFollower(sid, getContext()).observe(getActivity(), lista -> {
                Log.d("follower3", lista + "");
                RecyclerView recycleview = getView().findViewById(R.id.recycler);
                recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
                MyAdapterProfile adapter = new MyAdapterProfile(getContext(), new ArrayList<>(lista),view);

                recycleview.setAdapter(adapter);

                ImageView image = getView().findViewById(R.id.imagep);
                Log.d("ciao",image+"");

                /*
                recycleview.setOnClickListener(clc->{
                    Log.d("ciao","");
                });
                */











                });



            });


            Log.d("MainActivity", "questa è la lista");
            //getActivity().setContentView(R.layout.fragment_another);


            //cose da fare domani -->
            // creare il repository per il getfollowed creando un oggetto con una lista
            //il repository ritorna una lista, dove dentro devo iterare per prendere l'immagine e salvo tutto su un twok
            //poi creo un array di twok che sarebbe questo quello passato all'adapter


            //cose da fare in generale--> gestire lo scroll che non riesco a gestire


            //immagazzinare l'immagine nel db che non ho fatto



    }
}