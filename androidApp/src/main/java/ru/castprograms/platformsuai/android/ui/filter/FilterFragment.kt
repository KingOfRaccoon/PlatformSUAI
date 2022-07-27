package ru.castprograms.platformsuai.android.ui.filter

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shuhart.stickyheader.StickyHeaderItemDecorator
import dev.icerock.moko.mvvm.livedata.Closeable
import dev.icerock.moko.mvvm.livedata.addCloseableObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.viewModels.NewsViewModel
import ru.castprograms.platformsuai.android.databinding.FragmentFilterBinding
import ru.castprograms.platformsuai.data.news.tags.TagFilter

class FilterFragment : BottomSheetDialogFragment() {
    private val newsViewModel: NewsViewModel by inject()
    private val filtersAdapter =
        FiltersAdapter(newsViewModel.selectedTagFiltersLiveData.value ,{ addTagFilter(it) }) { removeTagFilter(it) }
    lateinit var binding: FragmentFilterBinding
    private lateinit var observer: Closeable

    override fun getTheme() = R.style.bottomSheet

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface: DialogInterface ->
            setupRatio(dialogInterface as BottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet: FrameLayout =
            bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
                ?: return

        BottomSheetBehavior.from<FrameLayout>(bottomSheet).state =
            BottomSheetBehavior.STATE_COLLAPSED
        val bottomSheetLayoutParams = bottomSheet.layoutParams

        bottomSheetLayoutParams.height =
            requireContext().resources.displayMetrics.heightPixels * 90 / 100

        bottomSheet.layoutParams = bottomSheetLayoutParams
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).skipCollapsed = false
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).isHideable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        loadFilters()

        observer = newsViewModel.selectedTagFiltersLiveData.addCloseableObserver {
            filtersAdapter.selectedItems = it
        }
        binding.inputTextTextFilter.addTextChangedListener { filtersAdapter.filter.filter(it) }
    }

    private fun loadFilters() {
        lifecycleScope.launch {
            newsViewModel.filtersFlow.collectLatest {
                it.map { it.data }.let {
                    filtersAdapter.differ.submitList(it.flatMap {
                        (it?.second
                            ?: listOf()).map { filter ->
                            filter.apply {
                                category = it?.first ?: ""
                            }
                        }
                    })
                }
            }
        }
    }

    private fun setupRecyclerViews() {
        binding.recyclerFilters.adapter = filtersAdapter
        val decorator = StickyHeaderItemDecorator(filtersAdapter)
        decorator.attachToRecyclerView(binding.recyclerFilters)
        binding.recyclerFilters.setHasFixedSize(true)
        ViewCompat.setNestedScrollingEnabled(binding.recyclerRecentFilters, false)
    }

    private fun addTagFilter(tagFilter: TagFilter){
        lifecycleScope.launch(Dispatchers.IO) {
            newsViewModel.addSelectedTagFilter(tagFilter)
        }
    }

    private fun removeTagFilter(tagFilter: TagFilter){
        lifecycleScope.launch(Dispatchers.IO) {
            newsViewModel.removeSelectedTagFilter(tagFilter)
        }
    }

    override fun onStop() {
        super.onStop()
        observer.close()
    }
}