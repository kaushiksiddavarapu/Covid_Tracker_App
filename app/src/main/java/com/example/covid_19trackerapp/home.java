package com.example.covid_19trackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class home extends AppCompatActivity {
    CardView cardGlobal,cardIndia,cardSym;
    ImageButton menu_btn;

    TextView tvRecovered, tvActive,
            tvTodayCases, tvTotalDeaths,
            tvTodayDeaths;
    TextView tviRecovered, tviActive,
            tviTodayCases, tviTotalDeaths,
            tviTodayDeaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        cardGlobal = findViewById(R.id.cardGlobal);
        cardIndia=findViewById(R.id.cardIndia);
        cardSym=findViewById(R.id.cardSym);


        tvRecovered
                = findViewById(R.id.tvGrecD);
        tvActive
                = findViewById(R.id.tvGtotD);
        tvTodayCases
                = findViewById(R.id.tvGtotDN);
        tvTotalDeaths
                = findViewById(R.id.tvGdedD);
        tvTodayDeaths
                = findViewById(R.id.tvGdedDN);


        tviRecovered
                = findViewById(R.id.tvIrecD);
        tviActive
                = findViewById(R.id.tvItotD);
        tviTodayCases
                = findViewById(R.id.tvItotDN);
        tviTotalDeaths
                = findViewById(R.id.tvIdedD);
        tviTodayDeaths
                = findViewById(R.id.tvIdedDN);



        cardGlobal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), globalstat.class);
                startActivity(intent);
                setContentView(R.layout.activity_globalstat);
            }
        });

        cardIndia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), indiastat.class);
                startActivity(intent);
                setContentView(R.layout.activity_indiastat);
            }
        });

        cardSym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), precau.class);
                startActivity(intent);
                setContentView(R.layout.activity_precau);
            }
        });

        menu_btn = (ImageButton) findViewById(R.id.menu_btn);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(home.this, menu_btn);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.one:
                                Intent intent1 = new Intent(getApplicationContext(), globalstat.class);
                                startActivity(intent1);
                                setContentView(R.layout.activity_globalstat);
                                return true;
                            case R.id.two:
                                Intent intent2 = new Intent(getApplicationContext(), indiastat.class);
                                startActivity(intent2);
                                setContentView(R.layout.activity_indiastat);
                                return true;
                            case R.id.three:
                                Intent intent3 = new Intent(getApplicationContext(), precau.class);
                                startActivity(intent3);
                                setContentView(R.layout.activity_precau);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();
            }
        });

        fetchdata();
    }

    private void fetchdata() {

        String url = "https://corona.lmao.ninja/v2/all";

        String url2 = "https://corona.lmao.ninja/v2/countries/India";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try {

                    JSONObject jsonObject
                            = new JSONObject(
                            response.toString());


                    tvRecovered.setText(
                            jsonObject.getString(
                                    "recovered"));
                    tvActive.setText(
                            jsonObject.getString(
                                    "active"));
                    tvTodayCases.setText(
                            jsonObject.getString(
                                    "todayCases"));
                    tvTotalDeaths.setText(
                            jsonObject.getString(
                                    "deaths"));
                    tvTodayDeaths.setText(
                            jsonObject.getString(
                                    "todayDeaths"));

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(
                                home.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });



        StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try {

                    JSONObject jsonObject2
                            = new JSONObject(
                            response.toString());

                    tviRecovered.setText(
                            jsonObject2.getString(
                                    "recovered"));
                    tviActive.setText(
                            jsonObject2.getString(
                                    "active"));
                    tviTodayCases.setText(
                            jsonObject2.getString(
                                    "todayCases"));
                    tviTotalDeaths.setText(
                            jsonObject2.getString(
                                    "deaths"));
                    tviTodayDeaths.setText(
                            jsonObject2.getString(
                                    "todayDeaths"));

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(
                                home.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        requestQueue.add(request2);

    }
}