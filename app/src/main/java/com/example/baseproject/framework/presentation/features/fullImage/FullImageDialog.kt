package com.example.baseproject.framework.presentation.features.fullImage

import android.content.Intent
import androidx.navigation.fragment.navArgs
import com.example.baseproject.R
import com.example.baseproject.databinding.DialogFullImageBinding
import com.example.baseproject.framework.presentation.features.base.BaseDialogFragment
import com.example.baseproject.framework.utils.isIntentCanBeHandled
import com.example.baseproject.framework.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FullImageDialog @Inject constructor() : BaseDialogFragment<DialogFullImageBinding>() {

    private val args: FullImageDialogArgs by navArgs()

    override fun bindViews() {
        initUI()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.shareBtn.setOnClickListener {
            val intent = getShareIntent()
            if (context?.isIntentCanBeHandled(intent) == true)
                activity?.startActivity(intent)
            else
                activity?.showSnackBar(getString(R.string.you_do_not_have_application_to_handle_sharing_function))
        }
    }

    private fun getShareIntent(): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_App))
        intent.putExtra(Intent.EXTRA_TEXT, args.imageUrl)
        Intent.createChooser(intent, getString(R.string.share_App))
        return intent
    }

    private fun initUI() {
        binding.imageUrl = args.imageUrl
    }

    override fun getLayoutResId(): Int = R.layout.dialog_full_image
}