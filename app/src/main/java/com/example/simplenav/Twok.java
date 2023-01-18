package com.example.simplenav;

import android.graphics.Bitmap;

import java.util.List;

public class Twok {
    public String name, text;
    public String picture,pversion;
    Integer uid,fontsize,fonttype,halign,valign;
    String bgcol,fontcol;
    Double lat,lon;






    public Twok(String name, String text,String picture,Integer uid) {
        this.name = name;
        this.text = text;
        this.picture = picture;
        this.uid = uid;
    }

    public Twok(Integer uid,String name,String picture,String pversion){
        this.uid = uid;
        this.name = name;
        this.picture = picture;
        this.pversion = pversion;

    }

    public Twok(Integer uid,String name, String text,String picture,String bgcol,String textcolor,Integer fontSize,Integer fontType,Integer halign,Integer valign,Double lat, Double lon) {
        this.name = name;
        this.text = text;
        this.picture = picture;
        this.uid = uid;
        this.bgcol = bgcol;
        this.fontcol = textcolor;
        this.fontsize = fontSize;
        this.fonttype = fontType;
        this.halign = halign;
        this.valign = valign;
        this.lat = lat;
        this.lon = lon;
    }
    public Twok(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getPversion() {
        return pversion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }
    public String getBgcol() {
        return bgcol;
    }

    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid){this.uid = uid;}

    public Integer getFontSize() {
        return fontsize;
    }

    public Integer getFontType() {
        return fonttype;
    }

    public Integer getHalign() {
        return halign;
    }

    public Integer getValign() {
        return valign;
    }

    public String getFontcol() {
        return fontcol;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Twok{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", bg='" + bgcol + '\'' +
                ", uid=" + uid +
                '}';
    }
}
