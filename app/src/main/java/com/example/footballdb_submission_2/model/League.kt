package com.example.footballdb_submission_2.model

import com.google.gson.annotations.SerializedName

data class League(

    @SerializedName("id")
    var leagueId: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("strBadge")
    var leagueBadge: String? = null,

    @SerializedName("strDescriptionEN")
    var leagueDesc: String? = null
)