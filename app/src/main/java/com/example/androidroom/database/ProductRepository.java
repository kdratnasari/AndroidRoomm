package com.example.androidroom.database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.androidroom.dao.productDAO;
import com.example.androidroom.entity.Product;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ProductRepository {
    private productDAO productDAO;
    private LiveData<List<Product>> mAllProduct;

   public ProductRepository(Application application) {
        ProductAppDatabase db = ProductAppDatabase.getDatabase(application);
        productDAO = db.productDAO();
       mAllProduct = productDAO.getAllProduct();
    }

    public LiveData<List<Product>> getmAllProduct() {
        return mAllProduct;
    }

    public void insert (Product product) {
        new insertAsyncTask(productDAO).execute(product);
    }

    public void delete(Product product) { new DeleteProductAsyncTask(productDAO).execute(product);
    }

    public void update(Product product) { new UpdateProductAsyncTask(productDAO).execute(product); }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private productDAO mAsyncTaskDao;

        insertAsyncTask(productDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private productDAO productDAO;

        private UpdateProductAsyncTask(productDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.update(products[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private productDAO productDAO;

        private DeleteProductAsyncTask(productDAO productDAO) {
            this.productDAO = productDAO;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDAO.delete(products[0]);
            return null;
        }
    }
}
