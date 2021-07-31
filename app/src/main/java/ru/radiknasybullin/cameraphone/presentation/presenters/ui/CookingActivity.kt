package ru.radiknasybullin.cameraphone.presentation.presenters.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import ru.radiknasybullin.cameraphone.data.api.RetrofitClient
import ru.radiknasybullin.cameraphone.data.db.database.LocalRoomDb
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.repository.RepositoryImpl
import ru.radiknasybullin.cameraphone.databinding.ActivityCookingBinding
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces
import ru.radiknasybullin.cameraphone.domain.usecases.GetRecipeExample
import ru.radiknasybullin.cameraphone.presentation.viewModel.CookingViewModel


class CookingActivity : AppCompatActivity(), CommonInterfaces.View{

    private val TAG = "CookingActivity"
    lateinit var mBinding: ActivityCookingBinding
    val disposable = CompositeDisposable()
    var id = 0
    private lateinit var cookingViewModel: CookingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCookingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        cookingViewModel = CookingViewModel(GetRecipeExample(RepositoryImpl(RetrofitClient.getClient(), LocalRoomDb.getAppDataBase(applicationContext)), this))

        loadData()

        if(intent.extras?.isEmpty == false){
            Log.d(TAG, "Extras isn't empty")
            id = intent.extras!!.getInt("dish_id")
            showIntentDish()
        } else {
            Log.d(TAG, "Extras is empty")
        }
    }

    fun showIntentDish(){
        val dishId : Int = intent.extras!!.getInt("dish_id")


    }

//    fun loadingData(dish: RecipeList) {
//        mBinding.collapsingToolbar.title = dish.strMeal
//        mBinding.tvMealName.text = dish.strInstructions
//        Picasso.get().load(dish.strMealThumb?.toUri()).into(mBinding.ivIconMeal)
//    }

    fun loadData(){
        cookingViewModel.getDishById(52772)
    }

    override fun onLoadedRecipeData(recipe: RecipeList) {
        Picasso.get().load(recipe.strMealThumb.toUri()).into(mBinding.ivIconMeal)
        mBinding.tvMealName.text = recipe.strMeal
    }

    override fun onLoadedError() {
        TODO("Not yet implemented")
    }
}
