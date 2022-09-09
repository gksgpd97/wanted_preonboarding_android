package com.example.wantedpreonboardingandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wantedpreonboardingandroid.R
import com.example.wantedpreonboardingandroid.data.Article
import com.example.wantedpreonboardingandroid.databinding.FragmentNewsdetailBinding
import com.example.wantedpreonboardingandroid.utilities.DateConvertor
import com.example.wantedpreonboardingandroid.viewmodel.NewsDetailViewModel

class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsdetailBinding? = null
    private lateinit var article: Article
    private val newsDetailViewModel by lazy {
        ViewModelProvider(
            this,
            NewsDetailViewModel.Factory(requireActivity().application)
        )[NewsDetailViewModel::class.java]
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsdetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        article = arguments?.getSerializable("article") as Article

        setUi()

        return root
    }

    private fun setUi() {
        if (article.urlToImage != null) {
            Glide.with(requireContext()).load(article.urlToImage)
                .into(binding.imageviewNewsdetailImage)
        } else {
            binding.imageviewNewsdetailImage.visibility = View.GONE
        }
        binding.textviewNewsdetailTitle.text = article.title
        binding.textviewNewsdetailAuthor.text = article.author
        binding.textviewNewsdetailPublishedAt.text =
            DateConvertor().dateConvertor(article.publishedAt.toString())
        binding.textviewNewsdetailContent.text = article.description + "..."
        binding.textviewNewsdetailUrl.text = "전문 보러가기 : ${article.url}"

        if (arguments?.getString("before") == "notSaved") {
            binding.imageviewNewsdetailScrap.setOnClickListener {
                newsDetailViewModel.insert(article)
                binding.imageviewNewsdetailScrap.setImageResource(R.drawable.ic_scrap_yellow)
            }
        } else if (arguments?.getString("before") == "saved") {
            binding.imageviewNewsdetailScrap.setImageResource(R.drawable.ic_scrap_yellow)
            binding.imageviewNewsdetailScrap.setOnClickListener {
                newsDetailViewModel.delete(article.id!!)
                binding.imageviewNewsdetailScrap.setImageResource(R.drawable.ic_scrap_white)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}