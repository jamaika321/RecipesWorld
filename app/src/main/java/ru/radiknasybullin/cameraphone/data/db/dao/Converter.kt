package ru.radiknasybullin.cameraphone.data.db.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList

class Converter {
    companion object {
        val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToRecipeObjectList(data: String?): List<RecipeList?>? {
            if (data == null) {
                return emptyList<RecipeList>()
            }
            val listType = object : TypeToken<List<RecipeList?>?>() {}.type
            return Converter.gson.fromJson<List<RecipeList>>(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun recipeObjectListToString(someObjects: List<RecipeList?>?): String? {
            return gson.toJson(someObjects)
        }

        @TypeConverter
        @JvmStatic
        fun stringToIngredientsObjectList(data: String?): List<IngredientList?>? {
            if (data == null) {
                return emptyList<IngredientList>()
            }
            val listType = object : TypeToken<List<IngredientList?>?>() {}.type
            return Converter.gson.fromJson<List<IngredientList>>(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun ingredientsObjectListToString(someObjects: List<IngredientList?>?): String? {
            return gson.toJson(someObjects)
        }
    }
}