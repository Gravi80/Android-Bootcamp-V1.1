package androidplugins.imagefetcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.util.Date;

import androidplugins.Callback;
import bootcamp.android.R;

public class ImageFetcher extends AsyncTask<String, Void, Bitmap> {
    private Callback<Bitmap> bitmapCallback;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String filePath;

    public ImageFetcher(Callback<Bitmap> bitmapCallback, Context context) {
        this.bitmapCallback = bitmapCallback;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("image_cache",context.MODE_PRIVATE);
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String imageUrl = urls[0];
        Bitmap imageBitmap = null;
        imageBitmap = download(imageUrl);
        return imageBitmap;
    }

    private Bitmap download(String imageUrl) {
        Bitmap imageBitmap = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {

            String filePath = sharedPreferences.getString(imageUrl, null);
            if(filePath!=null){
                Log.e("FromCache", filePath);
                imageBitmap =readFromCache(filePath);
            }else{
                URL url = new URL(imageUrl);
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                HttpRequestBase httpRequest = new HttpGet(uri);
                HttpResponse httpResponse = httpClient.execute(httpRequest);
                StatusLine statusLine = httpResponse.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    InputStream content = httpEntity.getContent();
                    imageBitmap = BitmapFactory.decodeStream(content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File externalCacheDir = this.context.getExternalCacheDir();

        FileOutputStream fileOutputStream = null;
        try {
            filePath = externalCacheDir + "/file_" + System.currentTimeMillis();
            fileOutputStream = new FileOutputStream(filePath);
            imageBitmap.compress(Bitmap.CompressFormat.PNG,90,fileOutputStream);
            sharedPreferences.edit().putString(imageUrl,filePath).commit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return imageBitmap;
    }

    private Bitmap readFromCache(String filePath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        return BitmapFactory.decodeStream(fileInputStream,null,new BitmapFactory.Options());
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        bitmapCallback.execute(bitmap);
    }
}