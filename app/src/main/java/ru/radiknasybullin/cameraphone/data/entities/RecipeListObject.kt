package ru.radiknasybullin.cameraphone.data.entities

import com.google.gson.annotations.SerializedName

class RecipeListObject (
    @SerializedName("meals")
    val recipeList: Array<RecipeList>
        )