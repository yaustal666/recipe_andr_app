package com.example.todos.fragments.recipe

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todos.R
import com.example.todos.adapters.RecipeAdapter
import com.example.todos.data.entities.Category
import com.example.todos.data.entities.Diary
import com.example.todos.databinding.FragmentRecipeBinding
import com.example.todos.viewModel.DiaryViewModel
import com.example.todos.viewModel.RecipeViewModel

class RecipeFragment : Fragment() {

    private val args: RecipeFragmentArgs by navArgs()
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var diaryViewModel: DiaryViewModel

    companion object {
        var categoryFilter: Category? = null
        var isSortByCalories: Boolean = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addRecipeButton.setOnClickListener() {
            findNavController().navigate(R.id.nav_add_recipe)
        }

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        diaryViewModel = ViewModelProvider(this)[DiaryViewModel::class.java]

        if(args.categoryFilter != null) {
            categoryFilter = args.categoryFilter
        }

        val date = SimpleDateFormat("yyyy-MM-dd").format(java.util.Date())
        var diary: Diary? = null
        diaryViewModel.getDiaryByDate(date).observe(viewLifecycleOwner) { item ->
            diary = item
        }

        val adapter = RecipeAdapter() { recipe ->
            run {
                if(diary == null){
                    Toast.makeText(requireContext(),"Add a diary first", Toast.LENGTH_LONG).show()
                }
                if (diary?.recipes?.contains(recipe) == true) {
                    Toast.makeText(requireContext(),"Already in diary", Toast.LENGTH_LONG).show()
                } else if (diary != null){
                    Toast.makeText(requireContext(),"Added to Diary", Toast.LENGTH_LONG).show()
                    diary!!.recipes.add(recipe)
                    diary!!.calories += recipe.calories
                    diaryViewModel.updateDiary(diary!!)
                }
            }
        }

        val recyclerView = binding.recipeRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recipeViewModel.getAllRecipes.observe(viewLifecycleOwner) { recipe ->
            run {
                var recipeList = recipe

                if (categoryFilter != null) {
                    binding.filterInterface.visibility = VISIBLE
                    binding.currentCategoryFilter.text = categoryFilter!!.name
                    recipeList =
                        recipeList.filter { (it.category?.name ?: "") == categoryFilter!!.name }
                }

                if (isSortByCalories) {
                    recipeList = recipeList.sortedBy { it.calories }
                }

                adapter.setData(recipeList)
            }
        }

        binding.resetFilterButton.setOnClickListener() {
            categoryFilter = null
            binding.filterInterface.visibility = GONE
            findNavController().navigate(R.id.nav_recipe)
        }

        binding.sortByCalories.setOnClickListener() {
            if(isSortByCalories) {
                isSortByCalories = false
            } else {
                isSortByCalories = true
            }
            findNavController().navigate(R.id.nav_recipe)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}