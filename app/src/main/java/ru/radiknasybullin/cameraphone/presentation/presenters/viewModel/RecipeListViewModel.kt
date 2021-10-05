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

    fun recipeList(category: String) = repository.loadRecipeListByCategories(category)

    var mealCategories = repository.loadMealCategories()

    var favoriteList = repository.getFavoriteList()
}