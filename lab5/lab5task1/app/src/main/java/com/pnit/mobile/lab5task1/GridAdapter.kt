package com.pnit.mobile.lab5task1

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_card.view.*

class GridAdapter(
    private val circles: List<Circle>,
    private val listener: ItemInteractionListener
) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    interface ItemInteractionListener {
        fun onItemClicked(circle: Circle)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            circle: Circle,
            listener: ItemInteractionListener
        ) {
            itemView.tv_number.text = circle.number.toString()
            itemView.item_container.background.colorFilter =
                PorterDuffColorFilter(circle.color, PorterDuff.Mode.SRC_IN)
            itemView.setOnClickListener {
                listener.onItemClicked(circle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        )
    }

    override fun getItemCount() = circles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(circles[position], listener)
    }
}