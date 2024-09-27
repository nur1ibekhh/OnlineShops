package com.example.newproject180.activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.newproject180.Adapter.BestSellerAdapter
import com.example.newproject180.Adapter.CategoryAdapter
import com.example.newproject180.Adapter.SliderAdapter
import com.example.newproject180.Model.SliderModel
import com.example.newproject180.ViewModel.MainViewModel
import com.example.newproject180.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanners()
        initCategories()
        initBestSeller()
        bottomNaviagion()
        openProfilePage()
    }

    private fun bottomNaviagion() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
    }


    private fun initBanners() {
        binding.progressBarBanner.visibility=View.VISIBLE
        viewModel.banners.observe(this,{
            banners(it)
            binding.progressBarBanner.visibility=View.GONE
        })
        viewModel.loadBanners()
    }

    private fun banners(images:List<SliderModel>){
        binding.viewPagerSlider.adapter=SliderAdapter(images,binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding=false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit=3
        binding.viewPagerSlider.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size>1){
            binding.dotIndicator.visibility=View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerSlider)
        }
    }

    private fun initCategories() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })

        viewModel.loadCategory()

    }

    private fun initBestSeller() {
        binding.progressBarBestseller.visibility=View.VISIBLE
        viewModel.bestSeller.observe(this,{
            binding.viewBestSeller.layoutManager=GridLayoutManager(this,2)
            binding.viewBestSeller.adapter=BestSellerAdapter(it)
            binding.progressBarBestseller.visibility=View.GONE
        })
        viewModel.loadBestSeller()
    }

    private fun openProfilePage(){
        binding.profilepage.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }
    }
}