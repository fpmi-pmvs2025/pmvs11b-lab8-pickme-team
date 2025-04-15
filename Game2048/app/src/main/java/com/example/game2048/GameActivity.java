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

        gameManager = new GameManager();
        initGrid();
        updateGrid();

        // Загрузка случайного числа из внешнего API
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int randomNum = RandomNumberTask.getRandomNumber(); // получает число от 1 до 100
                // модифицируем счёт
                if (randomNum > 50) {
                    scoreModifier = 10;
                } else {
                    scoreModifier = -5;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        tvRandom.setText(String.format(Locale.getDefault(), "Случайное число: %d (%+d к счёту)", randomNum, scoreModifier));
                        updateScore();
                    }
                });
            }
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

        // Кнопки управления
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { gameManager.moveUp(); updateAfterMove(); }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { gameManager.moveDown(); updateAfterMove(); }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { gameManager.moveLeft(); updateAfterMove(); }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { gameManager.moveRight(); updateAfterMove(); }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Возвращаемся на главный экран
            }
        });

        btnUp.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.BOLD));
        btnDown.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.BOLD));
        btnLeft.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.BOLD));
        btnRight.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.BOLD));
        btnBack.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.BOLD));
        btnUp.setTextColor(getResources().getColor(R.color.sand_text));
        btnDown.setTextColor(getResources().getColor(R.color.sand_text));
        btnLeft.setTextColor(getResources().getColor(R.color.sand_text));
        btnRight.setTextColor(getResources().getColor(R.color.sand_text));
        btnBack.setTextColor(getResources().getColor(R.color.sand_text));

        btnUp.setBackgroundResource(R.drawable.btn_control_bg);
        btnDown.setBackgroundResource(R.drawable.btn_control_bg);
        btnLeft.setBackgroundResource(R.drawable.btn_control_bg);
        btnRight.setBackgroundResource(R.drawable.btn_control_bg);
        btnBack.setBackgroundResource(R.drawable.btn_back_bg);
    }

    private void updateAfterMove() {
        if (gameOverProcessed) return;
        updateGrid();
        updateScore();
        if (gameManager.isGameOver()) {
            // По окончании игры выводим итоговый счёт (с учётом модификатора)
            int finalScore = gameManager.getScore() + scoreModifier;
            // Если рекорд, сохраняем его в БД
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.insertRecord(finalScore);
            // Вывод сообщения об окончании игры
            tvScore.setText(String.format(Locale.getDefault(), "Игра окончена! Итоговый счёт: %d", finalScore));
            gameOverProcessed = true;
        }
    }

    private void updateScore() {
        int score = gameManager.getScore() + scoreModifier;
        tvScore.setText(String.format(Locale.getDefault(), "Счёт: %d", score));
    }

    // Инициализация ячеек поля в GridLayout (4х4)
    @SuppressLint("ResourceType")
    private void initGrid() {
        gridLayout.removeAllViews();
        gridLayout.setColumnCount(4);
        gridLayout.setRowCount(4);

        for (int i = 0; i < 16; i++) {
            TextView cell = new TextView(this);
            cell.setId(View.generateViewId());
            cell.setTextSize(20); // Меньше текст
            cell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            cell.setGravity(Gravity.CENTER);

            // Кастомный фон для ячейки
            cell.setBackgroundResource(R.drawable.custom_cell_background);

            // Цвет и стиль текста
            cell.setTextColor(getResources().getColor(R.color.sand_text));
            cell.setTypeface(android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.BOLD));

            // Отступы внутри ячейки
            cell.setPadding(2, 2, 2, 2);

            // Параметры размещения в GridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(8, 8, 8, 8); // Внешние отступы между ячейками
            cell.setLayoutParams(params);

            gridLayout.addView(cell);
        }
    }


    // Перерисовка поля
    private void updateGrid() {
        int[][] grid = gameManager.getGrid();
        for (int i = 0; i < 16; i++) {
            TextView cell = (TextView) gridLayout.getChildAt(i);
            int row = i / 4;
            int col = i % 4;
            int num = grid[row][col];
            cell.setText(num == 0 ? "" : String.valueOf(num));
        }
    }

    // Обработка событий касаний
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
