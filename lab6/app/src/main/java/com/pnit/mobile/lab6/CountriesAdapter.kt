package com.pnit.mobile.lab6

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.country_card.view.*

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    private var countries = listOf<Country>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: Country) {
            itemView.tv_name.text = country.name
            itemView.tv_capital_name.text = country.capital
            itemView.tv_region_name.text = country.region
            itemView.tv_population_num.text = country.population.toString()
            itemView.tv_area_num.text = country.area.toString()
            Glide
                .with(itemView.context)
                .load(country.flag)
                .transform(FitCenter(), RoundedCorners(11))
                .into(itemView.iv_flag)
        }
    }

    fun setCountries(countries: List<Country>) {
        d("CountriesAdapter", "countries:$countries")
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}
