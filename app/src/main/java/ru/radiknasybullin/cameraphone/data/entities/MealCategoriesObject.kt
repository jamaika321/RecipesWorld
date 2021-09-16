package ru.radiknasybullin.cameraphone.data.entities

import com.google.gson.annotations.SerializedName

class MealCategoriesObject (
        @SerializedName("categories")
    val categoriesList : Array<MealCategoriesList> = arrayOf(MealCategoriesList(""))
)