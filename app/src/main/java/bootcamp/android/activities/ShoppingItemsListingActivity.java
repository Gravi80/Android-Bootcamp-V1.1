package bootcamp.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

import static bootcamp.android.constants.Constants.DESCRIPTION_KEY;
import static bootcamp.android.constants.Constants.IMAGE_URL_KEY;
import static bootcamp.android.constants.Constants.TITLE_KEY;

public class ShoppingItemsListingActivity extends Activity {

  private List<Product> products;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

    final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...", true, true);
    final GridView gridView = (GridView) findViewById(R.id.grid_view);

    new Thread(new Runnable() {
      @Override
      public void run() {
        ProductRepository productRepository = new ProductRepository();
        products = productRepository.getProducts();
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            gridView.setAdapter(new ShoppingItemsListAdapter(ShoppingItemsListingActivity.this, products));
            progressDialog.dismiss();
          }
        });
      }
    }).start();
    
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
}
