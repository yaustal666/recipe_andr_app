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
import androidx.navigation.fragment.navArgs
import com.example.todos.R
import com.example.todos.data.entities.Ingredient
import com.example.todos.databinding.FragmentUpdateIngredientBinding
import com.example.todos.viewModel.IngredientViewModel

class UpdateIngredientFragment : Fragment() {

    private val args: UpdateIngredientFragmentArgs by navArgs()

    private var _binding: FragmentUpdateIngredientBinding? = null
    private val binding get() = _binding!!

    private lateinit var ingredientViewModel: IngredientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateIngredientBinding.inflate(inflater, container, false)
        val view = binding.root

        ingredientViewModel = ViewModelProvider(this)[IngredientViewModel::class.java]

        binding.updateIngredientName.setText(args.currentIngredient.name)

        binding.confirmUpdateIngredientButton.setOnClickListener() {
            updateIngredient()
        }

        return view
    }

    private fun updateIngredient() {
        val name = binding.updateIngredientName.text.toString()

        if ( name == args.currentIngredient.name) {
            findNavController().navigate(R.id.nav_ingredient)
            return
        }

        if(inputValidate(name)) {
            val ingredient = Ingredient(args.currentIngredient.id, name)
            ingredientViewModel.updateIngredient(ingredient)

            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.nav_ingredient)
        } else {
            Toast.makeText(requireContext(), "You forgot to fill the fields!", Toast.LENGTH_LONG).show()
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