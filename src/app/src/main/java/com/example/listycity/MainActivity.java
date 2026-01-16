package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    private Button AddCityButton;
    private Button RemoveCityButton;
    View bottomView;
    private Button sendButton;
    EditText editText;
    int selectedIndex = -1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //View header = findViewById(R.id.header_title);
        cityList = findViewById(R.id.city_list);
        AddCityButton = findViewById(R.id.AddCityButton);
        RemoveCityButton = findViewById(R.id.RemoveCityButton);
        bottomView = findViewById(R.id.Bottom);
        sendButton = findViewById(R.id.sendButton);
        editText = findViewById(R.id.inputField);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });



        // Bottom bar dynamic spawning logic
        AddCityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bottomView.setVisibility(View.VISIBLE);


                sendButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        bottomView.setVisibility(View.GONE);
                        String value = editText.getText().toString();
                        //Log.d(value, value); debugging :)
                        dataList.add(value);
                        cityAdapter.notifyDataSetChanged();







                    }
                });

                // The following code snippet was adapted from OpenAI, ChatGPT,
                // Subject: Android ListView item removal using ArrayAdapter,
                // Date: 2026-01-16
                RemoveCityButton.setOnClickListener(v -> {
                    if (selectedIndex != -1) {
                        dataList.remove(selectedIndex);
                        cityAdapter.notifyDataSetChanged();
                        selectedIndex = -1;
                    }
                });




            }
        });





        //String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        //Bigger test list
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi","Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi","Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi","Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}