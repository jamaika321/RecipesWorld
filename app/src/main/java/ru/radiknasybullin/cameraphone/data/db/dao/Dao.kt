package ru.radiknasybullin.cameraphone.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import ru.radiknasybullin.cameraphone.data.entities.*

@Dao
interface Dao {
    //Ingredients
    @Query("SELECT * FROM ingredients")
    fun getAllIngredients(): LiveData<Array<IngredientList>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllIngredients(ingredientList: Array<IngredientList>)
    @Update
    suspend fun updateIngredientList(ingredientList: Array<IngredientList>)

    //Categories
    @Query("SELECT * FROM foodClasses")
    fun getMealCategories(): LiveData<Array<MealCategoriesList>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealCategories(mealCategoriesList: Array<MealCategoriesList>)
    @Query("Select * From areaCategories")
    fun getAreaCategoriesList(): LiveData<Array<AreaCategoriesList>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreaCategoriesList(areaCategoriesList: Array<AreaCategoriesList>)

    //Recipes
    @Query("Select * From recipeList Where name = :categories")
    fun getRecipeListByCategories(categories: String): LiveData<RecipeListObject>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeListByCategories(recipeList: RecipeListObject)
    @Query("Select * From recipes Where strMeal = :name")
    fun getRecipeByName(name: String): LiveData<RecipeList>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeList(recipeList : List<RecipeList>)

    @Query("Select * From recipes Where idMeal = :id")
    fun getDishById(id : Int): LiveData<RecipeList>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDishById(recipe: RecipeList)
    @Update
    suspend fun updateDish(recipe: RecipeList)
    @Query("Select * From recipes Where favorites = :favorite")
    fun getFavoriteRecipes(favorite: Boolean): LiveData<Array<RecipeList>>

}