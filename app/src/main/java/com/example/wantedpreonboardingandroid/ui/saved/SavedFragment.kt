package com.example.wantedpreonboardingandroid.ui.saved

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
import com.example.wantedpreonboardingandroid.viewmodel.SavedViewModel

class SavedFragment : Fragment() {

    private var _binding: FragmentTopnewsBinding? = null
    private val savedViewModel by lazy {
        ViewModelProvider(
            this,
            SavedViewModel.Factory(requireActivity().application)
        )[SavedViewModel::class.java]
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
            Log.d("SavedFragment", "click1")
            (activity as MainActivity).changeToNewsDetailFragment(
                NewsDetailFragment(),
                article,
                "saved"
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
        savedViewModel.getAll().observe(requireActivity()) { articles ->
            adapter.setArticles(articles)
        }
    }
}