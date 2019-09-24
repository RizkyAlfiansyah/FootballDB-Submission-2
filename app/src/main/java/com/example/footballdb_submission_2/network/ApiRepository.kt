package com.example.footballdb_submission_2.network

import java.net.URL

class ApiRepository{
    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}