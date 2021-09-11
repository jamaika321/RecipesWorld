package ru.radiknasybullin.cameraphone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "areaCategories")
class AreaCategoriesList (
        @PrimaryKey
        @SerializedName("strArea")
        val strArea : String
        )