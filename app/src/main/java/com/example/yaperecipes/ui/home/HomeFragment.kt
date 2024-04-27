package com.example.yaperecipes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yaperecipes.R
import com.example.yaperecipes.data.model.Recipe
import com.example.yaperecipes.databinding.FragmentHomeBinding
import com.example.yaperecipes.utils.Resource
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
            observeRecipes(it)
            setupSearchView(it)
            fillRecyclerView(it)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
        }

    }

    private fun setupRecyclerView(binding: FragmentHomeBinding) {
        recipeAdapter = RecipeAdapter(emptyList(), this::navigateToDetailFragment)
        binding.recyclerview.adapter = recipeAdapter
    }

    private fun observeRecipes(binding: FragmentHomeBinding) {
        viewModel.recipes.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoadingIcon(binding)
                }
                is Resource.Success -> {
                    showData(binding, resource)
                }
                is Resource.Error -> {

                    resource.message?.let {
                        Log.e(TAG, it)
                        if (resource.message.contains("Unable to resolve host")) {
                            showNoInternetConnectionMessage(binding)
                        } else {
                            showErrorMessage(binding)
                        }
                    }


                }
            }
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
        viewModel.recipes.value?.let { resource ->
            resource.data?.let { recipes ->
                if (recipes.isNotEmpty()) {
                    recipeAdapter.submitList(recipes)
                }
                if (binding.searchView.isNotEmpty()) {
                    recipeAdapter.filter.filter(binding.searchView.query.toString())
                }
            }
        }
    }


    private fun navigateToDetailFragment(recipe: Recipe) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(recipe)
        findNavController().navigate(action)
    }

    private fun showData(binding: FragmentHomeBinding, resource: Resource<List<Recipe>>) {
        binding.loadingIcon.visibility = View.GONE
        binding.recyclerview.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
        resource.data?.let {
            recipeAdapter.submitList(it)
        }
    }

    private fun showLoadingIcon(binding: FragmentHomeBinding){
        binding.noInternetMessage.visibility= View.GONE
        binding.errorMessage.visibility= View.GONE
        binding.loadingIcon.visibility = View.VISIBLE
    }

    private fun showNoInternetConnectionMessage(binding: FragmentHomeBinding){
        binding.swipeRefreshLayout.isRefreshing = false
        binding.errorMessage.visibility= View.GONE
        binding.loadingIcon.visibility = View.GONE
        binding.recyclerview.visibility = View.GONE
        binding.noInternetMessage.visibility= View.VISIBLE
    }

    private fun showErrorMessage(binding: FragmentHomeBinding){
        binding.swipeRefreshLayout.isRefreshing = false
        binding.noInternetMessage.visibility= View.GONE
        binding.loadingIcon.visibility = View.GONE
        binding.recyclerview.visibility = View.GONE
        binding.errorMessage.visibility= View.VISIBLE
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

}