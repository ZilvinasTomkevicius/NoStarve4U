package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class AddRecipeActivity extends AppCompatActivity {

    /*
       ASYNC TASK FOR CHECKING CONNECTION TO THE SERVER
    */
    class CheckConnectionTask extends AsyncTask<String, Void, Integer> {

        protected Integer doInBackground(String... uri) {

            String url = uri[0];

            try {

                URL myUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection)myUrl.openConnection();

                int code = urlConnection.getResponseCode();

                return code;
            }
            catch (Exception e) {

                return 0;
            }
        }

        protected void onPostExecute(Integer result) {

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/recipe/getrecipecount";

            if(result == 200) {

                new AddRecipeActivity.GetRecipeCount().execute(productUri);
            }

            else {

                ProgressBar progressBar = findViewById(R.id.loadingBar5);
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
       AN ASYNC TASK FOR ADDING A NEW RECIPE
    */
    class AddRecipetTask extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... uri) {

            final String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();
            try {
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                Recipe recipe = SharingObjects.OneRecipeForTransfer;

                HttpEntity<Object> entity = new HttpEntity<Object>(recipe, httpHeaders);

                restTemplate.exchange(url, HttpMethod.POST, entity, Recipe.class);

                return true;

            } catch (Exception ex) {

                String message = ex.getMessage();

                return false;
            }
        }

        protected void onPostExecute(Boolean result) {

            if(result) {

                final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/recipeproducts/add";

                new AddRecipeActivity.AddRecipeProductsTask().execute(productUri);
            }

            else {

                ProgressBar progressBar = findViewById(R.id.loadingBar5);
                progressBar.setVisibility(View.INVISIBLE);

                Context context = getApplicationContext();
                CharSequence text = "Recipe with this name already exists";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }
    }

    /*
        AN ASYNC TASK FOR GETTING COUNT OF RECIPES
     */
    class GetRecipeCount extends AsyncTask<String, Void, ResponseEntity<Integer>> {

        protected ResponseEntity<Integer> doInBackground(String... uri) {

            final String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();
            try {
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Object> entity = new HttpEntity<Object>(httpHeaders);

                ResponseEntity<Integer> count =  restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);

                return count;

            } catch (Exception ex) {

                String message = ex.getMessage();

                return null;
            }
        }

        protected void onPostExecute(ResponseEntity<Integer> result) {

            int count = result.getBody();

            AddRecipeProducts(count + 1);
        }
    }

    /*
        AN ASYNC TASK FOR ADDING RECIPE PRODUCTS TO DATABASE
     */
    class AddRecipeProductsTask extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... uri) {

            final String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();
            try {
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                RecipeProducts recipeProducts = SharingObjects.RecipeProductsForTransfer;

                HttpEntity<Object> entity = new HttpEntity<Object>(recipeProducts, httpHeaders);

                restTemplate.exchange(url, HttpMethod.POST, entity, RecipeProducts.class);

                return true;

            } catch (Exception ex) {

                String message = ex.getMessage();

                return false;
            }
        }

        protected void onPostExecute(Boolean result) {

            ProgressBar progressBar = findViewById(R.id.loadingBar5);
            progressBar.setVisibility(View.INVISIBLE);

            if(result) {

                Context context = getApplicationContext();
                CharSequence text = "Recipe has been added!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            else {

                Context context = getApplicationContext();
                CharSequence text = "Recipe with this name already exists";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    /*
        A BUTTON_CLICK METHOD FOR EXECUTING TASKS
     */
    public void AddRecipe(View view) {

        EditText editText1 = (EditText) findViewById(R.id.recName);
        EditText editText2 = (EditText) findViewById(R.id.recDescr);
        EditText editText3 = (EditText) findViewById(R.id.recCookT);

        String recName = editText1.getText().toString();
        String recDescr = editText2.getText().toString();
        String recCookT = editText3.getText().toString();

        if(recName.equals("")) {

            Context context = getApplicationContext();
            CharSequence text = "Write recipe name";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(recDescr.equals("")) {

            Context context = getApplicationContext();
            CharSequence text = "Write recipe description";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(recCookT.equals("")) {

            Context context = getApplicationContext();
            CharSequence text = "Set cooking time";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {

            int recCookTint = Integer.parseInt(recCookT);

            Recipe recipe = new Recipe();

            recipe.Name = recName;
            recipe.Description = recDescr;
            recipe.CookingTime = recCookTint;

            SharingObjects.OneRecipeForTransfer = recipe;

            ProgressBar progressBar = findViewById(R.id.loadingBar5);
            progressBar.setVisibility(View.VISIBLE);

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/recipe/getrecipecount";

            new AddRecipeActivity.CheckConnectionTask().execute(productUri);
        }
    }

    /*
        A METHOD FOR GETTING CHECKED PRODUCTS
     */
    public void AddRecipeProducts(int count) {

        ListView listView = (ListView) findViewById(R.id.listViewForRec);

        RecipeProducts recipeProducts = new RecipeProducts();

        recipeProducts.ProductIDList = new ArrayList<>();
        recipeProducts.RecipeID = count;

        for(int i = 0; i < SharingObjects.ProductForStatic.size(); i ++) {

            if(listView.isItemChecked(i)) {

                for(int j = 0; j < SharingObjects.ProductForStatic.size(); j++) {

                    if(SharingObjects.ProductForStatic.get(j).Name.equals(listView.getItemAtPosition(i).toString())) {

                        int prodID = SharingObjects.ProductForStatic.get(j).ID;

                        recipeProducts.ProductIDList.add(prodID);

                        break;
                    }
                }
            }
        }

        if(recipeProducts.ProductIDList.size() == 0) {

            ProgressBar progressBar = findViewById(R.id.loadingBar5);
            progressBar.setVisibility(View.INVISIBLE);

            Context context = getApplicationContext();
            CharSequence text = "Choose products";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {

            SharingObjects.RecipeProductsForTransfer = recipeProducts;

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/recipe/add";

            new AddRecipeActivity.AddRecipetTask().execute(productUri);
        }
    }

    /*
        ON CREATE METHOD
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        ProgressBar progressBar = findViewById(R.id.loadingBar5);
        progressBar.setVisibility(View.INVISIBLE);

        ArrayList<Product> productArrayList = SharingObjects.ProductForStatic;

        SetList setList = new SetList();

        String[] productList = setList.SetProductArrayListName(productArrayList);

        ListView listView = (ListView) findViewById(R.id.listViewForRec);
        Arrays.sort(productList);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, productList);
        listView.setAdapter(myAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}


