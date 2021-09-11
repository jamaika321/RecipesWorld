package ru.radiknasybullin.cameraphone.presentation.presenters.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.repository.Repository
import ru.radiknasybullin.cameraphone.data.utils.Resource
import timber.log.Timber
import javax.inject.Inject

class IngredientViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    var ingredientsList: LiveData<Resource<Array<IngredientList>>>? = null

    fun loadData(){
        ingredientsList = repository.loadIngredientList()
    }

    fun updateIngredientList(ingredientList: Array<IngredientList>){
        viewModelScope.launch {
            repository.updateIngredientList(ingredientList)
        }
    }


}