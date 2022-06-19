package com.example.hackillinois;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.hackillinois.adapters.LeaderboardAdapter;
import com.example.hackillinois.models.Leaderboard;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Gson g = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());

        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fetchDataAndInitializeView(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fetchDataAndInitializeView("10");
    }

    void fetchDataAndInitializeView(String limit){

        AndroidNetworking.get("https://api.hackillinois.org/profile/leaderboard/?limit="+String.valueOf(limit))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        ArrayList<Leaderboard> leaderboardArrayList = new ArrayList<>();
                        try {
                            Log.d(TAG, "leaderboardList size : " + response.getString("profiles"));
                            Log.d(TAG, String.valueOf(response.getString("profiles").getClass()));

                            JSONArray array = new JSONArray(response.getString("profiles"));

                            for(int i=0; i < array.length(); i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                Leaderboard l = g.fromJson(String.valueOf(object), Leaderboard.class);
                                leaderboardArrayList.add(l);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        initRecyclerView(leaderboardArrayList);
                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Log.d(TAG, "Error occured  " + anError);
                    }
                });

    }
    void initRecyclerView(ArrayList<Leaderboard> leaderboardArrayList){
        Log.d("DEBUG","Inisde Init Recycler View");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LeaderboardAdapter leaderboardAdapter = new LeaderboardAdapter(this, leaderboardArrayList);
        recyclerView.setAdapter(leaderboardAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

    }
}