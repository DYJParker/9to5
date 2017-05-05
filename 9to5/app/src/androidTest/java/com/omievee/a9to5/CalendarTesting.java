package com.omievee.a9to5;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;

import com.omievee.a9to5.Calendar.CalendarEvents;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;
import com.omievee.a9to5.RecyclerView.RECYAdapter;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by Dave - Work on 5/5/2017.
 */

public class CalendarTesting {
    @Rule
    public IntentsTestRule<MainActivity> mainActivityActivityTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void CalendarVisibilityTest() {
        onView(withId(R.id.RECY)).perform(RecyclerViewActions.scrollToPosition(
                ((RECYAdapter)InterfaceSingleton.getInstance().getListener()).getItemCount()-1));
        onView(allOf(hasDescendant(withId(R.id.icon)),instanceOf(CardView.class))).check(matches(isDisplayed()));
    }

    @Test
    public void CalendarEntryTest(){
        String title = "Test Description";
        MockEntry(title);
        onView(withId(R.id.RECY)).perform(RecyclerViewActions.scrollToPosition(
                ((RECYAdapter)InterfaceSingleton.getInstance().getListener()).getItemCount()-1));
        onView(allOf(withId(R.id.title),isDisplayed())).check(matches(withText(title)));
    }

    @Test
    public void CalendarEmptyTest(){
        final CalendarEvents test = new CalendarEvents();
        mainActivityActivityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InterfaceSingleton.getInstance().updateList(test);
            }
        });

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.RECY)).perform(RecyclerViewActions.scrollToPosition(
                ((RECYAdapter)InterfaceSingleton.getInstance().getListener()).getItemCount()-1));
        onView(allOf(hasDescendant(withId(R.id.title)),instanceOf(LinearLayoutCompat.class)))
                .check(matches(not(isDisplayed())));
        onView(allOf(hasDescendant(withId(R.id.icon)),instanceOf(CardView.class)))
                .check(matches(hasDescendant(allOf(
                        withText("No calendar events found within the next 24 hours"),
                        isDisplayed()
                ))));
    }

    @Test
    public void CalendarShareTest(){
        String title = "testing";
        MockEntry(title);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.RECY)).perform(RecyclerViewActions.scrollToPosition(
                ((RECYAdapter)InterfaceSingleton.getInstance().getListener()).getItemCount()-1));
        onView(allOf(withId(R.id.icon),hasSibling(withText(title)))).
                perform(click());
        //intended(/*hasType("text/plain"),*/hasExtra(Intent.EXTRA_TEXT,
        //        matchesPattern("(I have .* today, starting at .*)")
        //));
        intended(anyIntent());
    }

    public void MockEntry(String title){
        final CalendarEvents test = new CalendarEvents();
        test.addInstance((long)0,0,title,0,0);
        mainActivityActivityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InterfaceSingleton.getInstance().updateList(test);
            }
        });
    }
}
