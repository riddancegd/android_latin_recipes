package com.example.yaperecipes.data.repository

import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    /**
     * Fetches all the recipes
     * @return [List] of [Recipe]
     */
    fun getRecipes(): Flow<Resource<List<Recipe>>>
}