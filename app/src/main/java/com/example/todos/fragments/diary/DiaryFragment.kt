package com.example.todos.fragments.diary

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todos.adapters.DiaryAdapter
import com.example.todos.data.entities.Diary
import com.example.todos.data.entities.Recipe
import com.example.todos.databinding.FragmentDiaryBinding
import com.example.todos.viewModel.DiaryViewModel


class DiaryFragment : Fragment() {

    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding!!

    private lateinit var diaryViewModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        val view = binding.root

        diaryViewModel = ViewModelProvider(this)[DiaryViewModel::class.java]

        val adapter = DiaryAdapter()
        val recyclerView = binding.diaryRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        diaryViewModel.getDiary.observe(viewLifecycleOwner, Observer { diary ->
            adapter.setData(diary)
        })

        val date = SimpleDateFormat("yyyy-MM-dd").format(java.util.Date())
        var diary : Diary? = null
        diaryViewModel.getDiaryByDate(date).observe(viewLifecycleOwner, Observer {item ->
            diary = item
        })

        binding.addDiaryButton.setOnClickListener() {
            if ((diary?.date ?: "") != date) {
                insertDiary(date)
            } else {
                Toast.makeText(requireContext(),"Already exists today diary", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun insertDiary(date: String) {
        val diary = Diary(0, date, mutableListOf<Recipe>(), 0)
        diaryViewModel.addDiary(diary)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}