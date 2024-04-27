package com.example.yaperecipes.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yaperecipes.R
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)

        binding.let {
            displayRecipeDetails(binding)
            args.recipe?.let { setRecipeLocationButton(binding, it) }
        }
    }

    private fun displayRecipeDetails(binding: FragmentDetailBinding) {
        val recipe = args.recipe
        recipe?.let {
            Glide.with(this)
                .load(it.image)
                .into(binding.imageRecipe)

            //Set the toolbar name to the recipe name
            (activity as? AppCompatActivity)?.supportActionBar?.title = it.name

            val ingredientsText = it.ingredients.joinToString("\n") { ingredient ->
                "${ingredient.amount} ${ingredient.unit} ${"of"} ${ingredient.name}"
            }
            binding.textIngredients.text = ingredientsText
            binding.textProcedure.text = it.procedure ?: ""
            binding.textLocation.text = it.location.name ?: ""
        } ?: run {
            Toast.makeText(context, "Recipe data not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecipeLocationButton(binding: FragmentDetailBinding, recipe: Recipe) {
        binding.btnRecipeOrigin.setOnClickListener {
                val action = DetailFragmentDirections
                    .actionDetailFragmentToMapFragment(recipe.location, recipe.name)
                findNavController().navigate(action)
        }
    }

}