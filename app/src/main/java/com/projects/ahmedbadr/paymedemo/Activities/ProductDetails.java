package com.projects.ahmedbadr.paymedemo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.ahmedbadr.paymedemo.API.ServiceBuilder;
import com.projects.ahmedbadr.paymedemo.API.ServiceInterfaces;
import com.projects.ahmedbadr.paymedemo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        submit = (Button) findViewById(R.id.submit);

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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAddPromo();
            }
        });
    }

    public void PerformAddPromo(){

        //progressDialog = ProgressDialog.show(getActivity(),"", "Loading. Please wait...", true);
        ServiceBuilder builder = new ServiceBuilder();
        ServiceInterfaces.AddPromo addPromo = builder.NewPromo();
        Call<ServiceInterfaces> apiModelCall = addPromo.AddPromo("qw","134ewq");
        apiModelCall.enqueue(new Callback<ServiceInterfaces>() {
            @Override
            public void onResponse(Call<ServiceInterfaces> call, Response<ServiceInterfaces> response) {
                startActivity(new Intent(getApplication(),Products.class));
                Toast.makeText(getApplication(), "Done Successfully", Toast.LENGTH_SHORT).show();
                Log.v("Success",response.message());
            }

            @Override
            public void onFailure(Call<ServiceInterfaces> call, Throwable t) {
                Log.v("Retrieve Error", t.toString());
                try {
                    Toast.makeText(getApplication(), "Connection Failed", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
