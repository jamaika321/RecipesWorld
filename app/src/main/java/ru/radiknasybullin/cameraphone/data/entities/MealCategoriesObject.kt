package ru.radiknasybullin.cameraphone.data.entities

import com.google.gson.annotations.SerializedName

class MealCategoriesObject (
        @SerializedName("meals")
    val mealCategoriesList : Array<FoodClassesList> = arrayOf(FoodClassesList(0)),
        @SerializedName("categories")
    val mealsCategoryList : Array<FoodClassesList> = arrayOf(FoodClassesList(0))
)