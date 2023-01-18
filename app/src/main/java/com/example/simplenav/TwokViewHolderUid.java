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

public class TwokViewHolderUid extends RecyclerView.ViewHolder{
    private TextView  txtText;
    private View vie;
    final float[] fontSizearr = {20, 30, 40};
    final Typeface[] fontTypearr = {Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL),Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC),Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)};
    final int[] halign = {Gravity.LEFT,Gravity.CENTER_HORIZONTAL,Gravity.RIGHT};
    final int[] valign = {Gravity.TOP,Gravity.CENTER_VERTICAL,Gravity.BOTTOM};




    public TwokViewHolderUid(@NonNull View itemView) {
        super(itemView);

        txtText = itemView.findViewById(R.id.textuid);
        vie = itemView.findViewById(R.id.view3uid);



    }

    public void updateContent(Twok twok) {

        txtText.setText(twok.text);
       // int backgroundColor = Color.parseColor(twok.bgcol);

        try {
            int backgroundColor = Color.parseColor("#"+twok.bgcol);
            txtText.setBackgroundColor(backgroundColor);
        }catch (java.lang.NumberFormatException e){
            int backgroundColor = Color.parseColor(	"#FFFFFF");
            txtText.setBackgroundColor(backgroundColor);
        }catch (java.lang.IllegalArgumentException e){
            int backgroundColor = Color.parseColor(	"#FFFFFF");
            txtText.setBackgroundColor(backgroundColor);
        }
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



        txtText.setTypeface(fontTypearr[twok.fonttype]);
        Log.d("size",twok.fontsize+"");
        txtText.setTextSize(fontSizearr[twok.fontsize]);
        txtText.setGravity(halign[twok.halign]|valign[twok.valign]);





       //



      //  Bitmap bitmap = BitmapFactory.decodeFile("path/to/image.jpg");
    }
}
