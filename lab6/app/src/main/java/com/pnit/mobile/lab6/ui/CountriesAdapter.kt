package com.pnit.mobile.lab6.ui

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.pnit.mobile.lab6.data.Country
import com.pnit.mobile.lab6.R
import kotlinx.android.synthetic.main.country_card.view.*

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.ViewHolder>(), Filterable {
    private var countries = listOf<Country>()
    private var filteredCountries = listOf<Country>()
    private var filterString: String = ""

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
        filter.filter(filterString)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filteredCountries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredCountries[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private var bufferList = listOf<Country>()  // to avoid Inconsistency error
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                filterString = charString
                bufferList = if (charString.isEmpty()) {
                    countries
                } else {
                    val filteredList = arrayListOf<Country>()
                    for (row in countries) {
                        if (row.name.toLowerCase().contains(charString.toLowerCase())
                            || (row.iso2.toLowerCase().contains(charString.toLowerCase()))
                            || (row.region.toLowerCase().contains(charString.toLowerCase()))
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = bufferList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                if (p1?.values != null) {
                    bufferList = p1?.values as List<Country>
                    filteredCountries = bufferList
                    notifyDataSetChanged()
                }
            }

        }
    }

}
