package com.example.todos.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.data.entities.Category
import com.example.todos.databinding.RecyclerCategoryElementBinding
import com.example.todos.fragments.category.CategoryFragmentDirections
import com.example.todos.fragments.ingredient.IngredientFragmentDirections

class CategoryAdapter (
    private val listener: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categoryList = emptyList<Category>()

    class ViewHolder(val binding: RecyclerCategoryElementBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerCategoryElementBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categoryList[position]

        holder.binding.categoryTitle.text = currentItem.name

        holder.itemView.setOnClickListener { listener(currentItem)}

        holder.binding.showRecipesCategoryButton.setOnClickListener() {
            val action = CategoryFragmentDirections.actionNavCategoryToNavRecipe(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(category: List<Category>) {
        this.categoryList = category
        notifyDataSetChanged()
    }
}