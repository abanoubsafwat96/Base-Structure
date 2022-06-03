package com.example.baseproject.framework.presentation.features.photos

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseproject.R
import com.example.baseproject.databinding.FragmentPhotosBinding
import com.example.baseproject.framework.presentation.features.base.BaseFragment
import com.example.baseproject.framework.utils.DataState.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding>() {
    private val viewModel by viewModels<PhotosViewModel>()
    private val args: PhotosFragmentArgs by navArgs()

    @Inject
    lateinit var adapter: PhotosAdapter

    override fun bindViews() {
        initUI()
        subscribeOnObservers()
    }

    private fun initUI() {
        mainViewModel.updateToolbarName(args.albumName)
        binding.photosRv.layoutManager = GridLayoutManager(context, 3)
        binding.photosRv.setHasFixedSize(true)
        binding.photosRv.adapter = adapter
    }

    private fun subscribeOnObservers() {
        viewModel.photosDataState.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> adapter.addPhotos(it.data)
                is Failure -> showMessage(it.throwable.message ?: "error")
                is Loading -> {}
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_photos
}