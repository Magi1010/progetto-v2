package com.example.simplenav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TwokViewHolder extends RecyclerView.ViewHolder {
    private TextView txtName, txtText,txtUid;
    private ImageView imageView;
    private View view3;
    final float[] fontSizearr = {20, 30, 40};
    final Typeface[] fontTypearr = {Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL),Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC),Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)};
    final int[] halign = {Gravity.LEFT,Gravity.CENTER_HORIZONTAL,Gravity.RIGHT};
    final int[] valign = {Gravity.TOP,Gravity.CENTER_VERTICAL,Gravity.BOTTOM};


    public TwokViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.name);
        txtText = itemView.findViewById(R.id.twok);
        imageView =itemView.findViewById(R.id.imageView);
        txtUid = itemView.findViewById(R.id.uid);
        view3 = itemView.findViewById(R.id.view3uid);

    }

    public void updateContent(Twok twok) {
        Log.d("color",twok.bgcol);
        try {
            int backgroundColor = Color.parseColor("#"+twok.bgcol);
            view3.setBackgroundColor(backgroundColor);
        }catch (java.lang.NumberFormatException e){
            int backgroundColor = Color.parseColor(	"#FFFFFF");
            view3.setBackgroundColor(backgroundColor);
        }catch (java.lang.IllegalArgumentException e){
            int backgroundColor = Color.parseColor(	"#FFFFFF");
            view3.setBackgroundColor(backgroundColor);
        }
        Log.d("color",twok.fontcol+"");
        try {
            int backgroundColor = Color.parseColor("#"+twok.fontcol);
            txtText.setTextColor(backgroundColor);
        }catch (java.lang.NumberFormatException e){
            int backgroundColor = Color.parseColor(	"#FFFFFF");
            txtText.setTextColor(backgroundColor);
        }catch (java.lang.IllegalArgumentException e){
            int backgroundColor = Color.parseColor(	"#FFFFFF");
            txtText.setTextColor(backgroundColor);
        }
       //


        txtName.setText(twok.name);
        txtText.setText(twok.text);
        txtText.setTypeface(fontTypearr[twok.fonttype]);
        Log.d("size",twok.fontsize+"");
        txtText.setTextSize(fontSizearr[twok.fontsize]);
        txtText.setGravity(halign[twok.halign]|valign[twok.valign]);
        txtUid.setText(twok.uid.toString());
        Log.d("immagine",""+twok.picture);
        if(twok.picture!=null){
            byte[] imageBytes = Base64.decode(twok.picture, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(bitmap);
        }else{
            imageView.setImageResource(R.drawable.placeholder);
        }



       //



      //  Bitmap bitmap = BitmapFactory.decodeFile("path/to/image.jpg");
    }
}
