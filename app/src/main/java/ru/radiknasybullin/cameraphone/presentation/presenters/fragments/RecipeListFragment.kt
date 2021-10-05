package ru.radiknasybullin.cameraphone.presentation.presenters.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.MealCategoriesList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.utils.Resource
import ru.radiknasybullin.cameraphone.databinding.RecipeListFragmentBinding
import ru.radiknasybullin.cameraphone.presentation.adapter.FoodClassesAdapter
import ru.radiknasybullin.cameraphone.presentation.adapter.RecipeListAdapter
import ru.radiknasybullin.cameraphone.presentation.interfaces.ItemClickListener
import ru.radiknasybullin.cameraphone.presentation.presenters.MainActivity
import ru.radiknasybullin.cameraphone.presentation.presenters.viewModel.RecipeDetailViewModel
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
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backFromRecipeListToFoodClasses()

        val strCategory = arguments?.getString("category")
        if (!strCategory.isNullOrEmpty()) {
            getRecipeListByCategories(strCategory)
        }else{
            getMealCategoriesFromLocalDB()
        }
    }

    private fun getMealCategoriesFromLocalDB(){
        viewModel.mealCategories!!.observe(viewLifecycleOwner, Observer {
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
                    mBinding.tvMealCategories.text = "Categories"
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

    private fun getRecipeListByCategories(category: String){
        viewModel.recipeList(category).observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    Timber.d("Recipe Loading")
                    mBinding.recipeListProgressBar.visibility = View.VISIBLE
                    mBinding.rcViewRecipeList.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("Recipe Success")
                    if (it.data != null) {
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
        val adapter = RecipeListAdapter(recipeList, this)
        mBinding.rcViewRecipeList.layoutManager = LinearLayoutManager(context)
        mBinding.rcViewRecipeList.adapter = adapter
        mBinding.previousImage.visibility = View.VISIBLE
    }

    private fun backFromRecipeListToFoodClasses(){
        mBinding.previousImage.setOnClickListener{
            getMealCategoriesFromLocalDB()
            mBinding.previousImage.visibility = View.INVISIBLE
        }
    }

    private fun initFoodClassesRecyclerView(mealCategoriesList: Array<MealCategoriesList>){
        val adapter = FoodClassesAdapter(mealCategoriesList.toList(), this)
        mBinding.rcViewRecipeList.layoutManager = GridLayoutManager(context, 2)
        mBinding.rcViewRecipeList.adapter = adapter
    }

    override fun onClick(itemName: String, type: String) {
        if (type == "recipe"){
            findNavController().navigate(
                R.id.action_nav_categories_to_nav_detail,
                bundleOf("id" to itemName.toInt())
            )
        } else if(type == "category"){
            getRecipeListByCategories(itemName)
        }
    }
}