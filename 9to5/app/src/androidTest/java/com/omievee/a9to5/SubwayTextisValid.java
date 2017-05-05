package com.omievee.a9to5;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SubwayTextisValid {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void subwayTextisValid() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.m123), withText("1 |  2  | 3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("1 |  2  | 3")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.mACE), withText("A |  C  | E"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                4),
                        isDisplayed()));
        textView2.check(matches(withText("A |  C  | E")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.mNQR), withText("N | Q | R | W"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                8),
                        isDisplayed()));
        textView3.check(matches(withText("N | Q | R | W")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.m7), withText("7"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                12),
                        isDisplayed()));
        textView4.check(matches(withText("7")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.mShuttle), withText("S"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                16),
                        isDisplayed()));
        textView5.check(matches(withText("S")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.m456), withText("4 |  5  | 6"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                2),
                        isDisplayed()));
        textView6.check(matches(withText("4 |  5  | 6")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.mBDFM), withText(" B | D | F | M"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                6),
                        isDisplayed()));
        textView7.check(matches(withText(" B | D | F | M")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.mG), withText("G"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                10),
                        isDisplayed()));
        textView8.check(matches(withText("G")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.mJZ), withText("J-Z"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                14),
                        isDisplayed()));
        textView9.check(matches(withText("J-Z")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.mL), withText("L"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.CardView),
                                        0),
                                18),
                        isDisplayed()));
        textView10.check(matches(withText("L")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
