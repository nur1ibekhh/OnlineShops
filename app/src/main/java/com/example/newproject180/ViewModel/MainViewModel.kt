package com.example.newproject180.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproject180.Model.CategoryModel
import com.example.newproject180.Model.ItemsModel
import com.example.newproject180.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.sql.Ref

class MainViewModel:ViewModel() {

    private val _category=MutableLiveData<MutableList<CategoryModel>>()
    private val _bestSeller=MutableLiveData<MutableList<ItemsModel>>()

    private val firebaseDatabase=FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()

    val banners:LiveData<List<SliderModel>> = _banner
    val category:LiveData<MutableList<CategoryModel>> = _category
    val bestSeller:LiveData<MutableList<ItemsModel>> = _bestSeller

    fun loadBanners(){
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                    _banner.value = lists
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadCategory(){
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadBestSeller(){
        val Ref= firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }
                _bestSeller.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}