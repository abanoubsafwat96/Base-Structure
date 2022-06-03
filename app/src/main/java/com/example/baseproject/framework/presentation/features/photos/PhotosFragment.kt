package com.example.baseproject.framework.presentation.features.photos

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseproject.R
import com.example.baseproject.business.entities.Photo
import com.example.baseproject.databinding.FragmentPhotosBinding
import com.example.baseproject.framework.presentation.callback.OnItemClickListener
import com.example.baseproject.framework.presentation.features.base.BaseFragment
import com.example.baseproject.framework.utils.DataState.*
import com.example.baseproject.framework.utils.debounceFlow
import com.example.baseproject.framework.utils.navigateSafe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
class PhotosFragment : BaseFragment<FragmentPhotosBinding>() {
    private val viewModel by viewModels<PhotosViewModel>()
    private val args: PhotosFragmentArgs by navArgs()

    @Inject
    lateinit var adapter: PhotosAdapter

    private val clickListener = object : OnItemClickListener<Photo> {
        override fun onItemClicked(item: Photo) {
            findNavController().navigateSafe(PhotosFragmentDirections.toFullImageDialog(item.url))
        }
    }

    override fun bindViews() {
        initUI()
        subscribeOnObservers()
    }

    private fun initUI() {
        mainViewModel.updateToolbarName(args.albumName)
        binding.viewModel = viewModel
        binding.photosRv.layoutManager = GridLayoutManager(context, 3)
        binding.photosRv.setHasFixedSize(true)

        adapter.setListener(clickListener)
        binding.photosRv.adapter = adapter
    }

    private fun subscribeOnObservers() {
        viewModel.photosDataState.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> adapter.setPhotos(it.data)
                is Failure -> showMessage(it.throwable.message ?: getString(R.string.error_occurred))
                is Loading -> {}
            }
        }
        viewModel.searchTxtLiveData.debounceFlow(lifecycleScope) { query ->
            viewModel.search(query)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_photos
}