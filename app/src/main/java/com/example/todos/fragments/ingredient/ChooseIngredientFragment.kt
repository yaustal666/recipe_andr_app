package com.example.todos.fragments.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todos.adapters.IngredientChooseAdapter
import com.example.todos.data.entities.Ingredient
import com.example.todos.databinding.FragmentChooseIngredientBinding
import com.example.todos.fragments.recipe.AddRecipeFragment
import com.example.todos.fragments.recipe.UpdateRecipeFragment
import com.example.todos.viewModel.IngredientViewModel

class ChooseIngredientFragment : Fragment() {

    private val args: ChooseIngredientFragmentArgs by navArgs()
    private var _binding: FragmentChooseIngredientBinding? = null
    private val binding get() = _binding!!

    private lateinit var ingredientViewModel: IngredientViewModel

    private val chosenIngredients = mutableListOf<Ingredient>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseIngredientBinding.inflate(inflater, container, false)
        val view = binding.root

        ingredientViewModel = ViewModelProvider(this)[IngredientViewModel::class.java]

        binding.confirmChosenIngredients.setOnClickListener() {
            if (args.isUpdate) {
                UpdateRecipeFragment.isBack = true
                UpdateRecipeFragment.chosenIngredients = chosenIngredients
            } else {
                AddRecipeFragment.isBack = true
                AddRecipeFragment.chosenIngredients = chosenIngredients
            }
            findNavController().navigateUp()
        }

        val adapter = IngredientChooseAdapter() { ingredient, todo ->
            run {
                if (todo) {
                    chosenIngredients.add(ingredient)
                } else {
                    chosenIngredients.remove(ingredient)
                }
            }

        }
        val recyclerView = binding.ingredientRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        ingredientViewModel.getAllIngredients.observe(
            viewLifecycleOwner
        ) { ingredient ->
            adapter.setData(ingredient)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}