package com.pnit.mobile.lab6

import android.R.attr.src
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.country_card.view.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class CountriesAdapter(var countries: List<Country>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: Country) {
            itemView.tv_name.text = country.name
            itemView.tv_capital_name.text = country.capital
            itemView.tv_region_name.text = country.region
            itemView.tv_population_num.text = country.population.toString()
            itemView.tv_area_num.text = country.area.toString()
            ImageLoadAsyncTask(country.flag, itemView).execute()
        }

        class ImageLoadAsyncTask(var url: String, var view: View) : AsyncTask<Void, Void, Bitmap>() {
            override fun doInBackground(vararg params: Void?): Bitmap? {
                var myBitmap: Bitmap? = null
                for (i in 0..0) {
                    val url = URL(url)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    connection.doInput = true
                    connection.connect()
                    val input: InputStream = connection.getInputStream()
                    myBitmap = BitmapFactory.decodeStream(input)
                }
                return myBitmap
            }

            override fun onPostExecute(result: Bitmap?) {
                super.onPostExecute(result)
                Glide
                    .with(view.context)
                    .load(result)
                    .transform(CenterCrop(), RoundedCorners(11))
                    .into(view.iv_flag)
            }
        }
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
