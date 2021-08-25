package ru.radiknasybullin.cameraphone.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.presentation.presenters.ui.CookingActivity
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.domain.interfaces.ListItemClickListener

class RecipeListAdapter(recipeListR : List<RecipeList>, contextR: Context?): RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {

    var recipeList : List<RecipeList> = recipeListR
    val context : Context? = contextR

    class RecipeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivIconMeal : ImageView?
        val tvNameMeal : TextView?

        init {
            ivIconMeal = itemView.findViewById(R.id.iv_icon_meal)
            tvNameMeal = itemView.findViewById(R.id.tv_name_meal)
        }

        companion object {
            fun create(parent: ViewGroup): RecipeListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recipe_item, parent, false)
                return RecipeListViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        return RecipeListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        val current = recipeList[position]
        holder.tvNameMeal?.text = current.strMeal
        Picasso.get().load(current?.strMealThumb?.toUri()).into(holder.ivIconMeal)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CookingActivity::class.java).apply{
                putExtra("recipeName", current.strMeal)
            }
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}