package com.projects.ahmedbadr.paymedemo.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.ahmedbadr.paymedemo.DataObjects.ProductsList;
import com.projects.ahmedbadr.paymedemo.R;

import java.util.List;

/**
 * Created by Ahmed Badr on 1/9/2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ProductsViewHolder> {

    List<ProductsList> productsLists;
    private static MyClickListener myClickListener;

    public MyRecyclerViewAdapter(List<ProductsList> productsLists){
        this.productsLists = productsLists;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        ProductsViewHolder pvh = new ProductsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.productName.setText(productsLists.get(position).productName);
        holder.descreption.setText(productsLists.get(position).productDescription);
        holder.productPhoto.setImageResource(productsLists.get(position).productPhoto);
    }

    @Override
    public int getItemCount() {
        return productsLists.size();
    }

    public void addItem(ProductsList productsList, int index) {
        productsLists.add(index, productsList);
        notifyItemInserted(index);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView productName;
        TextView descreption;
        ImageView productPhoto;


        public ProductsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            productName = (TextView)itemView.findViewById(R.id.ProductName);
            descreption = (TextView)itemView.findViewById(R.id.descreption);
            productPhoto = (ImageView)itemView.findViewById(R.id.productImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
