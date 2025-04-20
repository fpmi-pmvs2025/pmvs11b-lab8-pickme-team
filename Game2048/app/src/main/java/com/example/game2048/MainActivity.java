package com.example.game2048;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим кнопки
        Button btnStart = findViewById(R.id.btnStart);
        Button btnRecords = findViewById(R.id.btnRecords);
        Button btnExit = findViewById(R.id.btnExit);

        // Обработчик для кнопки "Начать игру"
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });

        // Обработчик для кнопки "Рекорды"
        btnRecords.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecordsActivity.class);
            startActivity(intent);
        });

        // Обработчик для кнопки "Выход"
        btnExit.setOnClickListener(v -> finish());
    }
}
