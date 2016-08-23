package com.pawegio.blackcat.ui.activity

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import com.pawegio.blackcat.TestRepository
import com.pawegio.blackcat.domain.GitHubRetrofit
import com.pawegio.blackcat.domain.RepositoryProvider
import com.pawegio.blackcat.waitAtLeast
import com.pawegio.kandroid.IntentFor
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author pawegio
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UserDetailsActivityTest {

    companion object {
        @BeforeClass @JvmStatic
        fun setUp() {
            RepositoryProvider.testRespository = TestRepository
        }
    }

    @Rule @JvmField
    val activityRule: ActivityTestRule<UserDetailsActivity> =
            object : ActivityTestRule<UserDetailsActivity>(UserDetailsActivity::class.java) {
                override fun getActivityIntent(): Intent {
                    val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                    return IntentFor<UserDetailsActivity>(targetContext).apply {
                        putExtra(UserDetailsActivity.KEY_USERNAME, "johnny")
                    }
                }
            }

    @Test
    fun testDisplayUserDetails() {
        onView(ViewMatchers.isRoot()).perform(waitAtLeast(5000))

        onView(withText("John Doe")).check(matches(isDisplayed()))
        onView(withText("(johnny)")).check(matches(isDisplayed()))
        onView(withText("Followers: 5")).check(matches(isDisplayed()))
        onView(withText("Starred: 0")).check(matches(isDisplayed()))
    }
}