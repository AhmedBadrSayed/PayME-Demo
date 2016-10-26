package com.projects.ahmedbadr.paymedemo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.ahmedbadr.paymedemo.R;

public class ProductDetails extends AppCompatActivity {

    ImageView productImage;
    TextView productNametv;
    TextView productDescriptiontv;
    Button submit;
    private String productDescription, productName, productInf;
    private int Image;
    private String[] DetailsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productImage = (ImageView)findViewById(R.id.detProductImage);
        productNametv = (TextView)findViewById(R.id.detProductName);
        productDescriptiontv = (TextView)findViewById(R.id.detDescription);

        Intent intent = this.getIntent();
        if(intent!=null && intent.hasExtra(Intent.EXTRA_TEXT) &&  intent.hasExtra("produceImage")) {
            productInf = intent.getStringExtra(Intent.EXTRA_TEXT);
            DetailsArray = productInf.split("!");
            productName = DetailsArray[0];
            productDescription = DetailsArray[1];
            Image = intent.getIntExtra("produceImage",R.drawable.netflix);
        }

        productNametv.setText(productName);
        productDescriptiontv.setText(productDescription);
        productImage.setImageResource(Image);
    }
}
