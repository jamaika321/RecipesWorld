package ru.radiknasybullin.cameraphone.data.db.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.radiknasybullin.cameraphone.data.entities.RecipeList

class Converter {
    companion object {
        val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToHeroesObjectList(data: String?): List<RecipeList?>? {
            if (data == null) {
                return emptyList<RecipeList>()
            }
            val listType = object : TypeToken<List<RecipeList?>?>() {}.type
            return Converter.gson.fromJson<List<RecipeList>>(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun weatherObjectListToString(someObjects: List<RecipeList?>?): String? {
            return gson.toJson(someObjects)
        }
    }
}