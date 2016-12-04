package oleart.nil.marvelcomics;

import android.os.AsyncTask;
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

/**
 * Created by niloleart on 3/12/16.
 */
public class AsyncFetch extends AsyncTask<String, Void, JSONObject> {
    public static final String TAG = AsyncFetch.class.getSimpleName();

    @Override
    protected JSONObject doInBackground(String... params) {
        Log.e(TAG,"doInBackground");
        String response = null;
        try {
            Log.e(TAG,"creating connection");
            URL myURL = new URL(params[0]);
            HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();
            myConnection.setRequestMethod("GET");
            myConnection.setDoInput(true);

            myConnection.connect();
            Log.e(TAG,"Connected");
            int responseCode = myConnection.getResponseCode();
            Log.e(TAG, String.valueOf(responseCode));
            Log.e(TAG, String.valueOf(HttpURLConnection.HTTP_OK));
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Log.e(TAG,"response code OK");
                InputStream myInputStream = myConnection.getInputStream();
                response = convertStreamToString(myInputStream);
                JSONObject myJSONObject = new JSONObject(response);
                return myJSONObject;

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    private String convertStreamToString(InputStream is) {
        Log.e(TAG,"Convert Stream to String");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                Log.e(TAG,"appending result");
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.e(TAG,"onPostExecute");
            super.onPostExecute(jsonObject);
    }
}
