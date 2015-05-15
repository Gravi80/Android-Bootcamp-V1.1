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
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import java.util.List;

public class ShoppingItemsListingActivity extends Activity {

    private ProductRepository productRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        productRepository = new ProductRepository();
        GridView layout = (GridView) findViewById(R.id.listofitems);
        List<Product> products  = productRepository.getProducts();
//        android.R.layout.simple_list_item_1  -> default Layout resource
        layout.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products));
    }
}
