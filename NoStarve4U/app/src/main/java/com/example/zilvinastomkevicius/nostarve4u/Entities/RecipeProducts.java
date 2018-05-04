package com.example.zilvinastomkevicius.nostarve4u.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zilvinas Tomkevicius on 2018-03-01.
 */

/*
    ENTITY CLASS FOR RECIPE PRODUCTS
 */
public class RecipeProducts {

    @JsonProperty("RecipeID")
    public  int RecipeID;
    @JsonProperty("ProductIDList")
    public ArrayList<Integer> ProductIDList;
}
