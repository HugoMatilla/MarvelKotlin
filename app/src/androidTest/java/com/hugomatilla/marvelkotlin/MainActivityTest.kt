package com.hugomatilla.marvelkotlin

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.core.deps.guava.collect.Ordering
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.ui.activities.MainActivity
import com.hugomatilla.marvelkotlin.ui.adapters.HeroesListAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by hugomatilla on 20/02/16.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    private var idlingResource: MainActivityIdlingResource? = null

    @Rule @JvmField
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIntentServiceIdlingResource() {
        val activity = activityTestRule.activity
        idlingResource = MainActivityIdlingResource(activity)
        Espresso.registerIdlingResources(idlingResource)
    }

    @After
    fun unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource)
    }

    @Test
    fun testThatDoNothing() {
        assertThat("asd", object : Matcher<String> {
            override fun matches(item: Any): Boolean {
                return true
            }

            override fun describeMismatch(item: Any, mismatchDescription: Description) {
            }

            override fun _dont_implement_Matcher___instead_extend_BaseMatcher_() {

            }

            override fun describeTo(description: Description) {
                description.appendText("Forced error")
            }
        })
    }


    @Test
    fun teamsListIsSortedAlphabetically() {
        onView(withId(R.id.main_list)).check(matches(isSortedAlphabetically))
    }

    private val isSortedAlphabetically: Matcher<View>
        get() = object : TypeSafeMatcher<View>() {

            private val heroesNames = ArrayList<String>()

            override fun matchesSafely(item: View): Boolean {
                val recyclerView = item as RecyclerView
                val heroesListAdapter = recyclerView.adapter as HeroesListAdapter
                heroesNames.clear()
                heroesNames.addAll(extractNames(heroesListAdapter.heroesList.heroes))
                return Ordering.natural<String>().isOrdered(heroesNames)
            }

            private fun extractNames(heroes: List<HeroDomain>): List<String> {
                val heroesNames = ArrayList<String>()
                for (hero in heroes) {
                    heroesNames.add(hero.name)
                }
                return heroesNames
            }

            override fun describeTo(description: Description) {
                description.appendText("has items sorted alphabetically: " + heroesNames)
            }

        }
}
