package com.example.game2048;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {

    private ListView lvRecords;
    private ProgressBar progressBar;
    private Button btnBack;
    private DBHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> recordsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        lvRecords = findViewById(R.id.lvRecords);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setBackgroundResource(R.drawable.btn_back_bg);
        dbHelper = new DBHelper(this);
        recordsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.record_item, R.id.tvRecordItem, recordsList);
        lvRecords.setAdapter(adapter);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadRecords();
    }

    private void loadRecords() {
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Integer> records = dbHelper.getTopRecords();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        recordsList.clear();
                        int rank = 1;
                        for (Integer rec : records) {
                            recordsList.add(rank++ + ". " + rec);
                        }
                        // для демонстрации загрузки
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }
}
