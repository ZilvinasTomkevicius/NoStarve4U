package com.example.zilvinastomkevicius.nostarve4u.AsyncTasks;

import android.os.AsyncTask;

import com.example.zilvinastomkevicius.nostarve4u.Activities.MainActivity;
import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
import com.example.zilvinastomkevicius.nostarve4u.Entities.SharingObjects;
import com.example.zilvinastomkevicius.nostarve4u.Fragments.ProductListFragment;
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

/**
 * Created by Zilvinas Tomkevicius on 2018-06-01.
 */

public class ProductAsyncTask {

    private MainActivity mainActivity = new MainActivity();

    /*
        ASYNC TASK FOR CHECKING CONNECTION TO THE SERVER
     */
    public class CheckConnectionTask extends AsyncTask<String, Void, Integer> {

        protected Integer doInBackground(String... uri) {

            String url = uri[0];

            try {

                URL myUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection)myUrl.openConnection();

                int code = urlConnection.getResponseCode();

                return code;
            }
            catch (Exception e) {

                e.printStackTrace();

                return 0;
            }
        }

        protected void onPostExecute(Integer result) {

            final String productUri = "https://otherpurplemouse9.conveyor.cloud/api/product/getlist";

            if(result == 200) {

                new ProductAsyncTask.ProductLoadingTask().execute(productUri);
            }

            else {

                mainActivity.NoConnectionProductList();
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

                return productList;

            } catch (Exception ex) {

                ex.printStackTrace();

                return null;
            }
        }

        protected void onPostExecute(ResponseEntity<Product[]> result) {

            Product[] products = result.getBody();

            SetList setList = new SetList();

            SharingObjects.ProductForStatic = setList.SetObjectListProduct(products);

            String[] productList = setList.SetProductListName(products);

            mainActivity.DisplayProductList(productList);
        }
    }
}
