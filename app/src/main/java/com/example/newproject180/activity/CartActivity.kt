package com.example.newproject180.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newproject180.Adapter.CartAdapter
import com.example.newproject180.Helper.ChangeNumberItemsListener
import com.example.newproject180.Helper.ManagmentCart
import com.example.newproject180.R
import com.example.newproject180.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax:Double=0.0
    private var total:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()
    }

    private fun calculateCart() {
        val perecentTax=0.02
        val delivery=15.0
        tax=Math.round((managmentCart.getTotalFee()*perecentTax)*100)/100.0
         total= (Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100).toDouble()
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text="$$total"
        }
    }

    private fun initCartList() {
        binding.cartView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter=CartAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }
        })
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}