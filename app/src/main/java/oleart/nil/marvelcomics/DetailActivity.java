package oleart.nil.marvelcomics;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * Created by niloleart on 4/12/16.
 */
public class DetailActivity extends AppCompatActivity {
    public static final String DETAIL_ACTIVITY_TAG = DetailActivity.class.getSimpleName();
    //public static final String URL = "http://gateway.marvel.com/v1/public/characters?ts=1&apikey=6cd9856dfd67d7e053798a2bf731b7a7&hash=91ac477fb8249d62c6489617ffcb97de&limit=100";
    private ArrayList<Hero> myListArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name;
        String avatar;
        String description;
        String id;
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        Log.e("NAME", name);
        description = extras.getString("description");
        avatar = extras.getString("avatar");
        id = extras.getString("id");

        Log.e(DETAIL_ACTIVITY_TAG, id);
        TextView detailName = (TextView) findViewById(R.id.detail_hero_name);
        detailName.setText(name);
        TextView detailDescription = (TextView) findViewById(R.id.detail_hero_description);
        detailDescription.setText(description);
        ImageView detailAvatar = (ImageView) findViewById(R.id.detail_avatar);
        Picasso.with(this).load(avatar).into(detailAvatar);

        try {
            String URL_WITH_ID = "http://gateway.marvel.com/v1/public/characters/" + id + "?ts=1&apikey=6cd9856dfd67d7e053798a2bf731b7a7&hash=91ac477fb8249d62c6489617ffcb97de";
            Log.e(DETAIL_ACTIVITY_TAG, URL_WITH_ID);

            JSONObject myJSONObject = new AsyncFetch().execute(URL_WITH_ID).get();

            JSONObject data = myJSONObject.getJSONObject("data");

            JSONArray results = data.getJSONArray("results");

            JSONObject urlsObject = (JSONObject) results.get(0);

            JSONArray urls = urlsObject.getJSONArray("urls");

            JSONObject detail = (JSONObject) urls.get(0);
            JSONObject wiki = (JSONObject) urls.get(1);
            JSONObject comics = (JSONObject) urls.get(2);

            String detail_url = detail.getString("url");
                Log.e("DETAIL_URL",detail_url);
            String wiki_url = wiki.getString("url");
                Log.e("WIKI_URL",wiki_url);
            String comics_url = comics.getString("url");
                Log.e("COMICS_URL",comics_url);



        } catch (InterruptedException e) {
            Log.e(DETAIL_ACTIVITY_TAG, e.toString());
        } catch (ExecutionException e1) {
            Log.e(DETAIL_ACTIVITY_TAG, e1.toString());
        } catch (JSONException e2) {
            Log.e(DETAIL_ACTIVITY_TAG, e2.toString());
        }
    }
}
