package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import android.content.Intent;

import androidx.appcompat.widget.ActionMenuView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;




@RunWith(AndroidJUnit4.class)

public class EspressoTest {
    private static final String text = "Settings";
    private static final String settingUrl = "https://google.com";

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void intentsTest() {
        ViewInteraction settings = onView(withParent(isAssignableFrom(ActionMenuView.class)));
        ViewInteraction settingsItem = onView(allOf(withId(R.id.title), withText(text)));

        settings.check(matches(isDisplayed()));
        settings.perform(click());

        settingsItem.check(matches(isDisplayed()));

        Intents.init();
        settingsItem.perform(click());
        intended(allOf(
                hasData(settingUrl),
                hasAction(Intent.ACTION_VIEW)
        ));
        Intents.release();
    }

}