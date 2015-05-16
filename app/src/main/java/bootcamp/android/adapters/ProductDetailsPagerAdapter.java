package bootcamp.android.adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import bootcamp.android.fragments.ProductDetailsFragment;
import bootcamp.android.models.Product;

public class ProductDetailsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Product> products;

    public ProductDetailsPagerAdapter(FragmentManager fm,List<Product> products) {
        super(fm);
        this.products = products;
    }

    @Override
    public Fragment getItem(int position) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("current_product",products.get(position));
        productDetailsFragment.setArguments(bundle);
        return productDetailsFragment;
    }

    @Override
    public int getCount() {
        return products.size();
    }
}
