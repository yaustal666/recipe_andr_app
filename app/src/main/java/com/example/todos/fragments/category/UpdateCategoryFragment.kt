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
import androidx.navigation.fragment.navArgs
import com.example.todos.R
import com.example.todos.data.entities.Category
import com.example.todos.databinding.FragmentUpdateCategoryBinding
import com.example.todos.viewModel.CategoryViewModel

class UpdateCategoryFragment : Fragment() {

    private val args: UpdateCategoryFragmentArgs by navArgs()

    private var _binding: FragmentUpdateCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        binding.updateCategoryName.setText(args.currentCategory.name)

        binding.confirmUpdateCategoryButton.setOnClickListener() {
            updateIngredient()
        }

        return view
    }

    private fun updateIngredient() {
        val name = binding.updateCategoryName.text.toString()

        if ( name == args.currentCategory.name) {
            findNavController().navigate(R.id.nav_category)
            return
        }

        if(inputValidate(name)) {
            val category = Category(args.currentCategory.id, name)
            categoryViewModel.updateCategory(category)

            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.nav_category)
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