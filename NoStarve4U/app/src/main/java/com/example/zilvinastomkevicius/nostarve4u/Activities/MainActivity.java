package com.example.zilvinastomkevicius.nostarve4u.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.R;
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

/*
    STARTING ACTIVITY
 */
public class MainActivity extends AppCompatActivity {

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

                HttpStatus code = productList.getStatusCode();

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
        }
    }

    /*
        STARTING ON CREATE METHOD
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
        METHODS FOR CHECKING GADGET'S INTERNET CONNECTION
     */
    private boolean isNetworkConnected() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    /*
        A BUTTON_CLICK METHOD WHICH INITIATES AN INTENT TO RecipeListActivity.java IF INTERNET CONNECTION IS AVAILABLE
     */
    public void IntentToProductListActivity(View view)
    {
        Intent intent = new Intent(this, ProductListActivity.class);

        if(isNetworkConnected() != false) {
            startActivity(intent);
        }

        else {

            Context context = getApplicationContext();
            CharSequence text = "Turn on Wi-fi or data roaming!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void IntentToAddRecipeActivity(View view) {

        Intent intent = new Intent(this, AddRecipeActivity.class);

        if(isNetworkConnected() != false) {

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/product/getlist";

            new MainActivity.ProductLoadingTask().execute(productUri);

            startActivity(intent);
        }

        else {

            Context context = getApplicationContext();
            CharSequence text = "Turn on Wi-fi or data roaming!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void IntentToMyRecipesActivity(View view)
    {
        Context context = getApplicationContext();
        CharSequence text = "Doesn't work yet!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
