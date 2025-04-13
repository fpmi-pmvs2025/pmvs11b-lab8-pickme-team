package com.example.game2048;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.*;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class GameActivityTest {
    @Rule public ActivityTestRule<GameActivity> rule = new ActivityTestRule<>(GameActivity.class);

    @Test
    public void testUIExists() {
        onView(withId(R.id.gridLayout));
    }
}
