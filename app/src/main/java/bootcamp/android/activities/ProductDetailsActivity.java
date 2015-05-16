package bootcamp.android.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import bootcamp.android.R;
import bootcamp.android.fragments.ProductDetailsFragment;


public class ProductDetailsActivity extends FragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.product_details_container);

    Bundle extraArguments = getIntent().getExtras();

    FragmentManager fragManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
    ProductDetailsFragment fragment = new ProductDetailsFragment();
    fragment.setArguments(extraArguments);
    fragmentTransaction.add(R.id.product_details_container, fragment);
    fragmentTransaction.commit();
  }
}
