package com.example.baseproject.framework.presentation.features.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseproject.R
import com.example.baseproject.business.entities.Album
import com.example.baseproject.databinding.FragmentProfileBinding
import com.example.baseproject.framework.presentation.callback.OnItemClickListener
import com.example.baseproject.framework.presentation.features.base.BaseFragment
import com.example.baseproject.framework.utils.DataState.*
import com.example.baseproject.framework.utils.Extensions.getOneRandom
import com.example.baseproject.framework.utils.navigateSafe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel by viewModels<ProfileViewModel>()

    @Inject
    lateinit var adapter: AlbumsAdapter

    private val clickListener = object : OnItemClickListener<Album> {
        override fun onItemClicked(item: Album) {
            findNavController().navigateSafe(
                ProfileFragmentDirections.toPhotosFragment(
                    item.id,
                    item.title
                )
            )
        }
    }

    override fun bindViews() {
        initUI()
        subscribeOnObservers()
    }

    private fun initUI() {
        mainViewModel.updateToolbarName(getString(R.string.profile))
        adapter.setListener(clickListener)
        binding.albumsRv.setHasFixedSize(true)
        binding.albumsRv.adapter = adapter
    }

    private fun subscribeOnObservers() {
        viewModel.profilesDataState.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    val profile = it.data.getOneRandom()
                    binding.profile = profile
                    viewModel.getUserAlbums(profile.id)
                }
                is Failure -> showMessage(it.throwable.message ?: "error")
                is Loading -> {}
            }
        }
        viewModel.albumsDataState.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> adapter.addAlbums(it.data)
                is Failure -> showMessage(it.throwable.message ?: "error")
                is Loading -> {}
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_profile
}