package com.arifrgilang.presentation.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.base.BaseBindingActivity
import com.arifrgilang.presentation.databinding.ActivityDashboardBinding

class DashboardActivity : BaseBindingActivity<ActivityDashboardBinding>() {
    override fun contentView(): Int = R.layout.activity_dashboard

    override fun setupData(savedInstanceState: Bundle?) {}

    override fun setupView() {}
}