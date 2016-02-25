package com.hugomatilla.marvelkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain
import com.hugomatilla.marvelkotlin.ui.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.heroes_list_item.view.*

/**
 * Created by hugomatilla on 08/02/16.
 */

class HeroesListAdapter(val heroesList: HeroesListDomain, val itemClick: (HeroDomain) -> Unit) : RecyclerView.Adapter<HeroesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.heroes_list_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHero(heroesList[position])
    }

    override fun getItemCount() = heroesList.size()

    class ViewHolder(view: View, val itemClick: (HeroDomain) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindHero(hero: HeroDomain) {
            with(hero) {
                Picasso.with(itemView.ctx).load(imageUrl).into(itemView.heroListImageView)
                itemView.heroListNameView.text = name
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}