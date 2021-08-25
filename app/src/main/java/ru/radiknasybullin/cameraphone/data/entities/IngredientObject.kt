package ru.radiknasybullin.cameraphone.data.entities

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import ru.radiknasybullin.cameraphone.data.db.dao.Converter

@Entity(tableName = "ingredients")
@TypeConverters(Converter::class)
class IngredientObject (
        @SerializedName("meals")
        val ingredientList : Array<IngredientList>
        )