package ru.radiknasybullin.cameraphone.domain.interfaces

import io.reactivex.Observable
import io.reactivex.Single
import ru.radiknasybullin.cameraphone.data.entities.*

interface CommonInterfaces {

    interface Repositories{
        fun getAllIngredients(list : String): Single<IngredientObject>
        fun getAllIngredientsFromLocalDB(): Single<Array<IngredientList>>
        fun insertIngredientsList(ingredientsList: Array<IngredientList>)

        fun getDishById(id : Int): Single<RecipeListObject>
        fun getDishByIdFromLocalDB(id : Int): Single<RecipeList>

//        fun getAreaCategories(): Single<MealCategoriesObject>
//        fun getAreaCategoriesFromLocalDB(): Single<Array<FoodClassesList>>
//        fun insertAreaCategories(areaCategoriesList: Array<FoodClassesList>)

        fun getMealCategories(): Single<MealCategoriesObject>
        fun getMealCategoriesFromLocalDB(): Single<Array<FoodClassesList>>
        fun insertMealCategoriesList(mealCategoriesList: Array<FoodClassesList>)

//        fun getRecipeListByCategories(categories: String): Single<RecipeListObject>
//        fun getRecipeListByArea(area : String): Single<RecipeListObject>
//        fun getRecipeListFromLocalDB(categories: String): Single<Array<RecipeList>>
//        fun insertRecipeList(recipeList : Array<RecipeList>)

        fun getRecipeListByCategories(categories: String): Single<RecipeListObject>
        fun getRecipeListByCategoriesFromLocalDB(categories: String): Single<RecipeListObject>
        fun insertRecipeListByCategories(recipeList : RecipeListObject)
    }

    interface View{
        fun onLoadedRecipeData(recipe: RecipeList)
        fun onLoadedError()
        fun showLoadingProgressDialog(show: Boolean)
    }

    interface BroadCastReceiver{
        fun onInternetConnectionSuccess()
        fun onInternetConnectionError()
    }
}