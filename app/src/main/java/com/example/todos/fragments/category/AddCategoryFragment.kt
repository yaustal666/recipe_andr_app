package com.example.todos.fragments.category

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
import com.example.todos.databinding.FragmentAddCategoryBinding
import com.example.todos.viewModel.CategoryViewModel


class AddCategoryFragment : Fragment() {

    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        binding.confirmAddCategoryButton.setOnClickListener() {
            insertCategory()
        }

        return view
    }

    private fun insertCategory() {
        val name = binding.setCategoryName.text.toString()

        if(inputValidate(name)) {
            val category = Category(0, name)
            categoryViewModel.addCategory(category)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_category)
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