package com.example.wantedpreonboardingandroid.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wantedpreonboardingandroid.R
import com.example.wantedpreonboardingandroid.databinding.FragmentCategoriesBinding

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
            navigateFragment("business")
        }
        binding.linearlayoutCategoriesEntertainment.setOnClickListener {
            navigateFragment("entertainment")
        }
        binding.linearlayoutCategoriesGeneral.setOnClickListener {
            navigateFragment("general")
        }
        binding.linearlayoutCategoriesHealth.setOnClickListener {
            navigateFragment("health")
        }
        binding.linearlayoutCategoriesScience.setOnClickListener {
            navigateFragment("science")
        }
        binding.linearlayoutCategoriesSports.setOnClickListener {
            navigateFragment("sports")
        }
        binding.linearlayoutCategoriesTechnology.setOnClickListener {
            navigateFragment("technology")
        }
    }

    private fun navigateFragment(category: String) {
        findNavController().navigate(
            R.id.action_categories_to_categorynews, bundleOf(
                "category" to category
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}