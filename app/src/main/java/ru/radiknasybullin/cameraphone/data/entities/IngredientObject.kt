package ru.radiknasybullin.cameraphone.data.entities

import com.google.gson.annotations.SerializedName

class IngredientObject (
        @SerializedName("meals")
        val ingredientList : Array<IngredientList>
        )