package com.example.baseproject.framework.presentation.features.photos

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.business.entities.Photo
import com.example.baseproject.framework.presentation.callback.OnItemClickListener
import javax.inject.Inject

class PhotosAdapter @Inject constructor() : RecyclerView.Adapter<PhotoHolder>() {
    private var photos = mutableListOf<Photo>()
    private lateinit var listener: OnItemClickListener<Photo>

    fun addPhotos(list: List<Photo>) {
        this.photos.addAll(list)
        notifyItemRangeInserted(itemCount, itemCount + list.size)
    }

    fun setListener(listener: OnItemClickListener<Photo>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoHolder.from(parent)

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        photos.elementAtOrNull(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = photos.size
}