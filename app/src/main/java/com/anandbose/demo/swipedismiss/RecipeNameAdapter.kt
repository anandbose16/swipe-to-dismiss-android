package com.anandbose.demo.swipedismiss

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anandbose.demo.swipedismiss.databinding.ItemRecipeNameBinding

class RecipeNameAdapter : RecyclerView.Adapter<RecipeItemViewHolder>() {
    var recipes: MutableList<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeNameBinding.inflate(inflater, parent, false)
        return RecipeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val recipe = recipes?.get(position)!!
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes?.size ?: 0
    }
}