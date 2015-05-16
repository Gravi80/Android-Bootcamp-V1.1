package bootcamp.android.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.R;
import bootcamp.android.adapters.ProductDetailsPagerAdapter;
import bootcamp.android.fragments.ProductDetailsFragment;
import bootcamp.android.models.Product;

import static bootcamp.android.constants.Constants.PRODUCT_KEY;


public class ProductDetailsActivity extends FragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.product_details_container);

    ArrayList<Product> products = getIntent().getExtras().getParcelableArrayList("products");

//    Product product = products.get(getIntent().getIntExtra("current_product_id", 0));

//    Bundle bundle = new Bundle();
//    bundle.putParcelable("current_product",product);

    FragmentManager fragManager = getSupportFragmentManager();
//    FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
//    ProductDetailsFragment fragment = new ProductDetailsFragment();
//    fragment.setArguments(bundle);
//    fragmentTransaction.add(R.id.product_details_container, fragment);
//    fragmentTransaction.commit();

    ProductDetailsPagerAdapter productDetailsPagerAdapter = new ProductDetailsPagerAdapter(fragManager,products);
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    viewPager.setAdapter(productDetailsPagerAdapter);
    viewPager.setCurrentItem(getIntent().getIntExtra("current_product_id", 0));
  }
}
