package com.arifrgilang.presentation.main.fragment.dashboard

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.databinding.FragmentDashboardBinding
import com.arifrgilang.presentation.util.base.BaseBindingFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DashboardFragment : BaseBindingFragment<FragmentDashboardBinding>() {
    lateinit var behavior: BottomSheetBehavior<*>
    override fun contentView(): Int = R.layout.fragment_dashboard

    override fun setupData(savedInstanceState: Bundle?) {}

    override fun setupView() {
        setupBottomSheet()
    }

    private fun setupBottomSheet() {
        behavior = BottomSheetBehavior.from(binding.llBottomSheet)
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        behavior.peekHeight = height / 2
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

}