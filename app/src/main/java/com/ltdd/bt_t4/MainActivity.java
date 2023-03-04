package com.ltdd.bt_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static List<String> lst = new ArrayList<String>(Arrays.asList("Bài 1", "Bài 2", "Bài 3", "Bài 4"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lstV = findViewById(R.id.lstMenu);
        lstV.setAdapter(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lst));
        lstV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(view.getContext(), "Bài 1", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(view.getContext(), FlagGridView.class);
//                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(view.getContext(), "Bài 2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(view.getContext(), "Bài 3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(view.getContext(), "Bài 4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}