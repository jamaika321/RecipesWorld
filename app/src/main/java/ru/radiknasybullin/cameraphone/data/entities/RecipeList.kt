package ru.radiknasybullin.cameraphone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipes")
class RecipeList(
    @SerializedName("idMeal")
    val idMeal: Int,
    @PrimaryKey
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb : String? = "",
    @SerializedName("strInstructions")
    val strInstructions: String? = "",
    @SerializedName("strCategory")
    val strCategory: String? = "",
    @SerializedName("strIngredient1")
    val strIng1: String? = "",
    @SerializedName("strIngredient2")
    val strIng2: String? = "",
    @SerializedName("strIngredient3")
    val strIng3: String? = "",
    @SerializedName("strIngredient4")
    val strIng4: String? = "",
    @SerializedName("strIngredient5")
    val strIng5: String? = "",
    @SerializedName("strIngredient6")
    val strIng6: String? = "",
    @SerializedName("strIngredient7")
    val strIng7: String? = "",
    @SerializedName("strIngredient8")
    val strIng8: String? = "",
    @SerializedName("strIngredient9")
    val strIng9: String? = "",
    @SerializedName("strIngredient10")
    val strIng10: String? = "",
    @SerializedName("strMeasure1")
    val strMer1: String? = "",
    @SerializedName("strMeasure2")
    val strMer2: String? = "",
    @SerializedName("strMeasure3")
    val strMer3: String? = "",
    @SerializedName("strMeasure4")
    val strMer4: String? = "",
    @SerializedName("strMeasure5")
    val strMer5: String? = "",
    @SerializedName("strMeasure6")
    val strMer6: String? = "",
    @SerializedName("strMeasure7")
    val strMer7: String? = "",
    @SerializedName("strMeasure8")
    val strMer8: String? = "",
    @SerializedName("strMeasure9")
    val strMer9: String? = "",
    @SerializedName("strMeasure10")
    val strMer10: String? = "",
    var favorites: Boolean = false
        )