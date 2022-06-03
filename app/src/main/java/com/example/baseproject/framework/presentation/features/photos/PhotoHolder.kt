package com.example.baseproject.framework.presentation.features.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.business.entities.Photo
import com.example.baseproject.databinding.ItemPhotoBinding

class PhotoHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        binding.photo = photo
    }

    companion object {
        fun from(parent: ViewGroup): PhotoHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPhotoBinding.inflate(layoutInflater, parent, false)
            return PhotoHolder(binding)
        }
    }
}