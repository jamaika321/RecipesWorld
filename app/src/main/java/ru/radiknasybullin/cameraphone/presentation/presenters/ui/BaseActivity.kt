package ru.radiknasybullin.cameraphone.presentation.presenters.ui

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.bottom_navigation_view.*
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.databinding.BottomNavigationViewBinding
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces

abstract class BaseActivity(val navNumber: Int) : AppCompatActivity(),
        CommonInterfaces.BroadCastReceiver{
    private val TAG = "BaseActivity"

    fun setupBottomNavigation(){
        bottom_navigation_view.setTextVisibility(false)
        bottom_navigation_view.enableItemShiftingMode(false)
        bottom_navigation_view.enableShiftingMode(false)
        bottom_navigation_view.enableAnimation(false)

        for(i in 0 until bottom_navigation_view.menu.size()){
            bottom_navigation_view.setIconTintList(i, null)
        }

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            val nextActivity =
                    when(it.itemId){
                        R.id.nav_item_0 -> IngredientsActivity::class.java
                        R.id.nav_item_1 -> RecipeListActivity::class.java
                        else -> {
                            Log.e(TAG, "Item error! $it")
                            null
                        }
                    }

            val nextNumber =
                    when(it.itemId){
                        R.id.nav_item_0 -> 0
                        R.id.nav_item_1 -> 1
                        else -> {
                            Log.e(TAG, "Item error! $it")
                            null
                        }
                    }

            if(nextActivity != null && nextNumber != navNumber){
                val intent = Intent(this, nextActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0,0)
                true
            }else{
                false
            }
        }
        bottom_navigation_view.menu.getItem(navNumber).isChecked = true
    }
}