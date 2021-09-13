package ru.radiknasybullin.cameraphone.presentation.presenters.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.radiknasybullin.cameraphone.data.entities.MealCategoriesList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.utils.Resource
import ru.radiknasybullin.cameraphone.databinding.RecipeListFragmentBinding
import ru.radiknasybullin.cameraphone.presentation.adapter.FoodClassesAdapter
import ru.radiknasybullin.cameraphone.presentation.adapter.RecipeListAdapter
import ru.radiknasybullin.cameraphone.presentation.interfaces.ItemClickListener
import ru.radiknasybullin.cameraphone.presentation.presenters.viewModel.RecipeListViewModel
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment(), ItemClickListener{

    @Inject
    lateinit var viewModel: RecipeListViewModel
    lateinit var mBinding: RecipeListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = RecipeListFragmentBinding.inflate(inflater, container, false)

        viewModel.loadMealCategories()
        getMealCategoriesFromLocalDB()

        return mBinding.root
    }

    private fun getMealCategoriesFromLocalDB(){
        viewModel.mealCategories!!.observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    Timber.d("Meal Loading")
                    mBinding.recipeListProgressBar.visibility = View.VISIBLE
                    mBinding.rcViewRecipeList.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("Meal Success")
                    if (!it.data.isNullOrEmpty()) {
                        initFoodClassesRecyclerView(it.data)
                    }
                    mBinding.recipeListProgressBar.visibility = View.GONE
                    mBinding.rcViewRecipeList.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Timber.d("Meal Error")
                    mBinding.recipeListProgressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun getRecipeListByCategories(){
        viewModel.recipeList!!.observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    Timber.d("Recipe Loading")
                    mBinding.recipeListProgressBar.visibility = View.VISIBLE
                    mBinding.rcViewRecipeList.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("Recipe Success")
                    if (!it.data!!.recipeList.isNullOrEmpty()) {
                        initRecipeListRecyclerView(it.data.recipeList)
                    }
                    mBinding.recipeListProgressBar.visibility = View.GONE
                    mBinding.rcViewRecipeList.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Timber.d("Recipe Error")
                    mBinding.recipeListProgressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun initRecipeListRecyclerView(recipeList: List<RecipeList>){
        val adapter = RecipeListAdapter(recipeList)
        mBinding.rcViewRecipeList.layoutManager = LinearLayoutManager(context)
        mBinding.rcViewRecipeList.adapter = adapter
    }

    private fun initFoodClassesRecyclerView(mealCategoriesList: Array<MealCategoriesList>){
        val adapter = FoodClassesAdapter(mealCategoriesList.toList(), this)
        mBinding.rcViewRecipeList.layoutManager = GridLayoutManager(context, 2)
        mBinding.rcViewRecipeList.adapter = adapter
    }

    override fun onClick(category: String) {
        viewModel.loadRecipeListByCategories(category)
        getRecipeListByCategories()
    }
}