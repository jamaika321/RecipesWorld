package ru.radiknasybullin.cameraphone.domain.interfaces.RecipeListInterface

import ru.radiknasybullin.cameraphone.data.entities.RecipeList

interface RecipeListInterfaces {

    interface View {
        fun setRecipeListToAdapter(recipeList : Array<RecipeList>)
    }
}