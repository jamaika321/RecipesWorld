package ru.radiknasybullin.cameraphone.presentation.presenters.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.databinding.ActivityCookingBinding
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces
import ru.radiknasybullin.cameraphone.presentation.viewModel.RecipeViewModel


class CookingActivity : AppCompatActivity(), CommonInterfaces.View{

    private val TAG = "CookingActivity"
    lateinit var mBinding: ActivityCookingBinding
    var id = 0
    lateinit var recipeViewModel : RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCookingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)

        if(intent.extras!!.getString("recipeName") != null){
            Log.d(TAG, "Extras isn't empty")
            recipeViewModel.getRecipeByName(getDishName()!!).observe(this, Observer{
                initView(it)
            })
        } else {
            Log.d(TAG, "Extras is empty")
        }
    }

    fun getDishName(): String? {
        val dish: String? = intent.extras!!.getString("recipeName")
        return dish
    }

    fun initView(recipe : RecipeList){
        mBinding.tvMealName.text = recipe.strMeal
        Picasso.get().load(recipe?.strMealThumb?.toUri()).into(mBinding.ivIconMeal)
    }

    override fun onLoadedError() {
        TODO("Not yet implemented")
    }

    override fun showLoadingProgressDialog(show: Boolean) {
        TODO("Not yet implemented")
    }
}
