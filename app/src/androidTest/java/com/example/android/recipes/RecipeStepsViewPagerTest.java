package com.example.android.recipes;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Baraa Hesham on 6/24/2018.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeStepsViewPagerTest {
    private static final String VIDEO_TEXT="No Video";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule=new ActivityTestRule<>(MainActivity.class);
    private IdlingResource idlingResource;


    @Before
    public void registerIdlingResources(){
        idlingResource=mainActivityActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }
    @Test
    public void clickRecipeItem_OpensVideoFragmentActivity_SpecificVideo(){

        onView(withId(R.id.rv))

                .perform(actionOnItemAtPosition(1,click()));

        onView(withId(R.id.recipe_details_card_rv))

                .perform(actionOnItemAtPosition(0,click()));

        onView(allOf(withId(R.id.steps_description),isDisplayed()))

                .perform(swipeLeft());

        onView(allOf(withId(R.id.video_place_holder),isDisplayed())).check(matches(withText(VIDEO_TEXT)));

    }
    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
