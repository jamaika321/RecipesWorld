package ru.radiknasybullin.cameraphone.presentation.presenters.ui

import android.os.Bundle
import android.view.MenuItem
import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.bottom_navigation_view.*
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.api.RetrofitClient
import ru.radiknasybullin.cameraphone.data.db.database.LocalRoomDb
import ru.radiknasybullin.cameraphone.data.entities.FoodClassesList
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.repository.RepositoryImpl
import ru.radiknasybullin.cameraphone.data.utils.showShortToast
import ru.radiknasybullin.cameraphone.databinding.ActivityRecipeListBinding
import ru.radiknasybullin.cameraphone.domain.interfaces.ListItemClickListener
import ru.radiknasybullin.cameraphone.domain.interfaces.RecipeListInterface.RecipeListInterfaces
import ru.radiknasybullin.cameraphone.domain.usecases.GetRecipeList
import ru.radiknasybullin.cameraphone.presentation.adapter.FoodClassesAdapter
import ru.radiknasybullin.cameraphone.presentation.adapter.RecipeListAdapter
import ru.radiknasybullin.cameraphone.presentation.viewModel.RecipeViewModel


class RecipeListActivity : BaseActivity(1), NavigationView.OnNavigationItemSelectedListener,
        RecipeListInterfaces.View,
        ListItemClickListener{

    private val TAG = "RecipeListActivity"

    lateinit var mBinding: ActivityRecipeListBinding
    private lateinit var recipeViewModel : RecipeViewModel
    lateinit var categoriesAdapter : FoodClassesAdapter
    lateinit var recipeAdapter : RecipeListAdapter
    lateinit var rcView : RecyclerView
    lateinit var dialog: AlertDialog
    lateinit var previousBtn : ImageView

    override fun onResume() {
        super.onResume()
        bottom_navigation_view.menu.getItem(navNumber).isChecked = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        previousBtn = findViewById(R.id.previous_image)
        setupBottomNavigation()
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        val fakeCategoriesList : List<FoodClassesList> = listOf(FoodClassesList(0))
        val fakeRecipeList : List<RecipeList> = listOf(RecipeList(0))
        categoriesAdapter = FoodClassesAdapter(fakeCategoriesList, this, this)
        recipeAdapter = RecipeListAdapter(fakeRecipeList, this)
        rcView = findViewById(R.id.rc_view_recipe_list)
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = recipeAdapter

        recipeViewModel = RecipeViewModel(GetRecipeList((RepositoryImpl(RetrofitClient.getClient(), LocalRoomDb.getAppDataBase(applicationContext))), this))

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.nav_categories) {
            recipeViewModel.loadCategoriesList()
            showShortToast("Categories")
        } else if (id == R.id.nav_favorites) {
            showShortToast("Favorites")
        } else if (id == R.id.nav_country) {
//            recipeViewModel.loadAreaList()
            showShortToast("Country")
        } else if (id == R.id.nav_settings) {
            showShortToast("share")
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun setRecipeListToAdapter(recipeList: List<RecipeList>) {
        previousBtn.visibility = View.VISIBLE
        recipeAdapter = RecipeListAdapter(recipeList.toList(), this)
        rcView.adapter = recipeAdapter
        recipeAdapter.notifyDataSetChanged()
        previousBtn.setOnClickListener{
            showShortToast("Prev Button")
        }
    }

    override fun setCategoriesListToAdapter(categoriesList: Array<FoodClassesList>) {
        Log.d(TAG, "setCategoriesList")
        categoriesAdapter = FoodClassesAdapter(categoriesList.toList(), this, this)
        rcView.adapter = categoriesAdapter
        categoriesAdapter.notifyDataSetChanged()
    }

    override fun onLoadedError() {
        showShortToast("Loading Error!")
    }

    override fun showLoadingProgressDialog(show: Boolean) {
        if(show){
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

    override fun onClick(category: String) {
        recipeViewModel.loadRecipeList(category)
    }
}