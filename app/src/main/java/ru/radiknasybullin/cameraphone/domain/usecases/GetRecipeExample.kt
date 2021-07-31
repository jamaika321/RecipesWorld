package ru.radiknasybullin.cameraphone.domain.usecases

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces

class GetRecipeExample(private val repositories: CommonInterfaces.Repositories, private val cookingView : CommonInterfaces.View) {

    fun getDishByIdFromLocalDB(id : Int){
        repositories.getDishByIdFromLocalDB(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           loadRecipeByScreen(it)
                },{
                    Log.d("Dish By Id From LocalDB", "Error - $it")
                })
    }

    fun getDishById(id: Int){
        repositories.getDishById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadRecipeByScreen(it.recipeList[0])
                },{
                    Log.d("Dish By Id", "Error - $it")
                })
    }

    fun loadRecipeByScreen(recipe : RecipeList){
        cookingView.onLoadedRecipeData(recipe)
    }

}