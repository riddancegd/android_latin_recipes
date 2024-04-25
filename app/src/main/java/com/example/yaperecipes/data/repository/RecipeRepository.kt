package com.example.yaperecipes.data.repository

import com.example.yaperecipes.data.api.RecipeApiService
import com.example.yaperecipes.data.model.Recipe
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeApiService: RecipeApiService
) {

    suspend fun getRecipes(): Response<List<Recipe>> {
        return recipeApiService.getAllRecipes()
    }



    /*fun getRecipes(): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val response = recipeApiService.getAllRecipes()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error("No recipes found"))
            } else {
                emit(Resource.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }*/
}