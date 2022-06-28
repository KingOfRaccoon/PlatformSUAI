package ru.castprograms.platformsuai.android.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.castprograms.calendarkmmsuai.util.Resource
import ru.castprograms.platformsuai.TimeTableViewModel
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main), DatesAdapter.OnDateItemClickListener {
    private var currentIndex = -1
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

    private val lessonsAdapter by lazy {
        LessonsAdapter { timeTableViewModel.getTime(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.recyclerDates.adapter = datesAdapter
        binding.recyclerEvents.adapter = lessonsAdapter

        setHasOptionsMenu(true)
        MainScope().launch(Dispatchers.Main) {
            timeTableViewModel.datesFlow.collectLatest {
                requireActivity().runOnUiThread {
                    datesAdapter.setNewDates(it)
                }
            }
        }

        MainScope().launch(Dispatchers.Main) {
            timeTableViewModel.timeTableGroupFlow.collectLatest {
                requireActivity().runOnUiThread {
                    lessonsAdapter.setNewLessons(it.data?.get(12) ?: listOf())
                }
            }
        }

        MainScope().launch(Dispatchers.Main) {
            timeTableViewModel.newsFlow.collectLatest {
                if (it is Resource.Success){
                    val urls = it.data?.let {
                        it.Banners.map { "https://media.guap.ru/${it.bannerMediaUrl}.jpg" }
                    }
                    println(urls)

                    requireActivity().runOnUiThread {
                        binding.banner.setAdapter(BaseBannerAdapter(requireContext(), urls))
                        binding.banner.setAutoPlaying(true)
                    }
                } else {
                    println(it.message)
                }
            }
        }
    }

    override fun onItemClick(position: Int, day: Int, week: Int) {

    }

    private fun changeCurrentDay(position: Int) {
        currentIndex = position
        Handler(requireContext().mainLooper).postDelayed({
            binding.recyclerDates.layoutManager?.startSmoothScroll(
                CenterSmoothScroller(requireContext()).apply {
                    targetPosition = currentIndex
                }
            )
        }, 2)
    }

    private fun setCurrentDay() {
        val date = timeTableViewModel.getCurrentDay()
        datesAdapter.dates.indexOfFirst {
            it.dayOfMouth == date.dayOfMouth && it.mouth == date.mouth && it.year == date.year
        }.let {
            changeCurrentDay(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.app_bar_menu, menu)
    }
}