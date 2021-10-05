package ru.radiknasybullin.cameraphone.presentation.presenters

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.*
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.databinding.ActivityMainBinding
import ru.radiknasybullin.cameraphone.presentation.presenters.fragments.FavoriteListFragment
import ru.radiknasybullin.cameraphone.presentation.presenters.fragments.IngredientFragment
import ru.radiknasybullin.cameraphone.presentation.presenters.fragments.RecipeDetailFragment
import ru.radiknasybullin.cameraphone.presentation.presenters.fragments.RecipeListFragment
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = binding.navView
        navView.setNavigationItemSelectedListener (this)

        changeFragment(RecipeListFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_categories -> {
                changeFragment(RecipeListFragment())
            }
            R.id.nav_ingredients -> {
                changeFragment(IngredientFragment())
            }
            R.id.nav_favorites -> {
                changeFragment(FavoriteListFragment())
            }
        }
        val drawer = binding.drawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun changeFragment(frag: Fragment){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.my_nav_host_fragment, frag).commit()
    }
}
