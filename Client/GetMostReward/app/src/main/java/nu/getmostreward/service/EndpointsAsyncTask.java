package nu.getmostreward.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nu.iot.getmostreward.server.myApi.MyApi;

/**
 * Created by mengchaowang on 5/7/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, Set<String>>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, Set<String>>... params) {
        if (myApiService == null) {  // Only do this once
            // end options for devappserver
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://infra-treat-129508.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0].first;
        Set<String> types = params[0].second;
        if(types == null || types.isEmpty()){
            types.add("Everything");
        }
        List<String> typeList = new ArrayList<String>(types);
        for(String s : typeList){
            Log.d("Selected Type in List:", s);
        }
        try {
            return myApiService.findCreditCard(typeList).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}