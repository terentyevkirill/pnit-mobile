package com.pnit.mobile.lab5task1

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_card.view.*

class GridAdapter(
    private val context: Context,
    private val circles: List<Circle>,
    private val listener: ItemInteractionListener
) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    interface ItemInteractionListener {
        fun onItemClicked(circle: Circle)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(circle: Circle, listener: ItemInteractionListener, context: Context) {
            itemView.tv_number.text = circle.number.toString()
//            val drawable = context.resources.getDrawable(R.drawable.circle)
//            drawable.colorFilter = PorterDuffColorFilter(circle.color, PorterDuff.Mode.MULTIPLY)
            itemView.item_container.background.colorFilter = PorterDuffColorFilter(circle.color, PorterDuff.Mode.SRC_IN)
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

    override fun getItemCount(): Int {
        return circles.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(circles[position], listener, context)
    }
}