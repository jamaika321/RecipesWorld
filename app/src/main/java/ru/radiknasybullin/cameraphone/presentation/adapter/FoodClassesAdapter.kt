package ru.radiknasybullin.cameraphone.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.FoodClassesList

class FoodClassesAdapter(foodClassesListR : List<FoodClassesList>): RecyclerView.Adapter<FoodClassesAdapter.FoodClassesViewHolder>() {

    val foodClassesList : List<FoodClassesList> = foodClassesListR

    class FoodClassesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvFoodClass = itemView.findViewById<TextView>(R.id.tv_food_class)

        companion object {
            fun create(parent: ViewGroup): FoodClassesViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.food_adapter_item, parent, false)
                return FoodClassesViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodClassesViewHolder {
        return FoodClassesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FoodClassesViewHolder, position: Int) {
        val current = foodClassesList[position]
        if(current.strArea.isEmpty())
        {
            holder.tvFoodClass.text = current.strCategory
        } else {
            holder.tvFoodClass.text = current.strArea
        }
    }

    override fun getItemCount(): Int {
        return foodClassesList.size
    }
}