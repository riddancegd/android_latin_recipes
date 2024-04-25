package com.example.yaperecipes.data.api
import com.example.yaperecipes.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface RecipeApiService {
    @GET("recipes.json")
    suspend fun getAllRecipes(): Response<List<Recipe>>
}