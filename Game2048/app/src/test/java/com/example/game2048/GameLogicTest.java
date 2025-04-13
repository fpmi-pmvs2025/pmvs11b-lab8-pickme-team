package com.example.game2048;

import org.junit.*;
import static org.junit.Assert.*;

public class GameLogicTest {
    @Test
    public void testInit() {
        GameLogic g = new GameLogic();
        assertNotNull(g.getBoard());
    }
}
