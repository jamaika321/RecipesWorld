package ru.radiknasybullin.cameraphone.data.api

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.radiknasybullin.cameraphone.data.entities.*

interface RetrofitServices {

    @GET("list.php")
    fun getIngredientList(@Query("i")list : String): Call<IngredientObject>

    @GET("filter.php")
    fun getRecipeListByCategories(@Query("c")category : String): Call<RecipeListObject>

    @GET("lookup.php")
    fun getDishById(@Query("i")id: Int): Call<RecipeListObject>

    @GET("list.php")
    fun getAreaCategoriesList(@Query("a")list : String): Call<AreaCategoriesObject>

    @GET("categories.php")
    fun getMealCategories(): Call<MealCategoriesObject>

    @GET("filter.php")
    fun getRecipeListByArea(@Query("a")area : String): Call<RecipeListObject>
}