package com.pnit.mobile.lab5task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
class MainActivity : AppCompatActivity(), GridAdapter.ItemInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val colors = resources.getIntArray(R.array.circleColors)
        val circles = Array(24) {
            Circle(Random.nextInt(1, 99), colors[Random.nextInt(colors.size)])
        }.toList()
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.adapter = GridAdapter(this, circles, this)
    }

    override fun onItemClicked(circle: Circle) {
        Toast.makeText(this, "${circle.number} clicked!", Toast.LENGTH_SHORT).show()
    }

}
