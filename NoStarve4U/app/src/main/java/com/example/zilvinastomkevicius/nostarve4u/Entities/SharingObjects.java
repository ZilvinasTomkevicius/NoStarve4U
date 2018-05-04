package com.example.zilvinastomkevicius.nostarve4u.Entities;

import java.util.ArrayList;

/**
 * Created by Zilvinas Tomkevicius on 2/25/2018.
 */

/*
    A CLASS FOR SAVING OBJECTS
 */
public final class SharingObjects {

    public static ArrayList<Recipe> RecipeForTransfer = new ArrayList<>();
    public static  ArrayList<Recipe> RecipeForStatic = new ArrayList<>();

    public static Recipe OneRecipeForTransfer = new Recipe();
    public static Recipe OneRecipeForStatic = new Recipe();

    public static  ArrayList<Product> ProductForTransfer = new ArrayList<>();
    public static  ArrayList<Product> ProductForStatic = new ArrayList<>();

    public static RecipeProducts RecipeProductsForTransfer = new RecipeProducts();
}
