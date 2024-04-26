package com.example.yaperecipes.data.model

import java.io.Serializable

data class Ingredient(
    val name: String,
    val amount: Int,
    val unit: String
) : Serializable
