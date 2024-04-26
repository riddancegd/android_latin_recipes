package com.example.yaperecipes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter
    private var recipeList = emptyList<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeRecipes()
        setupSearchView()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter(emptyList(), this::navigateToDetailFragment)
        binding.recyclerview.adapter = recipeAdapter
    }

    private fun observeRecipes() {
        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            recipeList = recipes
            recipeAdapter.submitList(recipes)
        }
    }

    private fun navigateToDetailFragment(recipe: Recipe) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(recipe)
        findNavController().navigate(action)
    }


    private fun setupSearchView(){

        Log.d("LAUNCHED", "ola k ase")

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val recipeFiltered = recipeList.filter { recipeFilter ->
                    recipeFilter.name.lowercase().contains(newText.toString().lowercase())
                }
                recipeAdapter.submitList(recipeFiltered)
                return true
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}