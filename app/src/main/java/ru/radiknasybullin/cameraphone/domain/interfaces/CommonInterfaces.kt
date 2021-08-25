package ru.radiknasybullin.cameraphone.domain.interfaces

import android.content.Context
import androidx.lifecycle.LiveData
import io.reactivex.Single
import ru.radiknasybullin.cameraphone.data.entities.*

interface CommonInterfaces {

    interface NewRepository{

        fun getIngredientListFromLocalDB(): LiveData<Array<IngredientList>>
        fun insertIngredientList(ingredientList: Array<IngredientList>)
        fun loadIngredientListRemote()

        fun getRecipeByName(name: String): LiveData<RecipeList>
        fun getRecipeListByCategoriesFromLocalDB(categories: String): LiveData<RecipeListObject>
        fun insertRecipeListByCategories(recipeList: RecipeListObject, category: String)
        fun loadRecipeListByCategories(categories: String)

        fun getMealCategoriesFromLocalDB(): LiveData<Array<MealCategoriesList>>
        fun insertMealCategoriesList(mealCategoriesList: Array<MealCategoriesList>)
        fun loadMealCategories()

        fun getAreaCategoriesListFromLocalDB(): LiveData<Array<AreaCategoriesList>>
        fun insertAreaCategoriesList(areaCategoriesList: Array<AreaCategoriesList>)
        fun loadAreaCategoriesList(list: String)
    }

    interface View{
        fun onLoadedError()
        fun showLoadingProgressDialog(show: Boolean)
    }

    interface BroadCastReceiver{
        fun onInternetConnectionSuccess()
        fun onInternetConnectionError()
    }
}