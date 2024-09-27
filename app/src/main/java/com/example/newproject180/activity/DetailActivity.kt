package com.example.newproject180.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Size
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.newproject180.Adapter.PicListAdapter
import com.example.newproject180.Adapter.SizeListAdapter
import com.example.newproject180.Helper.ManagmentCart
import com.example.newproject180.Model.ItemsModel
import com.example.newproject180.R
import com.example.newproject180.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()
        initList()
    }

    private fun initList() {
        val sizeList= ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter=SizeListAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }

        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.picList.adapter=PicListAdapter(colorList,binding.picMain)
        binding.picList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getBundle(){
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating}"
        binding.SellerNameTxt.text=item.sellerName

        binding.AddToCartBtn.setOnClickListener {
            item.numberInCart=numberOrder
            managmentCart.insertItems(item)
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.CartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }

        Glide.with(this)
            .load(item.sellerPic)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(binding.picSeller)

        binding.msgToSellerBtn.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.setData(Uri.parse("sms"+item.sellerName))
            sendIntent.putExtra("sms_body","type your message")
            startActivity(intent)
        }

        binding.callToSellerBtn.setOnClickListener {
            val phone=item.sellerTell.toString()
            val intent=Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",phone,null))
            startActivity(intent)
        }
    }
}