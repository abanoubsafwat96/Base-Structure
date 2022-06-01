package com.example.baseproject.framework.presentation.features.photos

import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentPhotosBinding
import com.example.baseproject.framework.presentation.features.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding>() {
    override fun bindViews() {

    }

    override fun getLayoutResId() = R.layout.fragment_photos
}