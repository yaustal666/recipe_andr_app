package com.example.todos.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.data.entities.Ingredient
import com.example.todos.databinding.ReciclerChooseIngredientElementBinding

class IngredientChooseAdapter(
    private val listener: (Ingredient, Boolean) -> Unit
) : RecyclerView.Adapter<IngredientChooseAdapter.ViewHolder>() {
    private var ingredientList = emptyList<Ingredient>()

    class ViewHolder(val binding: ReciclerChooseIngredientElementBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReciclerChooseIngredientElementBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = ingredientList[position]

        holder.binding.ingredientTitle.text = currentItem.name

        holder.itemView.setOnClickListener() {
            if(!holder.binding.isIngredientChosen.isChecked) {
                listener(currentItem, true)
                holder.binding.isIngredientChosen.isChecked = true
            } else {
                listener(currentItem, false)
                holder.binding.isIngredientChosen.isChecked = false
            }
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