package bootcamp.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import bootcamp.android.R;

public class TitleTextView extends TextView{
  public TitleTextView(Context context) {
    super(context, null);
  }

  public TitleTextView(Context context, AttributeSet attrs) {
    super(context, attrs, R.attr.textViewHighlight);
  }

  public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

}