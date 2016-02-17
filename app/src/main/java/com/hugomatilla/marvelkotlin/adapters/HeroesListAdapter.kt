package com.hugomatilla.marvelkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain

/**
 * Created by hugomatilla on 08/02/16.
 */

class HeroesListAdapter(val heroesList: HeroesListDomain) : RecyclerView.Adapter<HeroesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(heroesList.heroes[position]) {
            holder.textView.text = "$name - $description"
        }

    }

    override fun getItemCount(): Int = heroesList.heroes.size

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}