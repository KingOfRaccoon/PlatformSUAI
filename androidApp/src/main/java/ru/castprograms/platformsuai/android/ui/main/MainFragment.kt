package ru.castprograms.platformsuai.android.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.castprograms.platformsuai.TimeTableViewModel
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main), DatesAdapter.OnDateItemClickListener {
    private val timeTableViewModel: TimeTableViewModel by viewModel()
    lateinit var binding: FragmentMainBinding
    private val linearLayoutManager by lazy {
        LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }
    private val datesAdapter by lazy {
        DatesAdapter(
            linearLayoutManager,
            this
        ) { setCurrentDay() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.recyclerDates.adapter = datesAdapter

        MainScope().launch(Dispatchers.Main) {
            timeTableViewModel.datesFlow.collectLatest {
                requireActivity().runOnUiThread {
                    datesAdapter.setNewDates(it)
                    println(it)
                }
            }
        }
    }

    override fun onItemClick(position: Int, day: Int, week: Int) {

    }

    private fun setCurrentDay() {
        val date = timeTableViewModel.getCurrentDay()
        datesAdapter.dates.indexOfFirst {
            it.dayOfMouth == date.dayOfMouth && it.mouth == date.mouth && it.year == date.year
        }.let {
            println(it)
            binding.recyclerDates.scrollToPosition(it)
        }
    }
}