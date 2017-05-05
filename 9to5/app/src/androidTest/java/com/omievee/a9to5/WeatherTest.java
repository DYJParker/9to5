package com.omievee.a9to5;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by justinstanger on 5/4/17.
 */

public class WeatherTest {
        @Rule
        public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
                new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void testIfWeatherIsDisplayed(){

        onView(withId(R.id.temperature_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.description_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.hi_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.low_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.city_textview)).check(matches(isDisplayed()));
    }

    @Test
    public void testIfWeatherFailureIsDisplayed(){


        //InterfaceSingleton.getInstance().updateList(new WeatherInfoObject(false));

        onView(withId(R.id.weather_not_conected)).check(matches(isDisplayed()));

        onView(withId(R.id.temperature_textview)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.description_textview)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.hi_textview)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.low_textview)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.city_textview)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        }
    }

