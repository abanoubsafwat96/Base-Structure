package com.example.baseproject.framework.presentation.features.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.baseproject.framework.presentation.features.main.MainViewModel

abstract class BaseDialogFragment<VB : ViewDataBinding> : DialogFragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!
    protected val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        bindViews()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
    }

    abstract fun bindViews()

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}