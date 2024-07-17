package com.example.todos.fragments.recipe

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todos.R
import com.example.todos.data.entities.Category
import com.example.todos.data.entities.Ingredient
import com.example.todos.data.entities.Recipe
import com.example.todos.databinding.FragmentAddRecipeBinding
import com.example.todos.viewModel.RecipeViewModel

class AddRecipeFragment : Fragment() {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!

    companion object {
        var isBack: Boolean = false
        var chosenCategory: Category? = null
        var chosenIngredients = mutableListOf<Ingredient>()
    }

    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        if (isBack) {
            var inglist = ""
            chosenIngredients.sortBy { it.id }
            if (chosenIngredients.isEmpty()) {
                binding.setRecipeIngredients.text = "Choose Ingredients"
            } else {
                for (i in chosenIngredients) {
                    inglist += i.name
                    inglist += "\n"
                }

                binding.setRecipeIngredients.text = inglist
            }

            binding.setRecipeCategory.text = chosenCategory?.name ?: "Category"
            isBack = false
        }

        binding.setRecipeIngredients.setOnClickListener() {
            val action = AddRecipeFragmentDirections.actionNavAddRecipeToNavChooseIngredient(false)
            findNavController().navigate(action)
        }

        binding.setRecipeCategory.setOnClickListener() {
            val action = AddRecipeFragmentDirections.actionChooseCategory(true, false)
            findNavController().navigate(action)
        }
            binding.confirmAddRecipeButton.setOnClickListener() {
                insertRecipe()
                isBack = false
                chosenCategory = null
                chosenIngredients = mutableListOf<Ingredient>()
                findNavController().navigate(R.id.nav_recipe)
            }
        return view
    }

    private fun insertRecipe() {
        val name = binding.setRecipeName.text.toString()
        val timeToCook = binding.setRecipeTimeToCook.text.toString()
        val preparation = binding.setRecipePreparation.text.toString()
        val cookingProcess = binding.setRecipeCookingProcess.text.toString()
        val serving = binding.setRecipeServing.text.toString()
        val calories = binding.setRecipeCalories.text.toString()

        if (inputValidate(name, timeToCook, preparation, cookingProcess, serving, calories)) {
            val recipe = Recipe(
                0, name, chosenIngredients, preparation, cookingProcess, serving,
                chosenCategory, calories.toInt(), timeToCook, false
            )
            recipeViewModel.addRecipe(recipe)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Fill all fields!", Toast.LENGTH_LONG).show()
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