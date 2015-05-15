package bootcamp.android.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.R;
import bootcamp.android.models.Product;
import bootcamp.android.services.ImageDownloader;

public class ShoppingItemsListAdapter extends BaseAdapter {
  private final Context context;
  public List<Product> products = new ArrayList<>();
  private ImageDownloader imageDownloader;

  public ShoppingItemsListAdapter(Context context, List<Product> products){
    this.context = context;
    this.products = products;
  }

  @Override
  public int getCount() {
    return products.size();
  }

  @Override
  public Object getItem(int position) {
    return products.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
    }

    final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
    TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
    final Product product = products.get(position);
    titleTextView.setText(product.getTitle());

    AsyncTask<Void, Void, Bitmap> asyncTask = new AsyncTask<Void, Void, Bitmap>() {
      @Override
      protected Bitmap doInBackground(Void... params) {
        imageDownloader = new ImageDownloader();
        return imageDownloader.downloadImage(product.getImageUrl());
      }

      @Override
      protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
      }
    }.execute();

    return convertView;
  }

}
