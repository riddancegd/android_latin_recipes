package com.example.yaperecipes.data.model

data class Recipe(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val location: Location,
    val ingredients: List<Ingredient>,
    val procedure: String
)
