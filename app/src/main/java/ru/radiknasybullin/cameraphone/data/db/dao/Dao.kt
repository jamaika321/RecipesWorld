package ru.radiknasybullin.cameraphone.data.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single
import ru.radiknasybullin.cameraphone.data.entities.*

@Dao
interface Dao {
    //Ingredients
    @Query("SELECT * FROM ingredients")
    fun getAllIngredients(): LiveData<Array<IngredientList>>
    @Insert
    suspend fun insertAllIngredients(ingredientList: Array<IngredientList>?)
    @Update
    suspend fun updateIngredientList(ingredientList: Array<IngredientList>)

    //Categories
    @Query("SELECT * FROM foodClasses")
    fun getMealCategories(): LiveData<Array<MealCategoriesList>>
    @Insert
    fun insertMealCategories(mealCategoriesList: Array<MealCategoriesList>)
    @Query("Select * From areaCategories")
    fun getAreaCategoriesList(): LiveData<Array<AreaCategoriesList>>
    @Insert
    fun insertAreaCategoriesList(areaCategoriesList: Array<AreaCategoriesList>)

    //Recipes
    @Query("Select * From recipeList Where name = :categories")
    fun getRecipeListByCategories(categories: String): LiveData<RecipeListObject>
    @Insert
    fun insertRecipeListByCategories(recipeList: RecipeListObject)
    @Query("Select * From recipes Where strMeal = :name")
    fun getRecipeByName(name: String): LiveData<RecipeList>
    @Insert
    fun insertRecipeList(recipeList : List<RecipeList>)

}