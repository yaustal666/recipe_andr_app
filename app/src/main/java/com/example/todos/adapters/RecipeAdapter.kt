package com.example.todos.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.data.entities.Recipe
import com.example.todos.databinding.RecyclerRecipeElementBinding
import com.example.todos.fragments.recipe.RecipeFragmentDirections

class RecipeAdapter(
    private val listener: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private var recipeList = emptyList<Recipe>()

    class ViewHolder(val binding: RecyclerRecipeElementBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRecipeElementBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recipeList[position]

        holder.binding.recipeTitle.text = currentItem.name
        holder.binding.recipeTimeToCook.text = currentItem.timeToCook

        holder.binding.updateRecipeButton.setOnClickListener() {
            val action = RecipeFragmentDirections.actionRecipeFragmentToUpdateRecipeFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.binding.addToDiary.setOnClickListener {listener(currentItem)}
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(recipe: List<Recipe>) {
        this.recipeList = recipe
        notifyDataSetChanged()
    }
}