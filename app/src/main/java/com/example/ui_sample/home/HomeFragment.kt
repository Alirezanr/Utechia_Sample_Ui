package com.example.ui_sample.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ui_sample.MainActivity
import com.example.ui_sample.R
import com.example.ui_sample.databinding.FragmentHomeBinding
import com.example.ui_sample.util.toPriceFormat
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private var galleryPageAdapter: HomeGalleryPageAdapter? = null
    private var stocksAdapter: HomeStockAdapter? = null

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
        setupClickListeners()
    }

    private fun setupClickListeners() {
        with(binding) {
            txtViewMore.setOnClickListener {
                Toast.makeText(requireContext(), "View More", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupUi() {
        (requireActivity() as MainActivity).changeStatusBarColor(R.color.blue_300)
        with(binding) {

        }
        setupStocksList()
        setupViewPager()
    }

    private fun setupStocksList() {
        stocksAdapter = HomeStockAdapter { stock ->
            Toast.makeText(
                requireContext(),
                "Stock name :${stock.stockName}\nStock price:${stock.stockPrice.toPriceFormat()} TL",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.rvStockList.adapter = stocksAdapter

        val stockList = listOf("Gold", "USDT", "ONS", "EUR", "BTC")
        stocksAdapter?.upDateDataSet(
            List(15) {
                StockData(
                    id = it,
                    stockName = stockList.random(),
                    stockPrice = (500..2000).random().toDouble()
                )
            }
        )
    }

    private fun setupViewPager() {
        galleryPageAdapter = HomeGalleryPageAdapter()
        with(binding) {
            vpGallery.adapter = galleryPageAdapter

            TabLayoutMediator(tabLayout, vpGallery) { tab, position ->
            }.attach()

        }

        galleryPageAdapter?.upDateDataSet(
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