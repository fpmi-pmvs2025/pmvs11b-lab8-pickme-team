package com.example.game2048;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameManagerTest {
    private GameManager gameManager;

    @Before
    public void setUp() {
        gameManager = new GameManager();
    }

    @Test
    public void testInitialTiles() {
        int nonZeroCount = 0;
        int[][] grid = gameManager.getGrid();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] != 0) nonZeroCount++;
            }
        }
        // При инициализации должно быть 2 непустые ячейки.
        assertEquals(2, nonZeroCount);
    }

    @Test
    public void testMoveLeft() {
        // Устанавливаем специально известное состояние для теста.
        int[][] testGrid = {
                {2, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        // Подменяем внутренний grid
        gameManager = new GameManager() {
            {
                this.grid = testGrid;
            }
        };
        gameManager.moveLeft();
        int[][] result = gameManager.getGrid();
        assertEquals(4, result[0][0]);
        // В остальных ячейках нули, кроме одной (там новое число)
        int badCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0 && j == 0) continue; // Пропускаем первую ячейку
                if (result[i][j] != 0) badCount++;
            }
        }
        assertEquals(1, badCount);
    }
}
