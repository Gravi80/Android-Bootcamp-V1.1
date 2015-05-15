package bootcamp.android.activities;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import bootcamp.android.R;

public class ProductDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        TextView title=(TextView) findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("product_detail"));

    }

}
