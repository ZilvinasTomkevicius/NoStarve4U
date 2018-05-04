package com.example.zilvinastomkevicius.nostarve4u.Entities;

import android.net.ConnectivityManager;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.ArrayList;

/**
 * Created by Zilvinas Tomkevicius on 2/14/2018.
 */

/*
    ENTITY CLASS FOR RECIPE
 */
public class Recipe {

    @JsonProperty("ID")
    public int ID;
    @JsonProperty("Name")
    public String Name;
    @JsonProperty("Description")
    public String Description;
    @JsonProperty("CookingTime")
    public  int CookingTime;
    @JsonProperty("Ingredients")
    public ArrayList<Product> Ingredients;

    public String toStringName() {

        return String.format("%s", Name);
    }

    public String toStringDescription() {

        return String.format("%s", Description);
    }

    public String toStringCookingTime() {

        return String.format("%d", CookingTime);
    }
}
