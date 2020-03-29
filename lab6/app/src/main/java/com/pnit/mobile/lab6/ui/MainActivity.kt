package com.pnit.mobile.lab6.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.pnit.mobile.lab6.R
import com.pnit.mobile.lab6.api.RestCountriesApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var networkUnavailableSnackBar: Snackbar
    private lateinit var searchView: SearchView

    private lateinit var apiService: RestCountriesApiService
    private lateinit var adapter: CountriesAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MainViewModel::class.java)
        initRecyclerView()
        initSnackBar()
        initSwipeRefreshLayout()
        initApi()
        onRefresh()
    }

    private fun loadData() {
        viewModel.countries.observe(this, Observer {
            adapter.setCountries(it)
            swipeRefreshLayout.isRefreshing = false
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
        apiService = retrofit.create(RestCountriesApiService::class.java)
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
            loadData()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu!!.findItem(R.id.search_item).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                searchView.clearFocus();
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_item -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
        } else {
            super.onBackPressed()
        }
    }
}
