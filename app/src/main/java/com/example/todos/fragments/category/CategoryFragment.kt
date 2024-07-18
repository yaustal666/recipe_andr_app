package com.example.todos.fragments.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todos.R
import com.example.todos.adapters.CategoryAdapter
import com.example.todos.data.entities.Category
import com.example.todos.databinding.FragmentCategoryBinding
import com.example.todos.fragments.recipe.AddRecipeFragment
import com.example.todos.fragments.recipe.UpdateRecipeFragment
import com.example.todos.viewModel.CategoryViewModel

class CategoryFragment : Fragment() {

    private val args: CategoryFragmentArgs by navArgs()

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addCategoryButton.setOnClickListener() {
            findNavController().navigate(R.id.nav_add_category)
        }

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        val adapter: CategoryAdapter
        if (!args.isChoose) {
            adapter = CategoryAdapter() { category: Category ->
                run {
                    val action =
                        CategoryFragmentDirections.actionCategoryFragmentToUpdateCategoryFragment(
                            category
                        )
                    findNavController().navigate(action)
                }
            }
        } else {
            binding.addCategoryButton.isVisible = false
            adapter = CategoryAdapter() { category: Category ->
                run {
                    if (args.isUpdate) {
                        UpdateRecipeFragment.isBack = true
                        UpdateRecipeFragment.chosenCategory = category
                    } else {
                        AddRecipeFragment.isBack = true
                        AddRecipeFragment.chosenCategory = category
                    }
                    findNavController().navigateUp()
                }
            }
        }

        val recyclerView = binding.categoryRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        categoryViewModel.getAllCategories.observe(viewLifecycleOwner, Observer { category ->
            adapter.setData(category)
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}