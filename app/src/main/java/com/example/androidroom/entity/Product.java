package com.example.androidroom.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int productId;

    @NonNull
    @ColumnInfo(name = "product_name")
    private String productName;

    @NonNull
    @ColumnInfo(name = "product_code")
    private String productCode;

    @NonNull
    @ColumnInfo(name = "product_desc")
    private String productDesc;


    public Product(@NonNull String productName, @NonNull String productCode, String productDesc) {
        this.productName = productName;
        this.productCode = productCode;
        this.productDesc = productDesc;

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    @NonNull
    public String getProductCode() { return productCode ; }

    public void setProductCode(@NonNull String productCode) {this.productCode = productCode; }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

}
