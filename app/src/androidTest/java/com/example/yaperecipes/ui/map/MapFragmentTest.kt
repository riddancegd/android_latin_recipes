package com.example.yaperecipes.ui.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.yaperecipes.R
import com.example.yaperecipes.launchFragmentInHiltContainer
import com.example.yaperecipes.models.fakeLocation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * IMPORTANT: These tests must be run with the screen of the device/emulator ON
 *
 */
@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MapFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val recipeName = "fakeName"
    private val args = MapFragmentArgs(fakeLocation, recipeName).toBundle()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun verify_that_mapview_is_displayed() {

        val scenario = launchFragmentInHiltContainer<MapFragment>(fragmentArgs = args)

        onView(withId(R.id.mapView))
            .check(matches(isDisplayed()))
    }

}
