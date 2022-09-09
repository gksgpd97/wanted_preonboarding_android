package com.example.wantedpreonboardingandroid.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wantedpreonboardingandroid.databinding.FragmentCategoriesBinding
import com.example.wantedpreonboardingandroid.ui.MainActivity

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setEvent()
        return root
    }

    private fun setEvent() {
        binding.linearlayoutCategoriesBusiness.setOnClickListener {
            (activity as MainActivity).changeToCategoryNewsFragment(
                CategoryNewsFragment(),
                "business"
            )
        }
        binding.linearlayoutCategoriesEntertainment.setOnClickListener {
            (activity as MainActivity).changeToCategoryNewsFragment(
                CategoryNewsFragment(),
                "entertainment"
            )
        }
        binding.linearlayoutCategoriesGeneral.setOnClickListener {
            (activity as MainActivity).changeToCategoryNewsFragment(
                CategoryNewsFragment(),
                "general"
            )
        }
        binding.linearlayoutCategoriesHealth.setOnClickListener {
            (activity as MainActivity).changeToCategoryNewsFragment(
                CategoryNewsFragment(),
                "health"
            )
        }
        binding.linearlayoutCategoriesScience.setOnClickListener {
            (activity as MainActivity).changeToCategoryNewsFragment(
                CategoryNewsFragment(),
                "science"
            )
        }
        binding.linearlayoutCategoriesSports.setOnClickListener {
            (activity as MainActivity).changeToCategoryNewsFragment(
                CategoryNewsFragment(),
                "sports"
            )
        }
        binding.linearlayoutCategoriesTechnology.setOnClickListener {
            (activity as MainActivity).changeToCategoryNewsFragment(
                CategoryNewsFragment(),
                "technology"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}