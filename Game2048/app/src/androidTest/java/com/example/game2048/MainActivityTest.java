package com.example.game2048;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testStartGameButton() {
        onView(withId(R.id.btnStart)).perform(click());
        // Проверяем, что GameActivity отображается (например, элемент с идентификатором gridGame)
        onView(withId(R.id.gridGame)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecordsButton() {
        onView(withId(R.id.btnRecords)).perform(click());
        // Проверяем, что таблица рекордов отображается
        onView(withId(R.id.lvRecords)).check(matches(isDisplayed()));
    }
}
