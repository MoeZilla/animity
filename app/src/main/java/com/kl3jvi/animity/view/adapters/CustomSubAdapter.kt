package com.kl3jvi.animity.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kl3jvi.animity.databinding.ItemCardAnimeBinding
import com.kl3jvi.animity.model.entities.AnimeMetaModel

class CustomSubAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<CustomSubAdapter.ViewHolder>() {

    private var list = ArrayList<AnimeMetaModel>()

    inner class ViewHolder(view: ItemCardAnimeBinding) : RecyclerView.ViewHolder(view.root) {
        val title = view.animeTitle
        val image = view.animeImage
        val episodeNumber = view.episodeNumber

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCardAnimeBinding =
            ItemCardAnimeBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = list[position]
        Glide.with(fragment)
            .load(element.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)

        holder.title.text = element.title
        holder.episodeNumber.text = element.episodeNumber
    }

    override fun getItemCount() = list.size


    fun getAnimes(entry: ArrayList<AnimeMetaModel>) {
        list = entry
        notifyDataSetChanged()
    }
}