package ru.radiknasybullin.cameraphone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "foodClasses")
class MealCategoriesList (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @SerializedName("strCategory")
    val strCategory: String = "",
    @SerializedName("strCategoryThumb")
    val strCategoryThumb : String = "",
    @SerializedName("strCategoryDescription")
    val strCategoryDescription : String = ""
        )