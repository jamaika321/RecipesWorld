package ru.radiknasybullin.cameraphone.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.MealCategoriesList
import ru.radiknasybullin.cameraphone.presentation.interfaces.ItemClickListener
import ru.radiknasybullin.cameraphone.presentation.presenters.fragments.RecipeListFragment

class FoodClassesAdapter(foodClassesListR : List<MealCategoriesList>, private val itemClicker: ItemClickListener): RecyclerView.Adapter<FoodClassesAdapter.FoodClassesViewHolder>(){
    private val TAG = "FoodClassesAdapter"

    val foodClassesList : List<MealCategoriesList> = foodClassesListR

    class FoodClassesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvFoodClasses = itemView.findViewById<TextView>(R.id.tv_food_class)
        val ivFoodClasses = itemView.findViewById<ImageView>(R.id.iv_food_classes)

        companion object {
            fun create(parent: ViewGroup): FoodClassesViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.food_classes_item, parent, false)
                return FoodClassesViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodClassesViewHolder {
        return FoodClassesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FoodClassesViewHolder, position: Int) {
        val current = foodClassesList[position]
        holder.tvFoodClasses.text = current.strCategory
        Picasso.get().load(current.strCategoryThumb.toUri()).into(holder.ivFoodClasses)

        holder.itemView.setOnClickListener {
            itemClicker.onClick(current.strCategory)
        }


    }

    override fun getItemCount(): Int {
        return foodClassesList.size
    }
}