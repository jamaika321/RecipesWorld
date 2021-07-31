package ru.radiknasybullin.cameraphone.domain.usecases

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces
import ru.radiknasybullin.cameraphone.domain.interfaces.RecipeListInterface.RecipeListInterfaces

class GetRecipeList(private val repositories: CommonInterfaces.Repositories, private val recipeListView : RecipeListInterfaces.View){

    fun getFirstLetterRecipeListFromLocalDb(firstLetter: String){
        repositories.getRecipeList(firstLetter)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       loadRecipeListByScreen(it.recipeList)
            },{
                Log.d("First Letter Local", "Error - $it")
            })
    }

    fun getFirstLetterRecipeList(firstLetter: String){
        repositories.getRecipeList(firstLetter)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loadRecipeListByScreen(it.recipeList)
            },{
                Log.d("First Letter Remote", "Error - $it")
            })
    }

    fun loadRecipeListByScreen(recipeList: Array<RecipeList>){
        recipeListView.setRecipeListToAdapter(recipeList)
    }
}