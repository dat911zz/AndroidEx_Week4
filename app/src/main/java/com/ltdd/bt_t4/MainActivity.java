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
    private static List<String> lst = new ArrayList<String>(Arrays.asList("Grid View Country", "Media Player+", "Unknown", "Unknown"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lstV = findViewById(R.id.lstMenu);
        lstV.setAdapter(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.select_dialog_item_material, lst));
        lstV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(view.getContext(), "Item 1", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(view.getContext(), CountryActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Toast.makeText(view.getContext(), "Item 2", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(view.getContext(), MusicPlayer.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Toast.makeText(view.getContext(), "Item 3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(view.getContext(), "Item 4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}