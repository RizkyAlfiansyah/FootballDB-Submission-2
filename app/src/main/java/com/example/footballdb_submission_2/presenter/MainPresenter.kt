package com.example.footballdb_submission_2.presenter

import com.example.footballdb_submission_2.model.LeagueResponse
import com.example.footballdb_submission_2.network.ApiRepository
import com.example.footballdb_submission_2.network.TSDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getLeagueList(league: String?){
        view. showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TSDBApi.getLeagues(league)),
                LeagueResponse::class.java)

        uiThread {
            view.hideLoading()
            view.showLeagueList(data.leagues)
        }
        }

    }

}