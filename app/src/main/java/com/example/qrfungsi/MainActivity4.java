package com.example.qrfungsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity4 extends AppCompatActivity
{
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView = findViewById(R.id.results);
        button = findViewById(R.id.buttonFetch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();

            }
        });
    }

    public void fetchData () {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://demo.codeseasy.com/files/2022/parsing-json-data-in-android.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                this::parseJson, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Dont work");
            }
        });

        queue.add(stringRequest);
    }
    public void parseJson (String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("students");
            for (int i = 0; i < jsonArray.length(); i++) {
                String nameVar, percentageVar, gradeVar;
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                nameVar = jsonObject2.getString("name");
                percentageVar = jsonObject2.getString("percentage");
                gradeVar = jsonObject2.getString("grade");
                textView.append("Name: " + nameVar + "Percentage: " + percentageVar + " Grade: " + gradeVar + "\n");
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}