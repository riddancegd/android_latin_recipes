package com.example.yaperecipes.models

import com.example.yaperecipes.data.model.Ingredient
import com.example.yaperecipes.data.model.Location
import com.example.yaperecipes.data.model.Recipe

val fakeRecipe = Recipe(
    id = 10092,
    name = "Chicken Hamurguer",
    image ="https://www.indianhealthyrecipes.com/wp-content/uploads/2016/02/chicken-burger-recipe-500x375.jpg",
    location = Location(
        name = "Chicago, USA",
        latitude = 17.568,
        longitude = 25.254
    ),
    ingredients = listOf(
        Ingredient(
            name = "Onion",
            amount = 2,
            unit = "Pieces"
        ),
        Ingredient(
            name = "Bun",
            amount = 2,
            unit = "Pieces"
        ),
        Ingredient(
            name = "Meat",
            amount = 80,
            unit = "Grams"
        )
    ),
    procedure = "Cut the onions and put them in the buns",
)

val fakeLocation = Location(
    name = "Chicago, USA",
    latitude = 17.568,
    longitude = 25.254
)