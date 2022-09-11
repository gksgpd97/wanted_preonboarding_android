package com.example.wantedpreonboardingandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var isSaved: Boolean = false
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
        setEvent()

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

        isSaved = newsDetailViewModel.isSaved(article.url)
        if (isSaved) {
            binding.imageviewNewsdetailScrap.setImageResource(R.drawable.ic_scrap_yellow)
        }
    }

    private fun setEvent() {
        binding.imageviewNewsdetailScrap.setOnClickListener {
            isSaved = if (isSaved) {
                newsDetailViewModel.delete(article.url)
                binding.imageviewNewsdetailScrap.setImageResource(R.drawable.ic_scrap_white)
                Toast.makeText(requireContext(), "스크랩이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                false
            } else {
                newsDetailViewModel.insert(article)
                binding.imageviewNewsdetailScrap.setImageResource(R.drawable.ic_scrap_yellow)
                Toast.makeText(requireContext(), "스크랩 되었습니다.", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}