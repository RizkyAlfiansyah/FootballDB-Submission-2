package com.example.footballdb_submission_2.presenter

import com.example.footballdb_submission_2.model.League

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<League>)
}