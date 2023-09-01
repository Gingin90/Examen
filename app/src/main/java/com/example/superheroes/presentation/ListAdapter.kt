package com.example.superheroes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroes.data.local.SuperHeroEntity
import com.example.superheroescomics.R
import com.example.superheroescomics.databinding.ItemBinding


class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    lateinit var itemBinding: ItemBinding
    private val listOfSuperHeroes = mutableListOf<SuperHeroEntity>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context))
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val superHero = listOfSuperHeroes[position]
        holder.bind(superHero)
    }

    override fun getItemCount(): Int {
        return listOfSuperHeroes.size
    }

    fun setData(hero: List<SuperHeroEntity>) {
        this.listOfSuperHeroes.clear()
        this.listOfSuperHeroes.addAll(hero)
        notifyDataSetChanged()
    }

    class ListViewHolder(private val itemBinding: ItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(superHero: SuperHeroEntity) {
            itemBinding.imageViewItem.load(superHero.imageUrl)
            itemBinding.tvNameItem.text = superHero.name
            itemBinding.cvItemList.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", superHero.id.toString())
                Navigation.findNavController(itemBinding.root).navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }
    }}
