package com.example.covid_19trackerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.StackedBarModel;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class indiastat extends AppCompatActivity {

    ImageButton backButton;
    PieChart mPieChart;
    PieChart todaypieChart;
    StackedBarChart mStackedBarChart;


    TextView tviRecovered, tviActive,
            tviTodayCases, tviTotalDeaths,
            tviTodayDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_indiastat);

        backButton = findViewById(R.id.back_btn1);
        mPieChart = findViewById(R.id.piechart);
        todaypieChart = findViewById(R.id.glopiechart2);
        mStackedBarChart =findViewById(R.id.glostackchart3);

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


        backButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                startActivity(intent);
                setContentView(R.layout.activity_home);
            }
        });



        setData();
        fetchdata();

    }

    private void fetchdata() {

        String url2 = "https://corona.lmao.ninja/v2/countries/India";

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
                                indiastat.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request2);
    }

    private void setData() {
        mPieChart.addPieSlice(new PieModel("Active", 32745457, Color.parseColor("#1E90FF")));
        mPieChart.addPieSlice(new PieModel("Recovered", 31926124, Color.parseColor("#42BE00")));
        mPieChart.addPieSlice(new PieModel("Death", 438411, Color.parseColor("#FF6347")));

        mPieChart.startAnimation();

        todaypieChart.addPieSlice(new PieModel("Active", 7888, Color.parseColor("#1E90FF")));
        todaypieChart.addPieSlice(new PieModel("Recovered", 10155 , Color.parseColor("#42BE00")));
        todaypieChart.addPieSlice(new PieModel("Death", 24, Color.parseColor("#FF6347")));

        todaypieChart.startAnimation();


        StackedBarModel s1 = new StackedBarModel("Andhra Pradesh");

        s1.addBar(new BarModel(2.3f, Color.parseColor("#1E90FF")));
        s1.addBar(new BarModel(2.3f, Color.parseColor("#FF6347")));
        s1.addBar(new BarModel(2.3f, Color.parseColor("#42BE00")));

        StackedBarModel s2 = new StackedBarModel("Tamilnadu");
        s2.addBar(new BarModel(1.1f, Color.parseColor("#1E90FF")));
        s2.addBar(new BarModel(2.7f, Color.parseColor("#FF6347")));
        s2.addBar(new BarModel(0.7f, Color.parseColor("#42BE00")));

        StackedBarModel s3 = new StackedBarModel("Kerala");

        s3.addBar(new BarModel(2.3f, Color.parseColor("#1E90FF")));
        s3.addBar(new BarModel(2.f, Color.parseColor("#FF6347")));
        s3.addBar(new BarModel(3.3f, Color.parseColor("#42BE00")));

        StackedBarModel s4 = new StackedBarModel("Telangana");
        s4.addBar(new BarModel(1.f, Color.parseColor("#1E90FF")));
        s4.addBar(new BarModel(4.2f, Color.parseColor("#FF6347")));
        s4.addBar(new BarModel(2.1f, Color.parseColor("#42BE00")));

        StackedBarModel s5 = new StackedBarModel("Goa");
        s5.addBar(new BarModel(1.f, Color.parseColor("#1E90FF")));
        s5.addBar(new BarModel(4.2f, Color.parseColor("#FF6347")));
        s5.addBar(new BarModel(2.1f, Color.parseColor("#42BE00")));

        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);
        mStackedBarChart.addBar(s4);
        mStackedBarChart.addBar(s5);

        mStackedBarChart.startAnimation();

    }
}