package ru.radiknasybullin.cameraphone.presentation.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.repository.NewRepository

class IngredientsViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "IngredientViewModel"

    private var repository : NewRepository = NewRepository(application)

    fun getIngredientListFromLocalDB(): LiveData<Array<IngredientList>>? {
        return repository.getIngredientListFromLocalDB()
    }

    fun loadIngredientsList(){
        repository.loadIngredientListRemote()
    }
}