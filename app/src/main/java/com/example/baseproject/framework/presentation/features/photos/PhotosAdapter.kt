package com.example.baseproject.framework.presentation.features.photos

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.business.entities.Photo
import com.example.baseproject.framework.presentation.callback.OnItemClickListener
import javax.inject.Inject

class PhotosAdapter @Inject constructor() : RecyclerView.Adapter<PhotoHolder>() {
    private var photos = mutableListOf<Photo>()
    private lateinit var listener: OnItemClickListener<Photo>

    fun setPhotos(list: List<Photo>) {
        this.photos = list.toMutableList()
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickListener<Photo>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoHolder.from(parent)

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        photos.elementAtOrNull(position)?.let { holder.bind(it,listener) }
    }

    override fun getItemCount() = photos.size
}