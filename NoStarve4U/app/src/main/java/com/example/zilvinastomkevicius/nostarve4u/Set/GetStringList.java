package com.example.zilvinastomkevicius.nostarve4u.Set;

import android.widget.ListView;

import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
import com.example.zilvinastomkevicius.nostarve4u.Entities.Recipe;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;

import java.util.ArrayList;

/**
 * Created by Zilvinas Tomkevicius on 2/17/2018.
 */

/*
    A CLASS FOR GETTING CHECKED ITEMS IN A LIST
 */
public class GetStringList {

    public boolean[] GetCheckedItems(ListView listView) {

        boolean[] checkedList = new boolean[listView.getAdapter().getCount()];

        for(int i = 0; i < listView.getAdapter().getCount(); i++) {

            if(listView.isItemChecked(i)) {

                checkedList[i] = true;
            }

            else {
                checkedList[i] = false;
            }
        }

        return checkedList;
    }

    public String GetRecipeNameAtPosition(boolean[] recipeID, ListView listView) {

        String recipe = null;

        for(int i = 0; i < recipeID.length; i++) {

            if(recipeID[i]) {

                Object product = listView.getItemAtPosition(i);

                recipe = product.toString();

                return recipe;
            }
        }

        return recipe;
    }

    public ArrayList<String> GetProductKind() {

        ArrayList<Product> productArrayList = SharingObjects.ProductForStatic;

        ArrayList<String> kindList = new ArrayList<>();

        for(int i = 0; i < productArrayList.size(); i++) {

            if(!kindList.contains(productArrayList.get(i).Kind)) {

                kindList.add(productArrayList.get(i).Kind);
            }
        }

        return kindList;
    }
}
