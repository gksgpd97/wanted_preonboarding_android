package com.example.wantedpreonboardingandroid.ui.saved

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wantedpreonboardingandroid.R
import com.example.wantedpreonboardingandroid.adapter.RecyclerviewAdapter
import com.example.wantedpreonboardingandroid.databinding.FragmentSavedBinding
import com.example.wantedpreonboardingandroid.viewmodel.SavedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val savedViewModel by lazy {
        ViewModelProvider(
            this
        )[SavedViewModel::class.java]
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = RecyclerviewAdapter({ article ->
            Log.d("SavedFragment", "click")
            findNavController().navigate(
                R.id.action_saved_to_newsdetail, bundleOf(
                    "article" to article,
                    "before" to "saved"
                )
            )
        }, requireContext())
        binding.recyclerviewSaved.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewSaved.adapter = adapter
        binding.recyclerviewSaved.setHasFixedSize(true)

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
        val haveSaved = savedViewModel.haveSaved()
        Log.d("Test", haveSaved.toString())
        if (!haveSaved) {
            binding.recyclerviewSaved.visibility = View.GONE
            binding.textviewSaved.visibility = View.VISIBLE
        } else {
            binding.textviewSaved.visibility = View.GONE
            binding.recyclerviewSaved.visibility = View.VISIBLE
        }
    }
}