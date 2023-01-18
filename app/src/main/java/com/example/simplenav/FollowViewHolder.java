package com.example.simplenav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FollowViewHolder extends RecyclerView.ViewHolder {
        private TextView txt;
        private ImageView immagine;

    public FollowViewHolder(@NonNull View itemView) {
        super(itemView);

        txt = itemView.findViewById(R.id.namep);
        immagine = itemView.findViewById(R.id.imagep);
    }

    public void updateContent(Twok t) {
        txt.setText(t.name);
        Log.d("Immaginef",t+"");

            if(!t.picture.equals("value")){
                byte[] imageBytes = Base64.decode(t.picture, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                immagine.setImageBitmap(bitmap);
            }else{
                immagine.setImageResource(R.drawable.placeholder);
            }


           //









    }

}
