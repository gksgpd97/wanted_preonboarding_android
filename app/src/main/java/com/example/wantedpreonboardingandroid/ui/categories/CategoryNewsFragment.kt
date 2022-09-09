package com.example.wantedpreonboardingandroid.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wantedpreonboardingandroid.adapter.RecyclerviewAdapter
import com.example.wantedpreonboardingandroid.databinding.FragmentTopnewsBinding
import com.example.wantedpreonboardingandroid.ui.MainActivity
import com.example.wantedpreonboardingandroid.ui.NewsDetailFragment
import com.example.wantedpreonboardingandroid.viewmodel.CategoriesViewModel

class CategoryNewsFragment : Fragment() {

    private var _binding: FragmentTopnewsBinding? = null
    private val categoriesViewModel by lazy {
        ViewModelProvider(
            this,
            CategoriesViewModel.Factory(
                requireActivity().application,
                arguments?.getString("category").toString()
            )
        )[CategoriesViewModel::class.java]
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
            Log.d("CategoryNewsFragment", "click1")
            (activity as MainActivity).changeToNewsDetailFragment(
                NewsDetailFragment(),
                article,
                "notSaved"
            )
        }, requireContext())
        binding.recyclerviewTopnews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
            binding.recyclerviewTopnews.setHasFixedSize(true)
        }

        subscribeUi(adapter)

        return root
    }

    override fun onResume() {
        super.onResume()
        val a: FragmentActivity? = activity
        var category = arguments?.getString("category").toString()
        category = when (category) {
            "business" -> " - 금융"
            "entertainment" -> " - 연예"
            "general" -> " - 일반"
            "health" -> " - 건강"
            "science" -> " - 과학"
            "sports" -> " - 스포츠"
            "technology" -> " - 기술"
            else -> ""
        }
        (activity as MainActivity).setActionBarTitle("카테고리${category}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUi(adapter: RecyclerviewAdapter) {
        categoriesViewModel.response.observe(requireActivity()) {
            categoriesViewModel.response.value?.let { it -> adapter.setArticles(it.articles) }
        }
    }
}