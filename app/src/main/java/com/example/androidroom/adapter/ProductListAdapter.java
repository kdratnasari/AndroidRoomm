package com.example.androidroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidroom.R;
import com.example.androidroom.entity.Product;
import com.example.androidroom.viewmodel.ProductViewModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
private ProductViewModel productViewModel;

    class ProductViewHolder extends RecyclerView.ViewHolder{
        private final TextView productNameView, productCodeView, productDescView;
        private final Button editButton, deleteButton;
        private ProductViewHolder (final View itemView) {
            super(itemView);
            productNameView = itemView.findViewById(R.id.textView_product_name);
            productCodeView = itemView.findViewById(R.id.textView_product_code);
            productDescView = itemView.findViewById(R.id.textView_product_desc);

            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);

        }
    }

    private final LayoutInflater mInflater;
    private List<Product> mProducts; // Cached copy of words

    public ProductListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        if (mProducts != null) {
            Product current = mProducts.get(position);
            holder.productNameView.setText("Name      : "+current.getProductName());
            holder.productCodeView.setText("Code   : "+current.getProductCode());
            holder.productDescView.setText("Desc  : "+current.getProductDesc());


        } else {
            // Covers the case of data not being ready yet.
            holder.productNameView.setText("No Product");
        }

    }

    public void setmProducts(List<Product> products){
        mProducts = products;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mProducts != null)
            return mProducts.size();
        else return 0;
    }

    public Product getProductAt (int position) {
        return mProducts.get(position);
    }
}
