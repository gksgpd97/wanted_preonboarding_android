package com.example.wantedpreonboardingandroid.ui.topnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wantedpreonboardingandroid.R
import com.example.wantedpreonboardingandroid.adapter.RecyclerviewAdapterTmp
import com.example.wantedpreonboardingandroid.databinding.FragmentTopnewsBinding
import com.example.wantedpreonboardingandroid.viewmodel.TopNewsViewModelTmp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopNewsFragment : Fragment() {

    private var _binding: FragmentTopnewsBinding? = null
//    private val topNewsViewModel by lazy {
//        ViewModelProvider(
//            this,
//            TopNewsViewModel.Factory(requireActivity().application)
//        )[TopNewsViewModel::class.java]
//    }

    private val viewModelTmp by lazy {
        ViewModelProvider(this)[TopNewsViewModelTmp::class.java]
    }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTopnewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerviewAdapterTmp = RecyclerviewAdapterTmp({ article ->
            Log.d("TopNewsFragment", "click")
            findNavController().navigate(
                R.id.action_topnews_to_newsdetail, bundleOf(
                    "article" to article,
                    "before" to "notSaved"
                )
            )
        }, requireContext())
        lifecycleScope.launch {
            recyclerviewAdapterTmp!!.loadStateFlow.distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerviewTopnews.scrollToPosition(0) }
        }
        binding.recyclerviewTopnews.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewTopnews.adapter = recyclerviewAdapterTmp
        binding.recyclerviewTopnews.setHasFixedSize(true)
        subscribeUi(recyclerviewAdapterTmp)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUi(adapter: RecyclerviewAdapterTmp?) {
//        topNewsViewModel.response.observe(requireActivity()) {
//            topNewsViewModel.response.value?.let { it -> adapter.setArticles(it.articles) }
//        }
//        lifecycleScope.launch {
//            topNewsViewModel.selectArticlesTmp().observe(viewLifecycleOwner) {
//                adapter.submitData(lifecycle, it)
//            }
//        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModelTmp.response.observe(requireActivity()) {
                adapter?.submitData(lifecycle, it)
            }
        }

    }
}
