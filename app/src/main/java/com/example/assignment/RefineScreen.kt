package com.example.assignment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider

class RefineScreen : AppCompatActivity() {


    private var selectedCategory: String? = null
    private var maxPrice: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_refine_screen)

        val image = findViewById<ImageView>(R.id.imageView3)
        image.setOnClickListener {
            onBackPressed()
        }

        val buttons = listOf(
            findViewById<Button>(R.id.button) to "Men's Clothing",
            findViewById<Button>(R.id.button6) to "Women's Clothing",
            findViewById<Button>(R.id.button7) to "Women's Fashion",
            findViewById<Button>(R.id.button8) to "Children's Clothing"
        )

        buttons.forEach { (button, category) ->
            button.setBackgroundColor(Color.WHITE)
            button.setOnClickListener {
                buttons.forEach { it.first.setBackgroundColor(Color.WHITE) }
                button.setBackgroundColor(Color.CYAN)
                selectedCategory = category

            }
        }

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                maxPrice = progress

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(this@RefineScreen, "Max price: $$maxPrice", Toast.LENGTH_SHORT).show()
            }
        })

        val doneBtn = findViewById<Button>(R.id.button12)
        doneBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("category",selectedCategory);
            intent.putExtra("price",maxPrice.toString())
            startActivity(intent)
        }
    }
}
