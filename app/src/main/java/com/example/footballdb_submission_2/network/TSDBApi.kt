package com.example.footballdb_submission_2.network

import android.net.Uri
import com.example.footballdb_submission_2.BuildConfig

object TSDBApi {
    fun getLeagues(league: Int?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupleague.php")
            .appendQueryParameter("id", league.toString())
            .build()
            .toString()
    }
}