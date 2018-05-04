package com.example.zilvinastomkevicius.nostarve4u.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Zilvinas Tomkevicius on 2/13/2018.
 */

/*
    ENTITY CLASS FOR PRODUCT
 */
public class Product {

    @JsonProperty("ID")
    public int ID;
    @JsonProperty("Name")
    public String Name;
    @JsonProperty("Kind")
    public String Kind;

    @Override
    public String  toString() {

        return Name;
    }
}
