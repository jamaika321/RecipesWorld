package ru.radiknasybullin.cameraphone.data.api

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.radiknasybullin.cameraphone.data.entities.FoodClassesList
import ru.radiknasybullin.cameraphone.data.entities.IngredientObject
import ru.radiknasybullin.cameraphone.data.entities.MealCategoriesObject
import ru.radiknasybullin.cameraphone.data.entities.RecipeListObject

interface RetrofitServices {

    @GET("list.php")
    fun getIngredientList(@Query("i")list : String): Single<IngredientObject>

    @GET("lookup.php")
    fun getDishById(@Query("i")id: Int): Single<RecipeListObject>

    @GET("list.php")
    fun getAreaCategoriesList(@Query("a")list : String): Single<MealCategoriesObject>

    @GET("categories.php")
    fun getMealCategories(): Single<MealCategoriesObject>

    @GET("filter.php")
    fun getRecipeListByCategories(@Query("c")category : String): Single<RecipeListObject>

    @GET("filter.php")
    fun getRecipeListByArea(@Query("a")area : String): Single<RecipeListObject>
}