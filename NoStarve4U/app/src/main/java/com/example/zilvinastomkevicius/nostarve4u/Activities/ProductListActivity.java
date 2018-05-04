package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.R;
import com.example.zilvinastomkevicius.nostarve4u.Set.GetStringList;
import com.example.zilvinastomkevicius.nostarve4u.Set.SetList;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.jar.Attributes;
import java.lang.Thread.UncaughtExceptionHandler;

public class ProductListActivity extends AppCompatActivity {

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

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/product/getlist";

            if(result == 200) {

                new ProductListActivity.ProductLoadingTask().execute(productUri);
            }

            else {

                ProgressBar progressBar = findViewById(R.id.loadingBar);
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
        ASYNC TASK FOR GETTING THE PRODUCT LIST
     */
    class ProductLoadingTask extends AsyncTask<String, Void, ResponseEntity<Product[]>> {

        protected ResponseEntity<Product[]> doInBackground(String... uri) {

            final String url = uri[0];

            RestTemplate restTemplate = new RestTemplate();
            try {

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders httpHeaders = new HttpHeaders();

                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

                ResponseEntity<Product[]> productList = restTemplate.exchange(url, HttpMethod.GET, entity, Product[].class);

            //    HttpStatus code = productList.getStatusCode();

                return productList;

            } catch (Exception ex) {

                String message = ex.getMessage();

                return null;
            }
        }

        protected void onPostExecute(ResponseEntity<Product[]> result) {

            HttpStatus statusCode = result.getStatusCode();

            Product[] products = result.getBody();

            SetList setList = new SetList();

            SharingObjects.ProductForStatic = setList.SetObjectListProduct(products);

            Action(products);
        }
    }

    /*
        METHOD WHICH DISPLAYS PRODUCT LIST
     */
    public void Action(Product[] products) {

        ListView listView = (ListView) findViewById(R.id.listView1);

        SetList setList = new SetList();

        String[] productList = setList.SetProductListName(products);

        ArrayList<Product> productArrayList = SharingObjects.ProductForStatic;

        ProgressBar progressBar = findViewById(R.id.loadingBar);

        progressBar.setVisibility(View.INVISIBLE);

        Arrays.sort(productList);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, productList);

        listView.setAdapter(myAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    /*
       A BUTTON_CLICK METHOD WHICH COLLECTS CHECKED ITEMS AND INITIATES AN INTENT TO RecipeListActivity.java
     */
    public void IntentToRecipeListActivity(View view){

        Intent intent = new Intent(this, RecipeListActivity.class);

        ListView listView = (ListView) findViewById(R.id.listView1);

        ArrayList<Product> productArrayList = new ArrayList<>();

        for(int i = 0; i < SharingObjects.ProductForStatic.size(); i ++) {

            if(listView.isItemChecked(i)) {

                for(int j = 0; j < SharingObjects.ProductForStatic.size(); j++) {

                    if(SharingObjects.ProductForStatic.get(j).Name.equals(listView.getItemAtPosition(i).toString())) {

                        Product product = new Product();

                        product.Name = SharingObjects.ProductForStatic.get(j).Name;
                        product.ID = SharingObjects.ProductForStatic.get(j).ID;
                        product.Kind = SharingObjects.ProductForStatic.get(j).Kind;

                        productArrayList.add(product);

                        break;
                    }
                }
            }
        }

        SharingObjects.ProductForTransfer = productArrayList;

        if(SharingObjects.ProductForTransfer.size() > 0) {

            startActivity(intent);
        }

        else {

            Context context = getApplicationContext();
            CharSequence text = "You haven't chosen any products!";
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
        setContentView(R.layout.activity_product_list);

        ProgressBar progressBar = findViewById(R.id.loadingBar);

        progressBar.setVisibility(View.VISIBLE);

        final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/product/getlist";

        new ProductListActivity.CheckConnectionTask().execute(productUri);
    }
}
