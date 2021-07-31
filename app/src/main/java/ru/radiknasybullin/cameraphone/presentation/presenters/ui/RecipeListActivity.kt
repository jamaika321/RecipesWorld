package ru.radiknasybullin.cameraphone.presentation.presenters.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.bottom_navigation_view.*
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.api.RetrofitClient
import ru.radiknasybullin.cameraphone.data.db.database.LocalRoomDb
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.repository.RepositoryImpl
import ru.radiknasybullin.cameraphone.data.utils.showShortToast
import ru.radiknasybullin.cameraphone.databinding.ActivityRecipeListBinding
import ru.radiknasybullin.cameraphone.domain.interfaces.RecipeListInterface.RecipeListInterfaces
import ru.radiknasybullin.cameraphone.domain.usecases.GetRecipeList
import ru.radiknasybullin.cameraphone.presentation.adapter.RecipeListAdapter
import ru.radiknasybullin.cameraphone.presentation.viewModel.RecipeViewModel


class RecipeListActivity : BaseActivity(1), NavigationView.OnNavigationItemSelectedListener , RecipeListInterfaces.View{

    private val TAG = "RecipeListActivity"

    lateinit var mBinding: ActivityRecipeListBinding
    private lateinit var recipeViewModel : RecipeViewModel
    lateinit var adapter : RecipeListAdapter
    lateinit var rcView : RecyclerView

    override fun onResume() {
        super.onResume()
        bottom_navigation_view.menu.getItem(navNumber).isChecked = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupBottomNavigation()

        val fakeRecipeList : List<RecipeList> = listOf(RecipeList(0," "," "," "))
        adapter = RecipeListAdapter(fakeRecipeList, this)
        rcView = findViewById(R.id.rc_view_recipe_list)
        rcView.layoutManager = LinearLayoutManager(this)

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
            showShortToast("camera")
        } else if (id == R.id.nav_favorites) {
            recipeViewModel.getFirstLetterRecipeList("l")
        } else if (id == R.id.nav_country) {
            showShortToast("2")
        } else if (id == R.id.nav_settings) {
            showShortToast("share")
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun setRecipeListToAdapter(recipeList: Array<RecipeList>) {
        adapter = RecipeListAdapter(recipeList.toList(), this)
        rcView.adapter = adapter
    }
}