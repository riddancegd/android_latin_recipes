package com.example.yaperecipes.data.model

import java.io.Serializable

data class Location(
    val name: String,
    val latitude: Double,
    val longitude: Double
) : Serializable
