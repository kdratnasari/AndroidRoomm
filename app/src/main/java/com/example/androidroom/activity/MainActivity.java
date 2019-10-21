package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidroom.R;
import com.example.androidroom.adapter.RecyclerItemClickListener;
import com.example.androidroom.adapter.ProductListAdapter;
import com.example.androidroom.entity.Product;
import com.example.androidroom.utils.ApplicationStrings;
import com.example.androidroom.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private ProductViewModel mProductViewModel;
    private Button editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mProductViewModel.getmAllStudent().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable final List<Product> products) {
                // Update the cached copy of the words in the adapter.
                adapter.setmProducts(products);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        editButton = v.findViewById(R.id.button_edit);
                        //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                        deleteButton = v.findViewById(R.id.button_delete);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Delete button is called"+adapter.getProductAt(position).getProductName() , Toast.LENGTH_SHORT).show();

                                mProductViewModel.delete(adapter.getProductAt(position));
                            }
                        });

                        editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Edit button is called"+adapter.getProductAt(position).getProductName(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_ID, adapter.getProductAt(position).getProductId());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_NAME, adapter.getProductAt(position).getProductName());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_CODE, adapter.getProductAt(position).getProductCode());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_DESC, adapter.getProductAt(position).getProductDesc());
                                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST_CODE);
                            }
                        });
                    }
                })
        );


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String productName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_NAME);
            String productCode = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_CODE);
            String productDesc = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_DESC);

            Product product = new Product(productName,productCode, productDesc);
            mProductViewModel.insert(product);
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String productName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_NAME);
            String productCode = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_CODE);
            String productDesc = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_PRODUCT_DESC);

            Product product = new Product(productName,productCode, productDesc);
            product.setProductId(id);
            mProductViewModel.update(product);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }



}
