package com.arifrgilang.presentation.main.fragment.dashboard

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.databinding.FragmentDashboardBinding
import com.arifrgilang.presentation.util.Constant.TITLE_JALATISTA
import com.arifrgilang.presentation.util.Constant.TITLE_ODONG
import com.arifrgilang.presentation.util.Constant.TITLE_PACAR
import com.arifrgilang.presentation.util.Constant.TITLE_REGULER
import com.arifrgilang.presentation.util.Constant.TITLE_STUDENTS
import com.arifrgilang.presentation.util.Constant.TITLE_UNPAD
import com.arifrgilang.presentation.util.Constant.URL_JALATISTA
import com.arifrgilang.presentation.util.Constant.URL_ODONG
import com.arifrgilang.presentation.util.Constant.URL_PACAR
import com.arifrgilang.presentation.util.Constant.URL_REGULER
import com.arifrgilang.presentation.util.Constant.URL_STUDENTS
import com.arifrgilang.presentation.util.Constant.URL_UNPAD
import com.arifrgilang.presentation.util.Constant.URL_USER_PACAR_
import com.arifrgilang.presentation.util.base.BaseBindingFragment
import com.arifrgilang.presentation.webview.WebViewActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DashboardFragment : BaseBindingFragment<FragmentDashboardBinding>() {
    lateinit var behavior: BottomSheetBehavior<*>
    override fun contentView(): Int = R.layout.fragment_dashboard

    override fun setupData(savedInstanceState: Bundle?) {}

    override fun setupView() {
        setupBottomSheet()
        binding.ivWebUnpad.setOnClickListener {
            WebViewActivity.startActivity(requireContext(), TITLE_UNPAD, URL_UNPAD)
        }
        binding.ivPacarUnpad.setOnClickListener {
            navigateToInstagram()
        }
        binding.ivStudentsUnpad.setOnClickListener {
            WebViewActivity.startActivity(requireContext(), TITLE_STUDENTS, URL_STUDENTS)
        }
        binding.ivRegulerUnpad.setOnClickListener {
            WebViewActivity.startActivity(requireContext(), TITLE_REGULER, URL_REGULER)
        }
        binding.rlOdong.setOnClickListener {
            WebViewActivity.startActivity(requireContext(), TITLE_ODONG, URL_ODONG)
        }
        binding.rlJalatista.setOnClickListener {
            WebViewActivity.startActivity(requireContext(), TITLE_JALATISTA, URL_JALATISTA)
        }
    }

    private fun setupBottomSheet() {
        behavior = BottomSheetBehavior.from(binding.llBottomSheet)
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        behavior.peekHeight = height / 2
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun navigateToInstagram() {
        val uri = Uri.parse(URL_USER_PACAR_)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.instagram.android")

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(URL_PACAR))
            )
        }
    }
}