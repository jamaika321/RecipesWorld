package ru.radiknasybullin.cameraphone.presentation.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.radiknasybullin.cameraphone.data.api.Common
import ru.radiknasybullin.cameraphone.data.api.RetrofitServices
import ru.radiknasybullin.cameraphone.domain.usecases.GetRecipeList

class RecipeViewModel(private val useCases : GetRecipeList) : ViewModel() {

//    fun loadAreaList(){
//        useCases.loadAreaListRemote()
//    }

    fun loadCategoriesList(){
        useCases.loadCategoriesListFromLocalDB()
    }

    fun loadRecipeList(category: String){
        useCases.loadRecipeListByCategoriesFromLocalDB(category)
    }

}