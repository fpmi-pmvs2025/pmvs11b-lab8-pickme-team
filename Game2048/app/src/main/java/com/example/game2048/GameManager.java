package com.example.game2048;

import java.util.Random;

public class GameManager {
    public int[][] grid;
    private int score;
    private Random random;

    public GameManager() {
        grid = new int[4][4];
        score = 0;
        random = new Random();
        addRandomTile();
        addRandomTile();
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        // Простейшая проверка: если нет пустых клеток, считаем игру завершённой
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) return false;
            }
        }
        return true;
    }

    public void moveLeft() {
        boolean moved = false;
        for (int i = 0; i < 4; i++) {
            int[] row = grid[i];
            int[] compressed = compress(row);
            int[] merged = merge(compressed);
            int[] newRow = compress(merged);
            if (!equal(row, newRow)) {
                moved = true;
            }
            grid[i] = newRow;
        }
        if (moved) addRandomTile();
    }

    public void moveRight() {
        rotateGrid(180);
        moveLeft();
        rotateGrid(180);
    }

    public void moveUp() {
        rotateGrid(270);
        moveLeft();
        rotateGrid(90);
    }

    public void moveDown() {
        rotateGrid(90);
        moveLeft();
        rotateGrid(270);
    }

    // Сжатие строки влево (убираем нули между числами)
    private int[] compress(int[] row) {
        int[] newRow = new int[4];
        int index = 0;
        for (int num : row) {
            if (num != 0) {
                newRow[index++] = num;
            }
        }
        return newRow;
    }

    // Слияние одинаковых соседних клеток
    private int[] merge(int[] row) {
        for (int i = 0; i < 3; i++) {
            if (row[i] != 0 && row[i] == row[i+1]) {
                row[i] *= 2;
                score += row[i];
                row[i+1] = 0;
            }
        }
        return row;
    }

    // Поворот сетки на угол (90, 180, 270)
    private void rotateGrid(int angle) {
        int times = angle / 90;
        for (int t = 0; t < times; t++) {
            int[][] newGrid = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    newGrid[i][j] = grid[4 - j - 1][i];
                }
            }
            grid = newGrid;
        }
    }

    private boolean equal(int[] row1, int[] row2) {
        for (int i = 0; i < 4; i++) {
            if (row1[i] != row2[i]) return false;
        }
        return true;
    }

    private void addRandomTile() {
        int row, col;
        if (isGameOver()) return;
        do {
            row = random.nextInt(4);
            col = random.nextInt(4);
        } while (grid[row][col] != 0);
        grid[row][col] = random.nextInt(10) == 0 ? 4 : 2; // шанс на 4
    }
}
