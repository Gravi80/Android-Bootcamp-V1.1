package bootcamp.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import java.util.List;

public class ShoppingItemsListingActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    ProductRepository productRepository = new ProductRepository();
    List<Product> products = productRepository.getProducts();
    GridView gridView = (GridView) findViewById(R.id.grid_view);
    gridView.setAdapter(new ShoppingItemsListAdapter(this, products));
  }
}
