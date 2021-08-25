package ru.radiknasybullin.cameraphone.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.radiknasybullin.cameraphone.data.db.dao.Dao
import ru.radiknasybullin.cameraphone.data.entities.*

@Database(entities = [AreaCategoriesList::class, RecipeListObject::class, IngredientList::class, MealCategoriesList::class, RecipeList::class], version = 1, exportSchema = false)
abstract class LocalRoomDb: RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        var INSTANCE: LocalRoomDb? = null

        fun getAppDataBase(context: Context): LocalRoomDb {
            if(INSTANCE == null){
                synchronized(LocalRoomDb::class.java){
                    INSTANCE = Room.databaseBuilder(context,
                    LocalRoomDb::class.java,
                    "recipeDB")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}