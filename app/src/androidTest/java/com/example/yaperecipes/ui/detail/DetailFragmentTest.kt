package com.example.yaperecipes.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.yaperecipes.R
import com.example.yaperecipes.launchFragmentInHiltContainer
import com.example.yaperecipes.models.fakeRecipe
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
class DetailFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val args = DetailFragmentArgs(fakeRecipe).toBundle()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun verify_that_imageView_is_displayed() {
        val scenario = launchFragmentInHiltContainer<DetailFragment>(args)

        Espresso.onView(ViewMatchers.withId(R.id.image_recipe))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun verify_that_ingredients_textView_matches_with_ingredients() {

        val ingredients = fakeRecipe.ingredients.joinToString("\n") { ingredient ->
            "${ingredient.amount} ${ingredient.unit} ${"of"} ${ingredient.name}"
        }

        val scenario = launchFragmentInHiltContainer<DetailFragment>(args)

        Espresso.onView(ViewMatchers.withId(R.id.text_ingredients))
            .check(ViewAssertions.matches(ViewMatchers.withText(ingredients)))
    }

    @Test
    fun verify_that_procedure_textView_matches_with_procedure() {

        val scenario = launchFragmentInHiltContainer<DetailFragment>(args)

        Espresso.onView(ViewMatchers.withId(R.id.text_procedure))
            .check(ViewAssertions.matches(ViewMatchers.withText(fakeRecipe.procedure)))
    }

    @Test
    fun verify_that_location_textView_matches_with_location() {

        val scenario = launchFragmentInHiltContainer<DetailFragment>(args)

        Espresso.onView(ViewMatchers.withId(R.id.text_location))
            .check(ViewAssertions.matches(ViewMatchers.withText(fakeRecipe.location.name)))
    }

}