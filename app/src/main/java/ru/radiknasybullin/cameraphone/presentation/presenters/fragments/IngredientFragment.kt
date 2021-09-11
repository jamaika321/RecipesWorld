package ru.radiknasybullin.cameraphone.presentation.presenters.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.utils.Resource
import ru.radiknasybullin.cameraphone.databinding.IngredientFragmentBinding
import ru.radiknasybullin.cameraphone.presentation.adapter.ProductSelectionAdapter
import ru.radiknasybullin.cameraphone.presentation.presenters.MainActivity
import ru.radiknasybullin.cameraphone.presentation.presenters.viewModel.IngredientViewModel
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class IngredientFragment : Fragment() {

    @Inject
    lateinit var viewModel: IngredientViewModel
    private lateinit var mBinding: IngredientFragmentBinding
    private lateinit var adapter: ProductSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = IngredientFragmentBinding.inflate(inflater, container, false)
        viewModel.loadData()
        saveSelectedIngredients()
        return mBinding.root
    }

    fun saveSelectedIngredients(){
        mBinding.btnSave.setOnClickListener {
            viewModel.updateIngredientList(adapter.getActualProductList())
//            (activity as MainActivity).navController.navigate(R.id.action_ingredientFragment_to_recipeListFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fakeIngredientList: List<IngredientList> = listOf(IngredientList(0))

        adapter = ProductSelectionAdapter(fakeIngredientList)
        mBinding.rcView.layoutManager = LinearLayoutManager(context)
        mBinding.rcView.adapter = adapter

        mBinding.productSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        mBinding.productSearch.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mBinding.productSearch.setIconified(false)
            }
        })



        viewModel.ingredientsList!!.observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    mBinding.ingredientsProgressBar.visibility = View.VISIBLE
                    mBinding.rcView.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        mBinding.ingredientsProgressBar.visibility = View.GONE
                        mBinding.rcView.visibility = View.VISIBLE
                        initRecyclerView(it.data)
                    }
                }
                Resource.Status.ERROR -> {
                    mBinding.ingredientsProgressBar.visibility = View.GONE
                    Timber.d(it.message)
                }
            }
        })
    }

    private fun initRecyclerView(ingredientsList: Array<IngredientList>){
        adapter = ProductSelectionAdapter(ingredientsList.toList())
        mBinding.rcView.adapter = adapter
    }

}