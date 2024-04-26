package com.example.yaperecipes.ui.home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yaperecipes.R
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)

        binding.let {
            setupRecyclerView(it)
            observeRecipes()
            setupSearchView(it)
            fillRecyclerView(it)
        }

    }

    private fun setupRecyclerView(binding: FragmentHomeBinding) {
        recipeAdapter = RecipeAdapter(emptyList(), this::navigateToDetailFragment)
        binding.recyclerview.adapter = recipeAdapter
    }

    private fun observeRecipes() {
        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }
    }

    private fun setupSearchView(binding: FragmentHomeBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun fillRecyclerView(binding: FragmentHomeBinding) {
        viewModel.recipes.value?.let {
            if (it.isNotEmpty()) {
                recipeAdapter.submitList(it)
            }
            if (binding.searchView.isNotEmpty()) {
                recipeAdapter.filter.filter(binding.searchView.query.toString())
            }
        }
    }

    private fun navigateToDetailFragment(recipe: Recipe) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(recipe)
        findNavController().navigate(action)
    }

}