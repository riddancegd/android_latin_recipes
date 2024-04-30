package com.example.yaperecipes.ui.home

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.yaperecipes.R
import com.example.yaperecipes.general.DefaultApplication
import com.example.yaperecipes.launchFragmentInHiltContainer
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
class HomeFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var applicationContext: Context

    @Before
    fun setup() {
        hiltRule.inject()
        applicationContext = getApplicationContext<DefaultApplication>()
    }

    @Test
    fun verify_that_recyclerview_is_displayed() {
        val scenario = launchFragmentInHiltContainer<HomeFragment>()

        onView(withId(R.id.recyclerview))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verify_that_searchView_is_displayed() {
        val scenario = launchFragmentInHiltContainer<HomeFragment>()

        onView(withId(R.id.search_view))
            .check(matches(isDisplayed()))
    }


}
