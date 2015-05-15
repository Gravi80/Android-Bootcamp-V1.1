package bootcamp.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;
import static bootcamp.android.constants.Constants.*;

public class ShoppingItemsListingActivity extends Activity {

  private ProgressDialog progressDialog;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    progressDialog = new ProgressDialog(ShoppingItemsListingActivity.this);

    // by default android doesn't allow to do complex(network call) on main thread( UI thread)
    // we have disabled it
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

    final ProductRepository productRepository = new ProductRepository();

    AsyncTask<String, Void, List<Product>> asyncTask = new AsyncTask<String, Void, List<Product>>() {

      @Override
      protected void onPreExecute() {
        super.onPreExecute();
//        progressDialog.show(ShoppingItemsListingActivity.this,"Loading..","loading products");
        progressDialog.show();
      }

      @Override
      protected void onPostExecute(List<Product> products) {
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ShoppingItemsListAdapter(ShoppingItemsListingActivity.this, products));
        progressDialog.dismiss();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Product product = (Product) adapterView.getAdapter().getItem(position);
            Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
            intent.putExtra(TITLE_KEY, product.getTitle());
            intent.putExtra(DESCRIPTION_KEY, product.getDescription());
            intent.putExtra(IMAGE_URL_KEY, product.getImageUrl());
            startActivity(intent);
          }
        });
      }

      @Override
      protected List<Product> doInBackground(String... params) {
        return productRepository.getProducts();
      }
    };

    asyncTask.execute();
  }
}
