package ru.radiknasybullin.cameraphone.data.repository

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.radiknasybullin.cameraphone.data.api.RetrofitServices
import ru.radiknasybullin.cameraphone.data.db.database.LocalRoomDb
import ru.radiknasybullin.cameraphone.data.entities.*
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces

class RepositoryImpl(private val webClient: RetrofitServices, private val localRoomClient: LocalRoomDb) : CommonInterfaces.Repositories {
    private val TAG = "RepositoryImpl"

    var disposable : Disposable? = null

    override fun getAllIngredients(list : String): Single<IngredientObject> {
        return webClient
                .getIngredientList(list)
                .subscribeOn(Schedulers.io())
    }

    override fun getAllIngredientsFromLocalDB(): Single<Array<IngredientList>> {
        return localRoomClient
                .dao()
                .getAllIngredients()
                .subscribeOn(Schedulers.io())
    }

    override fun insertIngredientsList(ingredientsList: Array<IngredientList>) {
        disposable = Single.fromCallable {
            localRoomClient
                    .dao()
                    .insertAllIngredients(ingredientsList)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(disposable, "Ingredient List Updated")
                },{
                    error -> onError(disposable, error.localizedMessage)
                })
    }

    override fun getDishById(id: Int): Single<RecipeListObject> {
        return webClient
                .getDishById(id)
                .subscribeOn(Schedulers.io())
    }

    override fun getDishByIdFromLocalDB(id: Int): Single<RecipeList> {
        return localRoomClient
                .dao()
                .getDishById(id)
                .subscribeOn(Schedulers.io())
    }

//    override fun getAreaCategories(): Single<MealCategoriesObject> {
//        return webClient
//                .getAreaCategoriesList("list")
//                .subscribeOn(Schedulers.io())
//
//    }
//
//    override fun getAreaCategoriesFromLocalDB(): Single<Array<FoodClassesList>> {
//        return localRoomClient
//                .dao()
//                .getMealCategories()
//                .subscribeOn(Schedulers.io())
//    }
//
//    override fun insertAreaCategories(areaCategoriesList: Array<FoodClassesList>) {
//        disposable = Single.fromCallable {
//            localRoomClient.dao().insertMealCategories(areaCategoriesList)}
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    onSuccess(disposable, "Area Categories List Updated")
//                },{
//                    error -> onError(disposable, error.localizedMessage)
//                })
//    }

    override fun getMealCategories(): Single<MealCategoriesObject> {
        return webClient
                .getMealCategories()
                .subscribeOn(Schedulers.io())
    }

    override fun getMealCategoriesFromLocalDB(): Single<Array<FoodClassesList>> {
        return localRoomClient
                .dao()
                .getMealCategories()
                .subscribeOn(Schedulers.io())
    }

    override fun insertMealCategoriesList(mealCategoriesList: Array<FoodClassesList>) {
        disposable = Single.fromCallable {
            localRoomClient.dao().insertMealCategories(mealCategoriesList)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(disposable, "Meal Categories List Updated")
                },{
                    error -> onError(disposable, error.localizedMessage)
                })
    }

    override fun getRecipeListByCategories(categories: String): Single<RecipeListObject> {
        return webClient
                .getRecipeListByCategories(categories)
                .subscribeOn(Schedulers.io())
    }

    override fun getRecipeListByCategoriesFromLocalDB(categories: String): Single<RecipeListObject> {
        return localRoomClient
                .dao()
                .getRecipeListByCategories(categories)
                .subscribeOn(Schedulers.io())
    }

    override fun insertRecipeListByCategories(recipeList: RecipeListObject) {
        disposable = Single.fromCallable {
            localRoomClient.dao().insertRecipeList(recipeList)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(disposable, "Recipe List Updated")
                },{
                    error -> onError(disposable, error.localizedMessage)
                })
    }

//    override fun getRecipeListByCategories(categories : String): Single<RecipeListObject> {
//        return webClient
//                .getRecipeListByCategories(categories)
//                .subscribeOn(Schedulers.io())
//    }
//
//    override fun getRecipeListByArea(area: String): Single<RecipeListObject> {
//        return webClient
//                .getRecipeListByArea(area)
//                .subscribeOn(Schedulers.io())
//    }
//
//    override fun getRecipeListFromLocalDB(categories: String): Single<Array<RecipeList>> {
//        return localRoomClient
//                .dao()
//                .getRecipeListByCategory(categories)
//                .subscribeOn(Schedulers.io())
//    }
//
//    override fun insertRecipeList(recipeList: Array<RecipeList>) {
//        disposable = Single.fromCallable {
//            localRoomClient.dao().insertRecipeList(recipeList)}
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    onSuccess(disposable, "Meal Categories List Updated")
//                },{
//                    error -> onError(disposable, error.localizedMessage)
//                })
//    }

    private fun onSuccess(disposable: Disposable?, msg : String){
        Log.d(TAG , msg)
        disposable?.dispose()
    }

    private fun onError(disposable: Disposable?, msg : String){
        Log.d(TAG , msg)
        disposable?.dispose()
    }
}