package com.example.baseproject.framework.presentation.features.profile

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.business.entities.Album
import com.example.baseproject.framework.presentation.callback.OnItemClickListener
import javax.inject.Inject

class AlbumsAdapter @Inject constructor() : RecyclerView.Adapter<AlbumHolder>() {
    private var albums = mutableListOf<Album>()
    private lateinit var listener: OnItemClickListener<Album>

    fun addAlbums(list: List<Album>) {
        this.albums.addAll(list)
        notifyItemRangeInserted(itemCount, itemCount + list.size)
    }

    fun setListener(listener: OnItemClickListener<Album>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumHolder.from(parent)

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        albums.elementAtOrNull(position)?.let { holder.bind(it, listener) }
    }

    override fun getItemCount() = albums.size
}