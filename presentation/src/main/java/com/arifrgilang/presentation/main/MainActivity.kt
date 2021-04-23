package com.arifrgilang.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.util.base.BaseBindingActivity
import com.arifrgilang.presentation.databinding.ActivityMainBinding
import com.arifrgilang.presentation.util.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    private lateinit var navHostFragment: NavHostFragment
//    private val viewModel by viewModel<MainViewModel>()
    override fun contentView(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_UnpadMobile)
        super.onCreate(savedInstanceState)
    }

    override fun setupData(savedInstanceState: Bundle?) {}

    override fun setupView() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}