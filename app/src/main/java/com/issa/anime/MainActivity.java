package com.issa.anime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ParseAdapter adapter;

    private ArrayList<Parseltem> parseltems = new ArrayList<>();

    private ProgressBar progressBar;

    // private ShimmerRecyclerView mShimmerRecyclerView, mShimmerRecyclerViewEspic;
    private String url = "https://anime2001.com/anime_genre/%d8%a3%d9%83%d8%b4%d9%86/";
  ;
    private RequestQueue mQueue, mQueue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBarlist);
        recyclerView = findViewById(R.id.lisr_RecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new ParseAdapter(parseltems, this);
        recyclerView.setAdapter(adapter);

        //mShimmerRecyclerView.setItemAnimator(new DefaultItemAnimator());



        mQueue = MySingleton.getInstance(this).getRequestQueue();
        StringRequest mRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String newStr = null;
                try {
                    newStr = URLDecoder.decode(URLEncoder.encode(response, "iso8859-1"), "UTF-8");

                    Document document = Jsoup.parse(newStr,"UTF-8");
                    Elements item = document.select("div.hovereffect");
                    for (Element link : item) {

                        String imgUrl = link.child(0).attr("src"); //First child is the a href link of image. You can get the url from here.
                        String detail = link.child(1).attr("href"); //The child of that element is the image tag itself. You can get the image from here.
                        String title = link.child(1).child(0).text();
                        parseltems.add(new Parseltem(imgUrl, title, detail));
                        Log.e("items", "img: " + imgUrl + " . title: " + title);
                    }

                    progressBar.setVisibility(View.INVISIBLE);
                    adapter.notifyItemRangeInserted(1, parseltems.size());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error in connection");
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(mRequest);
    }
}

