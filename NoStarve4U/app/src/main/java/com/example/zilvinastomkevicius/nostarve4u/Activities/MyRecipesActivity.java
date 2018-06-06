package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.R;

public class MyRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_myrecipes);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_search:

                        startActivity(new Intent(MyRecipesActivity.this, ProductListActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;


                    case R.id.navigation_add:

                        startActivity(new Intent(MyRecipesActivity.this, AddRecipeActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;


                    case R.id.navigation_myrecipes:

                        break;
                }

                return false;
            }
        });
    }
}
