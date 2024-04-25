package com.example.yaperecipes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yaperecipes.R
import com.example.yaperecipes.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayRecipeDetails()
    }

    private fun displayRecipeDetails() {
        val recipe = args.recipe
        recipe?.let {
            Glide.with(this)
                .load(it.image_url)
                .into(binding.imageRecipe)

            //Set the toolbar name to the recipe name
            (activity as? AppCompatActivity)?.supportActionBar?.title = it.name

            val ingredientsText = it.ingredients.joinToString("\n") { ingredient ->
                "${ingredient.amount} ${ingredient.unit} ${"of"} ${ingredient.name}"
            }
            binding.textIngredients.text = getString(R.string.ingredients_label, ingredientsText)
            binding.textProcedure.text = getString(R.string.procedure_label, it.procedure ?: "")
        } ?: run {
            Toast.makeText(context, "Recipe data not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}