package ru.radiknasybullin.cameraphone.domain.interfaces

import ru.radiknasybullin.cameraphone.data.entities.AreaCategoriesList
import ru.radiknasybullin.cameraphone.data.entities.MealCategoriesList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList

interface RecipeListInterfaces {

    interface View {
        fun setRecipeListToAdapter(recipeList : List<RecipeList>)
        fun setCategoriesListToAdapter(categoriesList : Array<MealCategoriesList>)
        fun setAreaCategoriesListToAdapter(areaCategoriesList : Array<AreaCategoriesList>)
        fun onLoadedError()
        fun showLoadingProgressDialog(show: Boolean)
    }
}