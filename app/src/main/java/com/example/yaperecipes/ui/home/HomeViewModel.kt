package com.example.yaperecipes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.data.repository.RecipeRepository
import com.example.yaperecipes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableLiveData<Resource<List<Recipe>>>()
    val recipes: LiveData<Resource<List<Recipe>>> get() = _recipes

    init {
        fetchRecipes()
    }

    fun refreshData() {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            _recipes.value = Resource.Loading()
            repository.getRecipes().collect {
                _recipes.value = it
            }
        }
    }

}