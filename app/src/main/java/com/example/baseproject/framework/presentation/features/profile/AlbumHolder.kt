package com.example.baseproject.framework.presentation.features.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.business.entities.Album
import com.example.baseproject.databinding.ItemAlbumBinding
import com.example.baseproject.framework.presentation.callback.OnItemClickListener

class AlbumHolder(private val binding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album, listener: OnItemClickListener<Album>) {
        binding.album = album
        binding.listener = listener
    }

    companion object {
        fun from(parent: ViewGroup): AlbumHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemAlbumBinding.inflate(layoutInflater, parent, false)
            return AlbumHolder(binding)
        }
    }
}