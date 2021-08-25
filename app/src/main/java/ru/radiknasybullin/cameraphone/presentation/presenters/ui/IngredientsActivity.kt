package ru.radiknasybullin.cameraphone.presentation.presenters.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.databinding.ActivityIngredientsBinding
import ru.radiknasybullin.cameraphone.domain.interfaces.IngredientsActivityInterface
import ru.radiknasybullin.cameraphone.presentation.adapter.ProductSelectionAdapter
import ru.radiknasybullin.cameraphone.presentation.viewModel.IngredientsViewModel


class IngredientsActivity : BaseActivity(0) ,
        IngredientsActivityInterface.View {

    private val TAG = "IngredientsActivity"
    lateinit var adapter : ProductSelectionAdapter
    lateinit var mBinding : ActivityIngredientsBinding
    lateinit var dialog: AlertDialog
    lateinit var ingredientViewModel : IngredientsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityIngredientsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        ingredientViewModel = ViewModelProviders.of(this).get(IngredientsViewModel::class.java)

        val fakeProductsList : ArrayList<IngredientList> = arrayListOf(IngredientList(0, " ", " ", false))

        adapter = ProductSelectionAdapter(fakeProductsList, this)
        mBinding.rcView.adapter = adapter
        mBinding.rcView.layoutManager = LinearLayoutManager(this)


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

        ingredientViewModel.getIngredientListFromLocalDB()!!
            .observe(this, Observer{
                if(it.isNotEmpty()) {
                    adapter = ProductSelectionAdapter(it.toList(), this)
                    mBinding.rcView.adapter = adapter
                }else{
                    loadIngredientsList()
                }
            })
        changeSaved()
    }

    fun loadIngredientsList(){
        ingredientViewModel.loadIngredientsList()
    }

    fun changeSaved(){
        mBinding.btnSave.setOnClickListener {
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onInternetConnectionSuccess() {
        TODO("Not yet implemented")
    }

    override fun onInternetConnectionError() {
        TODO("Not yet implemented")
    }


    override fun onLoadedError() {
        TODO("Not yet implemented")
    }
}
