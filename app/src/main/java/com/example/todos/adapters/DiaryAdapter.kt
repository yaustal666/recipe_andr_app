package com.example.todos.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.data.entities.Diary
import com.example.todos.databinding.RecyclerDiaryElementBinding
import com.example.todos.fragments.diary.DiaryFragmentDirections

class DiaryAdapter() : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {

    private var diaryList = emptyList<Diary>()

    class ViewHolder(val binding: RecyclerDiaryElementBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerDiaryElementBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = diaryList[position]
        holder.binding.diaryTitle.text = currentItem.date.toString()

        holder.itemView.setOnClickListener() {
            val action = DiaryFragmentDirections.actionNavDiaryToNavDiaryPage(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(diary: List<Diary>) {
        this.diaryList = diary
        notifyDataSetChanged()
    }
}