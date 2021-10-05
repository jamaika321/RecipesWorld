package ru.radiknasybullin.cameraphone.presentation.presenters.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.utils.Resource
import ru.radiknasybullin.cameraphone.databinding.FragmentFavoriteListBinding
import ru.radiknasybullin.cameraphone.presentation.adapter.RecipeListAdapter
import ru.radiknasybullin.cameraphone.presentation.interfaces.ItemClickListener
import ru.radiknasybullin.cameraphone.presentation.presenters.viewModel.RecipeListViewModel
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteListFragment : Fragment() , ItemClickListener{

    @Inject
    lateinit var viewModel: RecipeListViewModel
    lateinit var mBinding: FragmentFavoriteListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFavoriteListBinding.inflate(layoutInflater, container, false)
        getFavoriteList()
        return mBinding.root
    }

    fun getFavoriteList(){
        viewModel.favoriteList.observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    Timber.d("Meal Loading")
                    mBinding.favoriteListProgressBar.visibility = View.VISIBLE
                    mBinding.rcViewFavoriteList.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("Meal Success")
                    if (!it.data.isNullOrEmpty()) {
                        initFavoriteListAdapter(it.data)
                    }
                    mBinding.tvMealCategories.text = "Favorite"
                    mBinding.favoriteListProgressBar.visibility = View.GONE
                    mBinding.rcViewFavoriteList.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Timber.d("Meal Error")
                    mBinding.favoriteListProgressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun initFavoriteListAdapter(favoriteRecipes: Array<RecipeList>){
        val adapter = RecipeListAdapter(favoriteRecipes.toList(), this)
        mBinding.rcViewFavoriteList.layoutManager = LinearLayoutManager(context)
        mBinding.rcViewFavoriteList.adapter = adapter
    }

    override fun onClick(itemName: String, type: String) {
        if (type == "recipe"){
            findNavController().navigate(
                R.id.action_nav_categories_to_nav_detail,
                bundleOf("id" to itemName.toInt())
            )
        }
    }

}