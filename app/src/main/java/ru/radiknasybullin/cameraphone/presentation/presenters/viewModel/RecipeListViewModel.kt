package ru.radiknasybullin.cameraphone.presentation.presenters.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.radiknasybullin.cameraphone.data.entities.MealCategoriesList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.entities.RecipeListObject
import ru.radiknasybullin.cameraphone.data.repository.Repository
import ru.radiknasybullin.cameraphone.data.utils.Resource
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var recipeList: LiveData<Resource<RecipeListObject>>? = null

    var mealCategories: LiveData<Resource<Array<MealCategoriesList>>>? = null

    fun loadMealCategories(){
        mealCategories = repository.loadMealCategories()
    }

    fun loadRecipeListByCategories(category: String){
        recipeList = repository.loadRecipeListByCategories(category)
    }
}