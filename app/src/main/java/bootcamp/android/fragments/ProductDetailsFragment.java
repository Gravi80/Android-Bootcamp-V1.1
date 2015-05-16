package bootcamp.android.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidplugins.Callback;
import androidplugins.imagefetcher.ImageFetcher;
import bootcamp.android.R;
import bootcamp.android.models.Product;

import static bootcamp.android.constants.Constants.PRODUCT_KEY;

// don't overload the constructor of fragment
public class ProductDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View productDetailsView = inflater.inflate(R.layout.product_details, container, false);
        Product product = getArguments().getParcelable("current_product");
        TextView imageTitle = (TextView) productDetailsView.findViewById(R.id.product_title);
        imageTitle.setText(product.getTitle());
        ImageView imageView = (ImageView) productDetailsView.findViewById(R.id.product_image);
        ImageFetcher imageFetcher = new ImageFetcher(bitmapCallback(imageView), this.getActivity());
        imageFetcher.execute(product.getImageUrl());
        TextView issueDescription = (TextView) productDetailsView.findViewById(R.id.product_description);
        issueDescription.setText(product.getDescription());
        return productDetailsView;
    }

    private Callback<Bitmap> bitmapCallback(final ImageView imageView) {
        return new Callback<Bitmap>() {

            @Override
            public void execute(Bitmap object) {
                imageView.setImageBitmap(object);
            }
        };
    }
}
