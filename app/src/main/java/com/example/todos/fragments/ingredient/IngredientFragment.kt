package com.example.todos.fragments.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todos.R
import com.example.todos.adapters.IngredientAdapter
import com.example.todos.databinding.FragmentIngredientBinding
import com.example.todos.viewModel.IngredientViewModel

class IngredientFragment : Fragment() {

    private var _binding: FragmentIngredientBinding? = null
    private val binding get() = _binding!!

    private lateinit var ingredientViewModel: IngredientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addIngredientButton.setOnClickListener() {
            findNavController().navigate(R.id.nav_add_ingredient)
        }

        ingredientViewModel = ViewModelProvider(this)[IngredientViewModel::class.java]

        val adapter = IngredientAdapter()
        val recyclerView = binding.ingredientRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        ingredientViewModel.getAllIngredients.observe(
            viewLifecycleOwner,
            Observer { ingredient ->
                adapter.setData(ingredient)
            })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}