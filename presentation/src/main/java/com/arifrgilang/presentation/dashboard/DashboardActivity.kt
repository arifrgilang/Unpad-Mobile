package com.arifrgilang.presentation.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.util.base.BaseBindingActivity
import com.arifrgilang.presentation.databinding.ActivityDashboardBinding
import com.arifrgilang.presentation.util.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : BaseBindingActivity<ActivityDashboardBinding>() {
    private val viewModel by viewModel<DashboardViewModel>()
    override fun contentView(): Int = R.layout.activity_dashboard

    override fun setupData(savedInstanceState: Bundle?) {
        setViewModelObservers()
        viewModel.getStudentData()
    }

    override fun setupView() {}

    private fun setViewModelObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
        viewModel.studentData.observe(this) { studentData ->
            binding.student = studentData.data
        }
        viewModel.isError.observeEvent(this) {
            showError()
        }
    }

    private fun showError() {
        // To do later
    }

    private fun showLoading(isLoading: Boolean) {
        binding.llDashboardData.isVisible = !isLoading
        binding.ivDashboardStudent.isVisible = !isLoading
        binding.pbDashboard.isVisible = isLoading
    }
}