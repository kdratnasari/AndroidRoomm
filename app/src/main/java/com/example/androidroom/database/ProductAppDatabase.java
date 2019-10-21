package com.example.androidroom.database;

import android.content.Context;

import com.example.androidroom.dao.productDAO;
import com.example.androidroom.entity.Product;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract  class ProductAppDatabase extends RoomDatabase {

    public abstract productDAO productDAO();

    private static volatile ProductAppDatabase INSTANCE;

    static ProductAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductAppDatabase.class, "product_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
