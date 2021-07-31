package ru.radiknasybullin.cameraphone.presentation.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.radiknasybullin.cameraphone.data.api.Common
import ru.radiknasybullin.cameraphone.data.api.RetrofitServices
import ru.radiknasybullin.cameraphone.domain.usecases.GetRecipeList

class RecipeViewModel(private val useCases : GetRecipeList) : ViewModel() {

    fun getFirstLetterRecipeList(firstLetter: String) {
        useCases.getFirstLetterRecipeList(firstLetter)
    }
}