package com.arifrgilang.presentation.dashboard

import android.os.Bundle
import androidx.core.view.isVisible
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.dashboard.model.StudentUiModel
import com.arifrgilang.presentation.util.base.BaseBindingActivity
import com.arifrgilang.presentation.databinding.ActivityDashboardBinding
import com.arifrgilang.presentation.util.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : BaseBindingActivity<ActivityDashboardBinding>() {
    private val viewModel by viewModel<DashboardViewModel>()
    override fun contentView(): Int = R.layout.activity_dashboard
    override fun setupView() {}

    override fun setupData(savedInstanceState: Bundle?) {
        setViewModelObservers()
        viewModel.getStudentData()
    }

    private fun setViewModelObservers() {
        viewModel.isLoading.observe(this, ::showLoading)
        viewModel.studentData.observe(this, ::bindStudent)
        viewModel.isError.observeEvent(this, ::showError)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.svDashboardData.isVisible = !isLoading
        binding.pbDashboard.isVisible = isLoading
    }

    private fun bindStudent(student: StudentUiModel) {
        binding.student = student.data
    }

    private fun showError(unit: Unit) { /* later*/ }
}