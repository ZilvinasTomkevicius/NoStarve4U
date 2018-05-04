package com.example.zilvinastomkevicius.nostarve4u.Set;

import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
import com.example.zilvinastomkevicius.nostarve4u.Entities.Recipe;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Zilvinas Tomkevicius on 2/13/2018.
 */

/*
    A CLASS FOR CONVERTING FROM STRING TO OBJECT OR FROM OBJECT TO STRING
 */
public class SetList {

    public String[] SetProductListName(Product[] productList) {

        String[] setProductList = new String[productList.length];

        for(int i = 0; i < productList.length; i++) {

            setProductList[i] = productList[i].Name;
        }

        return setProductList;
    }

    public String[] SetProductArrayListName(ArrayList<Product> productArrayList) {

        String[] setProductList = new String[productArrayList.size()];

        for(int i = 0; i < productArrayList.size(); i++) {

            setProductList[i] = productArrayList.get(i).Name;
        }

        return setProductList;
    }

    public String[] SetRecipeListName(Recipe[] recipeList) {

        String[] setRecipeList = new String[recipeList.length];

        for(int i = 0; i < recipeList.length; i++) {

            setRecipeList[i] = recipeList[i].Name;
        }

        return setRecipeList;
    }

    public String[] SetRecipeProductList(Recipe recipe) {

        String[] setProductList = new String[recipe.Ingredients.size()];

        for(int i = 0; i < recipe.Ingredients.size(); i++) {

            setProductList[i] = recipe.Ingredients.get(i).Name;
        }

        return setProductList;
    }

    public ArrayList<Recipe> SetObjectListRecipe(Recipe[] recipes) {

        ArrayList<Recipe> recipeArrayList = new ArrayList<>();

        for(int i = 0; i < recipes.length; i++) {

            Recipe recipe = new Recipe();

            recipe.ID = recipes[i].ID;
            recipe.Name = recipes[i].Name;
            recipe.Description = recipes[i].Description;
            recipe.CookingTime = recipes[i].CookingTime;
            recipe.Ingredients = recipes[i].Ingredients;

            recipeArrayList.add(recipe);
        }

        return recipeArrayList;
    }

    public ArrayList<Product> SetObjectListProduct(Product[] products) {

        ArrayList<Product> productArrayList = new ArrayList<>();

        for(int i = 0; i < products.length; i++) {

            Product product = new Product();

            product.ID = products[i].ID;
            product.Name = products[i].Name;
            product.Kind = products[i].Kind;

            productArrayList.add(product);
        }

        return productArrayList;
    }

    public void GetObjectFromStringRecipe(String recipe) {

        Recipe recipe1 = new Recipe();

        ArrayList<Recipe> allRecipes = SharingObjects.RecipeForStatic;

        for(int i = 0; i < allRecipes.size(); i++) {

            if(recipe.equals(allRecipes.get(i).Name)) {

                recipe1.ID = allRecipes.get(i).ID;
                recipe1.Name = allRecipes.get(i).Name;
                recipe1.Description = allRecipes.get(i).Description;
                recipe1.CookingTime = allRecipes.get(i).CookingTime;
                recipe1.Ingredients = allRecipes.get(i).Ingredients;
            }
        }

        SharingObjects.OneRecipeForTransfer = recipe1;
    }
}
