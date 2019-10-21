package com.example.androidroom.dao;

import com.example.androidroom.entity.Product;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface productDAO {
    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM products")
    void deleteAll();

    @Query("SELECT * from products ORDER BY product_name")
    LiveData<List<Product>> getAllProduct();
}
