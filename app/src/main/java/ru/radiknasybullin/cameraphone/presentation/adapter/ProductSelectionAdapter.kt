package ru.radiknasybullin.cameraphone.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.IngredientList
import java.util.*
import kotlin.collections.ArrayList

class ProductSelectionAdapter(private val productList : List<IngredientList>): RecyclerView.Adapter<ProductSelectionAdapter.IngredientViewHolder>(), Filterable {

    var productFilterList : List<IngredientList> = productList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder.create(parent)
    }

    @ExperimentalStdlibApi
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    productFilterList = productList
                } else {
                    val resultList = ArrayList<IngredientList>()
                    for (row in productList) {
                        if (row.strName?.lowercase(Locale.ROOT)!!.contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    productFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = productFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productFilterList = results?.values as List<IngredientList>
                notifyDataSetChanged()
            }
        }
    }

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProduct : TextView
        val productItem : CardView
        var productCheck : CheckBox

        init {
            tvProduct = itemView.findViewById(R.id.tv_product)
            productItem = itemView.findViewById(R.id.product_card_view)
            productCheck = itemView.findViewById(R.id.product_check)
        }


        companion object {
            fun create(parent: ViewGroup): IngredientViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.product_item, parent, false)
                return IngredientViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val current = productFilterList[position]
        holder.tvProduct.text = current.strName
        holder.productCheck.isChecked = current.isHave

        holder.productItem.setOnClickListener {
            holder.productCheck.isChecked = !holder.productCheck.isChecked
            saveCheckedProducts(holder, position)
        }
        holder.productCheck.setOnClickListener {
            saveCheckedProducts(holder, position)
        }

    }

    fun saveCheckedProducts(holder: IngredientViewHolder, position: Int){
        productFilterList[position].isHave = holder.productCheck.isChecked == true
    }

    fun getActualProductList(): Array<IngredientList> {
        return productFilterList.toTypedArray()
    }

    override fun getItemCount(): Int {
        return productFilterList.size
    }
}
