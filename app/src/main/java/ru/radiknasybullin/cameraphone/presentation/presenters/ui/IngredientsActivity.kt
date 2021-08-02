package ru.radiknasybullin.cameraphone.presentation.presenters.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import io.reactivex.disposables.CompositeDisposable
import ru.radiknasybullin.cameraphone.data.api.Common
import ru.radiknasybullin.cameraphone.data.api.RetrofitServices
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.databinding.ActivityIngredientsBinding
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces
import ru.radiknasybullin.cameraphone.presentation.adapter.ProductSelectionAdapter


class IngredientsActivity : BaseActivity(0) ,
        CommonInterfaces.BroadCastReceiver ,
        CommonInterfaces.View {

    private val TAG = "IngredientsActivity"
    lateinit var mService: RetrofitServices
    lateinit var adapter : ProductSelectionAdapter
    lateinit var mBinding : ActivityIngredientsBinding
    lateinit var dialog: AlertDialog
    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityIngredientsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mService = Common.retrofitServices
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()
        val productsList : ArrayList<IngredientList> = arrayListOf(IngredientList(0, " ", " ", false))

        adapter = ProductSelectionAdapter(productsList, this)
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
    }




    override fun onInternetConnectionSuccess() {
        TODO("Not yet implemented")
    }

    override fun onInternetConnectionError() {
        TODO("Not yet implemented")
    }

    override fun onLoadedRecipeData(recipe: RecipeList) {
        TODO("Not yet implemented")
    }

    override fun onLoadedError() {
        TODO("Not yet implemented")
    }

    override fun showLoadingProgressDialog(show: Boolean) {
        TODO("Not yet implemented")
    }
}
