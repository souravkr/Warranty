package com.example.android.warranty2;


import android.net.Uri;

import java.io.Serializable;
import java.util.Date;




public class Products implements Serializable {

    int mExpirery;
    private Uri mImageUri;
    private String mName;
    private String mBrand;
    private Date mDates;


    public Uri getImageUri() {
        return mImageUri;
    }

    public void setImageUri(Uri imageUri) {
        mImageUri = imageUri;
    }


    public String getName() {
        return mName;
    }



    public void setBrand(String brand) {
        mBrand = brand;
    }

    public String getBrand() {
        return mBrand;
    }

    public void setName(String name) {
        mName = name;
    }



    public Date getDate() {
        return mDates;
    }

    public void setDate(Date date) {
        mDates = date;
    }



    public int getExpirery() {
        return mExpirery;
    }

    public void setExpirery(int expirery) {
        mExpirery = expirery;
    }




}
