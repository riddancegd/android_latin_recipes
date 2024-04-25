package com.example.yaperecipes.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes
    init {
        viewModelScope.launch {
            try {
                val response = repository.getRecipes()
                if (response.isSuccessful) {
                    // Successful response
                    response.body()?.let {
                        _recipes.value = it
                        Log.d(TAG, it.toString())
                    } ?: run {
                        Log.e(TAG, "No recipes found")
                    }
                } else {
                    // Response has an error
                    Log.e(TAG, "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Network failure...
                Log.e(TAG, "Error fetching recipes: ${e.localizedMessage}")

            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }

}