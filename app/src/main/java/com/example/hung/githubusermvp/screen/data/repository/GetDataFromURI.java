package com.example.hung.githubusermvp.screen.data.repository;

import android.os.AsyncTask;

import com.example.hung.githubusermvp.screen.data.model.GithubUser;
import com.example.hung.githubusermvp.screen.data.model.Item;
import com.example.hung.githubusermvp.screen.screen.userlist.UserPresenterIplm;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetDataFromURI  extends AsyncTask<String, Void, ArrayList<Item>> {
    private UserPresenterIplm mOnFetchDataListener;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Item> doInBackground(String... strings) {

        try {
            String json = getJSONStringFromURL(strings[0]);
            GithubUser usersList = new Gson().fromJson(json,GithubUser.class);
            return usersList.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Item> users) {
        super.onPostExecute(users);
        mOnFetchDataListener.fetchSucces(users);
    }

    private String getJSONStringFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        String jsonString = sb.toString();

        urlConnection.disconnect();
        return jsonString;
    }

    public void setmOnFetchDataListener(UserPresenterIplm mOnFetchDataListener) {
        this.mOnFetchDataListener = mOnFetchDataListener;
    }
}
