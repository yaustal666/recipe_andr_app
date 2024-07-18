package com.example.todos.fragments.recipe

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todos.R
import com.example.todos.data.entities.Category
import com.example.todos.data.entities.Diary
import com.example.todos.data.entities.Ingredient
import com.example.todos.data.entities.Recipe
import com.example.todos.databinding.FragmentUpdateRecipeBinding
import com.example.todos.fragments.recipe.AddRecipeFragment.Companion
import com.example.todos.viewModel.DiaryViewModel
import com.example.todos.viewModel.RecipeViewModel

class UpdateRecipeFragment : Fragment() {

    private val args: UpdateRecipeFragmentArgs by navArgs()

    private var _binding: FragmentUpdateRecipeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeViewModel: RecipeViewModel

    companion object {
        var isBack: Boolean = false
        var chosenCategory: Category? = null
        var chosenIngredients = mutableListOf<Ingredient>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        binding.updateRecipeName.setText(args.currentRecipe.name)
        binding.updateRecipeTimeToCook.setText(args.currentRecipe.timeToCook)
        binding.updateRecipePreparation.setText(args.currentRecipe.preparation)
        binding.updateRecipeCookingProcess.setText(args.currentRecipe.cooking)
        binding.updateRecipeServing.setText(args.currentRecipe.serving)
        binding.updateRecipeCalories.setText(args.currentRecipe.calories.toString())

        binding.updateRecipeCategory.text = args.currentRecipe.category?.name ?: "Category"
        if(args.currentRecipe.ingredients.isEmpty()) {
            binding.updateRecipeIngredients.text = "Choose Ingredients"
        } else {
            var inglist = ""
            for (i in args.currentRecipe.ingredients) {
                inglist += i.name
                inglist += "\n"
            }

            binding.updateRecipeIngredients.text = inglist
        }

        if (isBack) {
            if(chosenIngredients.isNotEmpty()) {
                var inglist = ""
                chosenIngredients.sortBy { it.id }
                    for (i in chosenIngredients) {
                        inglist += i.name
                        inglist += "\n"
                    }

                    binding.updateRecipeIngredients.text = inglist
            }

            binding.updateRecipeCategory.text = chosenCategory?.name ?: args.currentRecipe.category?.name ?: "Category"
            isBack = false
        }

        binding.updateRecipeIngredients.setOnClickListener() {
            val action =
                UpdateRecipeFragmentDirections.actionNavUpdateRecipeToNavChooseIngredient(true)
            findNavController().navigate(action)
        }

        binding.updateRecipeCategory.setOnClickListener() {
            val action =
                UpdateRecipeFragmentDirections.actionNavUpdateRecipeToNavCategory(true, true)
            findNavController().navigate(action)
        }

        binding.confirmUpdateRecipeButton.setOnClickListener() {
            updateRecipe()
            isBack = false
            chosenCategory = null
            chosenIngredients = mutableListOf<Ingredient>()
            findNavController().navigate(R.id.nav_recipe)
        }

        return view
    }

    private fun updateRecipe() {
        val name = binding.updateRecipeName.text.toString()
        val timeToCook = binding.updateRecipeTimeToCook.text.toString()
        val preparation = binding.updateRecipePreparation.text.toString()
        val cookingProcess = binding.updateRecipeCookingProcess.text.toString()
        val serving = binding.updateRecipeServing.text.toString()
        val calories = binding.updateRecipeCalories.text.toString()

        val category =
            if (chosenCategory == null) args.currentRecipe.category
            else chosenCategory

        val ingredients =
            if (chosenIngredients.isEmpty()) args.currentRecipe.ingredients
            else chosenIngredients

        if (inputValidate(name, timeToCook, preparation, cookingProcess, serving, calories)) {
            val recipe = Recipe(
                args.currentRecipe.id,
                name,
                ingredients,
                preparation,
                cookingProcess,
                serving,
                category,
                calories.toInt(),
                timeToCook,
                false
            )
            recipeViewModel.updateRecipe(recipe)
        } else {
            Toast.makeText(requireContext(), "You forgot to fill the fields!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputValidate(
        name: String,
        timeToCook: String,
        preparation: String,
        cooking: String,
        serving: String,
        calories: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(timeToCook) && TextUtils.isEmpty(
            preparation
        ) && TextUtils.isEmpty(cooking) && TextUtils.isEmpty(serving) && TextUtils.isEmpty(calories))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}