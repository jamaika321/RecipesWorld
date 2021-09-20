package ru.radiknasybullin.cameraphone.presentation.presenters.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.utils.Resource
import ru.radiknasybullin.cameraphone.databinding.RecipeDetailFragmentBinding
import ru.radiknasybullin.cameraphone.presentation.interfaces.ItemClickListener
import ru.radiknasybullin.cameraphone.presentation.presenters.viewModel.RecipeDetailViewModel
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailFragment : Fragment(){

    @Inject
    lateinit var viewModel: RecipeDetailViewModel
    lateinit var mBinding: RecipeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = RecipeDetailFragmentBinding.inflate(inflater, container, false)

        val itemId = arguments?.getInt("id")

        if(itemId != null) {
            getRecipe(itemId)
        }
        return mBinding.root
    }

    private fun getRecipe(itemId: Int?) {
        viewModel.detailRecipe(itemId!!).observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> {
                    Timber.d("Loading")
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("Success")
                    if (it.data != null){
                        initView(it.data)
                    }
                }
                Resource.Status.ERROR -> {
                    Timber.d("Error")
                    previousBtn("")

                }
            }
        })
    }

    fun previousBtn(strCategory: String){
        mBinding.previousImageCook.setOnClickListener {
            findNavController().navigate(R.id.action_nav_detail_to_nav_categories,
            bundleOf("category" to strCategory))
        }
    }

    fun favoriteBtn(recipeDetail: RecipeList?){
        mBinding.favoritesImage.setOnClickListener {
            recipeDetail!!.favorites = !recipeDetail!!.favorites
            viewModel.updateRecipe(recipeDetail)
        }
    }

    private fun initView(recipeDetail: RecipeList?){
        favoriteBtn(recipeDetail)
        previousBtn(recipeDetail?.strCategory!!)
        Picasso.get().load(recipeDetail.strMealThumb).into(mBinding.ivIconMeal)
        mBinding.collapsingToolbar.title = recipeDetail?.strMeal
        mBinding.ing1.text = recipeDetail.strIng1
        mBinding.ing2.text = recipeDetail.strIng2
        mBinding.ing3.text = recipeDetail.strIng3
        mBinding.ing4.text = recipeDetail.strIng4
        mBinding.ing5.text = recipeDetail.strIng5
        mBinding.ing6.text = recipeDetail.strIng6
        mBinding.ing7.text = recipeDetail.strIng7
        mBinding.ing8.text = recipeDetail.strIng8
        mBinding.ing9.text = recipeDetail.strIng9
        mBinding.ing10.text = recipeDetail.strIng10
        mBinding.mer1.text = recipeDetail.strMer1
        mBinding.mer2.text = recipeDetail.strMer2
        mBinding.mer3.text = recipeDetail.strMer3
        mBinding.mer4.text = recipeDetail.strMer4
        mBinding.mer5.text = recipeDetail.strMer5
        mBinding.mer6.text = recipeDetail.strMer6
        mBinding.mer7.text = recipeDetail.strMer7
        mBinding.mer8.text = recipeDetail.strMer8
        mBinding.mer9.text = recipeDetail.strMer9
        mBinding.mer10.text = recipeDetail.strMer10
        mBinding.tvMealDescription.text = recipeDetail.strInstructions
        if (recipeDetail.favorites){
            mBinding.favoritesImage.setImageResource(R.drawable.ic_baseline_star_on)
        }else{
            mBinding.favoritesImage.setImageResource(R.drawable.ic_baseline_star_off)
        }
    }
}