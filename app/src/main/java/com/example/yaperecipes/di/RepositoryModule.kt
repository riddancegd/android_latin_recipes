package com.example.yaperecipes.di

import com.example.yaperecipes.data.api.RecipeApiService
import com.example.yaperecipes.data.repository.RecipeRepository
import com.example.yaperecipes.data.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    companion object {

        @Singleton
        @Provides
        fun provideRecipeRepository(
            recipeApiService: RecipeApiService
        ): RecipeRepository {
            return RecipeRepositoryImpl(recipeApiService)
        }



    }
}
