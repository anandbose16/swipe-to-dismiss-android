package com.anandbose.demo.swipedismiss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anandbose.demo.swipedismiss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val recipes = mutableListOf(
            "Cup Cake",
            "Donut",
            "Eclairs",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "Kitkat",
            "Lollypop",
            "Marshmallow",
            "Nougat",
            "Oreo",
            "Pie",
            "Quaker Oats",
            "Red Velvet",
    )
    val adapter = RecipeNameAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recipeListView.adapter = adapter
        binding.recipeListView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val touchHelper = ItemTouchHelper(SwipeCallback(this, adapter))
        touchHelper.attachToRecyclerView(binding.recipeListView)
        adapter.recipes = recipes
        adapter.notifyDataSetChanged()
    }
}