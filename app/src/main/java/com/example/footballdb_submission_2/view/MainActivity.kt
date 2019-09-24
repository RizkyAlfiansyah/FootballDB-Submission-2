package com.example.footballdb_submission_2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.kotlinacademy.util.invisible
import com.dicoding.kotlinacademy.util.visible
import com.example.footballdb_submission_2.model.League
import com.example.footballdb_submission_2.presenter.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import com.example.footballdb_submission_2.R.color.colorAccent
import com.example.footballdb_submission_2.adapter.MainAdapter
import com.example.footballdb_submission_2.presenter.MainPresenter
import com.example.footballdb_submission_2.R.array.idleague
import com.example.footballdb_submission_2.R.array.league
import com.example.footballdb_submission_2.network.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh


class MainActivity : AppCompatActivity(), MainView {
    private var leagues: MutableList<League> = mutableListOf()

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var overview: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner ()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    overview = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = MainAdapter(leagues)
        overview.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = MainPresenter(this, request,gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getLeagueList(leagueName)
            }

        }

        swipeRefresh.onRefresh {
            presenter.getLeagueList(leagueName)
        }


    }

    override fun showLoading() {
        progressBar.visible()

    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeagueList(data: List<League>) {
        swipeRefresh.isRefreshing = false
        leagues.clear()
        leagues.addAll(data)
        adapter.notifyDataSetChanged()

    }

}
