package bootcamp.android.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.R;
import bootcamp.android.models.Product;


public class CustomAdapter extends BaseAdapter {
    private final Activity activity;
    private List<Product> elements;

    public CustomAdapter(Activity activity,List<Product> elements) {
        this.elements = elements;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //recycling
        // first param is your Layout
        // for height,width calculation use parent but don't attach it to parent(GridView)  child is product cell
        View view = (convertView==null) ? activity.getLayoutInflater().inflate(R.layout.product,parent,false) : convertView;

        TextView title = (TextView)view.findViewById(R.id.title);
        ImageView image = (ImageView)view.findViewById(R.id.image);

        title.setText(this.elements.get(position).getTitle());

        image.setImageBitmap(BitmapFactory.decodeResource(activity.getResources(),this.elements.get(position).getDrawable()));
        return view;
    }
}
