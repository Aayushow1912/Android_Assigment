package com.example.assignment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var searchView:SearchView
    private  var mList = ArrayList<Item>()
    private lateinit var adapter: PeopleAdapter
    private  var filteredList = ArrayList<Item>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.imageView2)
        image.setOnClickListener {
            val intent = Intent(this, RefineScreen::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        val filterCategory:String = intent.getStringExtra("category").toString()
        val maxPrice:String = intent.getStringExtra("price").toString()


        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mList.clear()

        addDataToList()

        if (!filterCategory.isEmpty()||!maxPrice.isEmpty()) {
            filteredList = ArrayList(mList.filter { item ->
                item.name.toLowerCase().trim() == filterCategory.toLowerCase().trim() &&  (item.price.removePrefix("$").toDoubleOrNull() ?: 0.0) <= (maxPrice.removePrefix("$").toDoubleOrNull()?:0.0)
            })
        }

        if(filteredList.size!=0)
        adapter = PeopleAdapter(filteredList)
        else
            adapter=PeopleAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filerList(newText)
                return true
            }

        })
    }

    private fun filerList(query: String?){
        if(query != null){
            val filteredList = ArrayList<Item>()
            for(i in mList){
                if(i.category.toLowerCase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            else{
              adapter.setFiltereedList(filteredList)
            }
        }
    }
    private fun addDataToList() {
        mList.add(Item("Mens T-Shirts Adidas",R.drawable.men,"men's clothing","$45.00"))
        mList.add(Item("Mens wool Jacket Jockey",R.drawable.men_jacket,"men's clothing","$30.34"))
        mList.add(Item("Mens Casual Slim Fit H&M",R.drawable.men_casual_slim,"men's clothing","$50.23"))
        mList.add(Item("Women's T-shirts Prada",R.drawable.women_prada,"women's clothing","$60.77"))
        mList.add(Item("Women's Casual Wear LV",R.drawable.casual_wear_lv,"women's clothing","$100"))
        mList.add(Item("Women's Jeans Gucci",R.drawable.women_jeans_gucci,"women's clothing","$99.76"))
        mList.add(Item("Women's Purse ",R.drawable.women_purse,"women's Fashion","$40.34"))
        mList.add(Item("Women's EyeLasher",R.drawable.women_eyelasher,"women's Fashion","$48.89"))
        mList.add(Item("Women's Leggings",R.drawable.women_leggings,"women's clothing","$23.00"))
        mList.add(Item("Women's Sports Wear",R.drawable.women_sportswear,"women's clothing","$50.00"))
        mList.add(Item("Women's Lipstick",R.drawable.lipstick,"women's Fashion","$25.02"))
        mList.add(Item("Women's Hair Straightner",R.drawable.hair_straigthner,"women's Fashion","$70.80"))
        mList.add(Item("Mens Formal Wear",R.drawable.men_formalwear,"men's clothing","$89.80"))
        mList.add(Item("Mens Furry Coat",R.drawable.men_furrycoat,"men's clothing","$100.00"))
        mList.add(Item("Mens SweatShirts",R.drawable.men_sweatshirts,"men's clothing","$42.64"))
        mList.add(Item("Mens Premium Perfumes",R.drawable.men_perfume,"men's clothing","$79.98"))
        mList.add(Item("Mens Sports Wear",R.drawable.men_sportswear,"men's clothing","$30.45"))
        mList.add(Item("Mens Casual Pajamas",R.drawable.men_pajamas,"men's clothing","$15.67"))
        mList.add(Item("Children Underwear",R.drawable.children_underwear,"children's clothing","$10.65"))
        mList.add(Item("Children Casual T-shirts",R.drawable.children_tshirts,"children's clothing","$7.89"))
        mList.add(Item("Children Formal Wear",R.drawable.children_formalwear,"children's clothing","$50.98"))
        mList.add(Item("Children Sports Wear",R.drawable.children_sportswear,"children's clothing","$23.44"))
        mList.add(Item("Children School Uniform",R.drawable.children_uniform,"children's clothing","$28.45"))
    }
}