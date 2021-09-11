package ru.radiknasybullin.cameraphone.data.repository

import ru.radiknasybullin.cameraphone.data.api.RemoteDataSource
import ru.radiknasybullin.cameraphone.data.db.dao.Dao
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dao: Dao
)  {

    fun loadIngredientList() = performUpdateOperation(
        databaseQuery = { dao.getAllIngredients() },
        networkCall = { remoteDataSource.loadIngredientList() },
        saveCallResult = { dao.insertAllIngredients(it.ingredientList)}
    )

    suspend fun updateIngredientList(ingredientList: Array<IngredientList>){
        dao.updateIngredientList(ingredientList)
    }

}