package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.AsyncTasks.ProductAsyncTask;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.Fragments.AddRecipeFragment;
import com.example.zilvinastomkevicius.nostarve4u.Fragments.ProductListFragment;
import com.example.zilvinastomkevicius.nostarve4u.R;
import com.example.zilvinastomkevicius.nostarve4u.Set.SetList;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
        PROBLEMA TAME, KAD KAI NAUDOJU ProductListFragment OBJEKTA (PO KOMNTARU), TAI MAN META, KAD View, GRAZINTAS IS ProductListFragmen
        FRAGMENTO YRA null.
     */
    private ProductListFragment productListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationMain);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.navigation_search) {

                    productListFragment = new ProductListFragment();

                    android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                    fragmentManager1.beginTransaction().replace(R.id.fragment, productListFragment).commit();

                    Menu menu = navigation.getMenu();
                    MenuItem menuItem = menu.getItem(1);
                    menuItem.setChecked(true);

                    if(SharingObjects.ProductForStatic.size() > 0) {

                        SetList setList = new SetList();

                        String[] productList = setList.SetProductArrayListName(SharingObjects.ProductForStatic);

                        DisplayProductList(productList);
                    }

                    else {

                        final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/product/getlist";

                        ProductAsyncTask.CheckConnectionTask checkConnectionTask = new ProductAsyncTask().new CheckConnectionTask();
                        checkConnectionTask.execute(productUri);

                        productListFragment = new ProductListFragment();

                        productListFragment.ProductListLoadingBarVISIBLE();
                    }
                }

                else if (id == R.id.navigation_add) {

                    AddRecipeFragment addRecipeFragment = new AddRecipeFragment();

                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment, addRecipeFragment).commit();

                    Menu menu = navigation.getMenu();
                    MenuItem menuItem = menu.getItem(0);
                    menuItem.setChecked(true);
                }

                return false;
            }
        });
    }

    /*
      METHOD WHICH DISPLAYS PRODUCT LIST
   */
    public void DisplayProductList(String[] productList) {

        productListFragment = new ProductListFragment();

        ListView listView = productListFragment.ReturnProductListView();

        productListFragment.ProductListLoadingBarINVISIBLE();

        Arrays.sort(productList);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, productList);

        listView.setAdapter(myAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    public void NoConnectionProductList() {

        productListFragment = new ProductListFragment();

        productListFragment.ProductListLoadingBarINVISIBLE();

        Context context = getApplicationContext();
        CharSequence text = "No connection to the server.";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
