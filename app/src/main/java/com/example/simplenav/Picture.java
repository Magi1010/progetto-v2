package com.example.simplenav;

import android.graphics.Bitmap;

public class Picture {
    private String name;
    private String pversion;
    private String picture;

    public Picture(String name, String pversion, String picture) {
        this.name = name;
        this.pversion = pversion;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPversion() {
        return pversion;
    }

    public void setPversion(String pversion) {
        this.pversion = pversion;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String image) {
        this.picture = image;
    }
}
