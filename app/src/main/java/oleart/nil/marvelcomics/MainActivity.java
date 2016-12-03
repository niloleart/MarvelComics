package oleart.nil.marvelcomics;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    public static final String URL = "http://gateway.marvel.com/v1/public/characters?ts=1&apikey=6cd9856dfd67d7e053798a2bf731b7a7&hash=91ac477fb8249d62c6489617ffcb97de&limit=100";

    private RecyclerView recyclerView;
    private HeroesAdapter mAdapter;
    private ArrayList<Hero> myListArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView = (RecyclerView) this.findViewById(R.id.my_recycler);
        Log.e(MAIN_ACTIVITY_TAG, "onCreate");
        try {
            JSONObject myJSONObject = new AsyncFetch().execute(URL).get();
            JSONObject secLevelJObject = myJSONObject.getJSONObject("data");
            JSONArray result = secLevelJObject.getJSONArray("results");
            Log.e(ON_CREATE_TAG, result.toString());

            myListArray = new ArrayList<>();

            for (int i = 0; i < result.length(); i++) {
                JSONObject image = result.getJSONObject(i).getJSONObject("thumbnail");
                myListArray.add(new Hero(
                        result.getJSONObject(i).getString("name"),
                        result.getJSONObject(i).getString("description"),
                        image.getString("path")+"."+image.getString("extension"))
                );
                Log.e(ON_CREATE_TAG, myListArray.toString());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        //this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(new HeroesAdapter(myListArray,this));

    }
}
