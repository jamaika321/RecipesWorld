package ru.radiknasybullin.cameraphone.domain.usecases

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces
import ru.radiknasybullin.cameraphone.domain.interfaces.RecipeListInterface.RecipeListInterfaces

class GetRecipeList(private val repositories: CommonInterfaces.Repositories, private val recipeListView : RecipeListInterfaces.View){
    private val TAG = "GetRecipeList"

//    fun loadAreaListRemote(){
//        recipeListView.showLoadingProgressDialog(true)
//        repositories.getAreaCategories()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    repositories.insertAreaCategories(it.mealsCategoriesList)
//                    loadAreaListFromLocalDB()
//                },{
//                    Log.d(TAG, "Error - $it")
//                })
//    }
//
//    fun loadAreaListFromLocalDB(){
//        repositories.getAreaCategoriesFromLocalDB()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    if(it.isNotEmpty()){
//                        Log.d(TAG, "Area List is not empty")
//                        recipeListView.setCategoriesListToAdapter(it)
//                    }else{
//                        loadAreaListRemote()
//                    }
//                },{
//                    Log.d(TAG, "Error - $it")
//                    loadAreaListRemote()
//                })
//        recipeListView.showLoadingProgressDialog(false)
//    }

    fun loadCategoriesListRemote(){
        repositories.getMealCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    repositories.insertMealCategoriesList(it.categoriesList)
                    loadCategoriesListFromLocalDB()
                },{
                    Log.d(TAG, "Error - $it")
                })
    }

    fun loadCategoriesListFromLocalDB(){
        recipeListView.showLoadingProgressDialog(true)
        repositories.getMealCategoriesFromLocalDB()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.isNotEmpty()){
                        Log.d(TAG, "Categories List is not empty")
                        recipeListView.setCategoriesListToAdapter(it)
                    }else{
                        loadCategoriesListRemote()
                    }
                },{
                    Log.d(TAG, "Error - $it")
                })
        recipeListView.showLoadingProgressDialog(false)
    }

    fun loadRecipeListByCategories(categories: String){
        repositories.getRecipeListByCategories(categories)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.name = categories
                    repositories.insertRecipeListByCategories(it)
                    loadRecipeListByCategoriesFromLocalDB(categories)
                },{
                    Log.d(TAG, "Error - $it")
                })
    }

    fun loadRecipeListByCategoriesFromLocalDB(categories: String){
        recipeListView.showLoadingProgressDialog(true)
        repositories.getRecipeListByCategoriesFromLocalDB(categories)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.recipeList.isNotEmpty()) {
                        recipeListView.setRecipeListToAdapter(it.recipeList)
                    }else{
                        loadRecipeListByCategories(categories)
                    }
                },{
                    Log.d(TAG, "Error - $it")
                    if(it.toString() == "androidx.room.EmptyResultSetException: Query returned empty result set: Select * From categoriesRecipeList Where name = ?")
                        loadRecipeListByCategories(categories)
                })
        recipeListView.showLoadingProgressDialog(false)
    }

//    fun loadRecipeListRemoteOnCategory(category: String){
//        repositories.getRecipeListByCategories(category)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    repositories.insertRecipeList(it.recipeList)
//                    loadRecipeListFromLocalDBOnCategory(category)
//                },{
//                    Log.d(TAG, "Error - $it")
//                })
//    }
//
//    fun loadRecipeListFromLocalDBOnCategory(category: String){
//        recipeListView.showLoadingProgressDialog(true)
//        repositories.getRecipeListFromLocalDB(category)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    if(it.isNotEmpty()){
//                        recipeListView.setRecipeListToAdapter(it)
//                    }else{
//                        loadRecipeListRemoteOnCategory(category)
//                    }
//                },{
//                    Log.d(TAG, "Error - $it")
//                })
//        recipeListView.showLoadingProgressDialog(false)
//    }

    fun loadRecipeListByScreen(recipeList: List<RecipeList>){
        recipeListView.setRecipeListToAdapter(recipeList)
    }
}