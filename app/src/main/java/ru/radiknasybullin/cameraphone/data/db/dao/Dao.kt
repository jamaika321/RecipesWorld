package ru.radiknasybullin.cameraphone.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Observable
import io.reactivex.Single
import ru.radiknasybullin.cameraphone.data.entities.FoodClassesList
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.entities.RecipeListObject

@Dao
interface Dao {
    //Ingredients
    @Query("SELECT * FROM ingredients")
    fun getAllIngredients(): Single<Array<IngredientList>>
    @Insert
    fun insertAllIngredients(ingredientsList: Array<IngredientList>)

    //Categories
    @Query("SELECT * FROM foodClasses")
    fun getMealCategories(): Single<Array<FoodClassesList>>
    @Insert
    fun insertMealCategories(mealCategoriesList: Array<FoodClassesList>)

    //Recipes
//    @Query("Select * From recipes")
//    fun getRecipeList(): Single<RecipeListObject>
    @Query("Select * From categoriesRecipeList Where name = :categories")
    fun getRecipeListByCategories(categories: String): Single<RecipeListObject>
    @Insert
    fun insertRecipeList(recipeList: RecipeListObject)

    @Query("SELECT * FROM recipes WHERE idMeal = :id")
    fun getDishById(id : Int): Single<RecipeList>
}