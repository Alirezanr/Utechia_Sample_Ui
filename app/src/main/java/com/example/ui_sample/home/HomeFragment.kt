package com.example.ui_sample.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.ui_sample.MainActivity
import com.example.ui_sample.R
import com.example.ui_sample.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private var pageAdapter: HomeGalleryPageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        (requireActivity() as MainActivity).changeStatusBarColor(R.color.blue_300)
        setupViewPager()
        with(binding) {

        }
    }

    private fun setupViewPager() {
        pageAdapter = HomeGalleryPageAdapter()
        with(binding) {
            vpGallery.adapter = pageAdapter

            TabLayoutMediator(tabLayout, vpGallery) { tab, position ->
            }.attach()

        }

        pageAdapter?.upDateDataSet(
            List(size = 4) {
                GalleyData(
                    id = it,
                    title = "title $it",
                    body = getString(R.string.lorem_ipsum),
                    imageResource = R.drawable.sample_gallery
                )
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}