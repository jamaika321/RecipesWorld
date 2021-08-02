package ru.radiknasybullin.cameraphone.domain.interfaces.RecipeListInterface

import io.reactivex.Single
import ru.radiknasybullin.cameraphone.data.entities.FoodClassesList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList

interface RecipeListInterfaces {

    interface View {
        fun setRecipeListToAdapter(recipeList : List<RecipeList>)
        fun setCategoriesListToAdapter(categoriesList : Array<FoodClassesList>)
        fun onLoadedError()
        fun showLoadingProgressDialog(show: Boolean)
    }
}