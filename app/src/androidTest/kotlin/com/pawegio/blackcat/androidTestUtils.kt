package com.pawegio.blackcat

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * @author pawegio
 */

fun waitAtLeast(millis: Long): ViewAction = object : ViewAction {

    override fun getConstraints() = anyView()

    override fun getDescription() = "Wait for at least $millis millis."

    override fun perform(uiController: UiController, view: View) {
        uiController.loopMainThreadUntilIdle()
        uiController.loopMainThreadForAtLeast(millis)
    }
}

fun anyView(): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun matchesSafely(view: View): Boolean {
            return true
        }

        override fun describeTo(description: Description) {

        }
    }
}