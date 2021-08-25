package ru.radiknasybullin.cameraphone.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.radiknasybullin.cameraphone.data.entities.AreaCategoriesList
import ru.radiknasybullin.cameraphone.data.entities.MealCategoriesList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.entities.RecipeListObject
import ru.radiknasybullin.cameraphone.data.repository.NewRepository

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "RecipeViewModel"

    private var repository : NewRepository = NewRepository(application)

    fun getRecipeByName(name: String): LiveData<RecipeList>{
        return repository.getRecipeByName(name)
    }

    fun getRecipeListByCategoriesFromLocalDB(categories: String): LiveData<RecipeListObject>{
        return repository.getRecipeListByCategoriesFromLocalDB(categories)
    }

    fun loadRecipeListByCategories(categories: String){
        repository.loadRecipeListByCategories(categories)
    }

    fun getMealCategoriesListFromLocalDB(): LiveData<Array<MealCategoriesList>>{
        return repository.getMealCategoriesFromLocalDB()
    }

    fun loadMealCategoriesList(){
        repository.loadMealCategories()
    }

    fun getAreaCategoriesListFromLocalDB(): LiveData<Array<AreaCategoriesList>>{
        return repository.getAreaCategoriesListFromLocalDB()
    }

    fun loadAreaCategoriesList(){
        repository.loadAreaCategoriesList("list")
    }
}