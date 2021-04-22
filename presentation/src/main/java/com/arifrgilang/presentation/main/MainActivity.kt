package com.arifrgilang.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.arifrgilang.presentation.BuildConfig
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.util.base.BaseBindingActivity
import com.arifrgilang.presentation.dashboard.DashboardActivity
import com.arifrgilang.presentation.databinding.ActivityMainBinding
import com.arifrgilang.presentation.util.Constant
import com.arifrgilang.presentation.util.observeEvent
import com.arifrgilang.presentation.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.UUID

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    private lateinit var navHostFragment: NavHostFragment
    private val viewModel by viewModel<MainViewModel>()
    override fun contentView(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_UnpadMobile)
        super.onCreate(savedInstanceState)
    }

    override fun setupData(savedInstanceState: Bundle?) {
//        setViewModelObservers()
//        viewModel.checkUserToken()
    }

    private fun setViewModelObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
        viewModel.pausToken.observe(this) { pausToken ->
            viewModel.startSession(pausToken.accessToken)
        }
        viewModel.isError.observeEvent(this) {
            showError()
        }
        viewModel.isLoggedIn.observeEvent(this) { isLoggedIn ->
            if (isLoggedIn) navigateToDashboard()
        }
    }

    private fun navigateToDashboard() {
        startActivity(
            Intent(this@MainActivity, DashboardActivity::class.java)
                .apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
        )
    }

    private fun showError() {
        // To do later
    }

    private fun showLoading(isLoading: Boolean) {
//        binding.btnLogin.isVisible = !isLoading
//        binding.pbLogin.isVisible = isLoading
    }

    override fun setupView() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

//        binding.btnLogin.setOnClickListener {
//            val uniqueState = UUID.randomUUID().toString()
//            val intent = Intent(
//                Intent.ACTION_VIEW,
//                Constant.pausOAuthUri(uniqueState)
//            )
//            startActivity(intent)
//        }
    }

//    override fun onResume() {
//        super.onResume()
//        intent?.data?.let{
//            if(it.toString().startsWith(BuildConfig.REDIRECT_URI)) {
//                if(it.getQueryParameter("code") != null){
//                    viewModel.getAccessToken(it.getQueryParameter("code").toString())
//                    Timber.d(it.getQueryParameter("code").toString())
//                } else if (it.getQueryParameter("error") != null){
//                    toast(it.getQueryParameter("error").toString())
//                    Timber.e(it.getQueryParameter("error").toString())
//                }
//            }
//        }
//    }
}