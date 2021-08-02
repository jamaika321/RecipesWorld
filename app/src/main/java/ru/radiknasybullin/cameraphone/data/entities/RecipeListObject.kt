package ru.radiknasybullin.cameraphone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import ru.radiknasybullin.cameraphone.data.db.dao.Converter

@Entity(tableName = "categoriesRecipeList")
@TypeConverters(Converter::class)
class RecipeListObject (
        @SerializedName("meals")
        val recipeList: List<RecipeList>,
        @PrimaryKey
        var name: String
        )