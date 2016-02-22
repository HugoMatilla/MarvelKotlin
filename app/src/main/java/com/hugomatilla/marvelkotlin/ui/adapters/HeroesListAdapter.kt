package com.hugomatilla.marvelkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hugomatilla.marvelkotlin.R
import com.hugomatilla.marvelkotlin.domain.model.HeroDomain
import com.hugomatilla.marvelkotlin.domain.model.HeroesListDomain
import com.hugomatilla.marvelkotlin.ui.utils.ctx
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

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

        private val imageView: ImageView
        private val nameView: TextView

        init {
            nameView = view.find(R.id.name)
            imageView = view.find(R.id.image)
        }

        fun bindHero(hero: HeroDomain) {
            with(hero) {
                Picasso.with(itemView.ctx).load(thumbnail).into(imageView)
                nameView.text = name
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}