package com.example.todos.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.data.entities.Ingredient
import com.example.todos.databinding.RecyclerIngredientElementBinding
import com.example.todos.fragments.ingredient.IngredientFragmentDirections

class IngredientAdapter() : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private var ingredientList = emptyList<Ingredient>()

    class ViewHolder(val binding: RecyclerIngredientElementBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerIngredientElementBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = ingredientList[position]

        holder.binding.ingredientTitle.text = currentItem.name

        holder.binding.updateIngredientButton.setOnClickListener() {
            val action =
                IngredientFragmentDirections.actionIngredientFragmentToUpdateIngredientFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(ingredient: List<Ingredient>) {
        this.ingredientList = ingredient
        notifyDataSetChanged()
    }
}