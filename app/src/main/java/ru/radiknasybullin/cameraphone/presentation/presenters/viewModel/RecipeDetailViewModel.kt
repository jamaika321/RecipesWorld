package ru.radiknasybullin.cameraphone.presentation.presenters.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.repository.Repository
import ru.radiknasybullin.cameraphone.data.utils.Resource
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun detailRecipe(itemId: Int) = repository.getRecipeById(itemId)

    fun updateRecipe(recipeDetail: RecipeList){
        viewModelScope.launch {
            repository.updateRecipe(recipeDetail)
        }
    }

}