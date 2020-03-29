package com.pnit.mobile.lab6.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.pnit.mobile.lab6.data.Country
import com.pnit.mobile.lab6.R
import com.pnit.mobile.lab6.api.RestApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity(),
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var networkUnavailableSnackBar: Snackbar
    private lateinit var apiService: RestApiService
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initSnackBar()
        initSwipeRefreshLayout()
        initApi()
        onRefresh()
    }

    private fun loadData() {
        val task = LoadAsyncTask(
            swipeRefreshLayout,
            apiService,
            adapter
        )
        task.execute()
    }

    private fun loadDataEasy() {
        apiService.getCountriesData().enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.d("MainActivity", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val countries = response.body()!!
                    countries.map { c ->
                        c.flag =
                            "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/${c.iso2.toLowerCase()}.png"
                    }
                    adapter.setCountries(countries)
                }
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(applicationContext, "Something went wrong!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun initRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = CountriesAdapter()
        recyclerview.adapter = adapter
    }

    private fun initApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(RestApiService::class.java)
    }

    class LoadAsyncTask(
        var swipeRefreshLayout: SwipeRefreshLayout,
        var apiService: RestApiService,
        var adapter: CountriesAdapter
    ) :
        AsyncTask<Void, Void, Response<List<Country>>?>() {

        override fun doInBackground(vararg params: Void?): Response<List<Country>>? {
            try {
                return apiService.getCountriesData().execute()
            } catch (e: Exception) {
                Log.d("MainActivity", "Something wrong!")
            }
            return null
        }

        override fun onPostExecute(response: Response<List<Country>>?) {
            try {
                Log.d("MainActivity", "onResponse: ${response!!.body()}")
                if (response.isSuccessful) {
                    val countries = response.body()!!
                    countries.map { c ->
                        c.flag =
                            "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/${c.iso2.toLowerCase()}.png"
                    }
                    adapter.setCountries(countries)
                }
            } catch (e: Exception) {
                Log.d("MainActivity", "Something wrong!")
            } finally {
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorPrimary
        )
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        if (isNetworkAvailable()) {
            networkUnavailableSnackBar.dismiss()
            recyclerview.recycledViewPool.clear()
            swipeRefreshLayout.isRefreshing = true
            loadDataEasy()
        } else {
            swipeRefreshLayout.postDelayed({
                swipeRefreshLayout.isRefreshing = false
            }, 500)
            networkUnavailableSnackBar.show()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun initSnackBar() {
        networkUnavailableSnackBar =
            Snackbar.make(
                container_main_activity,
                R.string.no_conn,
                Snackbar.LENGTH_LONG
            )
        networkUnavailableSnackBar.setAction(R.string.settings) {
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }
    }
}
