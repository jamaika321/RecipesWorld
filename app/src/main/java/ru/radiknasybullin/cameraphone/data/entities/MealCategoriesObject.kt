package ru.radiknasybullin.cameraphone.data.entities

import com.google.gson.annotations.SerializedName

class MealCategoriesObject (
//        @SerializedName("meals")
//    val mealsCategoriesList : Array<FoodClassesList> = arrayOf(FoodClassesList(0)),
        @SerializedName("categories")
    val categoriesList : Array<FoodClassesList> = arrayOf(FoodClassesList(0))
)