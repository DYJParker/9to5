package com.omievee.a9to5.News;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dave - Work on 5/5/2017.
 */

public class News_Fetcher {
    private static final String TAG = "News_Fetcher";
    public static final String URL_V = "https://newsapi.org/v1/articles?source=al-jazeera-english&sortBy=top&apiKey=f12a4585dde14bd39a8e0405c0925671";

    public static void volleyNEWS(Context ctx) {

        RequestQueue request = Volley.newRequestQueue(ctx);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, URL_V
                , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            News_Object news = new News_Object();

                            JSONObject root = response;
                            JSONArray articles = root.getJSONArray("articles");
                            for (int i = 0; i < articles.length(); i++) {
                                JSONObject article = articles.getJSONObject(i);
                                news.addArticles(
                                        article.getString("title"),
                                        article.getString("description"),
                                        article.getString("urlToImage")

                                );
                                Log.d(TAG, "onResponse: " + article);
                            }
                            InterfaceSingleton.getInstance().updateList(news);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        request.add(jsonArrayRequest);
    }
}
