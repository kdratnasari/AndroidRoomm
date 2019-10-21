package com.example.androidroom.viewmodel;

import android.app.Application;

import com.example.androidroom.database.ProductRepository;
import com.example.androidroom.entity.Product;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mRepository;

    private LiveData<List<Product>> mAllStudent;

    public ProductViewModel(Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllStudent = mRepository.getmAllProduct();
    }

    public LiveData<List<Product>> getmAllStudent() { return mAllStudent; }

    public void insert(Product product) { mRepository.insert(product); }
    public void update(Product product) { mRepository.update(product); }
    public void delete(Product product) { mRepository.delete(product); }
}