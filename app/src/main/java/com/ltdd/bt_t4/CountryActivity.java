package com.ltdd.bt_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        List<Country> img_details = getListData();
        final GridView gridView = findViewById(R.id.gridView);

        gridView.setAdapter(new CustomGridAdapter(this, img_details));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = gridView.getItemAtPosition(i);
                Country country = (Country)o;
                Toast.makeText(getApplicationContext(), "Selected: " + country, Toast.LENGTH_SHORT).show();
//                adapterView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.purple_500));
            }
        });
    }

    private List<Country> getListData(){
        List<Country> list = new ArrayList<Country>(Arrays.asList(
           new Country("Vietnam", "vietnam", 98000000),
           new Country("A", "vietnam", 98000000),
           new Country("B", "vietnam", 98000000),
           new Country("C", "vietnam", 98000000),
           new Country("D", "vietnam", 98000000),
           new Country("E", "vietnam", 98000000)
        ));
        return list;
    }
}