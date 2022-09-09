package com.example.wantedpreonboardingandroid.ui.topnews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wantedpreonboardingandroid.adapter.RecyclerviewAdapter
import com.example.wantedpreonboardingandroid.databinding.FragmentTopnewsBinding
import com.example.wantedpreonboardingandroid.ui.MainActivity
import com.example.wantedpreonboardingandroid.ui.NewsDetailFragment
import com.example.wantedpreonboardingandroid.viewmodel.TopNewsViewModel

class TopNewsFragment : Fragment() {

    private var _binding: FragmentTopnewsBinding? = null
    private val topNewsViewModel by lazy {
        ViewModelProvider(
            this,
            TopNewsViewModel.Factory(requireActivity().application)
        )[TopNewsViewModel::class.java]
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTopnewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = RecyclerviewAdapter({ article ->
            Log.d("TopNewsFragment", "click1")
            (activity as MainActivity).changeToNewsDetailFragment(
                NewsDetailFragment(),
                article,
                "notSaved"
            )
        }, requireContext())
        binding.recyclerviewTopnews.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewTopnews.adapter = adapter
        binding.recyclerviewTopnews.setHasFixedSize(true)

        subscribeUi(adapter)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUi(adapter: RecyclerviewAdapter) {
        topNewsViewModel.response.observe(requireActivity()) {
            topNewsViewModel.response.value?.let { it -> adapter.setArticles(it.articles) }
        }
    }
}