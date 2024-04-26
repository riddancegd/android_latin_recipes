package com.example.yaperecipes.data.model

import java.io.Serializable

data class Recipe(
    val id: Int,
    val name: String,
    val image: String,
    val location: Location,
    val ingredients: List<Ingredient>,
    val procedure: String
) : Serializable
