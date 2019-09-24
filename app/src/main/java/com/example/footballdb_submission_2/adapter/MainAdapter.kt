package com.example.footballdb_submission_2.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballdb_submission_2.model.League
import org.jetbrains.anko.*
import com.example.footballdb_submission_2.R.id.league_badge
import com.example.footballdb_submission_2.R.id.league_desc
import com.squareup.picasso.Picasso


class MainAdapter(private val leagues: List<League>): RecyclerView.Adapter<LeagueViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
    return LeagueViewHolder(LeagueUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(leagues[position])
    }

}


class LeagueUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = league_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = league_desc
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

class LeagueViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val leagueBadge: ImageView = view.find(league_badge)
    private val leagueDesc: TextView = view.find(league_desc)


    fun bindItem(leagues: League){
        Picasso.get().load(leagues.leagueBadge).into(leagueBadge)
        leagueDesc.text = leagues.leagueDesc


    }
}