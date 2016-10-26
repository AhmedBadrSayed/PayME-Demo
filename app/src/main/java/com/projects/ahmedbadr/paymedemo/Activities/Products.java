package com.projects.ahmedbadr.paymedemo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.projects.ahmedbadr.paymedemo.Adapters.MyRecyclerViewAdapter;
import com.projects.ahmedbadr.paymedemo.DataObjects.ProductsList;
import com.projects.ahmedbadr.paymedemo.R;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProductsList> productsList;
    private static String LOG_TAG = "CardViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        initializeData();
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(productsList);
        mRecyclerView.setAdapter(myRecyclerViewAdapter);

        myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(Products.this, ProductDetails.class);
                intent.putExtra(Intent.EXTRA_TEXT, productsList.get(position).productName+"!"
                        +productsList.get(position).productDescription);
                intent.putExtra("produceImage", productsList.get(position).productPhoto);
                startActivity(intent);
            }
        });
    }

    private void initializeData() {
        productsList = new ArrayList<>();
        productsList.add(0,new ProductsList("Netflix",getString(R.string.Netflix),R.drawable.netflix));
        productsList.add(1,new ProductsList("ebay",getString(R.string.ebay),R.drawable.ebay));
        productsList.add(2,new ProductsList("Rinboo Recharge Cards",getString(R.string.Rinboo),R.drawable.rinboo));
        productsList.add(3,new ProductsList("Telemedic SAL",getString(R.string.Telemedic),R.drawable.telemedic));
        productsList.add(4,new ProductsList("Anghami",getString(R.string.Anghami),R.drawable.anghami));
    }
}
