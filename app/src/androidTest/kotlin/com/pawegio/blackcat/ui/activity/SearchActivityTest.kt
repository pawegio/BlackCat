package com.pawegio.blackcat.ui.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import com.pawegio.blackcat.R
import com.pawegio.blackcat.TestGitHubApiService
import com.pawegio.blackcat.domain.GitHubRetrofit
import com.pawegio.blackcat.waitAtLeast
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author pawegio
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchActivityTest {

    @Rule @JvmField
    val activityRule: ActivityTestRule<SearchActivity> = ActivityTestRule(SearchActivity::class.java)

    @Before
    fun setUp() {
        GitHubRetrofit.testService = TestGitHubApiService
    }

    @Test
    fun testShowResultsOnSearch() {
        val query = "kotlin"
        onView(withId(R.id.searchView)).perform(typeText(query))

        onView(ViewMatchers.isRoot()).perform(waitAtLeast(5000))
        onView(withText("$query r1")).check(matches(isDisplayed()))
        onView(withText("$query r2")).check(matches(isDisplayed()))
        onView(withText("$query u1")).check(matches(isDisplayed()))
        onView(withText("$query u3")).check(matches(isDisplayed()))
    }

    @Test
    fun testShowUserDetails() {
        val query = "kotlin"
        onView(withId(R.id.searchView)).perform(typeText(query))
        onView(ViewMatchers.isRoot()).perform(waitAtLeast(5000))
        onView(withText("$query u1")).perform(click())

        onView(withId(R.id.titleView)).check(matches(isDisplayed()))
    }
}