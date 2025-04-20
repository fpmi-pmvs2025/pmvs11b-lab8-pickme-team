package com.example.game2048;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    private GameManager gameManager;
    private boolean gameOverProcessed = false;
    private GridLayout gridLayout;
    private TextView tvScore, tvRandom;
    private Button btnUp, btnDown, btnLeft, btnRight, btnBack;
    // Для детекции свайпов
    private GestureDetector gestureDetector;
    private int scoreModifier = 0; // очки, добавляемые/вычитаемые в начале игры

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridLayout = findViewById(R.id.gridGame);
        tvScore = findViewById(R.id.tvScore);
        tvRandom = findViewById(R.id.tvRandom);
        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnBack = findViewById(R.id.btnBack);

        // Настройка сетки
        gridLayout.setBackgroundResource(R.drawable.cell_background);
        gridLayout.setPadding(16, 16, 16, 16);

        gameManager = new GameManager();
        initGrid();
        updateGrid();

        // Загрузка случайного числа из внешнего API
        new Thread(() -> {
            final int randomNum = RandomNumberTask.getRandomNumber();
            if (randomNum > 50) {
                scoreModifier = 10;
            } else {
                scoreModifier = -5;
            }
            new Handler(Looper.getMainLooper()).post(() -> {
                tvRandom.setText(String.format(Locale.getDefault(), 
                    "Случайное число: %d (%+d к счёту)", randomNum, scoreModifier));
                updateScore();
            });
        }).start();

        // Обработка свайпов
        gestureDetector = new GestureDetector(this, new SwipeGestureDetector() {
            @Override
            public void onSwipeTop() {
                gameManager.moveUp();
                updateAfterMove();
            }
            @Override
            public void onSwipeRight() {
                gameManager.moveRight();
                updateAfterMove();
            }
            @Override
            public void onSwipeLeft() {
                gameManager.moveLeft();
                updateAfterMove();
            }
            @Override
            public void onSwipeBottom() {
                gameManager.moveDown();
                updateAfterMove();
            }
        });

        // Обработчики для кнопок управления
        btnUp.setOnClickListener(v -> {
            gameManager.moveUp();
            updateAfterMove();
        });

        btnDown.setOnClickListener(v -> {
            gameManager.moveDown();
            updateAfterMove();
        });

        btnLeft.setOnClickListener(v -> {
            gameManager.moveLeft();
            updateAfterMove();
        });

        btnRight.setOnClickListener(v -> {
            gameManager.moveRight();
            updateAfterMove();
        });

        // Кнопка "Назад"
        btnBack.setOnClickListener(v -> finish());
    }

    private void updateAfterMove() {
        if (gameOverProcessed) return;
        updateGrid();
        updateScore();
        if (gameManager.isGameOver()) {
            int finalScore = gameManager.getScore() + scoreModifier;
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.insertRecord(finalScore);
            tvScore.setText(String.format(Locale.getDefault(), 
                "Игра окончена! Итоговый счёт: %d", finalScore));
            gameOverProcessed = true;
        }
    }

    private void updateScore() {
        int score = gameManager.getScore() + scoreModifier;
        tvScore.setText(String.format(Locale.getDefault(), "Счёт: %d", score));
    }

    @SuppressLint("ResourceType")
    private void initGrid() {
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(4);
        gridLayout.setRowCount(4);

        for (int i = 0; i < 16; i++) {
            TextView cell = new TextView(this);
            cell.setId(View.generateViewId());
            cell.setTextSize(24);
            cell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            cell.setGravity(Gravity.CENTER);
            cell.setBackgroundResource(R.drawable.custom_cell_background);
            cell.setTextColor(getResources().getColor(R.color.background));
            cell.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.BOLD));
            cell.setPadding(8, 8, 8, 8);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(4, 4, 4, 4);
            cell.setLayoutParams(params);

            gridLayout.addView(cell);
        }
    }

    private void updateGrid() {
        int[][] grid = gameManager.getGrid();
        for (int i = 0; i < 16; i++) {
            TextView cell = (TextView) gridLayout.getChildAt(i);
            int row = i / 4;
            int col = i % 4;
            int num = grid[row][col];
            
            if (num == 0) {
                cell.setText("");
                cell.setBackgroundResource(R.drawable.custom_cell_background);
            } else {
                cell.setText(String.valueOf(num));
                // Изменяем размер текста в зависимости от числа
                if (num < 100) {
                    cell.setTextSize(24);
                } else if (num < 1000) {
                    cell.setTextSize(20);
                } else {
                    cell.setTextSize(16);
                }
            }
        }
    }

    // Обработка событий касаний
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}