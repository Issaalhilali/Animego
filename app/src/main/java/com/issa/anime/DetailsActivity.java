package com.issa.anime;

import android.graphics.Bitmap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    private ImageView imagedetail;
    private TextView Textdetails, animeDescription, hostry, depar, tsn, steate1;
    private ProgressBar progressBar;
    private Bitmap bitmap;
    private String url1 = "https://anime2001.com/anime/just-because/";


    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url = "";
    private Parseltem parseltem;


    private RequestQueue mQueue;


    private ImageView mFavButton;
    private Boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detail();

        mFavButton = findViewById(R.id.iv_favButton);


        hostry = (TextView) findViewById(R.id.hostry);
        depar = (TextView) findViewById(R.id.depart);
        tsn = (TextView) findViewById(R.id.tsnif);
        steate1 = (TextView) findViewById(R.id.steate);


        imagedetail = (ImageView) findViewById(R.id.imageView);
        imagedetail.setImageBitmap(bitmap);
        Textdetails = (TextView) findViewById(R.id.titleDet);
        progressBar = (ProgressBar) findViewById(R.id.progess);
        imagedetail.setVisibility(View.INVISIBLE);
        //Textdetails.setText(getIntent().getStringExtra("title"));
        //Picasso.get().load(getIntent().getStringExtra("image")).into(imagedetail);


        hostry.setVisibility(View.INVISIBLE);
        depar.setVisibility(View.INVISIBLE);
        tsn.setVisibility(View.INVISIBLE);
        steate1.setVisibility(View.INVISIBLE);
        Textdetails.setVisibility(View.INVISIBLE);

        //this is show detail for anime
        detail();
    }
    private void setFavorite(Boolean fav){
        if (fav) {
            isFav = true;
            mFavButton.setImageResource(R.drawable.ic_star_border_black_24dp);
        } else {
            isFav = false;
            mFavButton.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }


    private void detail() {
        requestQueue = Volley.newRequestQueue(this);
        String baseurl = "";
        String detailUrl = getIntent().getStringExtra("detailUrl");
        stringRequest = new StringRequest(Request.Method.GET, url = baseurl + detailUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Got Response");

                String newStr = null;
                try {
                    newStr = URLDecoder.decode(URLEncoder.encode(response, "iso8859-1"), "UTF-8");

                    Document doc = Jsoup.parse(newStr, "UTF-8");

                    Elements details = doc.select("div.details-content-info");
                    String title = "", detail, detail2, sta11;
                    for (Element linkdetail : details) {


                        title = linkdetail.child(0).child(0).select("span").text();
                        detail = linkdetail.child(0).child(1).select("span").text();
                        detail2 = linkdetail.child(0).child(3).select("span").text();
                        sta11 = linkdetail.child(0).child(4).select("span").text();

                        hostry.setText(title);
                        depar.setText(detail);
                        tsn.setText(detail2);
                        steate1.setText(sta11);
                        progressBar.setVisibility(View.INVISIBLE);
                        hostry.setVisibility(View.VISIBLE);
                        depar.setVisibility(View.VISIBLE);
                        tsn.setVisibility(View.VISIBLE);
                        steate1.setVisibility(View.VISIBLE);

                        Elements title1 = doc.select("div.head-title");
                        String tilt = "";
                        for (Element linktitle : title1) {

                            tilt = linktitle.child(0).select("h2").text();
                            Textdetails.setText(tilt);
                            Textdetails.setVisibility(View.VISIBLE);

                        }

                        Elements image = doc.select("div.details-content-poster");
                        String tiimage = "";
                        for (Element link : image) {

                            tiimage = link.child(0).select("img").attr("src");
                            Picasso.get().load(tiimage).into(imagedetail);
                            imagedetail.setVisibility(View.VISIBLE);

                        }


                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }

        }


                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error in connection");
            }
        });
        requestQueue.add(stringRequest);
        Log.d(TAG, "Made the request");
    }





}



