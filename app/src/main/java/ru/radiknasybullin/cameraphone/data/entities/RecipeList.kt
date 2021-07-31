package ru.radiknasybullin.cameraphone.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipes")
class RecipeList (
    @PrimaryKey
    @SerializedName("idMeal")
    val idMeal: Int,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb : String,
    @SerializedName("strInstructions")
    val strInstructions : String
        )