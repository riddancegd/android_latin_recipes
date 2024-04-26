package com.example.yaperecipes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.databinding.RecipeListItemBinding

class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val onClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(), Filterable {

    var filteredRecipes: List<Recipe> = recipes

    class RecipeViewHolder(private val binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe, onClick: (Recipe) -> Unit) {
            binding.textRecipeName.text = recipe.name
            Glide.with(binding.imageRecipe.context).load(recipe.image_url).into(binding.imageRecipe)
            itemView.setOnClickListener { onClick(recipe) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding =
            RecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(filteredRecipes[position], onClick)
    }

    override fun getItemCount(): Int = filteredRecipes.size

    fun submitList(newRecipes: List<Recipe>) {
        recipes = newRecipes
        filteredRecipes = newRecipes
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrBlank()) {
                    recipes
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()
                    recipes.filter {
                        it.name.lowercase().contains(filterPattern) ||
                                it.ingredients.any { ingredient ->
                                    ingredient.name.lowercase().contains(filterPattern)
                                }
                    }
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredRecipes = results?.values as List<Recipe>
                notifyDataSetChanged()
            }
        }
    }
}