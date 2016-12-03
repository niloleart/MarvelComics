package oleart.nil.marvelcomics;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public static final String MAIN_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    public static final String ON_CREATE_TAG = "MainActivity: OnCreate";
   public static final String URL = "http://gateway.marvel.com/v1/public/characters?ts=1&apikey=6cd9856dfd67d7e053798a2bf731b7a7&hash=91ac477fb8249d62c6489617ffcb97de";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(MAIN_ACTIVITY_TAG, "onCreate");
        try {
            JSONObject myJSONObject = new AsyncFetch().execute(URL).get();
            JSONObject secLevelJObject = myJSONObject.getJSONObject("data");
            JSONArray result = secLevelJObject.getJSONArray("results");
            Log.e(ON_CREATE_TAG,result.toString());

            ArrayList myListArray = new ArrayList();

            for (int i = 0; i < result.length(); i++) {

                //Log.e(ON_CREATE_TAG, result.getJSONObject(i).get("name").toString());
                myListArray.add(result.getJSONObject(i).get("name"));
                myListArray.add(result.getJSONObject(i).get("description"));
                JSONObject image = result.getJSONObject(i).getJSONObject("thumbnail");
                //Log.e(ON_CREATE_TAG,image.toString());
                String myImage = image.getString("path")+"."+image.getString("extension");
                myListArray.add(myImage);
                Log.e(ON_CREATE_TAG,myListArray.toString());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }
}
