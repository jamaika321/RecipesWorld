package ru.radiknasybullin.cameraphone.data.db.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.radiknasybullin.cameraphone.data.db.dao.Dao
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList

@Database(entities = [RecipeList::class, IngredientList::class], version = 1, exportSchema = false)
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