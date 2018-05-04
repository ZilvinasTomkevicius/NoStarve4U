package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
import com.example.zilvinastomkevicius.nostarve4u.Entities.Recipe;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.R;
import com.example.zilvinastomkevicius.nostarve4u.Set.GetStringList;
import com.example.zilvinastomkevicius.nostarve4u.Set.SetList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

public class RecipeListActivity extends AppCompatActivity {

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

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/Matching/GetMatchedRecipes/";

            if (result == 405) {

                new RecipeListActivity.RecipeTask().execute(productUri);

            }

            else {

                ProgressBar progressBar = findViewById(R.id.loadingBar2);
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
        AN ASYNC TASK FOR GETTING MATCHED RECIPES
     */
    class RecipeTask extends AsyncTask<String, Void, ResponseEntity<Recipe[]>> {

        protected ResponseEntity<Recipe[]> doInBackground(String... uri) {

            final String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();

            ArrayList<Product> checkedProducts = SharingObjects.ProductForTransfer;

            try {

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Object> httpEntity = new HttpEntity<Object>(checkedProducts, httpHeaders);

                ResponseEntity<Recipe[]> recipeList = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Recipe[].class);

                return recipeList;

            } catch (Exception ex) {

                String message = ex.getMessage();

                return null;
            }
        }

        protected void onPostExecute(ResponseEntity<Recipe[]> result) {

            HttpStatus statusCode = result.getStatusCode();

            Recipe[] recipeList = result.getBody();

            SetList setList = new SetList();

            SharingObjects.RecipeForStatic = setList.SetObjectListRecipe(recipeList);

            Action(recipeList);
        }
    }

    /*
        A METHOD WHICH DISPLAYS LIST OF RECIPES
     */
    public void Action(Recipe[] recipeList) {

        ListView listView = (ListView) findViewById(R.id.listView2);

        SetList setList = new SetList();

        String[] recipeListName = setList.SetRecipeListName(recipeList);

        if(recipeListName.length == 0) {

            ProgressBar progressBar = findViewById(R.id.loadingBar2);
            progressBar.setVisibility(View.INVISIBLE);

            Context context = getApplicationContext();
            CharSequence text = "No recipes were found.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else {

            ProgressBar progressBar = findViewById(R.id.loadingBar2);

            progressBar.setVisibility(View.INVISIBLE);

            ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, recipeListName);
            listView.setAdapter(myAdapter);

            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    /*
        A BUTTON_CLICK METHOD WHICH COLLECTS CHECKED RECIPE AND INITIATES AN INTENT TO RecipeDetailsActivity.java
     */
    public void IntentToRecipeDetailsActivity(View view) {

        SharingObjects.OneRecipeForTransfer = null;

        ListView listView = (ListView) findViewById(R.id.listView2);

        GetStringList getStringList = new GetStringList();

        boolean[] checkedRecipe = getStringList.GetCheckedItems(listView);
        String recipe = getStringList.GetRecipeNameAtPosition(checkedRecipe, listView);

        SetList setLis = new SetList();

        if(recipe != null) {

            setLis.GetObjectFromStringRecipe(recipe);
        }

        Intent intent = new Intent(this, RecipeDetailsActivity.class);

        if(SharingObjects.OneRecipeForTransfer != null) {

            startActivity(intent);
        }

        else {

            Context context = getApplicationContext();
            CharSequence text = "Choose a recipe!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /*
        ON CREATE METHOD
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        ProgressBar progressBar = findViewById(R.id.loadingBar2);

        progressBar.setVisibility(View.VISIBLE);

        final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/Matching/GetMatchedRecipes/";

        new RecipeListActivity.CheckConnectionTask().execute(productUri);
    }
}
