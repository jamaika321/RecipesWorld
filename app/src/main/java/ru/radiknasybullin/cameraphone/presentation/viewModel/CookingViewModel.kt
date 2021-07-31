package ru.radiknasybullin.cameraphone.presentation.viewModel

import androidx.lifecycle.ViewModel
import ru.radiknasybullin.cameraphone.domain.usecases.GetRecipeExample

class CookingViewModel(private val useCases: GetRecipeExample): ViewModel() {

    fun getDishByIdFromLocalDB(id: Int){
        useCases.getDishByIdFromLocalDB(id)
    }

    fun getDishById(id: Int){
        useCases.getDishById(id)
    }

}