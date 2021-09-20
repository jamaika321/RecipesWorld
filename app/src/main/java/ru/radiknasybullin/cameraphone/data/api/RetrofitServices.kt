package ru.radiknasybullin.cameraphone.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.radiknasybullin.cameraphone.data.entities.*

interface RetrofitServices {

    @GET("list.php")
    suspend fun loadIngredientList(@Query("i")list : String): Response<IngredientObject>

    @GET("filter.php")
    suspend fun loadRecipeListByCategories(@Query("c")category : String): Response<RecipeListObject>

    @GET("lookup.php")
    suspend fun loadRecipeById(@Query("i")id: Int): Response<RecipeListObject>

    @GET("list.php")
    suspend fun loadAreaCategoriesList(@Query("a")list : String): Response<AreaCategoriesObject>

    @GET("categories.php")
    suspend fun loadMealCategories(): Response<MealCategoriesObject>

    @GET("filter.php")
    suspend fun loadRecipeListByArea(@Query("a")area : String): Response<RecipeListObject>


}