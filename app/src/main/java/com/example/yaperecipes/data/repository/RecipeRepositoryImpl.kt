package com.example.yaperecipes.data.repository

import com.example.yaperecipes.data.api.RecipeApiService
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApiService: RecipeApiService
) : RecipeRepository{
    override fun getRecipes(): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        try {
            val response = recipeApiService.getAllRecipes()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: run {
                    emit(Resource.Error("No recipes found"))
                }
            } else {
                emit(Resource.Error("Error: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error fetching recipes: ${e.localizedMessage}"))
        }
    }
}