package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidroom.R;

import androidx.appcompat.app.AppCompatActivity;

public class NewProductActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_PRODUCT_ID = "com.example.androidroom.EXTRA_ID";
    public static final String EXTRA_REPLY_PRODUCT_NAME = "com.example.androidroom.NAME";
    public static final String EXTRA_REPLY_PRODUCT_CODE = "com.example.androidroom.CODE";
    public static final String EXTRA_REPLY_PRODUCT_DESC = "com.example.androidroom.DESC";

    private EditText mEditProductNameView, mEditProductCodeView, mEditProductDescView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        mEditProductNameView = findViewById(R.id.edit_product_name);
        mEditProductCodeView = findViewById(R.id.edit_product_code);
        mEditProductDescView = findViewById(R.id.edit_product_desc);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REPLY_PRODUCT_ID)) {
            setTitle("Edit Product");
            mEditProductNameView.setText(intent.getStringExtra(EXTRA_REPLY_PRODUCT_NAME));
            mEditProductCodeView.setText(intent.getStringExtra(EXTRA_REPLY_PRODUCT_CODE));
            mEditProductDescView.setText(intent.getStringExtra(EXTRA_REPLY_PRODUCT_DESC));

        } else {
            setTitle("Add Product");
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditProductNameView.getText()) || TextUtils.isEmpty(mEditProductCodeView.getText()) || TextUtils.isEmpty(mEditProductDescView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String productName = mEditProductNameView.getText().toString();
                    String productCode = mEditProductCodeView.getText().toString();
                    String productDesc = mEditProductDescView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_PRODUCT_NAME, productName);
                    replyIntent.putExtra(EXTRA_REPLY_PRODUCT_CODE, productCode);
                    replyIntent.putExtra(EXTRA_REPLY_PRODUCT_DESC, productDesc);

                    int id = getIntent().getIntExtra(EXTRA_REPLY_PRODUCT_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(EXTRA_REPLY_PRODUCT_ID, id);
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
