package ru.radiknasybullin.cameraphone.data.repository

import ru.radiknasybullin.cameraphone.data.api.RemoteDataSource
import ru.radiknasybullin.cameraphone.data.db.dao.Dao
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dao: Dao
)  {

    fun loadIngredientList() = performGetOperation(
        databaseQuery = { dao.getAllIngredients() },
        networkCall = { remoteDataSource.loadIngredientList() },
        saveCallResult = { dao.insertAllIngredients(it.ingredientList)}
    )

    fun loadMealCategories() = performGetOperation(
        databaseQuery = { dao.getMealCategories() },
        networkCall = { remoteDataSource.loadMealCategories() },
        saveCallResult = { dao.insertMealCategories(it.categoriesList)}
    )

    fun loadRecipeListByCategories(category: String) = saveRecipeList(
        databaseQuery = { dao.getRecipeListByCategories(category) },
        networkCall = { remoteDataSource.loadRecipeListByCategories(category) },
        saveCallResult = { dao.insertRecipeList(it) },
        category = category
    )

    fun getFavoriteList() = getFavoriteList(
        databaseQuery = { dao.getFavoriteRecipes(true)}
    )

    suspend fun updateIngredientList(ingredientList: Array<IngredientList>){
        dao.updateIngredientList(ingredientList)
    }

    suspend fun updateRecipe(recipeDetail: RecipeList){
        dao.updateRecipe(recipeDetail)
    }

    fun getRecipeById(id: Int) = performGetOperation(
        databaseQuery = { dao.getRecipeById(id) },
        networkCall = { remoteDataSource.loadRecipeById(id) },
        saveCallResult = { dao.insertRecipe(it.recipeList[0])}
    )

}