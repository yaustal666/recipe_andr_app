package com.example.todos.fragments.diary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todos.databinding.FragmentDiaryPageBinding

class DiaryPageFragment : Fragment() {

    private val args: DiaryPageFragmentArgs by navArgs()

    private var _binding: FragmentDiaryPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiaryPageBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.diaryTitle.text = args.currentDiary.date.toString()
        binding.diaryTotalCalories.text = args.currentDiary.calories.toString()

        if(args.currentDiary.recipes.isNotEmpty()) {
            var recipeNames = ""
            for (i in args.currentDiary.recipes) {
                recipeNames += i.name
                recipeNames += "\n"
            }
            binding.diaryRecipes.text = recipeNames
        }

        binding.backToDiary.setOnClickListener() {
            findNavController().navigateUp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}