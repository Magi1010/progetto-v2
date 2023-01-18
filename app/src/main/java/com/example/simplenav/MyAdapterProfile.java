package com.example.simplenav;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterProfile extends RecyclerView.Adapter<FollowViewHolder>{

    private int clickedposition;
    MutableLiveData<ImageView> immagine;

        private LayoutInflater mInflater;
        ArrayList<Twok> twoks;
        View a;
        Context context;
        public MyAdapterProfile(Context context, ArrayList<Twok> mt, View a){
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.twoks = mt;
            this.a = a;


        }

        @NonNull
        @Override
        public FollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("size",twoks.size()+"");
            View view = mInflater.inflate(R.layout.single_row3, parent,false);
            return new FollowViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FollowViewHolder holder, int position) {


        SidRepository.initializeSid(context,sid -> {
            ImageView image = holder.itemView.findViewById(R.id.imagep);
            Bundle args = new Bundle();
            Twok twok = twoks.get(position);
            args.putString("someS", twok.getName()+"");
            args.putString("someu", twok.getUid().toString());
            args.putString("Sid", sid.getSid());
            args.putInt("page",1);
            ImageView imageView = holder.itemView.findViewById(R.id.imagep);
            /*
            Drawable drawable = imageView.getDrawable();

            //TODO  passare l'immagine quando si clicca sul profilo che non funziona
            if (drawable != null) {
                // Converti il Drawable in un oggetto Bitmap
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                //imageView.setImageBitmap(bitmap);

            }
            */
            Bitmap bitmap = null;
            if(twok.picture!=null) {
                byte[] imageBytes = Base64.decode(twok.picture, Base64.DEFAULT);
                 bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView.setImageBitmap(bitmap);
            }
            args.putParcelable("key_immagine", bitmap);




            image.setOnClickListener(cc->{
                Log.d("twok",twok.getUid()+"");
                NavController navController = Navigation.findNavController(a);
                navController.navigate(R.id.FollowtoProfile,args);
            });
            holder.updateContent(twok);
        });


        }

        @Override
        public int getItemCount() {
            return twoks.size();
        }




        public int getClickedposition(){
            return clickedposition;
        }
    }

