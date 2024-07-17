package com.example.todos.fragments.ingredient

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
import com.example.todos.data.entities.Ingredient
import com.example.todos.databinding.FragmentAddIngredientBinding
import com.example.todos.viewModel.IngredientViewModel

class AddIngredientFragment : Fragment() {

    private var _binding: FragmentAddIngredientBinding? = null
    private val binding get() = _binding!!

    private lateinit var ingredientViewModel: IngredientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddIngredientBinding.inflate(inflater, container, false)
        val view = binding.root

        ingredientViewModel = ViewModelProvider(this)[IngredientViewModel::class.java]

        binding.confirmAddIngredientButton.setOnClickListener() {
            insertIngredient()
        }

        return view
    }

    private fun insertIngredient() {
        val name = binding.setIngredientName.text.toString()

        if(inputValidate(name)) {
            val ingredient = Ingredient(0, name)
            ingredientViewModel.addIngredient(ingredient)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_ingredient)
        } else {
            Toast.makeText(requireContext(), "Fill all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputValidate(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}