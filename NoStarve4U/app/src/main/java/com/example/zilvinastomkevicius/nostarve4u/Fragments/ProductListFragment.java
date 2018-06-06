package com.example.zilvinastomkevicius.nostarve4u.Fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zilvinastomkevicius.nostarve4u.Activities.MainActivity;
import com.example.zilvinastomkevicius.nostarve4u.Activities.ProductListActivity;
import com.example.zilvinastomkevicius.nostarve4u.Entities.Product;
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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    private ListView productListView;
    private ProgressBar productListLoadingBar;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

       productListView = (ListView) view.findViewById(R.id.productListView);
       productListLoadingBar = (ProgressBar) view.findViewById(R.id.productListLoadingBar);

       return view;
    }

    public ListView ReturnProductListView() {

        return productListView;
    }

    public void ProductListLoadingBarVISIBLE() {

        productListLoadingBar.setVisibility(View.VISIBLE);
    }

    public void ProductListLoadingBarINVISIBLE() {

        productListLoadingBar.setVisibility(View.INVISIBLE);
    }
}
