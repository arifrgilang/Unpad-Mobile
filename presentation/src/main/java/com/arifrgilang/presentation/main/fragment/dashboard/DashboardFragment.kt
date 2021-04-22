package com.arifrgilang.presentation.main.fragment.dashboard

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.core.view.isVisible
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.databinding.FragmentDashboardBinding
import com.arifrgilang.presentation.main.fragment.dashboard.model.StudentUiModel
import com.arifrgilang.presentation.main.model.PausTokenUiModel
import com.arifrgilang.presentation.pauslogin.PausLoginActivity
import com.arifrgilang.presentation.util.Constant.LOGIN_PAUS_REQUEST
import com.arifrgilang.presentation.util.Constant.LOGIN_PAUS_RESULT
import com.arifrgilang.presentation.util.Constant.TITLE_JALATISTA
import com.arifrgilang.presentation.util.Constant.TITLE_ODONG
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
import com.arifrgilang.presentation.util.observeEvent
import com.arifrgilang.presentation.webview.WebViewActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DashboardFragment : BaseBindingFragment<FragmentDashboardBinding>() {
    private lateinit var behavior: BottomSheetBehavior<*>
    private val viewModel by viewModel<DashboardViewModel>()
    override fun contentView(): Int = R.layout.fragment_dashboard

    override fun setupData(savedInstanceState: Bundle?) {
        setViewModelObservers()
        viewModel.checkUserToken()
    }

    private fun setViewModelObservers() {
        viewModel.isLoading.observe(this, ::showLoading)
        viewModel.isError.observeEvent(this, ::showError)

        viewModel.isLoggedIn.observeEvent(this, ::handleIsLogin)
        viewModel.pausToken.observe(this, ::startSession)
        viewModel.studentData.observe(this, ::bindStudent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.ivProfile.isVisible = !isLoading
        binding.pbProfile.isVisible = isLoading
    }

    private fun showError(unit: Unit) { /* later*/ }

    private fun handleIsLogin(isLoggedIn: Boolean) {
        binding.ivProfile.isVisible = isLoggedIn
        binding.llSearchMahasiswa.isVisible = isLoggedIn

        binding.ivProfilePlaceholder.isVisible = !isLoggedIn
        binding.llSearchMahasiswaPlaceholder.isVisible = !isLoggedIn
        if (isLoggedIn) {
            viewModel.getStudentData()
        }
    }

    private fun startSession(model: PausTokenUiModel?) {
        viewModel.startSession(model!!.accessToken)
    }

    private fun bindStudent(student: StudentUiModel) {
        binding.student = student.data
    }

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
        binding.ivProfilePlaceholder.setOnClickListener {
            startActivityForResult(
                Intent(activity, PausLoginActivity::class.java),
                LOGIN_PAUS_REQUEST
            )
        }
        binding.ivProfile.setOnClickListener {

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == LOGIN_PAUS_REQUEST) {
                data?.let{
                    val code = it.getStringExtra(LOGIN_PAUS_RESULT)
                    Timber.d(code)
                    viewModel.getAccessToken(code)
                }
            }
        }
    }

}