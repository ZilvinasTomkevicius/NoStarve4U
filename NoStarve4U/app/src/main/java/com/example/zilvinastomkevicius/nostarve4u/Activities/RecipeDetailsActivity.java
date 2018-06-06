package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
import com.example.zilvinastomkevicius.nostarve4u.Entities.Recipe;
import com.example.zilvinastomkevicius.nostarve4u.Entities.RecipeProducts;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.R;
import com.example.zilvinastomkevicius.nostarve4u.Set.SetList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class RecipeDetailsActivity extends AppCompatActivity {

    /*
       ASYNC TASK FOR CHECKING CONNECTION TO THE SERVER
    */
    class CheckConnectionTask extends AsyncTask<String, Void, Integer> {

        protected Integer doInBackground(String... uri) {

            String url = uri[0];

            try {

                URL myUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) myUrl.openConnection();

                int code = urlConnection.getResponseCode();

                return code;
            } catch (Exception e) {

                return 0;
            }
        }

        protected void onPostExecute(Integer result) {

            Recipe recipe = SharingObjects.OneRecipeForTransfer;

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/recipeproducts/get?recipeID=" + recipe.ID;

            if (result == 200) {

                new RecipeDetailsActivity.ProductTask().execute(productUri);

            }

            else {

                ProgressBar progressBar = findViewById(R.id.loadingBar3);
                progressBar.setVisibility(View.INVISIBLE);

                Context context = getApplicationContext();
                CharSequence text = "No connection to the server.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    /*
        AN ASYNC TASK FOR GETTING RECIPE PRODUCTS
     */
    class ProductTask extends AsyncTask<String, Void, ResponseEntity<RecipeProducts>> {

        protected ResponseEntity<RecipeProducts> doInBackground(String... uri) {

            final String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();
            try {
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

                ResponseEntity<RecipeProducts> recipeProductsID = restTemplate.exchange(url, HttpMethod.GET, entity, RecipeProducts.class);

                return recipeProductsID;

            } catch (Exception ex) {
                String message = ex.getMessage();

                return null;
            }
        }

        protected void onPostExecute(ResponseEntity<RecipeProducts> result) {

            HttpStatus statusCode = result.getStatusCode();

            RecipeProducts productsID = result.getBody();

            Action(productsID);
        }
    }

    /*
        A METHOD FOR PUTTING GOTTEN PRODUCTS TO THE RECIPE
     */
    public void Action(RecipeProducts recipeProducts) {

        ArrayList<Product> productArrayList = SharingObjects.ProductForStatic;
        Recipe recipe = SharingObjects.OneRecipeForTransfer;

        recipe.Ingredients = new ArrayList<>();

        for(int i = 0; i < recipeProducts.ProductIDList.size(); i++) {

            for(int j = 0; j < productArrayList.size(); j++) {

                if(recipeProducts.ProductIDList.get(i).equals(productArrayList.get(j).ID)) {

                    Product product = new Product();

                    product.ID = recipeProducts.ProductIDList.get(i);
                    product.Name = productArrayList.get(j).Name;
                    product.Kind = productArrayList.get(j).Kind;

                    recipe.Ingredients.add(product);
                }
            }
        }

        SharingObjects.OneRecipeForStatic = recipe;

        DisplayContent();
    }

    /*
        A METHOD FOR DISPLAYING RECIPE DETAILS
     */
    public void DisplayContent() {

        Recipe recipe1 = SharingObjects.OneRecipeForStatic;

        TextView textView1 = (TextView) findViewById(R.id.name);
        TextView textView2 = (TextView) findViewById(R.id.cookingTime);
        TextView textView3 = (TextView) findViewById(R.id.description);

        ProgressBar progressBar = findViewById(R.id.loadingBar3);
        TextView textView =  (TextView) findViewById(R.id.products);
        Button button = findViewById(R.id.addRecipe);
        LinearLayout linearLayout = findViewById(R.id.productInfo);

        progressBar.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);

        textView1.setText(recipe1.toStringName());
        textView2.setText("Description: " + recipe1.toStringDescription());
        textView3.setText("Cooking time: " + recipe1.toStringCookingTime() + " min.");

        ListView listView3 = (ListView) findViewById(R.id.listView3);

        SetList setList = new SetList();

        String[] productList = setList.SetRecipeProductList(recipe1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        listView3.setAdapter(myAdapter);

        listView3.setChoiceMode(ListView.CHOICE_MODE_NONE);
    }

    /*
        A BUTTON_CLICK METHOD WHICH INITIATES AN INTENT TO AddRecipeActivity.java
     */
    public void AddToMyRecipes(View view) {

        if(SharingObjects.isLoggedOn) {

            if(SharingObjects.MyRecipes.contains(SharingObjects.OneRecipeForStatic) == false) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("Do you want it to be added to My Recipes?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharingObjects.MyRecipes.add(SharingObjects.OneRecipeForStatic);

                                dialog.cancel();

                                Context context = getApplicationContext();
                                CharSequence text = "Recipe has been added to My Recipes!";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }

            else {

                Context context = getApplicationContext();
                CharSequence text = "The recipe is already added to My Recipes!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        else {

            Context context = getApplicationContext();
            CharSequence text = "You must log in in order to add!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void BackToRecipeList(View view) {

        SetUpWindowAnimations();

        startActivity(new Intent(RecipeDetailsActivity.this, RecipeListActivity.class));
        CustomIntent.customType(RecipeDetailsActivity.this, "fadein-to-fadeout");
    }

    private void SetUpWindowAnimations() {

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);

        Slide slide1 = new Slide();
        slide1.setDuration(1000);
        getWindow().setReturnTransition(slide1);
    }

    /*
        ON CREATE METHOD
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationRecipeDetails);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_search:
                        break;

                    case R.id.navigation_add:

                        if(SharingObjects.isLoggedOn) {

                            startActivity(new Intent(RecipeDetailsActivity.this, AddRecipeActivity.class));
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                            break;
                        }

                        else {

                            Context context = getApplicationContext();
                            CharSequence text = "You must log in in order to add!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            break;
                        }

                    case R.id.navigation_myrecipes:

                        if(SharingObjects.isLoggedOn) {

                            startActivity(new Intent(RecipeDetailsActivity.this, MyRecipesActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            break;
                        }

                        else {

                            Context context = getApplicationContext();
                            CharSequence text = "You must log in in order to have my recipes!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            break;
                        }
                }

                return false;
            }
        });

        ProgressBar progressBar = findViewById(R.id.loadingBar3);
        TextView textView =  (TextView) findViewById(R.id.products);
        Button button = findViewById(R.id.addRecipe);
        LinearLayout linearLayout = findViewById(R.id.productInfo);

        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);

        Recipe recipe = SharingObjects.OneRecipeForTransfer;

        final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/recipeproducts/get?recipeID=" + recipe.ID;

        new RecipeDetailsActivity.CheckConnectionTask().execute(productUri);
    }
}
