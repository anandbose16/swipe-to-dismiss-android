package com.anandbose.demo.swipedismiss

import androidx.recyclerview.widget.RecyclerView
import com.anandbose.demo.swipedismiss.databinding.ItemRecipeNameBinding

class RecipeItemViewHolder(val binding: ItemRecipeNameBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipeName: String) {
        binding.recipeName.text = recipeName
    }
}